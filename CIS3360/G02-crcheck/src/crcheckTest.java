import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ricardo on 11/19/2015.
 */
public class crcheckTest {

    private BufferedReader reader;

    @Test
    public void testRead() throws Exception {

        BufferedReader readerTest1 = new BufferedReader(new FileReader(crcheckTest.class.getResource("test.raw").getFile()));
        Byte[][] readinput = crcheck.read(readerTest1);

        Byte[][] lines = new Byte[][]{

            toObjects("abcdefghijklmnopqrstuvwxyz12345-ABCDEFGHIJKLMNOPQRSTUVWXYZ12345a".getBytes()),
            toObjects("bcdefghijklmnopqrstuvwxyz12345-ABCDEFGHIJKLMNOPQRSTUVWXYZ12345ab".getBytes()),
            toObjects("cdefghijklmnopqrstuvwxyz12345-ABCDEFGHIJKLMNOPQRSTUVWXYZ12345abc".getBytes()),
            toObjects("defghijklmnopqrstuvwxyz12345-ABCDEFGHIJKLMNOPQRSTUVWXYZ12345abcd".getBytes()),
            toObjects("efghijklmnopqrstuvwxyz12345-ABCDEFGHIJKLMNOPQRSTUVWXYZ12345abcde".getBytes()),
            toObjects("fghijklmnopqrstuvwxyz12345-ABCDEFGHIJKLMNOPQRSTUVWXYZ12345......".getBytes()),
            toObjects("................................................................".getBytes()),
            toObjects("........................................................".getBytes())

        };

        assertArrayEquals(lines, readinput);

        BufferedReader readerTest2 = new BufferedReader(new FileReader(crcheckTest.class.getResource("in2.raw").getFile()));
        readinput = crcheck.read(readerTest2);
        crcheck.outputFileToCalculate(readinput);

        BufferedReader readerTest3 = new BufferedReader(new FileReader(crcheckTest.class.getResource("WC.raw").getFile()));
        readinput = crcheck.read(readerTest3);
        crcheck.outputFileToCalculate(readinput);

        BufferedReader readerTest4 = new BufferedReader(new FileReader(crcheckTest.class.getResource("WS.raw").getFile()));
        readinput = crcheck.read(readerTest4);
        crcheck.outputFileToCalculate(readinput);
    }
    @Test
    public void testCalculate() throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader(crcheckTest.class.getResource("test.raw").getFile()));
        Byte[][] readinput = crcheck.read(reader);

        String[] crcsExpected = new String[] {
                "00001a6a",
                "00007d1d",
                "00001fdf",
                "00000d90",
                "0000259f",
                "00003ef2",
                "000057ad",
                "000075dc"
        };
        String[] crcs = crcheck.calculate(readinput);

        readinput[7] = crcheck.getFinalLine(crcs, readinput);
        assertArrayEquals(crcs, crcsExpected);
        assertTrue(crcheck.verify(readinput));

    }
    @Test
    public void testVerify() throws  Exception {

        BufferedReader readerTest = new BufferedReader(new FileReader(crcheckTest.class.getResource("in2.crc").getFile()));
        Byte[][] readinput = crcheck.read(readerTest);
        for (Byte[] ba : readinput) {
            for (Byte b : ba) {
                System.out.print((char)b.byteValue());
            }
            System.out.println();
        }

        assertTrue(!crcheck.verify(readinput));
    }

    Byte[] toObjects(byte[] bytesPrim) {
        Byte[] bytes = new Byte[bytesPrim.length];

        int i = 0;
        for (byte b : bytesPrim) bytes[i++] = b;

        return bytes;
    }
}
