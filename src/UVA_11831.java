import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class UVA_11831 {

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
        reader.close();
        writer.close();
    }

    static class Solver {

        void solve() throws Exception {
            int t = nextInt();
            while (t-- > 0) {
                int N = nextInt();
                int M = nextInt();
                int S = nextInt();

                char[][] matrixItem = new char[N][];

                for (int i = 0; i < N; i++) {
                    matrixItem[i] = next().toCharArray();
                }

            }
        }

    }
}
