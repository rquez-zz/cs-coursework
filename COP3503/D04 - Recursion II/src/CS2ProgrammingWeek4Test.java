import static org.junit.Assert.*;

import org.junit.Test;


public class CS2ProgrammingWeek4Test {

	@Test
	public void testGroupSumsTarget() {
		assertTrue(CS2ProgrammingWeek4.groupSumsTarget(0, new int[]{2,4,8}, 10));
		assertTrue(CS2ProgrammingWeek4.groupSumsTarget(0, new int[]{2,4,8}, 10));
		assertFalse(CS2ProgrammingWeek4.groupSumsTarget(0, new int[]{2,4,8}, 9));
	}

	@Test
	public void testGroupSumsTarget6() {
		assertTrue(CS2ProgrammingWeek4.groupSumsTarget6(0, new int[]{5,6,2,}, 8));
		assertFalse(CS2ProgrammingWeek4.groupSumsTarget6(0, new int[]{5,6,2}, 9));
		assertFalse(CS2ProgrammingWeek4.groupSumsTarget6(0, new int[]{5,6,2}, 7));
	}

	@Test
	public void testGroupSumsTargetNoAdj() {
		assertTrue(CS2ProgrammingWeek4.groupSumsTargetNoAdj(0, new int[]{2, 5, 10, 4}, 12));
		assertFalse(CS2ProgrammingWeek4.groupSumsTargetNoAdj(0, new int[]{2, 5, 10, 4}, 14));
		assertFalse(CS2ProgrammingWeek4.groupSumsTargetNoAdj(0, new int[]{2, 5, 10, 4}, 7));
	}

	@Test
	public void testGroupSumsTarget5() {
		assertTrue(CS2ProgrammingWeek4.groupSumsTarget5(0, new int[]{2, 5, 10, 4}, 19));
		assertTrue(CS2ProgrammingWeek4.groupSumsTarget5(0, new int[]{2, 5, 10, 4}, 17));
		assertFalse(CS2ProgrammingWeek4.groupSumsTarget5(0, new int[]{2, 5, 10, 4}, 12));
		assertTrue(CS2ProgrammingWeek4.groupSumsTarget5(0, new int[]{2, 5, 1, 10, 4}, 19));
		assertFalse(CS2ProgrammingWeek4.groupSumsTarget5(0, new int[]{2, 5, 1, 10, 4}, 6));
	}

	@Test
	public void testGroupSumsTargetClump() {
		assertTrue(CS2ProgrammingWeek4.groupSumsTargetClump(0, new int[]{2, 4, 8}, 10));
		assertTrue(CS2ProgrammingWeek4.groupSumsTargetClump(0, new int[]{1, 2, 4, 8, 1}, 10));
		assertFalse(CS2ProgrammingWeek4.groupSumsTargetClump(0, new int[]{2, 4, 4, 8}, 14));
	}

	@Test
	public void testDivideArray() {
        assertTrue(CS2ProgrammingWeek4.divideArray(new int[]{2,2,})); 
        assertFalse(CS2ProgrammingWeek4.divideArray(new int[]{2,3}));
        assertTrue(CS2ProgrammingWeek4.divideArray(new int[]{5, 2, 3,}));
	}

	@Test
	public void testOddDivide10() {
		assertTrue(CS2ProgrammingWeek4.oddDivide10(new int[]{5,5,5}));
		assertFalse(CS2ProgrammingWeek4.oddDivide10(new int[]{5,5,6}));
		assertTrue(CS2ProgrammingWeek4.oddDivide10(new int[]{5,5,6,1}));
	}

	@Test
	public void testDivide53() {
		assertTrue(CS2ProgrammingWeek4.divide53(new int[]{1,1}));
		assertFalse(CS2ProgrammingWeek4.divide53(new int[]{1,1,1}));
		assertTrue(CS2ProgrammingWeek4.divide53(new int[]{2,4,2}));
	}
}
