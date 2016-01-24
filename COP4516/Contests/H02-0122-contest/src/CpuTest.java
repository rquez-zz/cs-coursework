import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;

public class CpuTest {

    @Test
    public void test_cpuMain() {

        String input =
                "2\n" +
                "3\n" +
                "0 1 2\n" +
                "1 0 4\n" +
                "2 4 0\n" +
                "5\n" +
                "0 3 2 4 1\n" +
                "3 0 1 2 5\n" +
                "2 1 0 7 1\n" +
                "4 2 7 0 3\n" +
                "1 5 1 3 0 ";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream in;
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        cpu.main(null);
        String[] out = outContent.toString().split("\n");

        assertEquals("Design 1: 3 micrometers", out[0].trim());
        assertEquals("Design 2: 5 micrometers", out[1].trim());
    }
}
