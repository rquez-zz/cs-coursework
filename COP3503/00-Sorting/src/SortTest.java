import static org.junit.Assert.*;

import org.junit.Test;

public class SortTest {

	@Test
	public void testSelectionSort() 
	{
		assertArrayEquals(new int[]{1,2,3,4,5}, SelectionSort.sort(new int[]{2, 3, 5, 4, 1}));
	}

	@Test
	public void testInsertionSort() 
	{
		assertArrayEquals(new int[]{1,2,3,4,5}, InsertionSort.sort(new int[]{2, 3, 5, 4, 1}));
	}

	@Test
	public void testMergeSort() 
	{
		assertArrayEquals(new int[]{1,2,3,4,5}, MergeSort.sort(new int[]{2, 3, 5, 4, 1}));
	}

	@Test
	public void testBubbleSort() 
	{
		assertArrayEquals(new int[]{1,2,3,4,5}, BubbleSort.sort(new int[]{2,3,4,5,1}));
	}
}
