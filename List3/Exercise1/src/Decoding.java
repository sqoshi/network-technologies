import java.io.*;

/**
 * @author kossa
 */
public class Decoding {

    public static void main(String[] args) throws IOException {

        String data = "";
        String line;

        File inputFile = new File("/home/piotr/IdeaProjects/TS/List3/Exercise1/src/W.txt");
        File outputFile = new File("/home/piotr/IdeaProjects/TS/List3/Exercise1/src/Z1.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            while ((line = reader.readLine()) != null) {
                data += line;

            }

            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("W.txt foes not exist");
            System.exit(0);
        }


        //sprawdzaj czy wczytano dane

        if (data.isEmpty()) {
            System.out.println("There isn't any data in W.txt");
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
            System.out.println("Can't find Z1.txt file");
            System.exit(0);
        }


    }
}