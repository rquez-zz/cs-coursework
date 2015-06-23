
public class HuffmanTree extends HuffmanTreeNode {
	
	public HuffmanTree(HuffmanTree left, HuffmanTree right)
	{
		super(left.getIntByteFrequency() + right.getIntByteFrequency(), (byte)0);
		this.setLeft(left);
		this.setRight(right);
	}
	
	/**
	 * @return result of two merged Huffman trees
	 */
	public static HuffmanTree merge(HuffmanTree left, HuffmanTree right)
	{
		return new HuffmanTree(left, right);
	}
	
}
