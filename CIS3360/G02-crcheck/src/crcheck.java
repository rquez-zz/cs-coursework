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
    public static Byte[][] read(Reader reader) throws IOException {

        ArrayList<Byte[]> lines = new ArrayList<Byte[]>();
        ArrayList<Byte> line = new ArrayList<Byte>();

        // Parse file
        for (byte b = (byte)reader.read(); b != -1; b = (byte)reader.read()) {
            // Check for Non ASCII Character
            if (b > 128 || b < 0) {
                throw new IOException("Input file can only have ASCI characters.");
            }
            // Break the line after 64 characters
            if (line.size() == 64) {
                lines.add(line.toArray(new Byte[line.size()]));
                line = new ArrayList<Byte>();
            }
            // Add Character if not a newline
            if (b != '\n') {
                line.add(b);
            }
        }

        // Pad the input
        while(lines.size() < 8) {
            if (lines.size() == 7) {
                while (line.size() < 56) {
                    line.add(new Byte((byte) '.'));
                }
            } else {
                while (line.size() < 64) {
                    line.add(new Byte((byte) '.'));
                }
            }
            lines.add(line.toArray(new Byte[line.size()]));
            line = new ArrayList<Byte>();
        }

        // Unwrap array list
        Byte[][] input = new Byte[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            input[i] = lines.get(i);
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
