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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertArrayEquals;

public class DuelTest {

    @Test
    public void test_duelMain() {

        String input =
                "5 4\n" +
                        "1 5\n" +
                        "5 2\n" +
                        "3 2\n" +
                        "4 3\n" +
                        "5 4\n" +
                        "3 1\n" +
                        "4 2\n" +
                        "1 5\n" +
                        "5 4\n" +
                        "2 2\n" +
                        "1 2\n" +
                        "2 1\n" +
                        "4 3\n" +
                        "2 2\n" +
                        "3 4\n" +
                        "2 1\n" +
                        "0 0\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream in;
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        duel.main(null);
        String[] out = outContent.toString().split("\n");

        assertThat(out[0].trim(), is("2"));
        assertThat(out[1].trim(), is("1"));
        assertThat(out[2].trim(), is("0"));
        assertThat(out[3].trim(), is("0"));
    }

    @Test
    public void test_duelMainFull() {

        String inputPath = "E:\\Workspace\\cs-coursework\\COP4516\\H05-0212-contest\\src\\duel.in";
        String outputPath = "E:\\Workspace\\cs-coursework\\COP4516\\H05-0212-contest\\src\\duel.out";

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

        duel.main(null);

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
