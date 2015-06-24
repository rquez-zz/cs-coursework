public class BinaryHeap {

	private HuffmanTreeNode[] HuffmanHeap;
	
	public BinaryHeap(int freq[])
	{
		HuffmanHeap = heapify(freq);
	}

	private HuffmanTreeNode[] heapify(int freq[])
	{
		// Count the number of non zero frequencies
		int intNonZeroFreqCount = 0;
		for (int i = 0; i < freq.length; i++)
		{
            if (freq[i] > 0)
            	intNonZeroFreqCount++;
		}
		
		// Initialize Heap Array 
		HuffmanTreeNode[] heap = new HuffmanTreeNode[intNonZeroFreqCount];

		// Create HuffmanTreeNodes for each frequency
		for (int i = 0; i < freq.length; i++)
		{
			if (freq[i] > 0)
				heap[i] = new HuffmanTreeNode(i, freq[i], null, null);
		}
		
		// Sort the array to a proper binary heap implementation
		heap = heapSort(heap);

		// Set left right nodes
		heap = setLeftRightNodes(heap);

		return heap;
	}
	
	public void deleteFromHeapAt(int index)
	{
		HuffmanTreeNode[] heap = this.getHuffmanHeap();

		// Make a new smaller array
		HuffmanTreeNode[] newHeap = new HuffmanTreeNode[heap.length - 1];
	
		for (int i = 0; i < heap.length; i++)
		{
			if (i == index)
			{
				if (i != (heap.length - 1))
				{
                    newHeap[i] = heap[i + 1];
                    i = i + 1;
				}
			}
			else
				newHeap[i] = heap[i];
		}
		
		// Sort and set left/right relations
		newHeap = heapSort(newHeap);
		newHeap = setLeftRightNodes(newHeap);

		this.setHuffmanHeap(newHeap);
	}
	
	public void insertIntoHeap(HuffmanTreeNode node)
	{

		HuffmanTreeNode[] heap = this.getHuffmanHeap();

		// Make a new larger array
		HuffmanTreeNode[] newHeap = new HuffmanTreeNode[heap.length + 1];
		
		for (int i = 0; i < heap.length; i++)
		{
			newHeap[i] = heap[i];
		}
		
		newHeap[heap.length] = node;
		
		// Sort and set left/right relations
		newHeap = heapSort(newHeap);
		newHeap = setLeftRightNodes(newHeap);
		
		this.setHuffmanHeap(newHeap);
	}
	
	private HuffmanTreeNode[] setLeftRightNodes(HuffmanTreeNode[] heap)
	{
		// Clear the left and right relations
		for (int i =0 ; i < heap.length; i++)
		{
			heap[i].setLeft(null);
			heap[i].setRight(null);
		}

		// Set left and right nodes
		for (int i = 0; i < heap.length; i++)
		{
            if (heap.length > (2*i + 1))
                heap[i].setLeft(heap[2*i + 1]);
            if (heap.length > (2*i + 2))
                heap[i].setRight(heap[2*i + 2]);
		}
		return heap;
	}

	public HuffmanTreeNode[] getHuffmanHeap() {
		return HuffmanHeap;
	}

	public void setHuffmanHeap(HuffmanTreeNode[] huffmanHeap) {
		HuffmanHeap = huffmanHeap;
	}

	private HuffmanTreeNode[] heapSort(HuffmanTreeNode[] heap)
	{
		boolean unsorted = true;
		while(unsorted)
		{
			// Sort and swap
			for (int i = 0; i < heap.length; i++)
            {
                // Check left
				if (heap.length > (2*i + 1))
				{
                    if (heap[i].compareTo(heap[2*i + 1]) < 0)
                        heap = swap(heap, i, (2*i + 1));
				}

                // Check right
                if (heap.length > (2*i + 2))
                {
                    if (heap[i].compareTo(heap[2*i + 2]) < 0)
                        heap = swap(heap, i, (2*i + 2));
                }
            }
			
			unsorted = false;

			// Check if sorted
			for (int i = 0; i < heap.length; i++)
			{
                // Check left
				if (heap.length > (2*i + 1))
				{
                    if (heap[i].compareTo(heap[2*i + 1]) < 0)
                    	unsorted = true;
				}

                // Check right
                if (heap.length > (2*i + 2))
                {
                    if (heap[i].compareTo(heap[2*i + 2]) < 0)
                    	unsorted = true;
                }
					
			}
		}
		
		return heap;
	}
	
	private HuffmanTreeNode[] swap(HuffmanTreeNode[] heap, int index1, int index2)
	{
		HuffmanTreeNode temp = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = temp;
		return heap;
	}
	
	public String toString()
	{
		return printInline(HuffmanHeap[0]);
	}

	public String printInline(HuffmanTreeNode node)
	{
		if (node != null)
		{
			StringBuffer sb = new StringBuffer();

			if (node.getLeft() != null)
                sb.append(this.printInline(node.getLeft()));

			sb.append(node.toString());
			
			if (node.getRight() != null)
                sb.append(this.printInline(node.getRight()));
			
			return sb.toString();
		}
		else
			return null;
	}

}
