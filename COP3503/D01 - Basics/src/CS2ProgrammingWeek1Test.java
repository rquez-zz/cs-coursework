import static org.junit.Assert.*;

import org.junit.Test;


public class CS2ProgrammingWeek1Test {

	@Test
	public void testGetNameAndPID() {
		assertEquals( "Vasquez,Ricardo,R2905753", CS2ProgrammingWeek1.GetNameAndPID());
	}

	@Test
	public void testCountEvenNumbersInArray() {
		int[] NumberList1 = {2, 1, 2, 3, 4};
		int[] NumberList2 = {2, 2, 0};
		int[] NumberList3 = {1, 3, 5};
		assertEquals(3, CS2ProgrammingWeek1.CountEvenNumbersInArray(NumberList1));
		assertEquals(3, CS2ProgrammingWeek1.CountEvenNumbersInArray(NumberList2));
		assertEquals(0, CS2ProgrammingWeek1.CountEvenNumbersInArray(NumberList3));
	}

	@Test
	public void testLookForLucky13() {
		int[] NumberList1 = {0, 2, 4};
		int[] NumberList2 = {1, 2, 3};
		int[] NumberList3 = {1, 2, 4};
		assertTrue(CS2ProgrammingWeek1.LookForLucky13(NumberList1));
		assertFalse(CS2ProgrammingWeek1.LookForLucky13(NumberList2));
		assertFalse(CS2ProgrammingWeek1.LookForLucky13(NumberList3));
	}

	@Test
	public void testMatchUpLists() {
		int[] NumberList1 = {1, 2, 3};
		int[] NumberList2 = {2, 3, 10};
		int[] NumberList3 = {2, 3, 5};
		int[] NumberList4 = {2, 3, 3};
		assertEquals(2, CS2ProgrammingWeek1.MatchUpLists(NumberList1, NumberList2));
		assertEquals(3, CS2ProgrammingWeek1.MatchUpLists(NumberList1, NumberList3));
		assertEquals(2, CS2ProgrammingWeek1.MatchUpLists(NumberList1, NumberList4));
	}

	@Test
	public void testModThreeNumbers() {
		int[] NumberList1 = {2, 1, 3, 5};
		int[] NumberList2 = {2, 1, 2, 5};
		int[] NumberList3 = {2, 4, 2, 5};
		assertTrue(CS2ProgrammingWeek1.ModThreeNumbers(NumberList1));
		assertFalse(CS2ProgrammingWeek1.ModThreeNumbers(NumberList2));
		assertTrue(CS2ProgrammingWeek1.ModThreeNumbers(NumberList3));
	}

	@Test
	public void testFindCenteredAverage() {
		int[] NumberList1 = {1, 2, 3, 4, 100};
		assertEquals(3, CS2ProgrammingWeek1.FindCenteredAverage(NumberList1));
		int[] NumberList2 = {1, 1, 5, 5, 10, 8, 7}; 
		assertEquals(5, CS2ProgrammingWeek1.FindCenteredAverage(NumberList2));
		int[] NumberList3 = {-10, -4, -2, -4, -2, 0};
		assertEquals(-3, CS2ProgrammingWeek1.FindCenteredAverage(NumberList3));
	}

	@Test
	public void testLookForTwoTwo() {
		int[] NumberList1 = {4, 2, 2, 3};
		int[] NumberList2 = {2, 2, 4};
		int[] NumberList3 = {2, 2, 4, 2};
		int[] NumberList4 = {2, 2, 4, 2, 5};
		int[] NumberList5 = {6, 2, 4, 2, 2, 3};
		int[] NumberList6 = {2};
		assertTrue(CS2ProgrammingWeek1.LookForTwoTwo(NumberList1));
		assertTrue(CS2ProgrammingWeek1.LookForTwoTwo(NumberList2));
		assertFalse(CS2ProgrammingWeek1.LookForTwoTwo(NumberList3));
		assertFalse(CS2ProgrammingWeek1.LookForTwoTwo(NumberList4));
		assertFalse(CS2ProgrammingWeek1.LookForTwoTwo(NumberList5));
		assertFalse(CS2ProgrammingWeek1.LookForTwoTwo(NumberList6));
	}

}
