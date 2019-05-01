import java.util.Iterator;
import java.util.LinkedList;

/*
My class where im making grapg and list of vertexes' neighbours
Matrix with weights i mean reliability
 */
public class Graph {
    static int V;
    static double[][] H;
    static LinkedList<Integer>[] adjListArray;

    /*
    building graph and adjlsits
     */
    public Graph(int v) {
        this.V = v;
        adjListArray = new LinkedList[V];


        for (int i = 0; i < v; i++) {
            adjListArray[i] = new LinkedList<>();
        }

        H = new double[V][V];

    }

    /*
    adding edge between source and destination and back with weight h
    and filling matrix
     */
    public void addEdge(int src, int dest, double h) {
        adjListArray[src].add(dest);
        adjListArray[dest].add(src);
        H[src][dest] = h;
        H[dest][src] = h;
    }

    /*
    removing edge
    and setting it weight to 0
     */
    public void removeEdge(int src, int dest) {
        adjListArray[src].remove(Integer.valueOf(dest));
        adjListArray[dest].remove(Integer.valueOf(src));
        H[src][dest] = 0;
        H[dest][src] = 0;
    }

    /*
    test on interval
     */
    public void IntervalTest() {
        double r;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < adjListArray[i].size(); ++j) {
                if (adjListArray[i].get(j) > i) {
                    r = Math.random();
                    if (r > H[i][adjListArray[i].get(j)]) {
                        removeEdge(i, adjListArray[i].get(j));
                    }
                }
            }
        }
    }

    /*
    depth first search
     */
    public void DFS(int v, boolean visited[]) {
        visited[v] = true;

        for (int n : adjListArray[v]) {
            if (!visited[n])
                DFS(n, visited);
        }
    }

    public static void BFS(int s) {
        boolean visited[] = new boolean[V];
        int[] truepath = new int[V];


        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();
            LinkedList<Integer> neighbours = adjListArray[s];
            int[] path = new int[neighbours.size()];
            for (int i = 0; i < neighbours.size(); i++) {
                int n = neighbours.get(i);
                if (!visited[n]) {
                    visited[n] = true;
                    path[i] = n;
                    queue.add(n);
                }
            }

        }

    }

    /*
fucntion that check if graph is connected
     */
    public boolean isConnected() {
        boolean[] visited = new boolean[V];
        DFS(0, visited);
        for (int i = V - 1; i >= 0; i--) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    /*
    function that printing graph
     */
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
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (matrix[i][j] / 100 > 0) System.out.print(matrix[i][j] + " ");
                else if (matrix[i][j] / 10 > 0) System.out.print("0" + matrix[i][j] + " ");
                else System.out.print("00" + matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

        /*
        a try to make function that will calculate AVGdelaay
         */
   /* static double averageDelay(int[][] matrixInt, Graph grah){
        double Sum=0;

        for (int i =0;i<V;++i){
            for (int j=0;j<V;j++){
                Sum+=matrixInt[i][j];
            }
        }
        double SumEdges=0;
        for(int i=0;i<V;i++){
            for(int j=0;j< adjListArray[V].size();j++){
                H[i][j]
            }
        }

    }*/


}
