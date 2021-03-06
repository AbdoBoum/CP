import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
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
            char[] seq = next().toCharArray();
            int min = (int)10e9;
            int i =0;
            char[] genome = new char[]{'A', 'C', 'T', 'G'};
            while (i < t-3) {
                int v1 = Math.min((seq[i] - genome[0] + 26) % 26, (genome[0] - seq[i] + 26) % 26);

                int v2 = Math.min((seq[i+1] - genome[1] + 26) % 26, (genome[1] - seq[i+1] + 26) % 26);

                int v3 = Math.min((seq[i+2] - genome[2] + 26) % 26, (genome[2] - seq[i+2] + 26) % 26);

                int v4 = Math.min((seq[i+3] - genome[3] + 26) % 26, (genome[3] - seq[i+3] + 26) % 26);

                min = Math.min(min,v1+v2+v3+v4);
                i++;
            }
            writer.println(min);
        }


    }
}
