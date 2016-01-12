import org.junit.Test;

import java.util.ArrayList;

/**
 *
 * Practice on Permutations
 */
public class Permutations {

    /**
     * Permutate k integers in array
     *
     * O(n!) time, O(n) space
     * @param array
     * @param k
     * @return
     */
    public static void permutate(int[] array, int k) {

        if (k == array.length) {
            for (int i : array)
                System.out.print(i + " ");
            System.out.println();
        }

        for (int i = k; i < array.length; i++) {
            swap(array, i, k);
            permutate(array, k+1);
            swap(array, k, i);
        }
    }

    private static void swap(int[] array, int i, int k) {
        int temp = array[i];
        array[i] = array[k];
        array[k] = temp;
    }


    /**
     * Permutate k characters in string
     *
     * O(n!) time, O(n) space
     * @param string
     * @param k
     * @return
     */
    public static void permutate(char[] string, int k) {

        if (k == string.length) {
            for (char i : string)
                System.out.print(i + " ");
            System.out.println();
        }

        for (int i = k; i < string.length; i++) {
            swap(string, i, k);
            permutate(string, k+1);
            swap(string, k, i);
        }
    }

    public static void permutate(String string, int k) {
        permutate(string.toCharArray(), k);
    }

    private static void swap(char[] array, int i, int k) {
        char temp = array[i];
        array[i] = array[k];
        array[k] = temp;
    }
}
