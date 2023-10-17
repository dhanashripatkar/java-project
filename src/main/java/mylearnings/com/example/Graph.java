package mylearnings.com.example;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    int v;

    // Adjacency Lists
    private LinkedList<Integer> adj[];

    Graph(int v) {
        this.v = v;
        this.adj = new LinkedList[v];

        // for (int i = 0; i < adj.length; i++) {
        // System.out.println(" adj : " + adj[i]);
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

    // BFS Graph
    public int orangesRotting(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int fresh = 0;

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[] { i, j });
                } else if (grid[i][j] == 1) {
                    fresh = fresh + 1;
                }
            }
        }

        int time = 0;
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        while (!queue.isEmpty() && fresh > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] pair = queue.poll();
                for (int[] dr : dir) {
                    int row = pair[0] + dr[0];
                    int col = pair[1] + dr[1];

                    if (row >= 0 && row < m && col >= 0 && col < n && grid[row][col] == 1) {
                        grid[row][col] = 2;
                        queue.offer(new int[] { row, col });
                        fresh = fresh - 1;
                    }
                }
            }
            time++;
        }
        if (fresh == 0) {
            return time;
        } else
            return -1;

    }

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int island = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    island++;
                }
            }
        }
        return island;

    }

    public void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 ||
                i >= grid.length || j >= grid[0].length ||
                grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }

}
