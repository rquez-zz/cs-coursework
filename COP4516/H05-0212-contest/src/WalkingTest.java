import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;

public class WalkingTest {
    @Test
    public void test_walkingMain() {

        String input =
                "1\n" +
                        "4\n" +
                        "30\n" +
                        "45\n" +
                        "20\n" +
                        "55\n" +
                        "4\n" +
                        "(1, 3)\n" +
                        "(2, 4)\n" +
                        "(4, 1)\n" +
                        "(3, 2)\n" +
                        "4\n" +
                        "1\n" +
                        "2\n" +
                        "3\n" +
                        "4\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream in;
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        walking.main(null);
        String[] out = outContent.toString().split("\n");

        assertThat(out[0].trim(), is("The least amount of work on trip 1 is: 95"));
    }

    @Test
    public void test_walkingMainFull() {

        String inputPath = "E:\\Workspace\\cs-coursework\\COP4516\\H05-0212-contest\\src\\walking.in";
        String outputPath = "E:\\Workspace\\cs-coursework\\COP4516\\H05-0212-contest\\src\\walking.out";

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

        walking.main(null);

        String[] cases = input.split("\n");
        String[] actual = outContent.toString().split("\r\n");
        String[] expected = output.toString().split("\n");

        List<String> tempActual = new ArrayList<>(Arrays.asList(actual));
        tempActual.forEach(e -> { tempActual.set(tempActual.indexOf(e), e.trim()); });
        actual = tempActual.toArray(new String[tempActual.size()]);

        List<String> tempExpected = new ArrayList<>(Arrays.asList(expected));
        tempExpected.forEach(e -> { tempExpected.set(tempExpected.indexOf(e), e.trim()); });
        expected = tempExpected.toArray(new String[tempActual.size()]);

        assertArrayEquals(expected, actual);
    }
}
