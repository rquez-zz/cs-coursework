import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;

public class PermutationsTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream systemOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
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

    }

    @Test
    public void testSubsetsBoolean() {

        int length = 3;
        Random rand = new Random();
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
           array[i] = rand.nextInt(11);
        }

        Permutations.subsets(0, new boolean[length], array);
        String[] subsets = outContent.toString().split(" \n");
        assertEquals(subsets.length, (int)Math.pow(2, length) - 1);
    }

    @Test
    public void testSubsetsBitmasking() {

        int length = 3;
        Random rand = new Random();
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(11);
        }

        Permutations.subsets(array);
        String[] subsets = outContent.toString().split(" \n");
        assertEquals(subsets.length, (int)Math.pow(2, length) - 1);
        printOutSet(subsets, array);
    }

    @Test
    public void testCombinations() {

        int length = 4;
        Random rand = new Random();
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(11);
        }

        Permutations.combinations(0, 2, 0, new int[2], array);
        String[] combos = outContent.toString().split(" \n");
        assertEquals(combos.length, combos(4,2));
        printOutSet(combos,array);
    }

    @Test
    public void testCombinationsBit() {
        int n = 4;
        int k = 2;

        Random rand = new Random();
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(11);
        }

        Permutations.combinations(k,array);
        String[] combos = outContent.toString().split(" \n");
        assertEquals(combos.length, combos(n,k));
        printOutSet(combos,array);
    }

    private int factorial(int num) {
        if (num == 1) return 1;
        return num * factorial(num - 1);
    }

    private int combos(int num, int k) {
        return factorial(num) / (factorial(k) * factorial(num - k));
    }

    private void printOutSet(String[] set, int[] array) {
        System.setOut(systemOut);
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (String i : set) {
            System.out.println(i);
        }
    }
}
