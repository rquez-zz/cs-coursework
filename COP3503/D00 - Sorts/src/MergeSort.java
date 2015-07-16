
public class MergeSort {

	public static int[] sort(int[] array)
	{
		return doMergeSort(array, 0, array.length - 1);
	}
	private static int[]  doMergeSort(int[] array, int lowIndex, int highIndex)
	{
		// Split while there's more than 1 element in the array
		if (lowIndex < highIndex)
		{
			// Due to int division, middle leans to the left
			int middle = lowIndex + (highIndex - lowIndex) / 2;
			
			// Split the left half
			doMergeSort(array, lowIndex, middle);
		
			// Split the right half
			doMergeSort(array, middle + 1, highIndex);
		
			// Returned the merged halves
			return merge(array, lowIndex, middle, highIndex);
		}
		
		return null;
	}
	private static int[] merge(int[] array, int lowIndex, int middle, int highIndex) 
	{
		// Make a temp array and fill it with contents of the array
		int[] tempMergeArray = new int[array.length];
		for (int i = lowIndex; i <= highIndex; i++)
		{
			tempMergeArray[i] = array[i];
		}

        int j = middle + 1;
        
        // A lowIndex that increases within the inner loop
	    int i = lowIndex;

        // A lowIndex that stays constant within the inner loop
        int k = lowIndex;
        while (i <= middle && j <= highIndex) {
            if (tempMergeArray[i] <= tempMergeArray[j]) {
                array[k] = tempMergeArray[i];
                i++;
            } else {
                array[k] = tempMergeArray[j];
                j++;
            }
            k++;
        }
        
        while (i <= middle) {
            array[k] = tempMergeArray[i];
            k++;
            i++;
        }
		return array;
	}
}
