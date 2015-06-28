import static org.junit.Assert.*;

import org.junit.Test;


public class DualPhilosophersTest {

	@Test
	public void testCaseEqualRelationsAndEssays() {

		int[][] input = new int [4][2];
		input[0] = new int[]{3, 3};
		input[1] = new int[]{1, 2};
		input[2] = new int[]{3, 2};
		input[3] = new int[]{2, 1};
		
		assertEquals(0, DualPhilosophers.findArragements(input));
	}

	@Test
	public void testCrossRelation() {

		int[][] input = new int [4][2];
		input[0] = new int[]{4, 3};
		input[1] = new int[]{3, 4};
		input[2] = new int[]{4, 3};
		input[3] = new int[]{1, 2};

		assertEquals(0, DualPhilosophers.findArragements(input));
	}
	
	public void printArray(int[] array)
	{
		for (int i = 0; i < array.length; i++)
			System.out.println(array[i]);
	}
}
