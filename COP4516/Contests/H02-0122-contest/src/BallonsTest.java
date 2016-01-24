import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class BallonsTest {

    @Test
    public void test_balloonsMain() {

        String input =
                "3 15 35\n" +
                "10 20 10\n" +
                "10 10 30\n" +
                "10 40 10\n" +
                "0 0 0\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream in;
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        balloons.main(null);
        String out = outContent.toString();

        assertEquals("300", out.trim());
    }

    @Test
    public void test_balloonsMain2() {

        String input =
                "4 20 30\n" +
                "5 70 30\n" +
                "15 30 70\n" +
                "15 90 10\n" +
                "10 10 90\n" +
                "0 0 0\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream in;
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        balloons.main(null);
        String out = outContent.toString();

        assertEquals("1050", out.trim());
    }

    @Test
    public void test_balloonsMain3() {

        String input =
                "4 0 0\n" +
                "5 70 30\n" +
                "15 30 70\n" +
                "15 90 10\n" +
                "10 10 90\n" +
                "0 0 0\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream in;
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        balloons.main(null);
        String out = outContent.toString();

        assertEquals("0", out.trim());
    }

    @Test
    public void test_balloonsMain4() {

        String input =
                "4 50 0\n" +
                "5 70 30\n" +
                "15 30 70\n" +
                "15 90 10\n" +
                "10 10 90\n" +
                "0 0 0\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream in;
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        balloons.main(null);
        String out = outContent.toString();

        assertEquals("2250", out.trim());
    }

    @Test
    public void test_balloonsMain5() {

        String input =
                "5 20 30\n" +
                "5 70 30\n" +
                "15 30 70\n" +
                "15 90 10\n" +
                "10 10 90\n" +
                "10 50 50\n" +
                "0 0 0\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream in;
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        balloons.main(null);
        String out = outContent.toString();

        assertEquals("1550", out.trim());
    }

    @Test
    public void test_balloonsMain6() {

        String input =
                "5 20 30\n" +
                "5 70 30\n" +
                "15 30 70\n" +
                "15 90 10\n" +
                "10 10 90\n" +
                "10 50 50\n" +
                "4 50 0\n" +
                "5 70 30\n" +
                "15 30 70\n" +
                "15 90 10\n" +
                "10 10 90\n" +
                "3 15 35\n" +
                "10 20 10\n" +
                "10 10 30\n" +
                "10 40 10\n" +
                "4 20 30\n" +
                "5 70 30\n" +
                "15 30 70\n" +
                "15 90 10\n" +
                "10 10 90\n" +
                "0 0 0\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream in;
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        balloons.main(null);
        String[] out = outContent.toString().split("\n");

        assertEquals("1550", out[0].trim());
        assertEquals("2250", out[1].trim());
        assertEquals("300", out[2].trim());
        assertEquals("1050", out[3].trim());
    }

    @Test
    public void test_balloonsMain7() {

        String input =
                "5 20 30\n" +
                "5 70 30\n" +
                "15 30 70\n" +
                "15 90 10\n" +
                "10 10 90\n" +
                "10 50 50\n" +
                "4 50 0\n" +
                "5 70 30\n" +
                "15 30 70\n" +
                "15 90 10\n" +
                "10 10 90\n" +
                "3 15 35\n" +
                "10 20 10\n" +
                "10 10 30\n" +
                "10 40 10\n" +
                "0 20 30\n" +
                "0 0 0\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream in;
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        balloons.main(null);
        String[] out = outContent.toString().split("\n");

        assertEquals("1550", out[0].trim());
        assertEquals("2250", out[1].trim());
        assertEquals("300", out[2].trim());
        assertEquals("0", out[3].trim());
    }

    @Test
    public void test_balloonsMainFull() {

        StringBuilder sb = new StringBuilder();
        Path path = Paths.get("E:\\Workspace\\cs-coursework\\COP4516\\Contests\\H02-0122-contest\\src\\balloons.in");
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> sb.append(s + "\n"));
        } catch (IOException ex) { }

        StringBuilder sb2 = new StringBuilder();
        path = Paths.get("E:\\Workspace\\cs-coursework\\COP4516\\Contests\\H02-0122-contest\\src\\balloons.out");
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> sb2.append(s + "\n"));
        } catch (IOException ex) { }

        String input = sb.toString();
        String output = sb2.toString();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        balloons.main(null);

        String[] actual = outContent.toString().split("\n");
        String[] expected = output.toString().split("\n");

        for (int i = 0; i < actual.length; i++) {
            actual[i] = actual[i].trim();
            expected[i] = expected[i].trim();
        }


        assertArrayEquals(expected, actual);
    }
}
