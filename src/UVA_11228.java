import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class UVA_11228 {
    static StringTokenizer tokenizer;
    static BufferedReader reader;
    static PrintWriter writer;
    static int areas = 0;

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
        Solver solver = new Solver();
        reader = new BufferedReader(new InputStreamReader(System.in));
        solver.solve();
        reader.close();
        writer.close();
    }

    static class Solver {

        void solve() throws Exception {
            int t = nextInt();
            Map<Graph.Vertex, Graph.Vertex> parent;
            Map<Graph.Vertex, Integer> rank;
            int v, r;
            int _case = 1;
            while (t-- > 0) {
                parent = new HashMap<>();
                rank = new HashMap<>();
                v = nextInt();
                r = nextInt();
                Graph graph = new Graph(v);
                List<Graph.Vertex> vertices = new ArrayList<>();
                for (int i = 0; i < v; i++) {
                    graph.vertices[i].x = nextInt();
                    graph.vertices[i].y = nextInt();
                    graph.make_set(parent, rank, graph.vertices[i]);
                    vertices.add(graph.vertices[i]);
                }
                int road = (int) Math.round(graph.find_road(parent, rank, r));
                parent.clear();
                rank.clear();
                for (int i = 0; i < v; i++) graph.make_set(parent, rank, graph.vertices[i]);
                writer.println("Case #" + _case++ + ": " + areas + " " + road + " "
                        + (areas < 2 ? 0 : Math.round(graph.rail(parent, rank, vertices))));
            }
        }
    }

    static class Graph {
        int v;
        Vertex[] vertices;

        Graph(int v) {
            this.v = v;
            vertices = new Vertex[v];
            for (int i = 0; i < v; i++) {
                vertices[i] = new Vertex();
            }
        }

        static class Edge implements Comparable<Edge> {
            Vertex src, dest;
            double weight;

            @Override
            public int compareTo(Edge o) {
                return (int)(this.weight - o.weight);
            }
        }

        /**
         * @x, @y: coordinates of the vertex
         * @comp: number of the component which the vertex belong to
         */
        static class Vertex {
            int x, y, comp;

            double distance(Vertex o) {
                return (Math.pow(this.x - o.x , 2) + Math.pow(this.y - o.y, 2));
            }

        }

        Edge make_edge(Vertex src, Vertex dest) {
            Edge edge = new Edge();
            edge.src = src;
            edge.dest = dest;
            edge.weight = src.distance(dest);
            return edge;
        }

        void make_set(Map<Vertex, Vertex> parent, Map<Vertex, Integer> rank, Vertex value) {
            parent.putIfAbsent(value, value);
            rank.putIfAbsent(value, 0);
        }

        Vertex find_set(Map<Vertex, Vertex> parent, Vertex value) {
            if (parent.get(value).equals(value)) {
                return value;
            }
            Vertex new_value = find_set(parent, parent.get(value));
            parent.replace(value, new_value);
            return new_value;
        }

        void union_set(Map<Vertex, Vertex> parent, Map<Vertex, Integer> rank, Vertex a, Vertex b) {
            Vertex aroot = find_set(parent, a);
            Vertex broot = find_set(parent, b);
            if (rank.get(aroot) > rank.get(broot)) {
                parent.replace(broot, aroot);
            } else if (rank.get(broot) > rank.get(aroot)) {
                parent.replace(aroot, broot);
            } else {
                parent.replace(aroot, broot);
                rank.replace(aroot, rank.get(aroot) + 1);
            }
        }

        /**
         * return a list of vertices that belong to same state
         */
        List<Vertex> dfs(Set<Vertex> visited, Vertex src, int r, List<Vertex> comp, int k) {
            visited.add(src);
            src.comp = k;
            comp.add(src);
            for (Vertex vertex : this.vertices) {
                if (!visited.contains(vertex) && (src.distance(vertex) <= r * r)) {
                    dfs(visited, vertex, r, comp, k);
                }

            }
            return comp;
        }

        /**
         * finds all connected components of the graph and return the sum of weights of their MST's
         */
        double find_road(Map<Vertex, Vertex> parent, Map<Vertex, Integer> rank, int r) {
            Set<Vertex> visited = new HashSet<>();
            List<Vertex> cities;
            double result = 0;
            areas = 0;
            for (int i = 0; i < v; i++) {
                if (!visited.contains(this.vertices[i])) {
                    cities = new ArrayList<>();
                    dfs(visited, vertices[i], r, cities, areas++);
                    //areas++;
                    result += road(parent, rank, cities);
                }
            }
            return result;
        }

        /**
         * to run kruskal on each state
         */
        double road(Map<Vertex, Vertex> parent, Map<Vertex, Integer> rank, List<Vertex> comp) {
            double result = 0;
            if (comp.size() < 2) return 0;
            Edge next_edge = new Edge();
            int edges_size = (comp.size() * (comp.size() - 1)) / 2; // number of edges for a complete graph
            Edge[] edges = new Edge[edges_size];
            for (int i = 0; i < edges_size; i++) edges[i] = new Edge();
            edges_size = 0;
            for (int i = 0; i < comp.size() - 1; i++) {
                for (int j = i + 1; j < comp.size(); j++) {
                    edges[edges_size++] = make_edge(comp.get(i), comp.get(j));
                }
            }
            Arrays.sort(edges);
            int e = 0, i = 0;
            while (e < comp.size() - 1) {
                next_edge = edges[i++];
                Vertex aroot = find_set(parent, next_edge.src);
                Vertex broot = find_set(parent, next_edge.dest);
                if (aroot != broot) {
                    result += Math.sqrt(next_edge.weight);
                    union_set(parent, rank, aroot, broot);
                    e++;
                }
            }
            return result;
        }

        double rail(Map<Vertex, Vertex> parent, Map<Vertex, Integer> rank, List<Vertex> comp) {
            double result = 0;
            if (comp.size() < 2) return 0;
            Edge next_edge = new Edge();
            int edges_size = (v * (v - 1)) / 2;
            Edge[] edges = new Edge[edges_size];
            for (int i = 0; i < edges_size; i++) edges[i] = new Edge();
            edges_size = 0;
            for (int i = 0; i < v - 1; i++) {
                for (int j = i + 1; j < v; j++) {
                    edges[edges_size++] = make_edge(comp.get(i), comp.get(j));
                }
            }
            Arrays.sort(edges);
            int e = 0, i = 0;
            while (e < v - 1) {
                next_edge = edges[i++];
                Vertex aroot = find_set(parent, next_edge.src);
                Vertex broot = find_set(parent, next_edge.dest);
                if (aroot != broot) {
                    if (next_edge.src.comp != next_edge.dest.comp) result += Math.sqrt(next_edge.weight);
                    union_set(parent, rank, aroot, broot);
                    e++;
                }
            }
            return result;
        }

    }
}
