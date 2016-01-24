import java.util.Scanner;

public class fruit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int fruitStands = scanner.nextInt();

        int[][] stands = new int[fruitStands][];

        for (int i = 0; i < fruitStands; i++) {

            int days = scanner.nextInt();
            int[] fruitSold = new int[days];

            for (int j = 0; j < days; j++) {
                fruitSold[j] = scanner.nextInt();
            }

            stands[i] = fruitSold;
        }

        for (int i = 0; i < stands.length; i++) {

            int fruitNeeded = 0;
            int sum = 0;
            int avg = 0;
            int leftOver = 0;
            int leftOverMax = 0;

            for (int j = 0; j < stands[i].length; j++) {

                sum += stands[i][j];
                double tempAvg = sum / (j + 1.00);
                avg = Math.max((int)Math.ceil(tempAvg), avg);
            }

            for (int j = 0; j < stands[i].length; j++) {

                leftOver = avg - stands[i][j] + leftOver;
                leftOverMax = Math.max(leftOver, leftOverMax);
            }

            System.out.println(avg + " " + leftOverMax);
        }
    }
}
