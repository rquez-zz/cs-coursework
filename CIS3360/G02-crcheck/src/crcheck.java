import java.io.*;
import java.util.ArrayList;

/**
 * Created by Ricardo Vasquez on 11/19/2015.
 * Program 2: CRC Codes
 */
public class crcheck {

    private static final int divisor = 0b1010000001010011;

    /**
     * Reads from the reader the characters from the input as 8 lines of 64 characters.
     *
     * @param reader
     * @return
     * @throws IOException
     */
    public static char[][] read(Reader reader) throws IOException {
        char[][] input = new char[8][64];

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                input[i][j] = (char) reader.read();
            }
        }

        return input;
    }

    static int calculate() {

        return 0;
    }

    static boolean verify(int crc) {

        return false;
    }

    public static void main(String args[]) {

        String flag = args[0];
        String inputPath = args[1];
        char[][] input;

        try (Reader reader = new BufferedReader(new FileReader(inputPath))) {
            input = read(reader);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        for (char[] c : input) {
            System.out.println(new String(c));
        }

    }
}
