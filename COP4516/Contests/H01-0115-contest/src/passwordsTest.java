import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class passwordsTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream systemOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testPrintPasswords() {

        int cases = 1;
        int length = 3;
        String[] letterSets = {"abc", "xy", "dmnr"};
        int rank = 10;

        passwords.printRankedPasswords(letterSets, new int[length], rank, 0, 0);
        String[] perms = outContent.toString().split("\n");
        assertTrue("bxm".equals(perms[0].substring(0,perms[0].length()-1)));
    }

    @Test
    public void testPrintPasswords2() {

        int cases = 1;
        int length = 2;
        String[] letterSets = {"abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz"};
        int rank = 676;

        passwords.printRankedPasswords(letterSets, new int[length], rank, 0, 0);
        String[] perms = outContent.toString().split("\n");
        assertTrue("zz".equals(perms[0].substring(0,perms[0].length()-1)));
    }
}
