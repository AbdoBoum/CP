import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVA_352 {
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
        solver.solve();
        reader.close();
        writer.close();
    }

    static class Solver {

        void solve() throws Exception {
            int t = nextInt();
            int ans;
            int count = 1;
            while (t-- > 0) {
                ans = 0;
                int n = nextInt();
                int[][] mat = new int[n][n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        mat[i][j] = nextInt();
                    }
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (mat[i][j] == 1) {
                            mat = findWarEagle(mat, i, j, n);
                            ans++;
                        }
                    }
                }
                writer.println("Image number " + (count++) + " contains " + ans + " war eagles.");
            }
        }

        int[][] findWarEagle(int[][] mat, int row, int col, int n) {

            if (row < 0 || row >= n || col < 0 || col >= n) {
                return mat;
            }
            if (mat[row][col] == 0) {
                return mat;
            }
            mat[row][col] = 0;

                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col + 1; j++) {
                        mat = findWarEagle(mat, i, j, n);
                    }
                }

            return mat;
        }

    }
}
