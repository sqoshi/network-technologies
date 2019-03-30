package networktechnology.L2.Net.Graph.networktechnology.L2.simulations;

import networktechnology.L2.Net.Graph.Graph;

public class Simulations {
    static public double S1() {


        double counter = 0;
        int V = 20;
        double numberOfAttempts = 10000;

        for (int j = 0; j < numberOfAttempts; j++) {
            Graph g = new Graph(V);
            for (int i = 0; i < V - 1; i++) {
                g.addEdge(i, i + 1, 0.95);
            }
            g.IntervalTest();
            if (g.isConnected()) counter++;

        }

        return counter / numberOfAttempts;
    }
}
