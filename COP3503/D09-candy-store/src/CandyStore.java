import java.util.Scanner;

/**
 * Created by Ricardo on 7/26/2015.
 */
public class CandyStore {

    public static int solve(int candy, int[] calories, double[] prices, double money)
    {
        if (money < 0.00 || candy >= calories.length)
            return 0;

        if (prices[candy] > money)
            return solve(candy + 1, calories, prices, money);
        else
            return Math.max(
                solve(candy + 1, calories, prices, money),
                solve(candy, calories, prices, money - prices[candy]) + calories[candy]
            );
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean isDone = false;
        while(!isDone)
        {
            int numCandies = scanner.nextInt();
            double money = scanner.nextDouble();
            if (numCandies == 0 && money == 0.00)
            {
                isDone = true;
                break;
            }
            int[] calories = new int[numCandies];
            double[] prices = new double[numCandies];
            for(int i = 0; i < numCandies; i++)
            {
                calories[i] = scanner.nextInt();
                prices[i] = scanner.nextDouble();
            }
            System.out.println(solve(0, calories, prices, money));
        }
    }
}

