public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cable c = new Cable();
        Device[] devices = new Device[5];
        for (int i = 0; i < devices.length; i++) {
            devices[i] = new Device(c);
            devices[i].start();
            //  sleep(5);
        }
    }

}