import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class perm {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int cases = scanner.nextInt();

        for (int i = 0; i < cases; i++) {
            scanner.nextInt();
            String stringnum = scanner.nextLine().trim();
            int[] numArray = getNumArray(stringnum);
            System.out.print(i + 1 + " ");
            getNextPerm(numArray);
            System.out.println();
        }
    }

    private static int[] getNumArray(String num) {

        char[] numArray = num.toCharArray();
        int[] intArray = new int[numArray.length];
        for (int i = numArray.length - 1; i >= 0; i--) {
            intArray[numArray.length - 1 - i] = numArray[i] - '0';
        }

        return intArray;
    }

    private static void getNextPerm(int[] numArray) {

        if (numArray.length <= 1) {
            System.out.print("BIGGEST");
            return;
        }
        int i = 0;
        while (numArray[i + 1] >= numArray[i]) {
            i++;
            if (i == numArray.length - 1) {
                System.out.print("BIGGEST");
                return;
            }
        }

        int startIndex = i + 1;

        int nextBiggest = numArray[startIndex] + 1;
        int k = 0;
        while (true) {
            if (numArray[k] >= nextBiggest) {
                nextBiggest = numArray[k];
                break;
            }
            k++;
        }

        int temp = numArray[startIndex];
        numArray[startIndex] = numArray[k];
        numArray[k] = temp;

        ArrayList<Integer> list = new ArrayList<>();
        for (int j = 0; j < startIndex; j++) {
            list.add(numArray[j]);
        }

        Collections.sort(list);

        int sum = 0;
        i = 0;
        for (int o = numArray.length - 1; o > startIndex; o--) {
            System.out.print(numArray[o]);
        }
        System.out.print(numArray[startIndex]);
        for (Integer l : list) {
            System.out.print(l);
        }

        return;
    }
}
