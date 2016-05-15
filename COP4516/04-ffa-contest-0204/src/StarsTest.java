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
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class StarsTest {

    @Test
    public void test_starsMain() {

        String input =
                "14\n" +
                "5 4\n" +
                "1 2\n" +
                "1 3\n" +
                "2 3\n" +
                "4 5\n" +
                "8 5\n" +
                "1 2\n" +
                "3 4\n" +
                "6 7\n" +
                "6 8\n" +
                "8 7\n" +
                "4 0\n" +
                "4 1\n" +
                "1 2\n" +
                "4 3\n" +
                "1 2\n" +
                "2 4\n" +
                "2 3\n" +
                "4 4\n" +
                "1 2\n" +
                "2 4\n" +
                "2 3\n" +
                "3 4\n" +
                "4 4\n" +
                "1 2\n" +
                "2 3\n" +
                "3 4\n" +
                "4 1\n" +
                "13 11\n" +
                "1 2\n" +
                "2 4\n" +
                "2 7\n" +
                "10 9\n" +
                "3 5\n" +
                "11 9\n" +
                "3 9\n" +
                "1 3\n" +
                "8 7\n" +
                "13 6\n" +
                "4 6\n" +
                "13 12\n" +
                "1 2\n" +
                "2 4\n" +
                "2 7\n" +
                "10 9\n" +
                "3 5\n" +
                "11 9\n" +
                "3 9\n" +
                "1 3\n" +
                "8 5\n" +
                "8 7\n" +
                "13 6\n" +
                "4 6\n" +
                "11 8\n" +
                "1 2\n" +
                "3 4\n" +
                "5 6\n" +
                "7 8\n" +
                "3 5\n" +
                "6 7\n" +
                "10 11\n" +
                "1 3\n" +
                "60 9\n" +
                "1 2\n" +
                "3 4\n" +
                "5 6\n" +
                "7 8\n" +
                "3 5\n" +
                "6 7\n" +
                "10 11\n" +
                "1 3\n" +
                "10 44\n" +
                "1000 11\n" +
                "1 2\n" +
                "3 4\n" +
                "5 6\n" +
                "7 8\n" +
                "3 5\n" +
                "6 7\n" +
                "10 11\n" +
                "1 3\n" +
                "10 44\n" +
                "999 1\n" +
                "9 54\n" +
                "1000 1\n" +
                "1000 4\n" +
                "0 0\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream in;
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        stars.main(null);
        String[] out = outContent.toString().split("\n\r\n");

        assertThat(out[0].trim(), is("Night sky #1: 2 constellations, of which 1 need to be fixed."));
        assertThat(out[1].trim(), is("Night sky #2: 3 constellations, of which 1 need to be fixed."));
        assertThat(out[2].trim(), is("Night sky #3: 0 constellations, of which 0 need to be fixed."));
        assertThat(out[3].trim(), is("Night sky #4: 1 constellations, of which 0 need to be fixed."));
        assertThat(out[4].trim(), is("Night sky #5: 1 constellations, of which 0 need to be fixed."));
        assertThat(out[5].trim(), is("Night sky #6: 1 constellations, of which 1 need to be fixed."));
        assertThat(out[6].trim(), is("Night sky #7: 1 constellations, of which 1 need to be fixed."));
        assertThat(out[7].trim(), is("Night sky #8: 1 constellations, of which 0 need to be fixed."));
        assertThat(out[8].trim(), is("Night sky #9: 1 constellations, of which 1 need to be fixed."));
        assertThat(out[9].trim(), is("Night sky #10: 2 constellations, of which 0 need to be fixed."));
        assertThat(out[10].trim(), is("Night sky #11: 2 constellations, of which 0 need to be fixed."));
        assertThat(out[11].trim(), is("Night sky #12: 3 constellations, of which 0 need to be fixed."));
        assertThat(out[12].trim(), is("Night sky #13: 1 constellations, of which 0 need to be fixed."));
        assertThat(out[13].trim(), is("Night sky #14: 0 constellations, of which 0 need to be fixed."));
    }

    @Test
    public void test_starsMainFull() {

        String inputPath = "C:\\Users\\rvzxj\\Workspace\\cs-coursework\\COP4516\\H04-0204-contest\\src\\stars.in";
        String outputPath = "C:\\Users\\rvzxj\\Workspace\\cs-coursework\\COP4516\\H04-0204-contest\\src\\stars.out";

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

        stars.main(null);

        String[] cases = input.split("\n");
        String[] actual = outContent.toString().split("\n\r\n");
        String[] expected = output.toString().split("\n\n");

        List<String> tempActual = new ArrayList<>(Arrays.asList(actual));
        tempActual.forEach(e -> { tempActual.set(tempActual.indexOf(e), e.trim()); });
        actual = tempActual.toArray(new String[tempActual.size()]);

        List<String> tempExpected = new ArrayList<>(Arrays.asList(expected));
        tempExpected.forEach(e -> { tempExpected.set(tempExpected.indexOf(e), e.trim()); });
        expected = tempExpected.toArray(new String[tempActual.size()]);

        assertArrayEquals(expected, actual);
    }
}
