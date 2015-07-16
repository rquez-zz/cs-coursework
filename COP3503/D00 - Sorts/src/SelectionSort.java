/*
 * Pseudocode
 * 
 *for i = 0 to array length - 1
 *	smallest = i
 *	for j = i to array length - 1
 *		if A[j] < A[smallest]
 *			smallest = A[j]
 *	swap A[j], A[smallest] 
 * 
 */

public class SelectionSort {
	
	public static int[] sort(int[] array) 
	{
		for (int i = 0; i < array.length; i++)
		{
			int indexSmallest = i;
			
			for (int j = i; j < array.length; j++)
			{
				if (array[j] < array[indexSmallest])
				{
					indexSmallest = j; 
				}
			}
			
			int temp = array[i];
			array[i] = array[indexSmallest];
			array[indexSmallest] = temp;
			
		}
		return array;
	}
	
}
