import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;

public class ConferenceTest {

    @Test
    public void test_conferenceMain() {

        String input = "5\n" +
                "1\n" +
                "15 1\n" +
                "2\n" +
                "0 30\n" +
                "5 25\n" +
                "2\n" +
                "0 1\n" +
                "1 29\n" +
                "2\n" +
                "20 2\n" +
                "25 2\n" +
                "5\n" +
                "0 1\n" +
                "20 10\n" +
                "5 18\n" +
                "7 11\n" +
                "10 10\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream in;
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        conference.main(null);
        String[] out = outContent.toString().split("\n");

        assertEquals("16384", out[0].trim());
        assertEquals("1073741823",out[1].trim());
        assertEquals("1073741823",out[2].trim());
        assertEquals("792",out[3].trim());
    }

    @Test
    public void test_conferenceMain2() {

        String input = "1\n" +
                "5\n" +
                "0 1\n" +
                "20 10\n" +
                "5 18\n" +
                "7 11\n" +
                "10 10\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream in;
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        conference.main(null);
        String[] out = outContent.toString().split("\n");

        assertEquals("570425216",out[0].trim());
    }
}
