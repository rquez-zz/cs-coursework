/**
 * @author Ricardo Vasquez
 *
 */
public class BinaryHeap {

	// The Array representation of the binary heap
	private HuffmanTreeNode[] HuffmanHeap;
	
	public BinaryHeap(int freq[][])
	{
		HuffmanHeap = heapify(freq);
	}
	
	/**
	 * Takes in the frequency array and transforms it into
	 * a binary heap array implementation.
	 * 
	 * The frequency and byte values are initialized into a 
	 * HuffmanTreeNode
	 * 
	 * @param freq
	 * @return HuffmanTreeNode[]
	 */
	private HuffmanTreeNode[] heapify(int freq[][])
	{
		HuffmanTreeNode[] heap = new HuffmanTreeNode[freq.length];
		for (int i = 0; i < freq.length; i++)
		{
			heap[i] = new HuffmanTreeNode(freq[i][1], freq[i][0], null, null);
		}
		
		heap = heapSort(heap);

		return heap;
	}
	
	/**
	 * Gets the index of the lowest value in the 
	 * binary heap array.
	 * 
	 * @return int
	 */
	public int getSmallestNodeIndex()
	{
		HuffmanTreeNode[] heap = this.getHuffmanHeap();
		
		int smallest = 0;
		
		for (int i = 1; i < heap.length; i++)
		{
			if (heap[smallest].compareTo(heap[i]) > 0)
				smallest = i;
		}
		
		return smallest;
	}
	
	/**
	 * Deletes a node from the binary heap at a specific
	 * index and returns the deleted node.
	 * 
	 * @param index
	 * @return HuffmanTreeNode
	 */
	public HuffmanTreeNode deleteFromHeapAt(int index)
	{
		HuffmanTreeNode[] heap = swap(this.getHuffmanHeap(), index, this.getHuffmanHeap().length - 1);
		
		HuffmanTreeNode deletedNode = heap[heap.length - 1];

		HuffmanTreeNode[] newHeap = new HuffmanTreeNode[heap.length - 1];
	
		for (int i = 0; i < newHeap.length; i++)
			newHeap[i] = heap[i];
		
		newHeap = heapSort(newHeap);

		this.setHuffmanHeap(newHeap);
		
		return deletedNode;
	}
	
	/**
	 * Inserts a new node into the binary heap.
	 * 
	 * @param node
	 */
	public void insertIntoHeap(HuffmanTreeNode node)
	{

		HuffmanTreeNode[] heap = this.getHuffmanHeap();

		HuffmanTreeNode[] newHeap = new HuffmanTreeNode[heap.length + 1];
		
		for (int i = 0; i < heap.length; i++)
		{
			newHeap[i] = heap[i];
		}
		
		newHeap[heap.length] = node;
		
		newHeap = heapSort(newHeap);
		
		this.setHuffmanHeap(newHeap);
	}
	
	/**
	 * Sorts the binary heap as a proper heap.
	 * 
	 * @param heap
	 * @return HuffmanTreeNode[]
	 */
	private HuffmanTreeNode[] heapSort(HuffmanTreeNode[] heap)
	{
		boolean unsorted = true;
		while(unsorted)
		{
			// Sort and swap
			for (int i = 0; i < heap.length; i++)
            {
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
	
	/**
	 * Swaps two nodes in the binary heap and
	 * returns the binary heap
	 * 
	 * @param heap
	 * @param index1
	 * @param index2
	 * @return HuffmanTreeNode[]
	 */
	private HuffmanTreeNode[] swap(HuffmanTreeNode[] heap, int index1, int index2)
	{
		HuffmanTreeNode temp = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = temp;
		return heap;
	}


	/**
	 * Returns the string representation of the binary
	 * heap inorder traversal. Called by toString()
	 * on the root node of the binary heap.
	 * 
	 * @param node
	 * @return String
	 */
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

	public String toString()
	{
		return printInline(HuffmanHeap[0]);
	}

	public HuffmanTreeNode[] getHuffmanHeap() {
		return HuffmanHeap;
	}

	public void setHuffmanHeap(HuffmanTreeNode[] huffmanHeap) {
		HuffmanHeap = huffmanHeap;
	}


}
