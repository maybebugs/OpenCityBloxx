import com.sun.source.tree.*;
import com.sun.source.util.*;
import javax.lang.model.element.*;
import javax.lang.model.type.*;
import javax.tools.*;
import java.nio.file.*;
import java.util.*;

public class Renamer {
    static Map<String,String> table = new HashMap<>();
    static boolean listMode;
    static String sigOf(ExecutableElement e) {
        StringBuilder sb = new StringBuilder();
        for (VariableElement p : e.getParameters()) {
            if (sb.length() > 0) sb.append(",");
            sb.append(simple(p.asType()));
        }
        return sb.toString();
    }
    static String simple(TypeMirror t) {
        String s = t.toString();
        return s.replaceAll("[a-zA-Z_][\\w]*\\.", "");
    }
    static String key(Element el) {
        if (el == null) return null;
        Element o = el.getEnclosingElement();
        if (!(o instanceof TypeElement)) return null;
        String owner = o.getSimpleName().toString();
        if (el instanceof VariableElement && (el.getKind() == ElementKind.FIELD)) return owner + "." + el.getSimpleName();
        if (el instanceof ExecutableElement && el.getKind() == ElementKind.METHOD) return owner + "." + el.getSimpleName() + "(" + sigOf((ExecutableElement) el) + ")";
        return null;
    }
    public static void main(String[] args) throws Exception {
        String mode = args[0];           // list | apply
        String cp = args[1];
        String tableFile = args[2];
        String outDir = args[3];
        listMode = mode.equals("list");
        if (!listMode) for (String l : Files.readAllLines(Paths.get(tableFile))) {
            l = l.trim(); if (l.isEmpty() || l.startsWith("#")) continue;
            int i = l.indexOf(" = "); table.put(l.substring(0, i).trim(), l.substring(i + 3).trim());
        }
        List<java.io.File> files = new ArrayList<>();
        for (int i = 4; i < args.length; i++) files.add(new java.io.File(args[i]));
        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fm = jc.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> units = fm.getJavaFileObjectsFromFiles(files);
        JavacTask task = (JavacTask) jc.getTask(null, fm, d -> {}, Arrays.asList("-proc:none", "-cp", cp, "-XDshould-stop.at=FLOW", "-Xmaxerrs", "100000"), null, units);
        Iterable<? extends CompilationUnitTree> cus = task.parse();
        task.analyze();
        Trees trees = Trees.instance(task);
        SourcePositions sp = trees.getSourcePositions();
        Set<String> listed = new TreeSet<>();
        Map<String,Integer> unresolved = new TreeMap<>();
        for (CompilationUnitTree cu : cus) {
            String src = cu.getSourceFile().getCharContent(true).toString();
            List<int[]> edits = new ArrayList<>();
            List<String> names = new ArrayList<>();
            new TreePathScanner<Void,Void>() {
                void edit(Element el, int pos, int len) {
                    String k = key(el);
                    if (k == null) return;
                    if (listMode) { listed.add((el instanceof VariableElement ? "F " : "M ") + k + (el.getModifiers().contains(Modifier.STATIC) ? " static" : "") + (el instanceof VariableElement ? " : " + simple(el.asType()) : " : " + simple(((ExecutableElement) el).getReturnType()))); return; }
                    String nn = table.get(k);
                    if (nn != null && pos >= 0) { edits.add(new int[]{pos, len}); names.add(nn); }
                }
                @Override public Void visitIdentifier(IdentifierTree t, Void v) {
                    Element el = trees.getElement(getCurrentPath());
                    if (el == null) { unresolved.merge(cu.getSourceFile().getName() + ":" + t.getName(), 1, Integer::sum); }
                    else { long s = sp.getStartPosition(cu, t); edit(el, (int) s, t.getName().length()); }
                    return super.visitIdentifier(t, v);
                }
                @Override public Void visitMemberSelect(MemberSelectTree t, Void v) {
                    Element el = trees.getElement(getCurrentPath());
                    if (el == null) { unresolved.merge(cu.getSourceFile().getName() + ":." + t.getIdentifier(), 1, Integer::sum); }
                    else { long e = sp.getEndPosition(cu, t); edit(el, (int) e - t.getIdentifier().length(), t.getIdentifier().length()); }
                    return super.visitMemberSelect(t, v);
                }
                @Override public Void visitVariable(VariableTree t, Void v) {
                    Element el = trees.getElement(getCurrentPath());
                    if (el != null && el.getKind() == ElementKind.FIELD) {
                        long te = sp.getEndPosition(cu, t.getType());
                        int p = (int) te; while (p < src.length() && Character.isWhitespace(src.charAt(p))) p++;
                        if (src.startsWith(t.getName().toString(), p)) edit(el, p, t.getName().length());
                    }
                    return super.visitVariable(t, v);
                }
                @Override public Void visitMethod(MethodTree t, Void v) {
                    Element el = trees.getElement(getCurrentPath());
                    if (el != null && el.getKind() == ElementKind.METHOD && t.getReturnType() != null) {
                        long te = sp.getEndPosition(cu, t.getReturnType());
                        int p = (int) te; while (p < src.length() && Character.isWhitespace(src.charAt(p))) p++;
                        if (src.startsWith(t.getName().toString(), p)) edit(el, p, t.getName().length());
                    }
                    return super.visitMethod(t, v);
                }
            }.scan(cu, null);
            if (!listMode) {
                Integer[] idx = new Integer[edits.size()];
                for (int i = 0; i < idx.length; i++) idx[i] = i;
                Arrays.sort(idx, (x, y) -> edits.get(y)[0] - edits.get(x)[0]);
                StringBuilder sb = new StringBuilder(src);
                Set<Integer> done = new HashSet<>();
                for (int i : idx) { int[] e = edits.get(i); if (!done.add(e[0])) continue; sb.replace(e[0], e[0] + e[1], names.get(i)); }
                Path out = Paths.get(outDir, Paths.get(cu.getSourceFile().toUri()).getFileName().toString());
                Path rel = out;
                String full = cu.getSourceFile().getName();
                int ix = full.indexOf("stage1/");
                if (ix >= 0) rel = Paths.get(outDir, full.substring(ix + 7));
                Files.createDirectories(rel.getParent());
                Files.write(rel, sb.toString().getBytes("UTF-8"));
            }
        }
        if (listMode) for (String s : listed) System.out.println(s);
        else for (Map.Entry<String,Integer> e : unresolved.entrySet()) System.err.println("UNRESOLVED " + e.getKey() + " x" + e.getValue());
    }
}
