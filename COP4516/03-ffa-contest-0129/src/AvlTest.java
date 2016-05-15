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

public class AvlTest {

    @Test
    public void test_avlMain() {

        String input =
                "33\n" +
                "3 2 1 3\n" + // 1
                "3 1 2 3\n" + // 2
                "3 3 2 1\n" + // 3
                "3 1 3 2\n" + // 4
                "3 3 1 2\n" + // 5
                "4 1 2 3 4\n" + // 6
                "4 2 1 3 4\n" + // 7
                "4 2 3 1 4\n" + // 8
                "4 2 1 4 3\n" + // 9
                "4 2 4 1 4\n" + // 10
                "4 3 1 2 4\n" + // 11
                "4 3 1 4 2\n" + // 12
                "4 3 2 1 4\n" + // 13
                "4 3 2 4 1\n" + // 14
                "4 1 3 2 4\n" + // 15
                "4 1 4 3 2\n" + // 16
                "4 1 2 4 3\n" + // 17
                "4 1 2 4 3\n" + // 18
                "4 4 1 2 3\n" + // 19
                "4 4 2 1 3\n" + // 20
                "4 4 2 3 1\n" + // 21
                "4 4 3 2 1\n" + // 22
                "4 4 1 2 3\n" + // 23
                "4 4 1 3 2\n" + // 24
                "4 4 3 1 2\n" + // 25
                "4 2 1 3 4\n" + // 26
                "4 2 3 4 1\n" + // 27
                "5 1 2 3 4 5\n" + // 28
                "5 1 2 3 5 4\n" + // 29
                "5 1 2 4 3 5\n" + // 30
                "5 1 2 5 3 4\n" + // 31
                "5 1 3 2 4 5\n" + // 32
                "5 3 1 4 2 5\n"; // 33

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream in;
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        avl.main(null);
        String[] out = outContent.toString().split("\n");

        assertThat(out[0], is("Tree #1: KEEP"));
        assertThat(out[1], is("Tree #2: REMOVE"));
        assertThat(out[2], is("Tree #3: REMOVE"));
        assertThat(out[3], is("Tree #4: REMOVE"));
        assertThat(out[4], is("Tree #5: REMOVE"));
        assertThat(out[5], is("Tree #6: REMOVE"));
        assertThat(out[6], is("Tree #7: KEEP"));
        assertThat(out[7], is("Tree #8: KEEP"));
        assertThat(out[8], is("Tree #9: KEEP"));
        assertThat(out[9], is("Tree #10: KEEP"));
        assertThat(out[10], is("Tree #11: REMOVE"));
        assertThat(out[11], is("Tree #12: KEEP"));
        assertThat(out[12], is("Tree #13: REMOVE"));
        assertThat(out[13], is("Tree #14: KEEP"));
        assertThat(out[14], is("Tree #15: REMOVE"));
        assertThat(out[15], is("Tree #16: REMOVE"));
        assertThat(out[16], is("Tree #17: REMOVE"));
        assertThat(out[17], is("Tree #18: REMOVE"));
        assertThat(out[18], is("Tree #19: REMOVE"));
        assertThat(out[19], is("Tree #20: REMOVE"));
        assertThat(out[20], is("Tree #21: REMOVE"));
        assertThat(out[21], is("Tree #22: REMOVE"));
        assertThat(out[22], is("Tree #23: REMOVE"));
        assertThat(out[23], is("Tree #24: REMOVE"));
        assertThat(out[24], is("Tree #25: REMOVE"));
        assertThat(out[25], is("Tree #26: KEEP"));
        assertThat(out[26], is("Tree #27: REMOVE"));
        assertThat(out[27], is("Tree #28: REMOVE"));
        assertThat(out[28], is("Tree #29: REMOVE"));
        assertThat(out[29], is("Tree #30: REMOVE"));
        assertThat(out[30], is("Tree #31: REMOVE"));
        assertThat(out[31], is("Tree #32: REMOVE"));
        assertThat(out[32], is("Tree #33: KEEP"));
    }

    @Test
    public void test_avlMainFull() {

        StringBuilder sb = new StringBuilder();
        Path path = Paths.get("E:\\Workspace\\cs-coursework\\COP4516\\Contests\\H03-0129-contest\\src\\avl.in");
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> sb.append(s + "\n"));
        } catch (IOException ex) { }

        StringBuilder sb2 = new StringBuilder();
        path = Paths.get("E:\\Workspace\\cs-coursework\\COP4516\\Contests\\H03-0129-contest\\src\\avl.out");
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

        avl.main(null);

        String[] actual = outContent.toString().split("\n");
        String[] expected = output.toString().split("\n");

        for (int i = 0; i < actual.length; i++) {
            actual[i] = actual[i].trim();
            expected[i] = expected[i].trim();
        }

        assertArrayEquals(expected, actual);
    }

}
