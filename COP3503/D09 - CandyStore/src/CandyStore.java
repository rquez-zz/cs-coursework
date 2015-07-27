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
}

