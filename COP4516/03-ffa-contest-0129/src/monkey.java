import java.util.Scanner;

public class monkey {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numCases = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numCases; i++) {

            String line = scanner.nextLine();
            int monkeys = (int)Math.pow(2, getMaxLevel(line));

            System.out.println((i+1) + " " + monkeys);
        }
    }

    private static int getMaxLevel(String line) {
        int height = 0;
        int maxHeight = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '[') {
                height++;
            } else if (line.charAt(i) == ']') {
                height--;
            }
            maxHeight = Math.max(height, maxHeight);
        }
        return maxHeight;
    }

}
