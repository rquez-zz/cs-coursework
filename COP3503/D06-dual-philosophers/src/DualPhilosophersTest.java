import static org.junit.Assert.*;

import org.junit.Test;


public class DualPhilosophersTest {

	@Test
	public void testCaseEqualRelationsAndEssays() {

		int[][] input = new int [4][2];
		input[0] = new int[]{3, 3};
		input[1] = new int[]{1, 2};
		input[1] = new int[]{2, 3};
		input[2] = new int[]{3, 1};
		
		assertEquals(0, DualPhilosophers.findArrangements(input));
	}

	@Test
	public void testCrossRelation() {

		int[][] input = new int [4][2];
		input[0] = new int[]{4, 3};
		input[1] = new int[]{3, 4};
		input[2] = new int[]{1, 2};
		input[3] = new int[]{4, 3};

		assertEquals(0, DualPhilosophers.findArrangements(input));
	}
	
	@Test
	public void testOneToOneWithOneLessRelation()
	{
		
		int[][] input = new int [5][2];
		input[0] = new int[]{5, 4};
		input[1] = new int[]{3, 1};
		input[2] = new int[]{4, 2};
		input[3] = new int[]{1, 5};
		input[4] = new int[]{5, 4};
		
		assertEquals(1, DualPhilosophers.findArrangements(input));
	}
	
	@Test
	public void testManyArrangements()
	{
		
		int[][] input = new int [5][2];
		input[0] = new int[]{5, 4};
		input[1] = new int[]{1, 5};
		input[2] = new int[]{5, 2};
		input[3] = new int[]{3, 2};
		input[4] = new int[]{4, 3};
		
		assertEquals(2, DualPhilosophers.findArrangements(input));
	}
	
	public void printArray(int[] array)
	{
		for (int i = 0; i < array.length; i++)
			System.out.println(array[i]);
	}
}
