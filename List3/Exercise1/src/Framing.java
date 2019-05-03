public class Framing {

    /**
     * Codes the input data and returns it in a string
     *
     * @param input:     String
     * @param frameSize: int - size of the frame (in bits)
     * @return coded data: String
     */
    public static String code(String input, int frameSize) {

        StringBuilder data = new StringBuilder();
        String line;
        String crc;
        String flag = "01111110";

        // a loop - dividing the input into frameSize pieces
        int i = 0;

        System.out.println("Downloaded " + input.length() + " bits of data.");

        while (i < input.length()) {
            // the last line doesn't have to have frameSize length
            if (input.length() - i > frameSize)
                line = input.substring(i, i + frameSize);
            else
                line = input.substring(i);

            System.out.print("Data: " + line + " ");

            crc = checkSum_CRC8(line);

            System.out.print("CRC8: " + crc + " ");

            // append crc to line's end
            line += crc;

            // make bit stuffing
            line = line.replace("11111", "111110");

            // add flags
            line = flag + line + flag;

            i += frameSize;
            data.append(line);
            data.append("\n");
            System.out.println("Stuffed frame: " + line + " ");
        }
        return data.toString();
    }


    static String decode(String input) {

        String flag = "01111110";
        StringBuilder output = new StringBuilder();

        input = input.replace(flag + flag, " ");
        input = input.replace(flag, "");

        String[] data = input.split(" ");

        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("111110", "11111");
        }
        int i=0;
        for (String s : data) {
            String crc = s.substring(s.length() - 8);
            String frame = s.substring(0, s.length() - 8);
            System.out.println("#: " + crc + " " + checkSum_CRC8(frame));
            if (crc.equals(checkSum_CRC8(frame))) {
                System.out.println(i+" Successfully decoded frame " + frame + " size: " + frame.length());
                output.append(frame);
                output.append("\n");
            } else {
                System.out.println(i+"Error while decoding frame " + frame + " size: " + frame.length());
            }
            i++;
        }
        return output.toString();
    }

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
