import java.util.*;
import java.io.*;

public class Graph {

  private int v;
  LinkedList<Integer> adj[];

  public Graph(int v) {
    this.v = v;
    adj = new LinkedList[v];
    for (int i = 0; i < v; i++) {
      adj[i] = new LinkedList<Integer>();
    }
  }


  public static void addEdge(Graph graph,int src, int dest) {
    graph.adj[src].add(dest);
    graph.adj[dest].add(src);
  }

  public static void printGraph(Graph graph) {
    for (int i = 0; i < graph.v; i++) {
      System.out.println("Adjacency list of node: " + i);
      for (Integer node: graph.adj[i]) {
        System.out.print("->" + node);
      }
      System.out.println("\n");
    }
  }

  public static void main(String args[]) {

    int v = 5;
    Graph graph = new Graph(v);
    addEdge(graph, 0, 1);
    addEdge(graph, 0, 4);
    addEdge(graph, 1, 2);
    addEdge(graph, 1, 3);
    addEdge(graph, 1, 4);
    addEdge(graph, 2, 3);
    addEdge(graph, 3, 4);

    // print the adjacency list representation of
    // the above graph
    printGraph(graph);
  }


}
