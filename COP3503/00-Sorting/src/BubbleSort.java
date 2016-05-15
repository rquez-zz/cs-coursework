public class BubbleSort {

	public static int[] sort(int[] array) 
	{
		for (int j = 0; j < array.length; j++)
		{
                for (int i = 1; i < array.length - j; i++)
                {
                        if (array[i-1] > array[i])
                        {
                                int temp = array[i-1];
                                array[i-1] = array[i];
                                array[i] = temp;
                        }
                }
		}
		return array;
	}
}
