import networktechnology.L2.Net.Graph.Graph;

public class Main {


    public static void main(String[] args) {
        double counter = 1;
        double numberOfAttempts = 100000;

        for (int j = 0; j < numberOfAttempts; j++) {
            Graph g = new Graph(21);
            for (int i = 0; i < 20; i++) {
                g.addEdge(i, i + 1, 0.95);
            }
            g.IntervalTest();
            if (g.isConnected()) counter++;

        }

        double probability =  counter / numberOfAttempts;
        System.out.println(probability);
    }
}