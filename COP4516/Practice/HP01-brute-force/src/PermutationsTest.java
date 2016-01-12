import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;

public class PermutationsTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream systemOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUp() {
    }

    @Test
    public void testPermutateIntArray() {
        int length = 3;

        int[] array = new int[length];
        for (int i = 0; i < length; i++)
            array[i] = i + 1;
        Permutations.permutate(array, 0);
        String[] permutations = outContent.toString().split(" \n");

        assertEquals(factorial(length), permutations.length);

        System.setOut(systemOut);
        for (String s : permutations) {
            System.out.println(s);
        }
    }

    @Test
    public void testPermutateString() {

        int length = 4;

        char[] array = new char[length];
        for (int i = 0; i < length; i++)
            array[i] = (char)(i + 97);

        Permutations.permutate(array, 0);

        String[] permutations = outContent.toString().split(" \n");

        assertEquals(factorial(length), permutations.length);

        System.setOut(systemOut);
        for (String s : permutations) {
            System.out.println(s);
        }
    }

    private int factorial(int num) {
        if (num == 1) return 1;
        return num * factorial(num - 1);
    }
}
