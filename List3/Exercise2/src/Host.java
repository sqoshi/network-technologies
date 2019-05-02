import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Host extends Thread {

    private Cable cab;

    public Host(Cable c) {
        this.cab = c;
    }

    /**
     * Wysyła wiadomość do hosta o numerze h
     */
    @Override
    public void run() {
        try {
            while (!cab.canTransmit()) {
                Random r = new Random();
                Thread.sleep(r.nextInt(5) * 1000);
            }

            cab.add("[BEGIN " + this.getId() + "]");
            Random r = new Random();
            Thread.sleep(500);
            String message = Integer.toString(r.nextInt(100000));
            cab.add(message);
            Thread.sleep(500);
            cab.add("[END " + this.getId() + "]");

            String l1, l3;
            l3 = cab.data.get(cab.data.size() - 1);
            l1 = cab.data.get(cab.data.size() - 3);

            if (l1.equals("[BEGIN " + this.getId() + "]")
                    &&
                    l3.equals("[END " + this.getId() + "]")) {
                System.out.println("Thread " + this.getId() + " has successfully transmitted data.");
                cab.succ++;
            } else {
                cab.add("[JAM " + this.getId() + "] //collision detected");
            }
        } catch (IOException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);

        } catch (InterruptedException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }

        // jeśli się zmienia, to czekam od 1 do 5 sekund

        // jeśli mogę zapisywać, to piszę:
        // [BEGIN nr. wątku]
        // czekam 0,5 s
        // wpisuje losową liczbę (0 - 100000) znak po znaku w taki sposób, aby kolejne litery pojawiały się co 0.5 s
        // wpisuję [END nr. wątku]

        // wczytuję 3 ostatnie linijki pliku
        // jeśli pierwsza i ostatnia są równe moim nagłówkom [BEGIN] i [END] to zatwierdzam transmisję jako udaną
        // jeśli nie, to dopisuję do pliku [JAM nr. wątku]
    }
}

