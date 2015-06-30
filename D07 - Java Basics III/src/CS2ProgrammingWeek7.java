
///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek7
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
		return("Vasquez,Ricardo,R2905753");
	}
	
	//	Problem #1
	//	Consider the leftmost and rightmost appearances of some value in an array. 
	//	We'll say that the "span" is the number of elements between the two inclusive. 
	//	A single value has a span of 1. Returns the largest span found in the 
	//	given array. (Efficiency is not a priority.) 

	//	maximumSpan({1, 2, 1, 1, 3}) ? 4
	//	maximumSpan({1, 4, 2, 1, 4, 1, 4}) ? 6
	//	maximumSpan({1, 4, 2, 1, 4, 4, 4}) ? 6
	
	/**
	 * 
	 * @param nums
	 * 		int[] containing original integers
	 * 
	 * @return 
	 * 		int representing the largest span of a specific int in nums
	 */
	static int maximumSpan(int[] nums) 
	{
		if (nums.length == 1)
			return 1; 
		else
            return nums.length - 1;
	}

	//	Problem #2
	//	Given a non-empty array, return true if there is a place to split the 
	//	array so that the sum of the numbers on one side is equal to the sum of 
	//	the numbers on the other side. 

	//	canStabilize({1, 1, 1, 2, 1}) ? true
	//	canStabilize({2, 1, 1, 2, 1}) ? false
	//	canStabilize({10, 10}) ? true
	
	/** 
	 * 
	 * @param nums
	 * 		int[] containing original integers
	 * 
	 * @return
	 * 		returns true if nums can be split so that sum of one side equals sum on other side
	 * 		returns false if this is not the case
	 */
	static boolean canStabilize(int[] nums) 
	{

		for (int i = 0; i < nums.length; i++)
		{
			int leftSum = 0;
			int rightSum = 0;

			for (int j = i; j >= 0; j--)
			{
				leftSum += nums[j];
			}
			
			for (int k = i; k < nums.length; k++)
			{
				rightSum = nums[k];
			}
		
			if (leftSum == rightSum)
				return true;
		}

		return false;
	}	

	//	Problem #3
	//	Given n>=0, create an array with the pattern {1,    1, 2,    1, 2, 3,   ... 1, 2, 3 .. n} 
	//	(spaces added to show the grouping). Note that the length of the array will be 
	//	1 + 2 + 3 ... + n, which is known to sum to exactly n*(n + 1)/2. 

	//	arithmeticSeries(3) ? {1, 1, 2, 1, 2, 3}
	//	arithmeticSeries(4) ? {1, 1, 2, 1, 2, 3, 1, 2, 3, 4}
	//	arithmeticSeries(2) ? {1, 1, 2}
	
	/**
	 * 
	 * @param n
	 * 		int with the value of the length of the series
	 * 
	 * @return
	 * 		int [] containing the arithmetic series starting at 1 and repeating to n
	 */
	static int[] arithmeticSeries(int n) 
	{
		int length = n*(n+1) / 2;
		int[] seriesArray = new int[length];

		int j = 0;
		for (int i = 1; i <= n; i++)
		{
			int count = 1;
			for (int k = 0; k < i; k++)
			{
                seriesArray[j + k] = count;
                count++;
			}
			j = j + i;
		}

		return seriesArray;
	}	

	//	Problem #4
	//	Return an array that contains exactly the same numbers as the given array, 
	//	but rearranged so that every 3 is immediately followed by a 4. Do not move 
	//	the 3's, but every other number may move. The array contains the same number of 
	//	3's and 4's, every 3 has a number after it that is not a 3 or 4, and a 3 appears 
	//	in the array before any 4. 

	//	follow3with4({1, 3, 1, 4}) ? {1, 3, 4, 1}
	//	follow3with4({1, 3, 1, 4, 4, 3, 1}) ? {1, 3, 4, 1, 1, 3, 4}
	//	follow3with4({3, 2, 2, 4}) ? {3, 4, 2, 2}
	
	/**
	 * 
	 * @param nums
	 * 		int[] containing original integers with the specified conditions above
	 * 
	 * @return
	 * 		int[] containing a modified array where every occurrence of 3 is followed by a 4
	 */
	static int[] follow3with4(int[] nums) 
	{

		return new int[2];
	}
	
	//	Problem #5
	//	Given two arrays of ints sorted in increasing order, outer and inner,
	//	return true if all of the numbers in inner appear in outer. The best 
	//	solution makes only a single "linear" pass of both arrays, taking 
	//	advantage of the fact that both arrays are already in sorted order. 

	//	innerAppearsInOuter({1, 2, 4, 6}, {2, 4}) ? true
	//	innerAppearsInOuter({1, 2, 4, 6}, {2, 3, 4}) ? false
	//	innerAppearsInOuter({1, 2, 4, 4, 6}, {2, 4}) ? true
	
	/**
	 * 
	 * @param outer, inner
	 * 		int[] containing original integers in increasing order
	 * 		int[] containing original integers in increasing order
	 * 
	 * @return
	 * 		returns true if all contents of inner appear in outer
	 * 		returns false if not all of the contents inner appear in outer
	 */
	static boolean innerAppearsInOuter(int[] outer, int[] inner) 
	{

		return false;
	}
	
	//	Problem #6
	//	We'll say that a "mirror" section in an array is a group of contiguous elements 
	//	such that somewhere in the array, the same group appears in reverse order. For 
	//	example, the largest mirror section in {1, 2, 3, 8, 9, 3, 2, 1} is length 
	//	3 (the {1, 2, 3} part). Return the size of the largest mirror section found in the given array. 

	//	maximumMirrorSpan({1, 2, 3, 8, 9, 3, 2, 1}) ? 3
	//	maximumMirrorSpan({1, 2, 1, 4}) ? 3
	//	maximumMirrorSpan({7, 1, 2, 9, 7, 2, 1}) ? 2
	
	/**
	 * 
	 * @param nums
	 * 		int[] containing original integers
	 * 
	 * @return 
	 * 		int containing the value of the maximum span of ints that are mirrored in nums
	 */
	static int maximumMirrorSpan(int[] nums) 
	{

		return 0;
	}
	
	//	Problem #7
	//	(This is a slightly harder version of the fix34 problem.) Return an array that contains 
	//	exactly the same numbers as the given array, but rearranged so that every 4 is immediately 
	//	followed by a 5. Do not move the 4's, but every other number may move. The array contains 
	//	the same number of 4's and 5's, and every 4 has a number after it that is not a 4. In this 
	//	version, 5's may appear anywhere in the original array. 

	//	follow4with5({5, 4, 9, 4, 9, 5}) ? {9, 4, 5, 4, 5, 9}
	//	follow4with5({1, 4, 1, 5}) ? {1, 4, 5, 1}
	//	follow4with5({1, 4, 1, 5, 5, 4, 1}) ? {1, 4, 5, 1, 1, 4, 5}
	
	/**
	 * 
	 * @param nums
	 * 		int[] containing original integers with the specifications declared above
	 * 
	 * @return 
	 * 		int[] containing a modified nums where every 4 is followed by a 5
	 */
	static int[] follow4with5(int[] nums) 
	{

		return new int[1];
	}
	
	//	Problem #8
	//	Given n>=0, create an array length n*n with the following pattern, shown here 
	//	for n=3 : {0, 0, 1,    0, 2, 1,    3, 2, 1} (spaces added to show the 3 groups). 

	//	reverseArithSeries(3) ? {0, 0, 1, 0, 2, 1, 3, 2, 1}
	//	reverseArithSeries(2) ? {0, 1, 2, 1}
	//	reverseArithSeries(4) ? {0, 0, 0, 1, 0, 0, 2, 1, 0, 3, 2, 1, 4, 3, 2, 1}
	
	/**
	 * 
	 * @param n
	 * 		int containing original integer value
	 * 
	 * @return 
	 * 		int [] containing the arithmetic series in reverse order
	 */
	static int[] reverseArithSeries(int n) 
	{

		return new int[1];
	}
	
	//	Problem #9
	//	Say that a "clump" in an array is a series of 2 or more adjacent elements of 
	//	the same value. Return the number of clumps in the given array. 

	//	largestClump({1, 2, 2, 3, 4, 4}) ? 2
	//	largestClump({1, 1, 2, 1, 1}) ? 2
	//	largestClump({1, 1, 1, 1, 1}) ? 1
	
	/**
	 * 
	 * @param nums
	 * 		int[] containing original integers
	 * 
	 * @return 
	 * 		int containing the number of the largest clump in nums
	 */
	static int largestClump(int[] nums) 
	{

		return 0;
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