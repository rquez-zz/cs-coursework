///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek1 
{
	
	///////////////////////////////////////////
	//
	// Start of assignment code.
	//
	///////////////////////////////////////////
	
	/**
	 * Returns the last name, first name, and PID of the student.
	 * 
	 * This is required in order to get credit for the programming assignment.
	 */
	static String GetNameAndPID()
	{
		return( "Vasquez,Ricardo,R2905753");
	}

	// Directions: Return the number of even ints in the given 
	//   array. Note: the % "mod" operator computes the remainder, 
	//   e.g. 5 % 2 is 1. 

	// CountEvenNumbersInArray({2, 1, 2, 3, 4}) → 3
	// CountEvenNumbersInArray({2, 2, 0}) → 3
	// CountEvenNumbersInArray({1, 3, 5}) → 0
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		int with the number of even numbers in NumberList
	 */
	static int CountEvenNumbersInArray(int[] NumberList) 
	{
		int intCount = 0; 
		for (int i = 0; i < NumberList.length; i++) 
		{
			if (NumberList[i] % 2 == 0) 
				intCount++;
		}
		return intCount;
	}

	// Given an array of ints, return true if the array contains no 
	//   1's and no 3's.

	// LookForLucky13({0, 2, 4}) → true
	// LookForLucky13({1, 2, 3}) → false
	// LookForLucky13({1, 2, 4}) → false
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		returns false if there is a 1 or 3 in the list.
	 * 		returns true if there are no 1s or 3s in the list.
	 */
	static boolean LookForLucky13(int[] NumberList) 
	{
		for(int i = 0; i < NumberList.length; i++)
		{
			if (NumberList[i] == 1 || NumberList[i] == 3)
				return false;
		}
		return true;
	}	

	// Given arrays NumberList1 and NumberList2 of the same length, 
	//   for every element in NumberList1, consider the 
	//   corresponding element in NumberList2 (at the same index). 
	//   Return the count of the number of times that the two 
	//   elements differ by 2 or less, but are not equal. 

	// MatchUpLists({1, 2, 3}, {2, 3, 10}) → 2
	// MatchUpLists({1, 2, 3}, {2, 3, 5}) → 3
	// MatchUpLists({1, 2, 3}, {2, 3, 3}) → 2
	
	static int MatchUpLists(int[] NumberList1, int[] NumberList2) 
	{
		int intCount = 0;
		for(int i = 0; i < NumberList1.length; i++)
		{
			if (Math.abs(NumberList1[i] - NumberList2[i]) <= 2 &&
					NumberList1[i] != NumberList2[i])
				intCount++;
		}
		return intCount;
	}	

	// Given an array of ints, return true if the array 
	//   contains either 3 even or 3 odd values all next 
	//   to each other. 

	// ModThreeNumbers({2, 1, 3, 5}) → true
	// ModThreeNumbers({2, 1, 2, 5}) → false
	// ModThreeNumbers({2, 4, 2, 5}) → true
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		return true if there are three consecutive events
	 * 			or three consecutive odds
	 * 
	 * 		otherwise returns false
	 */
	public static boolean ModThreeNumbers(int[] NumberList) 
	{
		int intCountConsecutiveEvens = 0;
		int intCountConsecutiveOdds = 0;
		for (int i = 0; i < NumberList.length; i++)
		{
			if (NumberList[i] % 2 == 1) 
			{
				intCountConsecutiveEvens = 0;
				intCountConsecutiveOdds++;
			}
			else
			{
				intCountConsecutiveOdds = 0;
				intCountConsecutiveEvens++;
			}
			if (intCountConsecutiveEvens == 3 || intCountConsecutiveOdds == 3)
				return true;
		}
		return false;
	}

	// Return the "centered" average of an array of ints, 
	//   which we'll say is the mean average of the values, 
	//   except ignoring the largest and smallest values in 
	//   the array. If there are multiple copies of the 
	//   smallest value, ignore just one copy, and likewise 
	//   for the largest value. Use int division to produce 
	//   the final average. You may assume that the array is 
	//   length 3 or more. 

	// FindCenteredAverage({1, 2, 3, 4, 100}) → 3
	// FindCenteredAverage({1, 1, 5, 5, 10, 8, 7}) → 5
	// FindCenteredAverage({-10, -4, -2, -4, -2, 0}) → -3	
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		Average of the list of numbers without the
	 * 		first of the lowest numbers and the last of the
	 * 		highest numbers.
	 */
	static int FindCenteredAverage(int[] NumberList) 
	{
		int intSmallest = NumberList[0];
		int intLargest = intSmallest; 
		for (int i = 1; i < NumberList.length; i++)
		{
			if (intSmallest > NumberList[i])
				intSmallest = NumberList[i];
			
			if (intLargest < NumberList[i])
				intLargest = NumberList[i];
		}
		int intSum = 0;
		for (int i = 0; i < NumberList.length; i++)
			intSum += NumberList[i];
		intSum -= (intSmallest + intLargest);
		return intSum / (NumberList.length - 2);
	}
	
	// Given an array of ints, return true if every 2 that 
	//   appears in the array is next to another 2. 

	// LookForTwoTwo({4, 2, 2, 3}) → true
	// LookForTwoTwo({2, 2, 4}) → true
	// LookForTwoTwo({2, 2, 4, 2}) → false
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		true if every 2 is adjacent to another 2
	 * 		otherwise false
	 */
	static boolean LookForTwoTwo(int[] NumberList) 
	{
		if (NumberList.length == 1 && NumberList[0] == 2)
			return false;
		for (int i = 1; i < NumberList.length; i++) 
		{
			if (NumberList[i] == 2) 
			{
				if (i == NumberList.length - 1 && NumberList[i-1] != 2)
					return false;
				if (NumberList[i-1] != 2 && NumberList[i+1]!= 2)
					return false;
			}
		}
		return true;	
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
	}
	
}
