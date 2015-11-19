import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by Ricardo on 11/19/2015.
 */
public class crcheckTest {

    private BufferedReader reader;

    @Before
    public void setup() throws Exception {
        reader = new BufferedReader(new FileReader(crcheckTest.class.getResource("test.txt").getFile()));
    }
    @Test
    public void testRead() throws Exception {
        char[][] input = {
                "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr".toCharArray(),
                "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss".toCharArray(),
                "tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt".toCharArray(),
                "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv".toCharArray(),
                "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy".toCharArray(),
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx".toCharArray(),
                "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz".toCharArray(),
                "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww".toCharArray()
        };
        char[][] readinput = crcheck.read(reader);
        assertArrayEquals(input, readinput);
    }
}
