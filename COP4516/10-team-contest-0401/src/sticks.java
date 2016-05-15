import java.util.HashMap;
import java.util.Scanner;

public class sticks {

    public static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numCases = scanner.nextInt();
        for (int i = 0; i < numCases; i++) {

            int length = scanner.nextInt();
            int numSplits = scanner.nextInt();

            int[] splits = new int[numSplits];
            for (int j = 0; j < numSplits; j++) {
                splits[j] = scanner.nextInt();
            }

            System.out.println(split(0, length, splits, 0, splits.length - 1));
            map.clear();
        }

    }

    private static int split(int left, int right, int[] splits, int sL, int sR) {

        String s = left + " " + right;
        if (map.containsKey(s)) {
            return map.get(s);
        }

        int min = Integer.MAX_VALUE;
        for (int i = sL; i <= sR; i++) {
            int subL = split(left, splits[i], splits, sL, i - 1);
            int subR = split(splits[i], right, splits, i + 1, sR);
            min = Math.min(subL + subR + right - left, min);
        }

        if (min == Integer.MAX_VALUE) {
            map.put(s, 0);
            return 0;
        } else {
            map.put(s, min);
            return min;
        }
    }
}
