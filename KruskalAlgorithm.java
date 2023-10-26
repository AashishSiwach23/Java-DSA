import java.util.*;

class Edge implements Comparable<Edge> {
    int source, destination, weight;

    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }
}

class KruskalAlgorithm {
    private int V, E; // Number of vertices and edges
    private List<Edge> edges; // List of edges in the graph

    KruskalAlgorithm(int vertices, int edges) {
        this.V = vertices;
        this.E = edges;
        this.edges = new ArrayList<>();
    }

    void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge();
        edge.source = source;
        edge.destination = destination;
        edge.weight = weight;
        edges.add(edge);
    }

    List<Edge> kruskalMST() {
        List<Edge> minimumSpanningTree = new ArrayList<>();
        Collections.sort(edges);

        int[] parent = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }

        int edgeCount = 0;
        int index = 0;

        while (edgeCount < V - 1 && index < E) {
            Edge nextEdge = edges.get(index++);
            int x = find(parent, nextEdge.source);
            int y = find(parent, nextEdge.destination);

            if (x != y) {
                minimumSpanningTree.add(nextEdge);
                union(parent, x, y);
                edgeCount++;
            }
        }

        return minimumSpanningTree;
    }

    int find(int[] parent, int vertex) {
        if (parent[vertex] != vertex) {
            parent[vertex] = find(parent, parent[vertex]);
        }
        return parent[vertex];
    }

    void union(int[] parent, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        parent[rootX] = rootY;
    }
}

public class Main {
    public static void main(String[] args) {
        int V = 4; // Number of vertices
        int E = 5; // Number of edges

        KruskalAlgorithm graph = new KruskalAlgorithm(V, E);

        // Add edges to the graph (source, destination, weight)
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        List<Edge> minimumSpanningTree = graph.kruskalMST();
        System.out.println("Minimum Spanning Tree Edges:");
        for (Edge edge : minimumSpanningTree) {
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
        }
    }
}
