
public class HuffmanTree extends HuffmanTreeNode {
	
	public HuffmanTree(HuffmanTreeNode left, HuffmanTreeNode right)
	{
		super(left.getByteValue() + right.getByteValue(), left.getIntByteFrequency() + right.getIntByteFrequency(), left, right);
	}
	
	/**
	 * @return result of two merged Huffman trees
	 */
	public static HuffmanTree merge(HuffmanTreeNode left, HuffmanTreeNode right)
	{
		return new HuffmanTree(left, right);
	}
	
	public String toString()
	{
		return printInline(this);
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
