package mylearnings.com.example;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {

    int v;

    // Adjacency Lists
    private LinkedList<Integer> adj[];

    Graph(int v) {
        this.v = v;
        this.adj = new LinkedList[v];

        // for (int i = 0; i < adj.length; i++) {
        //     System.out.println(" adj : " + adj[i]);
        // }

        // [{}, {}, {}, {}];

        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public static void main(String args[]) {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        // g.BFS(2);
        g.DFS(2);
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public void BFS(int source) {
        System.out.println("Source is " + source);

        boolean visited[] = new boolean[v];
        LinkedList<Integer> queue = new LinkedList<>();

        visited[source] = true;
        queue.add(source);

        while (!queue.isEmpty()) {
            int s = queue.poll();
            System.out.println("Graph.BFS() " + s);

            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    void DFS(int source) {
        boolean[] visted = new boolean[v];
        DFSUtil(source, visted);
    }

    void DFSUtil(int source, boolean[] visted) {
        visted[source] = true;
        System.out.println("DFS " + source);

        Iterator<Integer> i = adj[source].listIterator();

        while (i.hasNext()) {
            int n = i.next();

            if (!visted[n]) {
                DFSUtil(n, visted);
            }
        }
    }

}
