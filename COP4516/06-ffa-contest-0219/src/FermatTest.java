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

import static org.junit.Assert.assertArrayEquals;

public class FermatTest {
    @Test
    public void test_fermatMainFull() {

        String inputPath = "fermat.in";
        String outputPath = "fermat.out";

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

        fermat.main(null);

        String[] cases = input.split("\n");
        String[] actual = outContent.toString().split("\n");
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
