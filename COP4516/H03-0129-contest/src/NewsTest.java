import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class NewsTest {
    @Test
    public void test_newsMain() {


        String input =
                "5\n" +
                "3\n" +
                "0 0\n" +
                "5\n" +
                "0 0 2 2\n" +
                "9\n" +
                "0 0 1 1 2 2 3 4\n" +
                "5\n" +
                "0 1 2 3\n" +
                "7\n" +
                "0 1 2 3 3 3\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream in;
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        news.main(null);
        String[] out = outContent.toString().split("\n");

        assertThat(out[0].trim(), is("2"));
        assertThat(out[1].trim(), is("3"));
        assertThat(out[2].trim(), is("4"));
        assertThat(out[3].trim(), is("4"));
        assertThat(out[4].trim(), is("6"));
    }

    @Test
    public void test_newsMainFull() {

        StringBuilder sb = new StringBuilder();
        Path path = Paths.get("E:\\Workspace\\cs-coursework\\COP4516\\Contests\\H03-0129-contest\\src\\news.in");
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> sb.append(s + "\n"));
        } catch (IOException ex) { }

        StringBuilder sb2 = new StringBuilder();
        path = Paths.get("E:\\Workspace\\cs-coursework\\COP4516\\Contests\\H03-0129-contest\\src\\news.out");
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

        news.main(null);

        String[] cases = input.split("\n");
        String[] actual = outContent.toString().split("\n");
        String[] expected = output.toString().split("\n");

        for (int i = 0; i < actual.length; i++) {
            actual[i] = actual[i].trim();
            expected[i] = expected[i].trim();
        }

        assertArrayEquals(expected, actual);
    }
}
