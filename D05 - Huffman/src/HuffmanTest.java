import static org.junit.Assert.*;

import org.junit.Test;


public class HuffmanTest {

	@Test
	public void testBinaryHeapSort() 
	{
		
		int[] freq = {3, 5, 7, 11, 2};
		int[] sortedFreq = {11, 7, 5, 3, 2};
		BinaryHeap heap = new BinaryHeap(freq);
		
		for (int i = 0; i < heap.getHuffmanHeap().length; i++)
		{
			assertEquals(sortedFreq[i], heap.getHuffmanHeap()[i].getIntByteFrequency());
		}

	}

	@Test
	public void testBinaryHeapInsertDelete() 
	{
		
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

	@Test
	public void testBuildHuffmanTree()
	{
		int[] freq = {3, 5, 7, 11, 2};
		
		BinaryHeap heap = new BinaryHeap(freq);
		
		// Delete 2
		HuffmanTreeNode node1 = heap.deleteFromHeapAt(heap.getSmallestNodeIndex());
		// Delete 3
		HuffmanTreeNode node2 = heap.deleteFromHeapAt(heap.getSmallestNodeIndex());
		
		// Make 5
		HuffmanTree tree1 = HuffmanTree.merge(node1, node2);
		
		// Put tree1 back into heap
		heap.insertIntoHeap(tree1);
		
		// Delete 5
		HuffmanTreeNode node3 = heap.deleteFromHeapAt(heap.getSmallestNodeIndex());

		// Delete 5
		HuffmanTreeNode node4 = heap.deleteFromHeapAt(heap.getSmallestNodeIndex());
		
		// Make 10
		HuffmanTree tree2 = HuffmanTree.merge(node3, node4);

		// Put tree2 into heap
		heap.insertIntoHeap(tree2);
		
		// Delete 7
		HuffmanTreeNode node5 = heap.deleteFromHeapAt(heap.getSmallestNodeIndex());

		// Delete 10
		HuffmanTreeNode node6 = heap.deleteFromHeapAt(heap.getSmallestNodeIndex());
		
		// Make 17
		HuffmanTree tree3 = HuffmanTree.merge(node5, node6);
	
		// Put tree3 into heap
		heap.insertIntoHeap(tree3);
		
		// Delete 11
		HuffmanTreeNode node7 = heap.deleteFromHeapAt(heap.getSmallestNodeIndex());

		// Delete 17
		HuffmanTreeNode node8 = heap.deleteFromHeapAt(heap.getSmallestNodeIndex());
		
		// Make 28
		HuffmanTree tree4 = HuffmanTree.merge(node7, node8);
	
		// Put tree4 into heap
		heap.insertIntoHeap(tree4);
		
		System.out.println(heap.getHuffmanHeap()[0].toString());
	}

}
