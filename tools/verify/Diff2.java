import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class Diff2 {
    static Class<?> load(String dir) throws Exception {
        URLClassLoader l = new URLClassLoader(new URL[]{new File(dir).toURI().toURL()}, ClassLoader.getPlatformClassLoader());
        return Class.forName("House", true, l);
    }
    static Object deep(Object v) {
        if (v == null) return null;
        Class<?> c = v.getClass();
        if (c.isArray()) {
            int n = Array.getLength(v);
            if (c.getComponentType().isPrimitive()) {
                Object o = Array.newInstance(c.getComponentType(), n);
                System.arraycopy(v, 0, o, 0, n);
                return o;
            }
            Object o = Array.newInstance(c.getComponentType(), n);
            for (int i = 0; i < n; i++) Array.set(o, i, deep(Array.get(v, i)));
            return o;
        }
        return v;
    }
    static boolean simple(Field f) {
        Class<?> t = f.getType();
        while (t.isArray()) t = t.getComponentType();
        return t.isPrimitive() || t == String.class;
    }
    static Map<String, Field> fields(Class<?> c) {
        Map<String, Field> m = new LinkedHashMap<>();
        for (Field f : c.getDeclaredFields()) {
            if (!Modifier.isStatic(f.getModifiers())) continue;
            f.setAccessible(true);
            m.put(f.getName(), f);
        }
        return m;
    }
    static Map<String, Object> snap(Map<String, Field> fs) throws Exception {
        Map<String, Object> m = new LinkedHashMap<>();
        for (Map.Entry<String, Field> e : fs.entrySet()) {
            Field f = e.getValue();
            if (!simple(f)) continue;
            m.put(e.getKey(), deep(f.get(null)));
        }
        return m;
    }
    static void restore(Map<String, Field> fs, Map<String, Object> s) throws Exception {
        for (Map.Entry<String, Object> e : s.entrySet()) {
            Field f = fs.get(e.getKey());
            if (Modifier.isFinal(f.getModifiers())) {
                // final arrays: restore contents in place
                Object cur = f.get(null);
                if (cur != null && cur.getClass().isArray()) copyInto(cur, e.getValue());
                continue;
            }
            f.set(null, deep(e.getValue()));
        }
    }
    static void copyInto(Object dst, Object src) {
        int n = Array.getLength(dst);
        for (int i = 0; i < n; i++) {
            Object s = Array.get(src, i);
            if (s != null && s.getClass().isArray()) copyInto(Array.get(dst, i), s); else Array.set(dst, i, s);
        }
    }

    static Random R; static Map<String,String> O2N;
    static int ri(int lo, int hi) { return lo + R.nextInt(hi - lo + 1); }
    static int pick(int... v) { return v[R.nextInt(v.length)]; }

    // returns setup: newName -> value
    static Map<String, Object> setup() {
        Map<String, Object> m = new LinkedHashMap<>();
        int[] st = new int[5], tilt = new int[5], tt = new int[5], bx = new int[5], by = new int[5], vx = new int[5], vy = new int[5], ly = new int[5],
              sp = new int[5], spt = new int[5], dl = new int[5];
        for (int i = 0; i < 5; i++) {
            st[i] = i == 0 ? pick(1, 2, 2, 2, 2, 3, 4, 4, 5, 5, 6, 7) : pick(0, 4, 5, 5, 7, 7, 2);
            tilt[i] = pick(ri(-100, 100), 999, 888, ri(-45, 45));
            tt[i] = pick(ri(-90, 90), 0, ri(-45, 45));
            bx[i] = ri(-2048, 2048); by[i] = ri(0, 4200);
            vx[i] = ri(-500, 500); vy[i] = ri(0, 300); ly[i] = ri(1000, 4200);
            sp[i] = pick(ri(-60, 60), 999, 0); spt[i] = pick(ri(-60, 60), 0);
            dl[i] = pick(0, 150, 350, 550, 750);
        }
        int gt = ri(1000, 100000);
        m.put("blockState", st); m.put("tiltAngle", tilt); m.put("tiltTarget", tt); m.put("blockX", bx); m.put("blockY", by);
        m.put("blockVelX", vx); m.put("blockLaunchVelY", vy); m.put("blockLaunchY", ly); m.put("spinAngle", sp); m.put("spinTarget", spt);
        m.put("launchDelay", dl);
        m.put("gameTime", gt); m.put("eventTime", gt - pick(ri(0, 1500), 399, 400, 401, 0, 1));
        m.put("ropeLength", pick(0, ri(0, 1664), 1664, 1408)); m.put("craneTopY", 2432);
        m.put("hookX", ri(-1500, 1500)); m.put("hookY", ri(500, 3000)); m.put("hookTilt", ri(-100, 100));
        m.put("swingPeriod", ri(1000, 2000)); m.put("phase", pick(0, 0, 0, 1, 1, 2, 3, 4, 4));
        int fc = ri(1, 12); m.put("floorCount", fc); m.put("targetFloorCount", pick(ri(3, 14), fc + 1, fc + 1, fc, fc + 2)); m.put("score", ri(0, 1000)); m.put("level", ri(1, 4));
        m.put("comboTimer", pick(0, ri(1, 6000))); m.put("comboChain", ri(0, 5)); m.put("comboBonus", ri(0, 100));
        m.put("roofBonusEligible", R.nextBoolean());
        int[] ft = new int[5], fx = new int[5], fy = new int[5];
        for (int i = 0; i < 5; i++) { ft[i] = ri(-60, 60); fx[i] = ri(-300, 300); fy[i] = ri(0, 4200); }
        m.put("floorTilt", ft); m.put("floorX", fx); m.put("floorY", fy);
        m.put("topFloorIndex", ri(0, 4)); m.put("landingSearchIndex", ri(0, 4));
        m.put("cameraY", ri(800, 3200)); m.put("cameraX", ri(-300, 300)); m.put("screenCenterX", 120); m.put("screenCenterY", 160);
        m.put("viewHeight", 2560); m.put("viewWidth", 1920);
        m.put("e", pick(5, 5, 6));
        byte[] aF = new byte[20]; for (int i = 0; i < 20; i++) aF[i] = (byte) pick(0, ri(-127, 127));
        m.put("aF", aF);
        if (R.nextInt(100) < 60) {
            // targeted: block 0 falling onto the tower, near a floor, optionally at roof-trigger count
            int idx = ri(0, 4);
            int[] st2 = st.clone(); st2[0] = 2; m.put("blockState", st2);
            int top = ri(1, 6);
            m.put("floorCount", top); m.put("topFloorIndex", Math.min(4, top - 1)); m.put("landingSearchIndex", Math.min(4, top - 1));
            if (R.nextBoolean()) m.put("targetFloorCount", top + 1);
            int[] fx2 = new int[5], fy2 = new int[5];
            for (int i = 0; i < 5; i++) { fx2[i] = ri(-60, 60); fy2[i] = 1000 + i * 256; }
            m.put("floorX", fx2); m.put("floorY", fy2);
            int t2 = Math.min(4, top - 1);
            int[] bx2 = bx.clone(), by2 = by.clone(), vy2 = vy.clone();
            bx2[0] = fx2[t2] + pick(0, ri(-20, 20), ri(-130, 130), 127, 128, -127, -128, ri(-300, 300));
            by2[0] = fy2[t2] + pick(0, ri(-255, 255), 255, 256, -255, -256, 100);
            vy2[0] = ri(0, 50);
            m.put("blockX", bx2); m.put("blockY", by2); m.put("blockVelX", new int[]{0, 0, 0, 0, 0});
            m.put("blockLaunchY", by2); m.put("blockLaunchVelY", vy2);
            m.put("cameraY", ri(800, 1800));
            m.put("phase", pick(0, 0, 1, 4));
            m.put("e", pick(5, 5, 6));
        }
        if (R.nextInt(100) < 50) {
            // boundary values for time / view thresholds
            int gt2 = (Integer) m.get("gameTime");
            m.put("eventTime", gt2 - pick(399, 400, 401, 402, 2000, 2001));
            int[] sb = (int[]) m.get("blockState");
            if (R.nextBoolean()) { int[] s3 = sb.clone(); s3[0] = pick(4, 4, 3, 5); m.put("blockState", s3); }
            int[] bb = ((int[]) m.get("blockY")).clone();
            int cy = (Integer) m.get("cameraY");
            bb[0] = pick(cy - 1280, cy - 1281, cy - 1279, 2432 - 512, 2432 - 513, 2432 - 511);
            bb[1] = cy - 1280 + pick(-1, 0, 1);
            m.put("blockY", bb);
            int[] ld = ((int[]) m.get("launchDelay")).clone(); ld[1] = pick(0, 150, 350, 550, 750); m.put("launchDelay", ld);
        }
        return m;
    }
    static void apply(Map<String, Field> fs, Map<String, String> toOld, boolean isNew, Map<String, Object> s) throws Exception {
        for (Map.Entry<String, Object> e : s.entrySet()) {
            String old = toOld.getOrDefault(e.getKey(), e.getKey()); String name = isNew ? O2N.getOrDefault(old, old) : old;
            Field f = fs.get(name);
            if (f == null) throw new RuntimeException("no field " + name + (isNew ? " (new)" : " (old)"));
            Object v = deep(e.getValue());
            f.set(null, v);
        }
    }

    public static void main(String[] a) throws Exception {
        Class<?> O = load(a[0]), N = load(a[1]);
        int trials = Integer.parseInt(a[2]);
        Map<String, String> toOld = new HashMap<>(), toNew = new HashMap<>(); Map<String,String> o2n = new HashMap<>();
        for (String line : Files.readAllLines(Paths.get(a[4]))) { String[] p = line.trim().split(" "); if (p.length==2) o2n.put(p[0], p[1]); }
        for (String line : Files.readAllLines(Paths.get(a[3]))) {
            String[] p = line.trim().split(" ");
            if (p.length == 2) { toOld.put(p[0], p[1]); toNew.put(p[1], p[0]); }
        }
        O2N = o2n; Map<String, Field> fo = fields(O), fn = fields(N);
        Method mo = O.getDeclaredMethod("s", int.class), mn = N.getDeclaredMethod(o2n.get("METHOD:s"), int.class);
        mo.setAccessible(true); mn.setAccessible(true);
        // stubs
        Object vo = Class.forName("Vibra", true, O.getClassLoader()).getDeclaredConstructor().newInstance();
        Object vn = Class.forName("Vibra", true, N.getClassLoader()).getDeclaredConstructor().newInstance();
        Field lo = fo.get("l"), ln = fn.get(o2n.getOrDefault("l","l"));
        Field rngO = null, rngN = null;
        for (Field f : fo.values()) if (f.getType() == Random.class) rngO = f;
        for (Field f : fn.values()) if (f.getType() == Random.class) rngN = f;
        Map<String, Object> baseO = snap(fo), baseN = snap(fn);

        long mismatches = 0, exOld = 0, frames = 0;
        Map<String, Integer> exTypes = new TreeMap<>();
        Map<String, Integer> trans = new TreeMap<>();
        for (int t = 0; t < trials; t++) {
            long seed = 1000 + t;
            R = new Random(seed);
            restore(fo, baseO); restore(fn, baseN);
            Map<String, Object> s = setup();
            apply(fo, toOld, false, s); apply(fn, toOld, true, s);
            fo.get("c").set(null, vo); fn.get(o2n.getOrDefault("c","c")).set(null, vn);
            lo.set(null, new int[]{50, 100, 150, 200}); ln.set(null, new int[]{50, 100, 150, 200});
            if (rngO != null) { rngO.set(null, new Random(seed)); rngN.set(null, new Random(seed)); }
            int nf = ri(1, 8);
            for (int fr = 0; fr < nf; fr++) {
                int dt = ri(10, 60);
                int[] stBefore = ((int[]) fo.get("aw").get(null)).clone(); int phBefore = fo.get("bk").getInt(null);
                // keep time advancing like the real loop
                Throwable eo = null, en = null;
                try { mo.invoke(null, dt); } catch (InvocationTargetException x) { eo = x.getCause(); }
                try { mn.invoke(null, dt); } catch (InvocationTargetException x) { en = x.getCause(); }
                frames++;
                int[] stAfter = (int[]) fo.get("aw").get(null);
                for (int i = 0; i < 5; i++) trans.merge("slot" + (i == 0 ? "0" : "n") + " " + stBefore[i] + "->" + stAfter[i], 1, Integer::sum);
                trans.merge("PHASE " + phBefore + "->" + fo.get("bk").getInt(null), 1, Integer::sum);
                String so = eo == null ? "ok" : eo.getClass().getName() + ":" + eo.getMessage();
                String sn = en == null ? "ok" : en.getClass().getName() + ":" + en.getMessage();
                if (eo != null) { exOld++; exTypes.merge(eo.getClass().getSimpleName(), 1, Integer::sum); }
                boolean bad = !so.equals(sn);
                if (bad) System.out.println("EXC trial " + t + " frame " + fr + "\n   old=" + so + "\n   new=" + sn + "");
                Map<String, Object> xo = snap(fo), xn = snap(fn);
                for (Map.Entry<String, Object> e : xo.entrySet()) {
                    String nn = o2n.getOrDefault(e.getKey(), e.getKey());
                    if (!xn.containsKey(nn) || !Objects.deepEquals(e.getValue(), xn.get(nn))) {
                        if (!bad) System.out.println("MISMATCH trial " + t + " frame " + fr + " field " + e.getKey() + "/" + nn);
                        bad = true;
                        if (mismatches < 5) System.out.println("   old=" + Arrays.deepToString(new Object[]{e.getValue()}) + " new=" + Arrays.deepToString(new Object[]{xn.get(nn)}));
                    }
                }
                if (bad) { mismatches++; break; }
                // advance game time as the real loop would
                fo.get("cg").setInt(null, fo.get("cg").getInt(null) + dt);
                fn.get(o2n.get("cg")).setInt(null, fn.get(o2n.get("cg")).getInt(null) + dt);
            }
        }
        System.out.println("trials=" + trials + " frames=" + frames + " mismatches=" + mismatches + " framesWithException=" + exOld + " " + exTypes);
        System.out.println("state transitions covered:");
        for (Map.Entry<String, Integer> e : trans.entrySet()) System.out.println("  " + e.getKey() + "  x" + e.getValue());
    }
}
