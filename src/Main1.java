import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main1 {
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
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        Solver solver = new Solver();
        solver.solve(reader, writer);
        reader.close();
        writer.close();
    }

    static class Solver {

        void solve(BufferedReader reader, PrintWriter writer) throws Exception {
            int n = nextInt();
            int t = nextInt();
            int[] s = new int[n + 1];
            int d;
            for (int i = 1; i < n + 1; i++) {
                s[i] = nextInt();
                d = nextInt();
                while (s[i] < t) {
                    s[i] += d;
                }
            }

            int ans = s[1];
            int i = 1;
            int j = 1;
            while (j < n + 1) {
                if (s[j] < ans) {
                    ans = s[j];
                    i = j;
                }
                j++;
            }
            writer.println(i);
        }

    }
}
