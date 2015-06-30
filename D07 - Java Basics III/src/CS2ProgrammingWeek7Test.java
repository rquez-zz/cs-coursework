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
		fail("Not yet implemented");
	}

	@Test
	public void testInnerAppearsInOuter() {
		fail("Not yet implemented");
	}

	@Test
	public void testMaximumMirrorSpan() {
		fail("Not yet implemented");
	}

	@Test
	public void testFollow4with5() {
		fail("Not yet implemented");
	}

	@Test
	public void testReverseArithSeries() {
		fail("Not yet implemented");
	}

	@Test
	public void testLargestClump() {
		fail("Not yet implemented");
	}

}
