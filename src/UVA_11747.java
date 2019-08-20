import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class UVA_11747 {

    static class Graph {
        int V, E;
        Edge[] edges;

        class Edge {
            int src, dest, weight;
        }

        public Graph(int V, int E) {
            this.V = V;
            this.E = E;
            edges = new Edge[E];
            for (int i = 0; i < E; i++) {
                edges[i] = new Edge();
            }
        }

        void make_set(int[] parent, int[] rank, int value) {
            parent[value] = value;
            rank[value] = 0;
        }

        int find_set(int[] parent, int value) {
            if (parent[value] == value) return value;
            return parent[value] = find_set(parent, parent[value]);
        }

        void union_set(int[] parent, int[] rank, int a, int b) {
            int aroot = find_set(parent, a);
            int broot = find_set(parent, b);
            if (rank[aroot] > rank[broot]) {
                parent[broot] = aroot;
            } else if (rank[broot] > rank[aroot]) {
                parent[aroot] = broot;
            } else {
                parent[aroot] = broot;
                rank[aroot]++;
            }
        }

        boolean has_cycle(Graph graph, int[] parent, int[] rank) {
            if (graph.E < 2) return false;
            for (Edge edge: graph.edges) {
                if (find_set(parent, edge.src) == find_set(parent, edge.dest)) {
                    return true;
                }
                union_set(parent, rank, edge.src, edge.dest);
            }
            return false;
        }

        // return the index of MST edges
        Set<Integer> kruskal(Graph graph, int[] parent, int[] rank) {
            parent = new int[graph.V];
            rank = new int[graph.V];
            Set<Integer> MST = new HashSet<>();
            Arrays.sort(graph.edges, Comparator.comparingInt((Edge e2) -> e2.weight));
            for (int j = 0; j < graph.V; j++) {
                graph.make_set(parent, rank, j);
            }
            int i = 0, e = 0;
            while (e < graph.V - 1 && i < graph.E) {
                Edge next_edge = new Edge();
                next_edge = edges[i];
                int srcRoot = find_set(parent, next_edge.src);
                int destRoot = find_set(parent, next_edge.dest);
                if (srcRoot != destRoot) {
                    MST.add(i);
                    union_set(parent, rank, srcRoot, destRoot);
                }
                ++i;
            }
            return MST;
        }

    }

    static class Solver {

        void solve() throws Exception {
            int v, e;
            while (((v = nextInt()) != 0) && ((e = nextInt()) != 0)) {
                StringBuilder builder = new StringBuilder();
                int[] parent = new int[v];
                int[] rank = new int[v];
                Graph graph = new Graph(v, e);
                for (int i = 0; i < e; i++) {
                    graph.edges[i].src = nextInt();
                    graph.edges[i].dest = nextInt();
                    graph.edges[i].weight = nextInt();
                }
                for (int j = 0; j < graph.V; j++) {
                    graph.make_set(parent, rank, j);
                }
                if (!graph.has_cycle(graph, parent, rank)) {
                    writer.println("forest");
                } else {
                   Set<Integer> MST = graph.kruskal(graph, parent, rank);
                   for (int i = 0; i < e; i++) {
                       if (!MST.contains(i)) builder.append(graph.edges[i].weight + " ");
                   }
                   writer.println(builder.toString().trim());
                }
            }
        }

    }

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
}
