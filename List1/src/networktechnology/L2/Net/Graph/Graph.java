package networktechnology.L2.Net.Graph;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
    public static double destroCounter;
    static int V;
    static double[][] H;
    static LinkedList<Integer>[] adjListArray;

    public Graph(int v) {
        this.V = v;
        adjListArray = new LinkedList[V];


        for (int i = 0; i < V; i++) {
            adjListArray[i] = new LinkedList<>();
        }

        H = new double[V][V];

    }

    public void addEdge(int src, int dest, double h) {
        adjListArray[src].add(dest);
        adjListArray[dest].add(src);
        H[src][dest] = h;
        H[dest][src] = h;
    }

    public void removeEdge(int src, int dest) {
        adjListArray[src].remove(Integer.valueOf(dest));
        adjListArray[dest].remove(Integer.valueOf(src));
        H[src][dest] = 0;
        H[dest][src] = 0;
    }

    public void IntervalTest() {
        double r;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < adjListArray[i].size(); ++j) {
                if (adjListArray[i].get(j) > i) {
                    r = Math.random();
                    if (r <0.95/* H[i][adjListArray[i].get(j)]*/) {
                        removeEdge(i, adjListArray[i].get(j));
                    }
                }
            }
        }
    }

    public void DFSUtil(int v, boolean visited[]) {
        visited[v] = true;
        //  System.out.print(v + " ");

        Iterator<Integer> i = adjListArray[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    public void DFS(int v) {
        boolean visited[] = new boolean[V];
        DFSUtil(v, visited);
    }

    public boolean isConnected() {
        boolean[] visited = new boolean[V + 1];
        for (int i = 0; i < V + 1; i++) {
            DFSUtil(i, visited);
            if (!visited[V]) {
                return false;
            }
        }
        return true;
    }

    public static void printM(double mat[][]) {
        for (int i = 0; i < mat.length; i++)

            for (int j = 0; j < mat[i].length; j++)
                System.out.print(mat[i][j] + " ");
    }

    public static void printGraph() {
        for (int v = 0; v < V; v++) {
            System.out.println("Adjacency list of vertex " + v);
            System.out.print("head");
            for (Integer pCrawl : adjListArray[v]) {
                System.out.print(" -> " + pCrawl);
            }
            System.out.println("\n");
        }
    }
}
