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
import static org.junit.Assert.assertThat;

public class PuzzleTest {

    @Test
    public void test_puzzleMain() {

        String input =
                "6\n" +
                "1 2 3\n" +
                "4 5 6\n" +
                "7 8 0\n" +
                "1 2 3\n" +
                "7 0 4\n" +
                "8 6 5\n" +
                "4 2 6\n" +
                "3 1 5\n" +
                "7 0 8\n" +
                "3 8 7\n" +
                "6 0 5\n" +
                "1 2 4\n" +
                "8 4 0\n" +
                "5 7 6\n" +
                "2 3 1\n" +
                "3 1 7\n" +
                "6 8 4\n" +
                "5 2 0\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream in;
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        puzzle.main(null);
        String[] out = outContent.toString().split("\n");

        assertThat(out[0].trim(), is("0"));
        assertThat(out[1].trim(), is("8"));
        assertThat(out[2].trim(), is("17"));
        assertThat(out[3].trim(), is("26"));
        assertThat(out[4].trim(), is("26"));
        assertThat(out[5].trim(), is("28"));
    }

    @Test(timeout = 3000)
    public void test_puzzleMainFull() {

        String inputPath = "C:\\Users\\rvzxj\\Workspace\\cs-coursework\\COP4516\\H04-0204-contest\\src\\puzzle.in";
        String outputPath = "C:\\Users\\rvzxj\\Workspace\\cs-coursework\\COP4516\\H04-0204-contest\\src\\puzzle.out";

        StringBuilder sb = new StringBuilder();
        Path path = Paths.get(inputPath);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> sb.append(s + "\n"));
        } catch (IOException ex) { }

        StringBuilder sb2 = new StringBuilder();
        path = Paths.get(outputPath);
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

        puzzle.main(null);

        String[] cases = input.split("\n");
        String[] actual = outContent.toString().split("\n");
        String[] expected = output.toString().split("\n");

        for (int i = 0; i < actual.length; i++) {
            actual[i] = actual[i].trim();
            expected[i] = expected[i].trim();
        }

        assertThat(actual, is(expected));
    }
}
