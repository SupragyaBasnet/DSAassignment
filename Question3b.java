// b) Implement Kruskal algorithm and priority queue using minimum heap
// [5 Marks] 

import java.util.*;

public class Question3b {
    
    // Definition of Edge class to represent edges in the graph.
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        // Constructor to initialize an edge with source, destination, and weight.
        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        // Comparison method to compare edges based on weight.
        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    // Method to find the minimum spanning tree (MST) of a graph using Kruskal's algorithm.
    static List<Edge> minimumSpanningTree(int V, List<Edge> edges) {
        List<Edge> MST = new ArrayList<>(); // Initialize MST as an empty list to store edges of the MST.
        int[] parent = new int[V]; // Initialize an array to keep track of parent nodes for each vertex.
        Arrays.fill(parent, -1); // Initialize all parent nodes as -1, indicating no parent.

        Collections.sort(edges); // Sort the edges based on their weights in ascending order.

        // Iterate over each edge in sorted order.
        for (Edge edge : edges) {
            // Find the parent nodes of the source and destination vertices of the edge.
            int srcParent = find(parent, edge.src);
            int destParent = find(parent, edge.dest);

            // If the source and destination vertices do not belong to the same connected component,
            // add the edge to the MST and merge the connected components.
            if (srcParent != destParent) {
                MST.add(edge); // Add the current edge to the MST.
                union(parent, srcParent, destParent); // Merge the connected components.
            }
        }

        return MST; // Return the minimum spanning tree.
    }

    // Method to find the root/parent of a vertex in its connected component.
    static int find(int[] parent, int x) {
        if (parent[x] == -1) // If the current vertex is a root, return it.
            return x;
        return parent[x] = find(parent, parent[x]); // Path compression: Set the parent of the current vertex to its root.
    }

    // Method to merge two connected components by setting one as the parent of the other.
    static void union(int[] parent, int x, int y) {
        int rootX = find(parent, x); // Find the root of the component containing vertex x.
        int rootY = find(parent, y); // Find the root of the component containing vertex y.

        parent[rootX] = rootY; // Set the root of component x as the parent of component y.
    }

    // Main method to test the minimum spanning tree algorithm.
    public static void main(String[] args) {
        int V = 4; // Number of vertices in the graph.
        List<Edge> edges = new ArrayList<>(); // Initialize a list to store the edges of the graph.

        // Add the edges of the graph with their corresponding source, destination, and weights.
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        // Find and print the minimum spanning tree of the graph using Kruskal's algorithm.
        List<Edge> MST = minimumSpanningTree(V, edges);
        for (Edge edge : MST) {
            System.out.println(edge.src + " - " + edge.dest + ": " + edge.weight); // Print the edges of the MST.
        }
    }
}
