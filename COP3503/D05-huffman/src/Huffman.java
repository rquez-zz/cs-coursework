/**
 * @author Ricardo Vasquez
 *
 */
public class Huffman {

	/**
	 * The entry point for this program.
	 * Takes in an array of ints that represent the frequency
	 * of a byte value i-128 where i is the index of this 
	 * frequency array. 
	 * 
	 * Using this frequency array, a binary heap is created and 
	 * from that binary heap, huffman trees are built and merged
	 * together. 
	 * 
	 * Using the huffman tree, the huffman codes are generated 
	 * @param freq
	 * @return boolean[][]
	 */
	public static boolean[][] makeHuffmanCodes(int[] freq)
	{

		int[][] processedFreqArray = processFreqArray(freq);
		
		BinaryHeap heap = new BinaryHeap(processedFreqArray);
		int heapLength = heap.getHuffmanHeap().length;

		HuffmanTree mainTree = null;
		for (int i = 1; i < heapLength; i++)
		{
			mainTree = null;
            HuffmanTreeNode node1 = heap.deleteFromHeapAt(heap.getSmallestNodeIndex());
            HuffmanTreeNode node2 = heap.deleteFromHeapAt(heap.getSmallestNodeIndex());
            mainTree = HuffmanTree.merge(node1, node2);
            heap.insertIntoHeap(mainTree);
		}

		boolean[][] codes = new boolean[heapLength][];

		int mainTreeDepth = mainTree.getDepth(mainTree);
		
		for (int i = 0; i < heapLength; i++)
		{
			codes[i] = getLeafHuffmanCode(new boolean[mainTreeDepth], 
					mainTree, 0, mainTreeDepth, processedFreqArray[i][0], processedFreqArray[i][1]);
		}
		
		return codes;
	}
	
	/**
	 * Returns a frequency table that has no zeros and pairs together
	 * frequencies and byte values.
	 * 
	 * @param freq
	 * @return int[][]
	 */
	private static int[][] processFreqArray(int[] freq)
	{
		int[][] mergedFreqArray = new int[freq.length][2];

		int[] bytes = new int[freq.length];
		for (int i = 0; i < bytes.length; i++)
			bytes[i] = i - 128;

		for (int i = 0; i < freq.length; i++)
		{
			mergedFreqArray[i][0] = freq[i];
			mergedFreqArray[i][1] = bytes[i];
		}

		int intNonZeroFreqCount = 0;
		for (int i = 0; i < mergedFreqArray.length - intNonZeroFreqCount; i++)
		{
            if (mergedFreqArray[i][0] == 0)
            {
            	intNonZeroFreqCount++;
            	boolean isSetToTheBack = false;
            	int j = 1;
            	while (!isSetToTheBack)
            	{
            		if (mergedFreqArray[mergedFreqArray.length - j][0] == 0)
            			j++;
            		else
            		{
                        int temp = mergedFreqArray[mergedFreqArray.length - j][0];
                        mergedFreqArray[mergedFreqArray.length - j][0] = mergedFreqArray[i][0];
                        mergedFreqArray[i][0] = temp;
                        isSetToTheBack = true;
            		}
            	}
            }
		}
		
		int[][] processedFreqArray = new int[freq.length - intNonZeroFreqCount][2];
		
		for (int i = 0; i < processedFreqArray.length; i++)
		{
			processedFreqArray[i][0] = mergedFreqArray[i][0];
			processedFreqArray[i][1] = mergedFreqArray[i][1];
		}
		
		return processedFreqArray;
	}
	
	/**
	 * Protected access to the proccessFreqArray method
	 * for testing purposes
	 * @param freq
	 * @return
	 */
	protected static int[][] processFreqArrayPublic(int[] freq)
	{
		return processFreqArray(freq);
	}
	
	/**
	 * 
	 * The recursive function that generates the huffman code for a specific node in the huffman tree.
	 * The function traverses through the entire tree until the node is found.
	 * As it traverses, the huffman code for that node is filled. 
	 * Once the node is found, the huffman code for that node is returned. 
	 * Before returning, the huffman code is truncated to show the accurate huffman code in the case of 
	 * varying array lengths. 
	 * 
	 * @param huffmanCode
	 * @param root
	 * @param depth
	 * @param mainTreeDepth
	 * @param intFreqToFind
	 * @param intByteToFind
	 * @return boolean[]
	 */
	public static boolean[] getLeafHuffmanCode(boolean[] huffmanCode, HuffmanTreeNode root, int depth, int mainTreeDepth, int intFreqToFind, int intByteToFind)
	{
		if (root != null)
		{
			if (root.getLeft() != null)
			{
				huffmanCode[depth] = false;
				boolean[] returnCode = getLeafHuffmanCode(huffmanCode, root.getLeft(), depth + 1, mainTreeDepth, intFreqToFind, intByteToFind);
				if (returnCode != null)
				{
					if (returnCode.length == mainTreeDepth)
					{
						boolean[] truncatedCode = new boolean[depth + 1];
                        for (int i = 0; i < truncatedCode.length; i++)
                                truncatedCode[i] = returnCode[i];
                        returnCode = truncatedCode;
					}
					return returnCode;
				}
				else
					huffmanCode[depth] = false;
			}

			if (root.getRight() != null)
			{
				huffmanCode[depth] = true;
				boolean[] returnCode = getLeafHuffmanCode(huffmanCode, root.getRight(), depth + 1, mainTreeDepth, intFreqToFind, intByteToFind);
				if (returnCode != null)
				{
					if (returnCode.length == mainTreeDepth)
					{
						boolean[] truncatedCode = new boolean[depth + 1];
                        for (int i = 0; i < truncatedCode.length; i++)
                                truncatedCode[i] = returnCode[i];
                        returnCode = truncatedCode;
					}
					return returnCode;
				}
				else
					huffmanCode[depth] = false;
			}

			if (root.isLeaf() && intFreqToFind == root.getIntByteFrequency() && intByteToFind == root.getByteValue())
				return huffmanCode;
			else
				return null;
		}
		else
			return null;
	}
	
}
