import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {


        for (String arg : args) {
            if (arg.equals("code")) {
                Coding.main(args);
            } else if (args[0].equals("decode")) {
                Decoding.main(args);
            }
        }
        if (args.length < 1) {
            System.out.println("Please type \"code\" or \"decode\" to invoke expected function.");
        }

    }

}