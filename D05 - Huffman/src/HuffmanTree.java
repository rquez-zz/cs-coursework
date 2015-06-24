
public class HuffmanTree extends HuffmanTreeNode {
	
	public HuffmanTree(HuffmanTree left, HuffmanTree right)
	{
	}
	
	/**
	 * @return result of two merged Huffman trees
	 */
	public static HuffmanTree merge(HuffmanTree left, HuffmanTree right)
	{
		return new HuffmanTree(left, right);
	}
	
}
