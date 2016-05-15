import java.util.Scanner;

public class fundraised {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numCases = scanner.nextInt();

        for (int c = 0; c < numCases; c++) {

            int numItems = scanner.nextInt();

            int[] importance = new int[numItems + 1];
            int[] cost = new int[numItems + 1];

            int budget = scanner.nextInt();

            int[] table = new int[budget + 1];

            for (int i = 1; i <= numItems; i++)
                importance[i] = scanner.nextInt();

            for (int i = 1; i <= numItems; i++)
                cost[i] = scanner.nextInt();

            for (int i = 1; i <= numItems; i++) {
                for (int j = cost[i]; j <= budget; j++) {
                    table[j] = Math.max(table[j], table[j - cost[i]] + importance[i]);
                }
            }

            System.out.println(table[budget]);
        }
    }
}
