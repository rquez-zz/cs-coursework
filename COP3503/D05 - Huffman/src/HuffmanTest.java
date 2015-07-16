import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;


public class HuffmanTest {

	@Test
	public void testBinaryHeapSort() 
	{
		
		int[][] mergedfreq = new int[5][2];
		int[] freq = {3, 2, 7, 5, 11};
		int[] bytes = {-128, -127, -126, -125, -124};
		for (int i = 0; i < freq.length; i++)
		{
			mergedfreq[i][0] = freq[i];
			mergedfreq[i][1] = bytes[i];

		}
		BinaryHeap heap = new BinaryHeap(mergedfreq);
		
		int[] sortedFreq = {11, 7, 3, 2, 5};
		for (int i = 0; i < heap.getHuffmanHeap().length; i++)
		{
			assertEquals(sortedFreq[i], heap.getHuffmanHeap()[i].getIntByteFrequency());
		}

	}

	@Test
	public void testBinaryHeapInsertDelete() 
	{
		
		int[][] mergedfreq = new int[5][2];
		int[] freq = {3, 2, 7, 5, 11};
		int[] bytes = {-128, -127, -126, -125, -124};
		for (int i = 0; i < freq.length; i++)
		{
			mergedfreq[i][0] = freq[i];
			mergedfreq[i][1] = bytes[i];

		}

		int[] sortedFreq = {11, 7, 5, 2, 5, 3};
		int[] sortedModFreq = {11, 7, 5, 2, 5};

		BinaryHeap heap = new BinaryHeap(mergedfreq);
		HuffmanTreeNode newNode = new HuffmanTreeNode(22, 5, null, null);
		heap.insertIntoHeap(newNode);

		for (int i = 0; i < heap.getHuffmanHeap().length; i++)
			assertEquals(sortedFreq[i], heap.getHuffmanHeap()[i].getIntByteFrequency());
		
		heap.deleteFromHeapAt(5);

		for (int i = 0; i < heap.getHuffmanHeap().length; i++)
			assertEquals(sortedModFreq[i], heap.getHuffmanHeap()[i].getIntByteFrequency());

	}

	@Test
	public void testMakeHuffmanCodes1()
	{
		int[] freq = {1,2,3};
		int[][] freqTable = Huffman.processFreqArrayPublic(freq);
		boolean[][] codes = Huffman.makeHuffmanCodes(freq);
		
		assertNotNull(codes);
		System.out.println(stringifyHuffmanCodes(freqTable, codes));
	}
	
	@Test
	public void testMakeHuffmanCodes2()
	{
		int[] freq = {3, 5, 7, 11, 2, 6, 14, 22};
		int[][] freqTable = Huffman.processFreqArrayPublic(freq);
		boolean[][] codes = Huffman.makeHuffmanCodes(freq);
		
		assertNotNull(codes);
		System.out.println(stringifyHuffmanCodes(freqTable, codes));
	}
	
	@Test
	public void testMakeHuffmanCodes3()
	{
		Random randomGenerator = new Random();
		
		int[] freq = new int[100];
		for (int i = 0; i < freq.length; i++)
			freq[i] = randomGenerator.nextInt(20);

		int[][] freqTable = Huffman.processFreqArrayPublic(freq);
		boolean[][] codes = Huffman.makeHuffmanCodes(freq);
		
		assertNotNull(codes);
		System.out.println(stringifyHuffmanCodes(freqTable, codes));
	}

	@Test
	public void testMakeHuffmanCodes4()
	{
		Random randomGenerator = new Random();
		
		int[] freq = new int[256];
		for (int i = 0; i < freq.length; i++)
			freq[i] = randomGenerator.nextInt(20);

		int[][] freqTable = Huffman.processFreqArrayPublic(freq);
		boolean[][] codes = Huffman.makeHuffmanCodes(freq);
		
		assertNotNull(codes);
		System.out.println(stringifyHuffmanCodes(freqTable, codes));
	}
	
	@Test
	public void testMakeHuffmanCodes5()
	{
		int[] freq = {1, 2};
		int[][] freqTable = Huffman.processFreqArrayPublic(freq);
		boolean[][] codes = Huffman.makeHuffmanCodes(freq);
		
		assertNotNull(codes);
		System.out.println(stringifyHuffmanCodes(freqTable, codes));
	}

	public String stringifyHuffmanCodes(int[][] freqTable, boolean[][] codes)
	{
		StringBuffer sb = new StringBuffer();

		sb.append(freqTable.length + " nonzero elements in table.\n");
		for (int i = 0; i < codes.length; i++)
		{
			sb.append(freqTable[i][0] + ":" + freqTable[i][1] + "\t");
			for (int j = 0; j < codes[i].length; j++)
			{
				if (codes[i][j])
					sb.append(1);
				else
					sb.append(0);
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
