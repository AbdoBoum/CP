import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class UVA_12582 {

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
            int j = 1;
            while (t-- > 0) {
                int[] ans = new int[26];
                Arrays.fill(ans, 0);
                writer.println("Case " + j);
                j++;
                String Path = next();
                char[] path = Path.toCharArray();
                Stack<Character> stack = new Stack<>();
                stack.push(path[0]);
                for (int i = 1; i < path.length; i++) {
                    char c = path[i];
                    if (!stack.isEmpty() && c == stack.peek()) {
                        stack.pop();
                        if (!stack.isEmpty()) {
                            ++ans[c - 'A'];
                            ++ans[stack.peek() - 'A'];
                        }
                    } else {
                        stack.push(c);
                    }
                }
                for (int i = 0; i < ans.length; i++) {
                    if (ans[i] != 0) {
                        char c = (char) (i + 'A');
                        writer.println(c + " = " + ans[i]);
                    }
                }
            }
        }

    }
}
