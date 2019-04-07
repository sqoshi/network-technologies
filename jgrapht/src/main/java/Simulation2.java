import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Simulation2 {
    private static final Random random = new Random();
    /*
    Intesity matrix
    N[(i,j)]
     */
    private static int[][] N = {
            {0, 9, 8, 7, 6, 5, 4, 3, 2, 1},
            {9, 0, 9, 8, 7, 6, 5, 4, 3, 2},
            {8, 9, 0, 9, 8, 7, 6, 5, 4, 3},
            {7, 8, 9, 0, 9, 8, 7, 6, 5, 4},
            {6, 7, 8, 9, 0, 9, 8, 7, 6, 5},
            {5, 6, 7, 8, 9, 0, 9, 8, 7, 6},
            {4, 5, 6, 7, 8, 9, 0, 9, 8, 7},
            {3, 4, 5, 6, 7, 8, 9, 0, 9, 8},
            {2, 3, 4, 5, 6, 7, 8, 9, 0, 9},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}
    };
    private static int[][] A = new int[10][10];
    private static int[][] C = new int[10][10];

    public void perform() {
        SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph = MyGraph();
        generateA(graph);
        generateC();
        // Graph.printMatrix(A);
        // Graph.printMatrix(C);
        // Graph.printMatrix(N);
       // System.out.println(calculateAverageDelay(graph));
        System.out.print(networkCheck(graph));
    }

    /*
    1500 is a size of max packet  multiplied by 2 is the max number of packets that can be in router
     */
    private void generateC() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i != j) {
                    C[i][j] = 2 * A[i][j] * 1500;
                }
            }
        }
    }

    /*
    making the matrix of flow based on intesity matrix
     */
    private void generateA(SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == j) continue;
                List<DefaultWeightedEdge> sp = DijkstraShortestPath.findPathBetween(graph, i, j);
                int val = N[i][j];
                for (DefaultWeightedEdge e : sp) {
                    int y = Integer.parseInt(Character.toString((e.toString().charAt(1))));
                    int x = Integer.parseInt(Character.toString((e.toString().charAt(5))));

                    A[y][x] += val;
                    A[x][y] += val;
                }
            }
        }
    }

    private double calculateAverageDelay(SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph) {
        double T;
        double m = 1500.00;
        double G = calculateSumN();
        double sumE = 0;

        ArrayList<DefaultWeightedEdge> edges = new ArrayList<>();
        edges.addAll(graph.edgeSet());

        for (DefaultWeightedEdge e : edges) {
            int y = Integer.parseInt(Character.toString((e.toString().charAt(1))));//{x : y}
            int x = Integer.parseInt(Character.toString((e.toString().charAt(5))));

            sumE += ((A[y][x]) / (((C[y][x]) / (m)) - A[y][x]));//summ after all edgees
        }

        T = 1 / G * sumE;
        return T;
    }

    /*
    summ all elements of N
     */
    private int calculateSumN() {
        int sumN = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                sumN += N[i][j];
            }
        }
        return sumN;
    }

    private SimpleWeightedGraph<Integer, DefaultWeightedEdge> MyGraph() {
        SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph =
                new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        for (int i = 0; i < 10; i++) {
            graph.addVertex(i);
            if (i > 0)
                graph.setEdgeWeight(graph.addEdge(i - 1, i), 0.6);
        }
        graph.setEdgeWeight(graph.addEdge(0, 6), 0.2);
        graph.setEdgeWeight(graph.addEdge(1, 7), 0.2);
        graph.setEdgeWeight(graph.addEdge(2, 8), 0.2);
        graph.setEdgeWeight(graph.addEdge(3, 9), 0.2);
        graph.setEdgeWeight(graph.addEdge(5, 9), 0.2);
        graph.setEdgeWeight(graph.addEdge(0, 9), 0.2);

        return graph;
    }


    private double networkCheck(SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph) {
        int counter = 0;
        final int numberOfAttempts = 10000;
        double T_max = 1.2 *calculateAverageDelay(graph);

        for (int i = 0; i < numberOfAttempts; i++) {
            SimpleWeightedGraph<Integer, DefaultWeightedEdge> graphToCheck = GraphCloner.deepCloneGraph(graph);

            ArrayList<DefaultWeightedEdge> edges = new ArrayList<>();
            edges.addAll(graphToCheck.edgeSet());

            for (DefaultWeightedEdge edge : edges) {
                double reliability = graph.getEdgeWeight(edge);
                double number = random.nextDouble();
                if (number > reliability) {
                    graphToCheck.removeEdge(edge);
                }
            }
            ConnectivityInspector<Integer, DefaultWeightedEdge> inspector = new ConnectivityInspector<>(graphToCheck);
            if (inspector.isGraphConnected()) {
                if (calculateAverageDelay(graph) < T_max) {
                    counter++;
                }
            }
        }

        return (double) counter / numberOfAttempts;
    }


}

