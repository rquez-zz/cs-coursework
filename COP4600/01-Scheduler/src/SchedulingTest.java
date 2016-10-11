import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

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

public class SchedulingTest {

    @Test
    public void test_firstComeFirstServe() throws Exception {

        String outputPath = "test/set1_processes.out";

        StringBuilder sb2 = new StringBuilder();
        Path path = Paths.get(outputPath);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> sb2.append(s + "\n"));
        } catch (IOException ex) { }

        String output = sb2.toString();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        System.setOut(new PrintStream(outContent));

        Scheduling.main(new String[]{"test/set1_process.in"});

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

    @Test
    public void test_preemptiveShortestJobFirst() throws Exception {

        String outputPath = "test/set3_processes.out";

        StringBuilder sb = new StringBuilder();
        Path path = Paths.get(outputPath);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> sb.append(s + "\n"));
        } catch (IOException ex) { }

        String output = sb.toString();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        System.setOut(new PrintStream(outContent));

        Scheduling.main(new String[]{"test/set3_process.in"});

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

    @Test
    public void test_preemptiveShortestJobFirst2() throws Exception {

        String outputPath = "test/set4_processes.out";

        StringBuilder sb = new StringBuilder();
        Path path = Paths.get(outputPath);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> sb.append(s + "\n"));
        } catch (IOException ex) { }

        String output = sb.toString();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        System.setOut(new PrintStream(outContent));

        Scheduling.main(new String[]{"test/set4_process.in"});

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

    @Test
    public void test_roundRobin() throws Exception {

        String outputPath = "test/set2_processes.out";

        StringBuilder sb = new StringBuilder();
        Path path = Paths.get(outputPath);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> sb.append(s + "\n"));
        } catch (IOException ex) { }

        String output = sb.toString();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        System.setOut(new PrintStream(outContent));

        Scheduling.main(new String[]{"test/set2_process.in"});

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
