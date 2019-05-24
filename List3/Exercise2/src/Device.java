import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Device extends Thread {


    private Cable cab;

    Device(Cable c) {
        this.cab = c;
    }

    @Override
    public void run() {
        try {
            while (!cab.canTransmit()) {
                Random r = new Random();
                long x =r.nextInt(5) * 1000;
                Thread.sleep(x);
                System.out.println("Device: "+ this.getId()+" cant transmit now, need to wait: "+x/1000+" s");
            }

            cab.add("Device: " + this.getId() + " START");
            Thread.sleep(500);
            String message = "Data From: " + this.getId() +" Device";
            cab.add(message);
            Thread.sleep(500);
            cab.add("Device: " + this.getId() + " END");

            String l1, l3;
            l3 = cab.data.get(cab.data.size() - 1);
            l1 = cab.data.get(cab.data.size() - 3);

            if (l1.equals("Device: " + this.getId() + " START")
                    &&
                    l3.equals("Device: " + this.getId() + " END")) {
                System.out.println("Device " + this.getId() + " has successfully transmitted data.");
                cab.succ++;
            } else {
                cab.add("\t JAM " + this.getId() + "  Collision!");
            }
        } catch (InterruptedException | NullPointerException ex) {
//            Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);

        }

    }


}