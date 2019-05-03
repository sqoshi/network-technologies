import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Cable {

    List<String> data = new ArrayList();
    int succ = 0;

    /**
     * Returns wether data can be transmitted through the cable or not
     * @return can transmit boolean
     */
    boolean canTransmit() throws InterruptedException, IOException{

        int size = this.data.size();
        Thread.sleep(1000);
        return (size == this.data.size()) ? true : false;

    }

    /**
     * Adds new line to the data list
     * @param s
     */
    void add(String s) {
        this.data.add(s);
        System.out.println(s);
    }
}