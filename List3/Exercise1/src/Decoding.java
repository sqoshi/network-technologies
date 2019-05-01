import java.io.*;

/**
 * @author kossa
 */
public class Decoding {

    public static void main(String[] args) throws IOException {

        String data = "";
        String piece;

        File inputFile = new File("/home/piotr/IdeaProjects/TS/List3/Exercise1/src/W.txt");
        File outputFile = new File("/home/piotr/IdeaProjects/TS/List3/Exercise1/src/Z1.txt");

        try {
            // Wczytuję dane wejściowe
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            //wczytaj całą zawartość inputFile do zmiennej data
            while ((piece = reader.readLine()) != null) {
                data += piece;

            }

            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Unable to read from input file. No file or no read permission!");
            System.exit(0);
        }


        //sprawdzaj czy wczytano dane

        if (data.isEmpty()) {
            System.out.println("No data in input file! Application will be terminated!");
            System.exit(0);
        }

        // dekoduj dane
        System.out.print(data);
        String output = Framing.decode(data);

        // zapisz do pliku
        try {
            outputFile.delete();
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(output);
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("Unable to find the output file. No file or no write permission!");
            System.exit(0);
        }


    }
}