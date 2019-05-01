public class Framing {

    /**
     * Codes the input data and returns it in a string
     *
     * @param input:     String
     * @param frameSize: int - size of the frame (in bits)
     * @return coded data: String
     */
    public static String code(String input, int frameSize) {

        String data = "";
        String piece;
        String crc;
        String flag = "01111110";

        // a loop - dividing the input into frameSize pieces
        int i = 0;

        System.out.println("Pobrano " + input.length() + " bitów danych.");

        while (i < input.length()) {
            // the last piece doesn't have to have frameSize length
            if (input.length() - i > frameSize)
                piece = input.substring(i, i + frameSize);
            else
                piece = input.substring(i);

            System.out.print("Data: " + piece + " ");

            // create CRC code
            crc = checkSum_CRC8(piece);

            System.out.print("CRC8: " + crc + " ");

            // append crc to piece's end
            piece += crc;

            // make bit stuffing
            piece = piece.replace("11111", "111110");

            // add flags
            piece = flag + piece + flag;

            i += frameSize;
            data += piece;
            System.out.println("Stuffed frame: " + piece + " ");
        }
        return data;
    }

    /**
     * Decodes the input data and returns it in a string
     *
     * @param input: String
     * @return decoded data: String
     */
    static String decode(String input) {

        String flag = "01111110";
        String output = "";

        //remove all flags with space character
        input = input.replace(flag + flag, " ");
        input = input.replace(flag, "");

        String[] data = input.split(" ");

        // strip from stuffend zeros
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("111110", "11111");
        }

        for (String s : data) {
            String crc = s.substring(s.length() - 8);
            String frame = s.substring(0, s.length() - 8);
            System.out.println("#: " + crc + " " + checkSum_CRC8(frame));
            if (crc.equals(checkSum_CRC8(frame))) {
                System.out.println("Successfully decoded frame " + frame + " size: " + frame.length());
                output += frame;
            } else {
                System.out.println("Error while decoding frame " + frame + " size: " + frame.length());
            }
        }
        return output;
    }

    /**
     * Cyclic Redundancy Check
     *
     * @param input: string - Binary input data
     * @return: string - binary CRC
     */
    private static String checkSum_CRC8(String input) {
        StringBuilder frame = new StringBuilder();
        frame.append(input);
        String generator = "11000011";

        frame.append("00000000");

        for (int i = 0; i < frame.length() - 8; i++) {

            String s = frame.substring(i, i + 8); // skopiuj kawałek do xorowania
            StringBuilder o = new StringBuilder(); // tu będzie wynik ksorowania

            // jeśli kawałek zaczyna się od 1
            if (s.startsWith("1")) {

                for (int j = 0; j < generator.length(); j++) {
                    //ksoruj po wszystkich bitach
                    o.append(XOR(s.charAt(j), generator.charAt(j)));
                }

                frame.replace(i, i + 8, o.toString());  // to go wytnij
            }
        }


        //Zwracamy ostatnie 8 znaków (w ten sposób usuwamy zera z przodu)
        return frame.toString().substring(frame.length() - 8);
    }


    private static String XOR(char a, char b) {
        return a == b ? "0" : "1";
    }

}
