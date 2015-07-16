/**
 * @author Ricardo
 *
 */
public class Utils {

	/**
	 * @param strString
	 * @return The hashed out string
	 */
	public static long HashFromString(String strString)
	{
		int prime = 31;
		long hash = 0;
		for (int i = 0; i < strString.length(); i++)
		{
			hash = hash * prime + strString.charAt(i);
			prime = NextPrime(prime);
		}

		return hash;
	}
	
	/**
	 * @param nStartingPrime
	 * @return The next prime after nStartingPrime
	 */
	private static int NextPrime(int nStartingPrime)
	{
		do {
			nStartingPrime++;
		} while (!isPrime(nStartingPrime));

		return nStartingPrime;
	}
	
	private static boolean isPrime(int intNumber)
	{
		for (int i = 2; i < intNumber; i++)
		{
			if (intNumber % i == 0)
				return false;
		}
		return true;
	}
}
