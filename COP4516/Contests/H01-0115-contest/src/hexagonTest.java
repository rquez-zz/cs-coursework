import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertTrue;

public class hexagonTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream systemOut = System.out;
    private ByteArrayInputStream in;

    @Test
    public void testHexagons() {

        String input = "2\n" +
                "3 5 6 1 2 4 5 1 2 3 6 4 2 3 5 4 1 6 3 1 5 6 2 4 5 4 1 3 6 2 4 2 3 1 5 6 3 6 1 2 4 5\n" +
                "6 3 4 1 2 5 6 4 3 2 5 1 6 5 3 2 4 1 5 4 6 3 2 1 2 5 6 1 4 3 4 6 3 5 2 1 1 3 5 2 6 4";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        hexagon.main(null);
        String[] out = outContent.toString().split("\n");
        assertTrue("Case 1: 3 0 5 6 1 4 2 ".equals(out[0]));
        assertTrue("Case 2: No solution".equals(out[1]));
    }
}
