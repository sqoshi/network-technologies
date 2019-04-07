package pl.sp.kursjava;

import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Simulation2 {
    private static final Random random = new Random();
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

    void perform(){
        SimpleWeightedGraph<Integer, DefaultWeightedEdge> net = createMyNetwork();
        generateA(net);
        generateC();
        //System.out.println(calculateAverageDelay(net));
        System.out.print(networkCheck(net));
    }

    private void generateC() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i != j) {
                    C[i][j] = 2 * A[i][j] * 1500;
                }
            }
        }
    }

    private void generateA(SimpleWeightedGraph<Integer, DefaultWeightedEdge> net) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == j) continue;
                List<DefaultWeightedEdge> sp = DijkstraShortestPath.findPathBetween(net, i, j);
                int val = N[i][j];
                for (DefaultWeightedEdge e: sp) {
                    int y = Integer.parseInt(Character.toString((e.toString().charAt(1))));
                    int x = Integer.parseInt(Character.toString((e.toString().charAt(5))));

                    A[y][x] += val;
                    A[x][y] += val;
                }
            }
        }
    }

    private double calculateAverageDelay(SimpleWeightedGraph<Integer, DefaultWeightedEdge> net){
        double T;
        double m = 1500.00;
        double G = calculateSumN();
        double sumE = 0;

        ArrayList<DefaultWeightedEdge> edges = new ArrayList<>();
        edges.addAll(net.edgeSet());

        for (DefaultWeightedEdge e : edges) {
            int y = Integer.parseInt(Character.toString((e.toString().charAt(1))));
            int x = Integer.parseInt(Character.toString((e.toString().charAt(5))));

            sumE += ((A[y][x]) / (((C[y][x])/(m)) -  A[y][x]));
        }

        T = 1/G * sumE;
        return T;
    }

    private int calculateSumN() {
        int sumN = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                sumN += N[i][j];
            }
        }
        return sumN;
    }

    private SimpleWeightedGraph<Integer, DefaultWeightedEdge> createMyNetwork() {
        SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        for (int i = 0; i < 10; i++) {
            graph.addVertex(i);
            if(i > 0)
                graph.setEdgeWeight(graph.addEdge(i-1 , i), 0.95);
        }
        graph.setEdgeWeight(graph.addEdge(9, 6), 0.95);
        graph.setEdgeWeight(graph.addEdge(9, 7), 0.95);
        graph.setEdgeWeight(graph.addEdge(9, 4), 0.95);
        graph.setEdgeWeight(graph.addEdge(6, 0), 0.95);
        graph.setEdgeWeight(graph.addEdge(0, 2), 0.95);
        graph.setEdgeWeight(graph.addEdge(4, 2), 0.95);
        graph.setEdgeWeight(graph.addEdge(1, 3), 0.95);

        return graph;
    }

    private void printMatrix(int[][] matrix){
        for (int i = 0; i<10; i++){
            for (int j = 0; j<10; j++){
                if(matrix[i][j] / 100 > 0) System.out.print(matrix[i][j] + " ");
                else if(matrix[i][j] / 10 > 0) System.out.print("0" + matrix[i][j] + " ");
                else System.out.print("00" + matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private double networkCheck(SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph){
        int counter = 0;
        final int numberOfAttempts = 10000;
        double T_max = 0.16;

        for(int i = 0;  i < numberOfAttempts; i++){
            SimpleWeightedGraph<Integer, DefaultWeightedEdge> graphToCheck = (SimpleWeightedGraph<Integer, DefaultWeightedEdge>) graph.clone();//GraphCloner.deepCloneGraph(graph);
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
            if(inspector.isConnected()){
                if(calculateAverageDelay(graph) < T_max){
                    counter++;
                }
            }
        }

        return (double) counter / numberOfAttempts;
    }
}
