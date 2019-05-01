import java.util.concurrent.ThreadLocalRandom;

public class Simulation1 {

    /*
        First simulation, all 4 checks
     */
    static public double Simulation1() {


        double counter = 0;
        int V = 20;
        double numberOfAttempts = 10000;

        for (int j = 0; j < numberOfAttempts; j++) {
            Graph g = new Graph(V);
            // g.addEdge(0, 19, 0.95);
            for (int i = 0; i < V - 1; i++) {
                g.addEdge(i, i + 1, 0.95);
            }


            //   g.addEdge(1, 10, 0.8);
            //   g.addEdge(5, 15, 0.7);
            //for (int m = 0; m < 4; m++) {
            //    int x = ThreadLocalRandom.current().nextInt(0, 18 + 1);
            //    int y = ThreadLocalRandom.current().nextInt(0, 18 + 1);
            //    g.addEdge(x, y, 0.4);
            //}
            g.IntervalTest();
            if (g.isConnected()) counter++;

        }

        return counter / numberOfAttempts;
    }
// ALL UNDER THIS LINE IS NOT MARKABLE! continued with JGraphT

    /*
    example of matrix
     */
    private static int[][] N = {
            {0, 900, 800, 700, 600, 500, 400, 300, 200, 100},
            {900, 0, 900, 800, 700, 600, 500, 400, 300, 200},
            {800, 900, 0, 900, 800, 700, 600, 500, 400, 300},
            {700, 800, 900, 0, 900, 800, 700, 600, 500, 400},
            {600, 700, 800, 900, 0, 900, 800, 700, 600, 500},
            {500, 600, 700, 800, 900, 0, 900, 800, 700, 600},
            {400, 500, 600, 700, 800, 900, 0, 900, 800, 700},
            {300, 400, 500, 600, 700, 800, 900, 0, 900, 800},
            {200, 300, 400, 500, 600, 700, 800, 900, 0, 900},
            {100, 200, 300, 400, 500, 600, 700, 800, 900, 0}
    };
    private static int[][] A = new int[10][10];
    private static int[][] C = new int[10][10];

    /*
    A try to make A generator without lib
     */
    private void generateA(Graph graph) {
        boolean[] visited = new boolean[10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == j) continue;
                Graph.BFS(j);
                int val = N[i][j];
                A[i][j] += val;
                A[j][i] += val;
            }
        }
    }


    /*
     *
     * petersen's graph
     */
    public static void S2() {
        int V = 10;
        Graph graph = new Graph(V);
        graph.addEdge(0, 1, 0.95);
        graph.addEdge(0, 2, 0.95);
        graph.addEdge(0, 8, 0.95);
        graph.addEdge(1, 7, 0.95);
        graph.addEdge(1, 5, 0.95);
        graph.addEdge(2, 3, 0.95);
        graph.addEdge(2, 4, 0.95);
        graph.addEdge(3, 9, 0.95);
        graph.addEdge(3, 7, 0.95);
        graph.addEdge(4, 5, 0.95);
        graph.addEdge(4, 6, 0.95);
        graph.addEdge(6, 7, 0.95);
        graph.addEdge(6, 8, 0.95);
        graph.addEdge(8, 9, 0.95);
        graph.addEdge(5, 9, 0.95);
        // graph.BFS(2);
        //  System.out.println();
        //  graph.BFS2(2);
    }


}
