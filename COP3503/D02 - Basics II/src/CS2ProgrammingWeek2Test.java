import static org.junit.Assert.*;

import org.junit.Test;


public class CS2ProgrammingWeek2Test {

	@Test
	public void testFindThreeIncreasingNumbers() {
		int[] NumberList1 = {0, 2, 4};
		int[] NumberList2 = {1};
		int[] NumberList3 = {2, 2, 4};
		int[] NumberList4 = {5, 6, 2, 2, 4, 6, 1};
		assertTrue(CS2ProgrammingWeek2.FindThreeIncreasingNumbers(NumberList1));
		assertFalse(CS2ProgrammingWeek2.FindThreeIncreasingNumbers(NumberList2));
		assertFalse(CS2ProgrammingWeek2.FindThreeIncreasingNumbers(NumberList3));
		assertTrue(CS2ProgrammingWeek2.FindThreeIncreasingNumbers(NumberList4));
	}

	@Test
	public void testMultiplesOfTen() {
		int[] NumberList1 = {2, 10, 3, 4, 20, 5};
		int[] NumberList2 = {10, 1, 20, 2};
		int[] NumberList3 = {10, 1, 9, 20};
		int[] NumberList4 = {1, 1, 9, 20};
		assertArrayEquals(new int[]{2,10,10,10,20,20}, CS2ProgrammingWeek2.multiplesOfTen(NumberList1));
		assertArrayEquals(new int[]{10,10,20,20}, CS2ProgrammingWeek2.multiplesOfTen(NumberList2));
		assertArrayEquals(new int[]{10,10,10,20}, CS2ProgrammingWeek2.multiplesOfTen(NumberList3));
		assertArrayEquals(new int[]{1,1,9,20}, CS2ProgrammingWeek2.multiplesOfTen(NumberList4));
	}

	@Test
	public void testCheckForAloneNumbers() {
		int[] NumberList1 = {1, 2, 3};
		int[] NumberList2 = {1, 2, 3, 2, 5, 2};
		int[] NumberList3 = {3, 4};
		int[] NumberList4 = {1, 1, 9, 20, 11, 9, 9, 10, 70, 9, 1};
		assertArrayEquals(new int[]{1,3,3}, CS2ProgrammingWeek2.CheckForAloneNumbers(NumberList1, 2));
		assertArrayEquals(new int[]{1,3,3,5,5,2}, CS2ProgrammingWeek2.CheckForAloneNumbers(NumberList2, 2));
		assertArrayEquals(new int[]{3,4}, CS2ProgrammingWeek2.CheckForAloneNumbers(NumberList3, 3));
		assertArrayEquals(new int[]{1,1,20,20,11,9,9,10,70,70,1}, CS2ProgrammingWeek2.CheckForAloneNumbers(NumberList4, 9));
	}

	@Test
	public void testReplaceZerosWithLargestOdd() {
		int[] NumberList1 = {0,5,0,3};
		int[] NumberList2 = {0,4,0,3};
		int[] NumberList3 = {0,1,0};
		int[] NumberList4 = {0,0,0,0,0,2,0,0,0,1,0};
		assertArrayEquals(new int[]{5,5,3,3}, CS2ProgrammingWeek2.ReplaceZerosWithLargestOdd(NumberList1));
		assertArrayEquals(new int[]{3,4,3,3}, CS2ProgrammingWeek2.ReplaceZerosWithLargestOdd(NumberList2));
		assertArrayEquals(new int[]{1,1,0}, CS2ProgrammingWeek2.ReplaceZerosWithLargestOdd(NumberList3));
		assertArrayEquals(new int[]{1,1,1,1,1,2,1,1,1,1,0}, CS2ProgrammingWeek2.ReplaceZerosWithLargestOdd(NumberList4));
	}

	@Test
	public void testCreateIncreasingArray() {
		assertArrayEquals(new int[]{5,6,7,8,9}, CS2ProgrammingWeek2.CreateIncreasingArray(5, 10));
		assertArrayEquals(new int[]{11,12,13,14,15,16,17}, CS2ProgrammingWeek2.CreateIncreasingArray(11, 18));
		assertArrayEquals(new int[]{1,2}, CS2ProgrammingWeek2.CreateIncreasingArray(1, 3));
		assertArrayEquals(new int[]{}, CS2ProgrammingWeek2.CreateIncreasingArray(1, 1));
	}

	@Test
	public void testCopyNumbersBeforeFour() {
		assertArrayEquals(new int[]{1,2}, CS2ProgrammingWeek2.CopyNumbersBeforeFour(new int[]{1,2,4,1}));
		assertArrayEquals(new int[]{3,1}, CS2ProgrammingWeek2.CopyNumbersBeforeFour(new int[]{3,1,4}));
		assertArrayEquals(new int[]{1}, CS2ProgrammingWeek2.CopyNumbersBeforeFour(new int[]{1,4,4}));
		assertArrayEquals(new int[]{}, CS2ProgrammingWeek2.CopyNumbersBeforeFour(new int[]{1,3,2}));
	}

	@Test
	public void testMoveZerosToFront() {
		assertArrayEquals(new int[]{0,0,1,1}, CS2ProgrammingWeek2.MoveZerosToFront(new int[]{1,0,0,1}));
		assertArrayEquals(new int[]{0,0,1,1,1}, CS2ProgrammingWeek2.MoveZerosToFront(new int[]{0,1,1,0,1}));
		assertArrayEquals(new int[]{0,1}, CS2ProgrammingWeek2.MoveZerosToFront(new int[]{1,0}));
	}

	@Test
	public void testEvenFrontOddBack() {
		assertArrayEquals(new int[]{0,0,0,1,1,1,1}, CS2ProgrammingWeek2.EvenFrontOddBack(new int[]{1,0,1,0,0,1,1}));
		assertArrayEquals(new int[]{2,3,3}, CS2ProgrammingWeek2.EvenFrontOddBack(new int[]{3,3,2}));
		assertArrayEquals(new int[]{2,2,2}, CS2ProgrammingWeek2.EvenFrontOddBack(new int[]{2,2,2}));
		assertArrayEquals(new int[]{}, CS2ProgrammingWeek2.EvenFrontOddBack(new int[]{}));
	}
	
}
