import com.sun.source.tree.*;
import com.sun.source.util.*;
import javax.lang.model.element.*;
import javax.lang.model.type.*;
import javax.lang.model.util.*;
import javax.tools.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

/** Semantic (symbol-resolving) renamer for fields, methods and locals.
 *  Spec lines:  F Owner old new | M Owner old(SimpleType,..) new | L Owner method(SimpleType,..) old new  */
public class SemRename {
    record Spec(char kind, String owner, String sig, String oldName, String newName) {}
    static String simple(String t) { return t.replaceAll("[\\w$]+\\.", ""); }
    static String sigOf(ExecutableElement m) {
        return m.getParameters().stream().map(p -> simple(p.asType().toString())).collect(Collectors.joining(","));
    }
    public static void main(String[] a) throws Exception {
        Path src = Paths.get(a[0]);
        List<Spec> specs = new ArrayList<>();
        for (String line : Files.readAllLines(Paths.get(a[1]))) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#")) continue;
            String[] p = line.split("\\s+");
            switch (p[0]) {
                case "F" -> specs.add(new Spec('F', p[1], null, p[2], p[3]));
                case "M" -> { int k = p[2].indexOf('('); specs.add(new Spec('M', p[1], p[2].substring(k + 1, p[2].length() - 1), p[2].substring(0, k), p[3])); }
                case "L" -> { int k = p[2].indexOf('('); specs.add(new Spec('L', p[1], p[2].substring(k + 1, p[2].length() - 1), p[2].substring(0, k) + " " + p[3], p[4])); }
                default -> throw new RuntimeException("bad spec: " + line);
            }
        }
        List<File> files;
        try (Stream<Path> s = Files.walk(src)) { files = s.filter(x -> x.toString().endsWith(".java")).map(Path::toFile).collect(Collectors.toList()); }
        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fm = jc.getStandardFileManager(null, null, java.nio.charset.StandardCharsets.UTF_8);
        JavacTask task = (JavacTask) jc.getTask(null, fm, d -> {}, List.of("-proc:none", "-Xlint:none", "-encoding", "UTF-8"), null, fm.getJavaFileObjectsFromFiles(files));
        Iterable<? extends CompilationUnitTree> units = task.parse();
        task.analyze();
        Trees trees = Trees.instance(task);
        SourcePositions sp = trees.getSourcePositions();
        Types types = task.getTypes();
        Map<String, List<int[]>> editsByFile = new HashMap<>();
        Map<String, String> texts = new HashMap<>();
        Map<String, List<String>> repl = new HashMap<>();
        int[] hits = new int[specs.size()]; int[] artifactCount = new int[1];

        for (CompilationUnitTree unit : units) {
            String path = unit.getSourceFile().toUri().getPath();
            String text = unit.getSourceFile().getCharContent(true).toString();
            texts.put(path, text);
            List<int[]> edits = new ArrayList<>(); List<String> names = new ArrayList<>();
            new TreePathScanner<Void, Void>() {
                int match(Element e) {
                    if (e == null) return -1;
                    for (int i = 0; i < specs.size(); i++) {
                        Spec s = specs.get(i);
                        Element enc = e.getEnclosingElement();
                        switch (s.kind) {
                            case 'F' -> { if (e.getKind() == ElementKind.FIELD && e.getSimpleName().contentEquals(s.oldName) && enc.getSimpleName().contentEquals(s.owner)) return i; }
                            case 'M' -> {
                                if (e instanceof ExecutableElement m && e.getKind() == ElementKind.METHOD && e.getSimpleName().contentEquals(s.oldName) && sigOf(m).equals(s.sig)) {
                                    if (enc.getSimpleName().contentEquals(s.owner)) return i;
                                    TypeElement ot = null;
                                    for (Element ce : unitTypes) if (ce.getSimpleName().contentEquals(s.owner)) ot = (TypeElement) ce;
                                    if (ot != null && enc instanceof TypeElement et && (types.isSubtype(types.erasure(et.asType()), types.erasure(ot.asType())) || types.isSubtype(types.erasure(ot.asType()), types.erasure(et.asType())))) return i;
                                }
                            }
                            case 'L' -> {
                                if ((e.getKind() == ElementKind.LOCAL_VARIABLE || e.getKind() == ElementKind.PARAMETER || e.getKind() == ElementKind.EXCEPTION_PARAMETER || e.getKind() == ElementKind.RESOURCE_VARIABLE)
                                        && (e.getSimpleName().toString()).equals(s.oldName.split(" ")[1]) && enc instanceof ExecutableElement m
                                        && m.getSimpleName().contentEquals(s.oldName.split(" ")[0]) && sigOf(m).equals(s.sig) && m.getEnclosingElement().getSimpleName().contentEquals(s.owner)) return i;
                            }
                        }
                    }
                    return -1;
                }
                List<Element> unitTypes = new ArrayList<>();
                { for (Tree t : unit.getTypeDecls()) { /* filled lazily below */ } }
                void add(int i, int start, int end) { hits[i]++; edits.add(new int[]{start, end}); names.add(specs.get(i).newName); }
                @Override public Void visitCompilationUnit(CompilationUnitTree n, Void v) {
                    for (CompilationUnitTree u : units) for (Tree t : u.getTypeDecls()) { Element el = trees.getElement(TreePath.getPath(u, t)); if (el != null) unitTypes.add(el); }
                    return super.visitCompilationUnit(n, v);
                }
                Map<Element,String> artifact = new HashMap<>();
                @Override public Void visitIdentifier(IdentifierTree n, Void v) {
                    Element ae = trees.getElement(getCurrentPath());
                    if (ae != null && artifact.containsKey(ae)) { hits[0] += 0; edits.add(new int[]{(int) sp.getStartPosition(unit, n), (int) sp.getEndPosition(unit, n)}); names.add(artifact.get(ae)); artifactCount[0]++; }
                    int i = match(trees.getElement(getCurrentPath()));
                    if (i >= 0) add(i, (int) sp.getStartPosition(unit, n), (int) sp.getEndPosition(unit, n));
                    return super.visitIdentifier(n, v);
                }
                @Override public Void visitMemberSelect(MemberSelectTree n, Void v) {
                    int i = match(trees.getElement(getCurrentPath()));
                    if (i >= 0) { int end = (int) sp.getEndPosition(unit, n); add(i, end - n.getIdentifier().length(), end); }
                    return super.visitMemberSelect(n, v);
                }
                @Override public Void visitVariable(VariableTree n, Void v) {
                    Element e = trees.getElement(getCurrentPath());
                    if (e != null && e.getKind() == ElementKind.LOCAL_VARIABLE && n.getName().toString().matches("i2{2,}")) {
                        boolean enhanced = getCurrentPath().getParentPath().getLeaf() instanceof EnhancedForLoopTree;
                        String nn = enhanced ? "value" : "row";
                        artifact.put(e, nn);
                        int from = n.getType() != null ? (int) sp.getEndPosition(unit, n.getType()) : (int) sp.getStartPosition(unit, n);
                        Matcher mm = Pattern.compile("(?<![\\w$])" + Pattern.quote(n.getName().toString()) + "(?![\\w$])").matcher(text);
                        if (mm.find(from)) { edits.add(new int[]{mm.start(), mm.end()}); names.add(nn); artifactCount[0]++; }
                    }
                    int i = match(e);
                    if (i >= 0) {
                        int from = n.getType() != null && sp.getEndPosition(unit, n.getType()) >= 0 ? (int) sp.getEndPosition(unit, n.getType()) : (int) sp.getStartPosition(unit, n);
                        Matcher m = Pattern.compile("(?<![\\w$])" + Pattern.quote(n.getName().toString()) + "(?![\\w$])").matcher(text);
                        if (m.find(from)) add(i, m.start(), m.end());
                    }
                    return super.visitVariable(n, v);
                }
                @Override public Void visitMethod(MethodTree n, Void v) {
                    Element e = trees.getElement(getCurrentPath());
                    int i = match(e);
                    if (i >= 0) {
                        int from = n.getReturnType() != null ? (int) sp.getEndPosition(unit, n.getReturnType()) : (int) sp.getStartPosition(unit, n);
                        Matcher m = Pattern.compile("(?<![\\w$])" + Pattern.quote(n.getName().toString()) + "(?=\\s*\\()").matcher(text);
                        if (m.find(from)) add(i, m.start(), m.end());
                    }
                    return super.visitMethod(n, v);
                }
            }.scan(unit, null);
            editsByFile.put(path, edits); repl.put(path, names);
        }
        // collision check: new names must not equal any local/param name nor existing member in same owner
        Set<String> localNames = new HashSet<>();
        for (CompilationUnitTree unit : units) new TreePathScanner<Void, Void>() {
            @Override public Void visitVariable(VariableTree n, Void v) {
                Element e = trees.getElement(getCurrentPath());
                if (e != null && e.getKind() != ElementKind.FIELD) localNames.add(n.getName().toString());
                return super.visitVariable(n, v);
            }
        }.scan(unit, null);
        boolean bad = false;
        for (Spec s : specs) if (s.kind != 'L' && localNames.contains(s.newName)) { System.err.println("COLLISION with local/param: " + s.newName); bad = true; }
        for (int i = 0; i < specs.size(); i++) if (hits[i] == 0) { System.err.println("NO MATCH: " + specs.get(i)); bad = true; }
        if (bad && a.length < 3) { System.err.println("aborting (pass 'force' as 3rd arg to apply anyway)"); System.exit(2); }
        int total = 0;
        for (var en : editsByFile.entrySet()) {
            List<int[]> ed = en.getValue(); List<String> nm = repl.get(en.getKey());
            if (ed.isEmpty()) continue;
            Integer[] order = IntStream.range(0, ed.size()).boxed().sorted((x, y) -> ed.get(y)[0] - ed.get(x)[0]).toArray(Integer[]::new);
            StringBuilder sb = new StringBuilder(texts.get(en.getKey()));
            int last = Integer.MAX_VALUE;
            for (int idx : order) { int[] r = ed.get(idx); if (r[1] > last) continue; sb.replace(r[0], r[1], nm.get(idx)); last = r[0]; total++; }
            Files.writeString(Paths.get(en.getKey()), sb.toString(), java.nio.charset.StandardCharsets.UTF_8);
        }
        System.out.println("artifact locals renamed: " + artifactCount[0]);
        System.out.println("applied " + total + " edits, " + specs.size() + " specs");
    }
}
