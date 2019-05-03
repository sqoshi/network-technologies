import java.io.FileNotFoundException;
import java.io.IOException;

import static java.lang.Thread.sleep;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        Cable c = new Cable();
        Device[] devices = new Device[5];
        for (int i = 0; i < devices.length; i++) {
            devices[i] = new Device(c);
            devices[i].start();
          //  sleep(5);
        }
    }

}