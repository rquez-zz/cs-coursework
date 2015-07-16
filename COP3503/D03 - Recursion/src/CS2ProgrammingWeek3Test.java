import static org.junit.Assert.*;

import org.junit.Test;


public class CS2ProgrammingWeek3Test {

	@Test
	public void testSubCopies() {
		assertTrue(CS2ProgrammingWeek3.subCopies("catcowcat", "cat", 2));
		assertFalse(CS2ProgrammingWeek3.subCopies("catcowcat", "cow", 2));
		assertTrue(CS2ProgrammingWeek3.subCopies("catcowcat", "cow", 1));
		assertTrue(CS2ProgrammingWeek3.subCopies("wordorworword", "word", 2));
	}

	@Test
	public void testSumDigitsInNumber() {
		assertEquals(9, CS2ProgrammingWeek3.sumDigitsInNumber(126));
		assertEquals(13, CS2ProgrammingWeek3.sumDigitsInNumber(49));
		assertEquals(4, CS2ProgrammingWeek3.sumDigitsInNumber(13));
		assertEquals(2, CS2ProgrammingWeek3.sumDigitsInNumber(2));
	}

	@Test
	public void testExponential() {
		assertEquals(3, CS2ProgrammingWeek3.exponential(3, 1));
		assertEquals(9, CS2ProgrammingWeek3.exponential(3, 2));
		assertEquals(27, CS2ProgrammingWeek3.exponential(3, 3));
		assertEquals(512, CS2ProgrammingWeek3.exponential(2, 9));
		assertEquals(1, CS2ProgrammingWeek3.exponential(2, 0));
	}

	@Test
	public void testChangeXtoY() {
		assertEquals("codey", CS2ProgrammingWeek3.changeXtoY("codex"));
		assertEquals("yyhiyy", CS2ProgrammingWeek3.changeXtoY("xxhixx"));
		assertEquals("yhiyhiy", CS2ProgrammingWeek3.changeXtoY("yhiyhiy"));
		assertEquals("y", CS2ProgrammingWeek3.changeXtoY("x"));
	}

	@Test
	public void testFind6() {
		assertTrue(CS2ProgrammingWeek3.find6(new int[]{1,6,4}, 0));
		assertFalse(CS2ProgrammingWeek3.find6(new int[]{1,4}, 0));
		assertTrue(CS2ProgrammingWeek3.find6(new int[]{6}, 0));
	}

	@Test
	public void testInsertAsterisk() {
		assertEquals("h*e*l*l*o", CS2ProgrammingWeek3.insertAsterisk("hello"));
		assertEquals("a*b*c", CS2ProgrammingWeek3.insertAsterisk("abc"));
		assertEquals("a*b", CS2ProgrammingWeek3.insertAsterisk("ab"));
	}

	@Test
	public void testNumberPairs() {
		assertEquals(1, CS2ProgrammingWeek3.numberPairs("axa"));
		assertEquals(2, CS2ProgrammingWeek3.numberPairs("axax"));
		assertEquals(1, CS2ProgrammingWeek3.numberPairs("axbx"));
		assertEquals(3, CS2ProgrammingWeek3.numberPairs("AxAxA"));
		assertEquals(0, CS2ProgrammingWeek3.numberPairs("xx"));
		assertEquals(0, CS2ProgrammingWeek3.numberPairs("abc"));
	}

	@Test
	public void testReduceChars() {
		assertEquals("yza", CS2ProgrammingWeek3.reduceChars("yyzzza"));
		assertEquals("Helo", CS2ProgrammingWeek3.reduceChars("Hello"));
		assertEquals("abcd", CS2ProgrammingWeek3.reduceChars("abcd"));
		assertEquals("abcd", CS2ProgrammingWeek3.reduceChars("abcccccd"));
	}

	@Test
	public void testNestedParens() {
		assertTrue(CS2ProgrammingWeek3.nestedParens("(())"));
		assertTrue(CS2ProgrammingWeek3.nestedParens("((()))"));
		assertFalse(CS2ProgrammingWeek3.nestedParens("(((x))"));
	}

	@Test
	public void testSubStrSub() {
		assertEquals(9, CS2ProgrammingWeek3.subStrSub("catcowcat", "cat"));
		assertEquals(3, CS2ProgrammingWeek3.subStrSub("catcowcat", "cow"));
		assertEquals(9, CS2ProgrammingWeek3.subStrSub("cccatcowcatxx", "cat"));
	}

}
