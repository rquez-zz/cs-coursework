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

    /**
     * Calculate the CRC for each line and returns it as an array of strings with zero padding
     *
     * @param bytes
     * @return
     */
    public static String[] calculate(Byte[][] bytes) {

        int remainder = 0;
        ArrayList<String> list = new ArrayList<String>();

        // Calculate the CRC for each line
        for(int i = 0; i < 8; i++) {

            // Calculate CRC for two bytes at a time
            int crcTemp;
            for(int j = 0; j < bytes[i].length; j+=2) {

                // Merge two bytes
                int mergedBytes = (bytes[i][j] << 8) | bytes[i][j+1];

                // XOR the mergedBytes with the current remainder left shifted by 1
                crcTemp = mergedBytes ^ (remainder << 1);

                // Pad the temp crc value by 15
                crcTemp <<= 15;

                // Calculate CRC for each bit in the merged bytes
                for (int zeros = Integer.numberOfLeadingZeros(crcTemp); zeros <= 16; zeros = Integer.numberOfLeadingZeros(crcTemp)) {

                    // Line up divisor with the current bit
                    int bitCRC = divisor << (16 - zeros);

                    //XOR them to get the CRC result
                    crcTemp ^= bitCRC;

                }

                remainder = crcTemp;
            }

            // Parse CRC has a hex string with padded zeros
            StringBuilder sb = new StringBuilder();
            sb.append(Integer.toHexString(remainder));
            while (sb.length() < 8) {
                sb.insert(0, '0');
            }

            // Add CRC to list
            list.add(sb.toString());
        }

        return list.toArray(new String[list.size()]);
    }

    /**
     * Calculates the CRC of the input and verifies it
     * with the existing CRC at the end of the input
     *
     * @param input
     * @return
     */
    public static boolean verify(Byte[][] input) {

        // Get the CRC from the last row
        byte[] crc = new byte[8];
        Byte[] lastLine = new Byte[56];
        for (int i = 0; i < input[7].length; i++) {
            if (i <= 55)
                lastLine[i] = input[7][i];
            else
                crc[i-56] = input[7][i];
        }
        String hex = new String(crc);

        // Replace the existing final row with a row without the crc
        input[7] = lastLine;

        // Calculate the CRC for each line
        String[] crcArray = calculate(input);

        // Replace final row with real CRC
        input[7] = getFinalLine(crcArray, input);

        outputCRC(crcArray, input);
        System.out.println("\nCRC 16 result : " + crcArray[7]);

        // Verify result
        if (crcArray[7].equals(hex))
            return true;
        else
            return false;
    }

    /**
     * Replaces the final row when calculating CRC with a row
     * that includes the CRC
     *
     * @param crcArray
     * @param input
     * @return
     */
    public static Byte[] getFinalLine(String[] crcArray, Byte[][] input) {

        // Add final CRC to input buffer
        byte[] finalCrc = crcArray[crcArray.length - 1].getBytes();
        Byte[] lastLine = new Byte[64];
        for (int i = 0; i < lastLine.length; i++) {
            if (i >= 56) {
                lastLine[i] = finalCrc[i - 56];
            } else {
                lastLine[i] = input[7][i];
            }
        }
        return lastLine;
    }

    /**
     * Outputs the CRC line calculations
     *
     * @param crcArray
     * @param input
     */
    public static void outputCRC(String[] crcArray, Byte[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (Byte b : input[i]) {
                System.out.print((char)b.byteValue());
            }
            System.out.println(" - " + crcArray[i]);
        }
    }

    public static void outputFileToCalculate(Byte[][] input) {
        System.out.println("CRC16 Input test from file\n");
        for (Byte[] ba : input) {
            for (Byte b : ba) {
                if (b != '.') {
                    System.out.print((char) b.byteValue());
                }
            }
            if (ba[0] == '.')
                break;
            System.out.println();
        }
    }

    public static void outputFileToVerify(Byte[][] input) {
        System.out.println("CRC16 Input test from file\n");
        for (Byte[] ba : input) {
            for (Byte b : ba) {
                System.out.print((char) b.byteValue());
            }
            System.out.println();
        }

    }

    public static void main(String args[]) {

        if (args.length < 2) {
            throw new RuntimeException("Invalid arguments. Flag 'c' or 'v' and file path are needed.");
        }

        char flag = args[0].charAt(0);
        Byte[][] input;

        try (Reader reader = new BufferedReader(new FileReader(args[1]))) {
            input = read(reader);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        if (flag == 'c') {
            outputFileToCalculate(input);
            System.out.println("\nCRC 16 calculation progress:\n");
            String[] crcArray = calculate(input);
            input[7] = getFinalLine(crcArray, input);
            outputCRC(crcArray, input);
            System.out.println("\nCRC 16 result : " + crcArray[7]);
        } else if (flag == 'v') {
            outputFileToVerify(input);
            System.out.println("\nCRC 16 calculation progress:\n");
            if (verify(input)) {
                System.out.println("\nCRC 16 verification passed.");
            } else {
                System.out.println("\nCRC 16 verification failed.");
            }
        } else {
            throw new RuntimeException("Invalid first argument. Must be 'c' or 'v'.");
        }
    }
}
