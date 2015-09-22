import static org.junit.Assert.*;

import org.junit.Test;


public class CS2ProgrammingWeek7Test {

	@Test
	public void testMaximumSpan() {
		assertEquals(4, CS2ProgrammingWeek7.maximumSpan(new int[]{1, 2, 1, 1, 3}));
		assertEquals(6, CS2ProgrammingWeek7.maximumSpan(new int[]{1, 4, 2, 1, 4 ,1, 4}));
		assertEquals(6, CS2ProgrammingWeek7.maximumSpan(new int[]{1, 4, 2, 1, 4 ,4, 4}));
		assertEquals(1, CS2ProgrammingWeek7.maximumSpan(new int[]{1}));
	}

	@Test
	public void testCanStabilize() {
		assertEquals(true, CS2ProgrammingWeek7.canStabilize(new int[]{1, 1, 1, 2, 1}));
		assertEquals(false, CS2ProgrammingWeek7.canStabilize(new int[]{2, 1, 1, 2, 1}));
		assertEquals(true, CS2ProgrammingWeek7.canStabilize(new int[]{10, 10}));
	}

	@Test
	public void testArithmeticSeries() {
		assertArrayEquals(new int[]{1, 1, 2, 1, 2, 3}, CS2ProgrammingWeek7.arithmeticSeries(3));
		assertArrayEquals(new int[]{1, 1, 2, 1, 2, 3, 1, 2, 3, 4}, CS2ProgrammingWeek7.arithmeticSeries(4));
		assertArrayEquals(new int[]{1, 1, 2}, CS2ProgrammingWeek7.arithmeticSeries(2));
		assertArrayEquals(new int[]{1}, CS2ProgrammingWeek7.arithmeticSeries(1));
	}

	@Test
	public void testFollow3with4() {
		assertArrayEquals(new int[]{1, 3, 4, 1}, CS2ProgrammingWeek7.follow3with4(new int[]{1, 3, 1, 4}));
		assertArrayEquals(new int[]{1, 3, 4, 1, 1, 3, 4}, CS2ProgrammingWeek7.follow3with4(new int[]{1, 3, 1, 4, 4, 3, 1}));
		assertArrayEquals(new int[]{3, 4, 2, 2}, CS2ProgrammingWeek7.follow3with4(new int[]{3, 4, 2, 2}));
		assertArrayEquals(new int[]{3, 4}, CS2ProgrammingWeek7.follow3with4(new int[]{3, 4}));
	}

	@Test
	public void testInnerAppearsInOuter() {
		assertTrue(CS2ProgrammingWeek7.innerAppearsInOuter(new int[]{1,2,4,6}, new int[]{2, 4}));
		assertFalse(CS2ProgrammingWeek7.innerAppearsInOuter(new int[]{1,2,4,6}, new int[]{2, 3, 4}));
		assertTrue(CS2ProgrammingWeek7.innerAppearsInOuter(new int[]{1,2,4,4,6}, new int[]{2, 4}));
	}

	@Test
	public void testMaximumMirrorSpan() {
		assertEquals(3, CS2ProgrammingWeek7.maximumMirrorSpan(new int[]{1, 2, 3, 8, 9, 3, 2, 1}));
		assertEquals(3, CS2ProgrammingWeek7.maximumMirrorSpan(new int[]{1, 2, 3, 8, 9, 3, 2, 1}));
		assertEquals(2, CS2ProgrammingWeek7.maximumMirrorSpan(new int[]{7, 1, 2, 9, 7, 2, 1,}));
	}

	@Test
	public void testFollow4with5() {
		assertArrayEquals(new int[]{9,4,5,4,5,9}, CS2ProgrammingWeek7.follow4with5(new int[]{5, 4, 9, 4, 9, 5}));
		assertArrayEquals(new int[]{1,4,5,1}, CS2ProgrammingWeek7.follow4with5(new int[]{1,4,1,5}));
		assertArrayEquals(new int[]{1,4,5,1,1,4,5}, CS2ProgrammingWeek7.follow4with5(new int[]{1,4,1,5,5,4,1}));
	}

	@Test
	public void testReverseArithSeries() {
		assertArrayEquals(new int[]{0, 0, 1, 0, 2, 1, 3, 2, 1}, CS2ProgrammingWeek7.reverseArithSeries(3));
		assertArrayEquals(new int[]{0, 1, 2, 1}, CS2ProgrammingWeek7.reverseArithSeries(2));
		assertArrayEquals(new int[]{0, 0, 0, 1, 0, 0, 2, 1, 0, 3, 2, 1, 4, 3, 2, 1}, CS2ProgrammingWeek7.reverseArithSeries(4));
	}

	@Test
	public void testLargestClump() {
		assertEquals(2, CS2ProgrammingWeek7.largestClump(new int[]{1,2,2,3,4,4}));
		assertEquals(2, CS2ProgrammingWeek7.largestClump(new int[]{1,1,2,1,1}));
		assertEquals(1, CS2ProgrammingWeek7.largestClump(new int[]{1,1,1,1,1}));
	}

}
