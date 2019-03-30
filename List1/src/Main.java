import networktechnology.L2.Net.Graph.Graph;

import static networktechnology.L2.Net.Graph.Graph.counter2;

public class Main {


    public static void main(String[] args) {
        double counter = 0;
        int V= 21;
        double numberOfAttempts = 10000;

        for (int j = 0; j < numberOfAttempts; j++) {
            Graph g = new Graph(V);
            for (int i = 0;i<V-1; i++) {
                g.addEdge(i, i + 1, 0.95);
            }
            g.IntervalTest();
            System.out.println("**");
            if (g.isConnected()) counter++;

        }

        double probability =   counter2 / numberOfAttempts;
        System.out.println(probability);
    }
}