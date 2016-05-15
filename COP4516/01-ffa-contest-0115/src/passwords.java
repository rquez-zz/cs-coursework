import java.util.Scanner;

/**
 * Created by ricardo on 1/15/16.
 */
public class passwords {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numCases = scanner.nextInt();
        String[][] allSets = new String[numCases][];
        int[] ranks = new int[numCases];
        int[] passLengths = new int[numCases];

        for (int i = 0; i < numCases; i++) {

            int lengthPass = scanner.nextInt();
            passLengths[i] = lengthPass;
            String[] letterSets = new String[lengthPass];
            allSets[i] = letterSets;

            for (int j = 0; j < lengthPass; j++) {

                letterSets[j] = scanner.next();
            }

            int rank = scanner.nextInt();
            ranks[i] = rank;

        }

        for (int i = 0; i < numCases; i++) {
            printRankedPasswords(allSets[i], new int[passLengths[i]], ranks[i], 0, 0);
        }
    }

    public static int printRankedPasswords(String[] letterSets, int[] used, int rank, int p, int count) {

        if (p == letterSets.length) {

            if (count + 1 == rank) {
                for (int i = 0; i < used.length; i++) {
                    System.out.print(letterSets[i].charAt(used[i]));
                }
                System.out.println();
            }
            return count + 1;
        }


        for (int j = 0; j < letterSets[p].length(); j++) {

            used[p] = j;
            count = printRankedPasswords(letterSets, used, rank, p+1, count);
            if (count == rank) break;
        }

        return count;
    }
}
