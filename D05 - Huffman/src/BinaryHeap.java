import java.util.ArrayList;


public class BinaryHeap implements Comparable<BinaryHeap>{

	ArrayList<HuffmanTreeNode> HuffmanHeap;
	
	public BinaryHeap(int freq[])
	{
		HuffmanHeap = heapify(freq);
		
	}

	private ArrayList<HuffmanTreeNode> heapify(int freq[])
	{
		ArrayList<HuffmanTreeNode> heap = new ArrayList<HuffmanTreeNode>();

		// Count the number of non zero frequencies
		int intNonZeroFreqCount = 0;
		for (int i = 0; i < freq.length; i++)
		{
            // Create HuffmanTreeNodes for each non zero frequency
            HuffmanTreeNode node = null;
            if (intNonZeroFreqCount > 0)
            {
                node = new HuffmanTreeNode(freq[i], (byte)(i - 129));
                HuffmanHeap.add(node);
            }
		}
		
		return heap;
	}
	
	public HuffmanTree buildHuffmanTree()
	{
		return HuffmanTree.merge(null, null);
	}

	@Override
	public int compareTo(BinaryHeap arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
