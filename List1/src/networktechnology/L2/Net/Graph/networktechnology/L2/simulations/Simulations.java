package networktechnology.L2.Net.Graph.networktechnology.L2.simulations;

import networktechnology.L2.Net.Graph.Graph;

import java.util.concurrent.ThreadLocalRandom;

public class Simulations {
    static public double S1() {


        double counter = 0;
        int V = 20;
        double numberOfAttempts = 10000;

        for (int j = 0; j < numberOfAttempts; j++) {
            Graph g = new Graph(V);
            g.addEdge(0, 19, 0.95);
            for (int i = 0; i < V - 1; i++) {
                g.addEdge(i, i + 1, 0.95);
            }



            //     g.addEdge(0, 9, 0.8);
            //     g.addEdge(4, 14, 0.7);
            //   for (int m = 0; m < 4; m++) {
            //       int x = ThreadLocalRandom.current().nextInt(0, 19 + 1);
            //       int y = ThreadLocalRandom.current().nextInt(0, 19 + 1);
            //       g.addEdge(x, y, 0.4);
            //   }
            g.IntervalTest();
            if (g.isConnected()) counter++;

        }

        return counter/ numberOfAttempts;
    }


}
