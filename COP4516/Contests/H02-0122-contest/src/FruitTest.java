import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class FruitTest {

    @Test
    public void test_fruitMain() {

        String input = "6\n" +
                "3 3 4 5\n" +
                "7 10 10 10 10 10 10 10\n" +
                "4 4 7 1 5\n" +
                "4 4 7 1 8\n" +
                "4 0 0 0 0\n" +
                "7 0 0 0 1000 0 0 0";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream in;
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        fruit.main(null);
        String[] out = outContent.toString().split("\n");

        assertEquals("4 1", out[0].trim());
        assertEquals("10 0",out[1].trim());
        assertEquals("6 7",out[2].trim());
        assertEquals("6 6",out[3].trim());
        assertEquals("0 0",out[4].trim());
        assertEquals("250 750",out[5].trim());
    }
}
