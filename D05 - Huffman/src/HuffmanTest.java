import static org.junit.Assert.*;

import org.junit.Test;


public class HuffmanTest {

	@Test
	public void testBinaryHeapSort() {
		
		int[] freq = {3, 5, 7, 11, 2};
		int[] sortedFreq = {11, 7, 5, 3, 2};
		BinaryHeap heap = new BinaryHeap(freq);
		
		for (int i = 0; i < heap.getHuffmanHeap().length; i++)
		{
			assertEquals(sortedFreq[i], heap.getHuffmanHeap()[i].getIntByteFrequency());
		}

	}

	@Test
	public void testBinaryHeapInsertDelete() {
		
		int[] freq = {3, 5, 7, 11, 2};
		int[] sortedFreq = {11, 7, 5, 3, 2, 5};
		int[] sortedModFreq = {11, 7, 5, 3, 2};

		BinaryHeap heap = new BinaryHeap(freq);
		HuffmanTreeNode newNode = new HuffmanTreeNode(22, 5, null, null);
		heap.insertIntoHeap(newNode);

		for (int i = 0; i < heap.getHuffmanHeap().length; i++)
		{
			assertEquals(sortedFreq[i], heap.getHuffmanHeap()[i].getIntByteFrequency());
		}
		
		heap.deleteFromHeapAt(5);

		for (int i = 0; i < heap.getHuffmanHeap().length; i++)
		{
			assertEquals(sortedModFreq[i], heap.getHuffmanHeap()[i].getIntByteFrequency());
		}

	}

}
