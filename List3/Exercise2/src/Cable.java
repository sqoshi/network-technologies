import java.io.IOException;
import java.util.ArrayList;

class Cable {

    ArrayList<String> data = new ArrayList<>();
    int succ = 0;

    /**
     * Returns wether data can be transmitted through the cable or not
     * @return can transmit boolean
     */
    boolean canTransmit() throws InterruptedException{

        int size = data.size();
        Thread.sleep(1000);
        return size == data.size();

    }

    void add(String s) {
        data.add(s);
        System.out.println(s);
    }
}