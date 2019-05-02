import java.io.FileNotFoundException;
import java.io.IOException;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Cable c = new Cable();
        Host[] hosts = new Host[5];
        for (int i = 0; i < hosts.length; i++) {
            hosts[i] = new Host(c);
            hosts[i].start();
        }

        int threads = hosts.length;
    }

}