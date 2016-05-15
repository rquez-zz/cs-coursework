import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MonkeyTest {

    @Test
    public void test_monkeyMain() {

        String input =
                "3\n" +
                "[]\n" +
                "\n" +
                "[[][[]]]\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream in;
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));

        monkey.main(null);
        String[] out = outContent.toString().split("\n");

        assertThat(out[0].trim(), is("1 2"));
        assertThat(out[1].trim(), is("2 1"));
        assertThat(out[2].trim(), is("3 8"));
    }

}
