public class Graph {

    protected int v;
    LinkedList<Integer> adj[];

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    public static void addEdge(Graph graph, int src, int dest) {
        graph.adj[src].add(dest);
        graph.adj[dest].add(src);
    }

    public static void BFS(Graph graph, int currentNode) {
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        queue.add(currentNode);
        visited.add(currentNode);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for (int iterator : graph.adj[currentNode]) {
                if (!visited.contains(iterator)) {
                    visited.add(iterator);
                    queue.add(iterator);
                }
            }
        }
    }

    /* to print to graph using Depth First Search */
    public static void DFS(Graph graph, int currentNode) {
        HashSet<Integer> visited = new HashSet<>();
        DFS(graph, currentNode, visited);
    }

    public static void DFS(Graph graph, int currentNode, HashSet<Integer> visited) {
        visited.add(currentNode);
        System.out.print(currentNode + " ");
        for (Integer node : graph.adj[currentNode]) {
            if (!visited.contains(node)) {
                DFS(graph, node, visited);
            }
        }
    }
    /* -------------------------------------------- */

    /* to show if there is a path between two nodes */
    public static boolean hasPathDfs(Graph graph, int src, int dest) {
        HashSet<Integer> visited = new HashSet<>();
        return hasPathDfs(graph, src, dest, visited);
    }

    public static boolean hasPathDfs(Graph graph, int src, int dest, HashSet<Integer> visited) {
        if (visited.contains(src)) {
            return false;
        }
        visited.add(src);
        if (src == dest) {
            return true;
        }
        for (Integer node : graph.adj[src]) {
            if (hasPathDfs(graph, node, dest, visited)) {
                return true;
            }
        }
        return false;
    }
    /* --------------------------------------- */

    /* to find connected components */
    public static void connectedComponents(Graph graph) {
        HashSet<Integer> visited = new HashSet<>();
        int cc = 0;
        for (int i = 0; i < graph.v; i++) {
            if (!visited.contains(i)) {
                DFS(graph, i, visited);
                ++cc;
                System.out.println();
            }
        }
        System.out.println(cc);
    }
    /* ---------------------------- */

    /* Check if a graph is Bipartite */
    public static boolean isBipartite(Graph graph, int src) {
        int color[] = new int[graph.v];
        for (int i = 0; i < graph.v; i++) {
            color[i] = -1;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        color[src] = 1;
        queue.add(src);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int node : graph.adj[u]) {
                if (color[node] == -1) {
                    color[node] = 1 - color[u];
                    queue.add(node);
                } else if (color[node] == color[u]) {
                    return false;
                }
            }
        }
        return true;
    }
    /* ----------------------------- */

    public static void printGraph(Graph graph) {
        for (int i = 0; i < graph.v; i++) {
            System.out.println("Adjacency list of node: " + i);
            for (Integer node : graph.adj[i]) {
                System.out.print("->" + node);
            }
            System.out.println("\n");
        }
    }

    public static void main(String args[]) {

        int v = 5;
        Graph graph = new Graph(v);
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 4);
        printGraph(graph);
        System.out.println();
        System.out.println(isBipartite(graph, 0) ? "Graph is Bipartite" : "Graph is not Bipartite");
        //System.out.println(hasPathDfs(graph, 4, 2));

        //BFS(graph, 0);
        //System.out.println();
        //BFS(graph, 3);
        //System.out.println();
        //connectedComponents(graph);
    }


}
