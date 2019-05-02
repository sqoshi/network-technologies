import java.io.*;

public class Coding {

    public static void main(String[] args) throws IOException {

        StringBuilder data = new StringBuilder();
        String line;

        File inputFile = new File("/home/piotr/IdeaProjects/TS/List3/Exercise1/src/Z.txt");
        File outputFile = new File("/home/piotr/IdeaProjects/TS/List3/Exercise1/src/W.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            while ((line = reader.readLine()) != null) {
                data.append(line);

            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't find Z.txt file");
            System.exit(0);
        }


        if (data.length() == 0) {
            System.out.println("There is no data in Z.txt");
            System.exit(0);
        }

        String output = Framing.code(data.toString(), 32);

        try {
            //outputFile.delete();
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(output);
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("There is no W.txt file");
            System.exit(0);
        }


    }
}

