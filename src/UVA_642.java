import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class UVA_642 {

    static BufferedReader reader;
    static StringTokenizer tokenizer;
    static PrintWriter writer;

    static String next() throws Exception {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws Exception {
        return Integer.parseInt(next());
    }

    public static void main(String[] args) throws Exception {
        writer = new PrintWriter(System.out);
        reader = new BufferedReader(new InputStreamReader(System.in));
        Solver solver = new Solver();
        solver.solve();
        writer.close();
        reader.close();
    }

    static class Solver {

        void solve() throws Exception {
            String endl = "XXXXXX";
            List<String> list = new ArrayList<>();
            String s = next();
            int i = 0;
            while (!s.equals(endl)) {
                list.add(s);
                s = next();
            }
            s = next();
            while (!s.equals(endl)) {
                boolean isAn = false;
                TreeSet<String> set = new TreeSet<>();
                char[] _s = s.toCharArray();
                List<String> _list = list;
                for (String __s: _list) {
                    if (areAnagrams(__s.toCharArray(), _s)) {
                        set.add(__s);
                        if (!isAn) {
                            isAn = true;
                        }

                    }
                }
                if (isAn) {
                    for (String ___s: set) {
                        writer.println(___s);
                    }
                    writer.println("******");
                }
                if (!isAn) {
                    writer.println("NOT A VALID WORD");
                    writer.println("******");
                }
                s = next();
            }

        }
        boolean areAnagrams(char[] a, char[] b) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (char _a: a) {
                if (!map.containsKey(_a)) {
                    map.put(_a, 1);
                } else {
                    map.replace(_a, map.get(_a) + 1);
                }
            }
            for (char _b: b) {
                if (!map.containsKey(_b)) {
                    return false;
                } else {
                    map.replace(_b, map.get(_b) - 1);
                }
            }
            for (Map.Entry<Character, Integer> entry: map.entrySet()) {
                if (entry.getValue() != 0) {
                    return  false;
                }
            }
            return true;
        }
    }
}
