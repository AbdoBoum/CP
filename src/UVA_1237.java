import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class UVA_1237 {

    static BufferedReader reader;
    static StringTokenizer tokenizer;
    static PrintWriter writer;
    static int Max = 0;

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

    static class Node {
        int id;
        int value;

        public Node(int id, int value) {
            this.id = id;
            this.value = value;
        }
    }

   static class graph {
        int v;
        LinkedList<Node> adj[];

        public graph(int v) {
            this.v = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; i++) {
                adj[i] = new LinkedList<Node>();
            }
        }

        public void addEdge(Node src, Node dest) {
            adj[src.id].add(dest);
        }

       public void printGraph() {
           for (int i = 0; i < v; i++) {
               System.out.println("Adjacency list of node: " + i);
               for (Node node : adj[i]) {
                   System.out.print("->" + node.id);
               }
               System.out.println("\n");
           }
       }

        public void MaxPathDfs(Node src, int result) {

            int maxAdj = 0;

            for (Node node: adj[src.id]) {
                 if (node.value > maxAdj) {
                     maxAdj = node.value;
                     src = node;
                 }
            }
            if (adj[src.id].isEmpty()) {
                writer.println(result + src.value + " " + src.id);
                return;
            }
            MaxPathDfs(src, result + src.value);

        }
    }


    static class Solver {

        void solve() throws Exception {
            int t = nextInt();
            int j = 1;
            while (t-- > 0) {
                writer.print("Case " + j++ + ": ");
                int v = nextInt();
                graph g = new graph(v);
                int edges = nextInt();
                Node[] nodes = new Node[v];
                for (int i = 0; i < v; i++) {
                    nodes[i] = new Node(i, nextInt());
                }
                for (int i = 0; i < edges; i++) {
                    g.addEdge(nodes[nextInt()], nodes[nextInt()]);
                }
                //g.printGraph();
                g.MaxPathDfs(nodes[0], 0);
            }
        }

    }
}
