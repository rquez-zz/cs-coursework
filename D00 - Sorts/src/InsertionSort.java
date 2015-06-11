/*
 * for i = 1 to length - 1
 * 		j = i
 * 		while (j > 0) and A[j-1] > A[j]
 * 			swap A[j] and A[j-1]
 * 			j = j - 1
 */
public class InsertionSort {
	
	public static int[] sort(int[] array)
	{
		for (int i = 1; i < array.length; i++)
		{
			int j = i;
			while( j > 0 && array[j-1] > array[j])
			{
				int temp = array[j-1];
				array[j-1] = array[j];
				array[j] = temp;
				j = j - 1;
			}
		}

		return array;
	}
}
