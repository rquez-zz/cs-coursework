/**
 * @author Ricardo Vasquez
 *
 */
public class HuffmanTreeNode implements Comparable<HuffmanTreeNode>{

	private int intByte;
	private int intFrequency;
	private HuffmanTreeNode left;
	private HuffmanTreeNode right;

	public HuffmanTreeNode(int intByte, int intFrequency, HuffmanTreeNode left, HuffmanTreeNode right)
	{
		this.setByteValue(intByte);
		this.setIntByteFrequency(intFrequency);
		this.setLeft(left);
		this.setRight(right);
	}

	/**
	 * Returns the depth of the node (how far its subtree goes down).
	 * If its just a node, it'll return only 1.
	 * 
	 * @param node
	 * @return
	 */
	public int getDepth(HuffmanTreeNode node)
	{
		if ( node == null)
			return 0;
		else
		{
			int leftDepth = getDepth(node.getLeft());
			int rightDepth = getDepth(node.getRight());
			
			if (leftDepth > rightDepth)
				return (leftDepth + 1);
			else
				return (rightDepth + 1);
		}
	}
	
	/**
	 * Returns true if node is a leaf. 
	 * 
	 * @return
	 */
	public boolean isLeaf() 
	{
		return ( left == null && right == null );
	}

	@Override
	public int compareTo(HuffmanTreeNode node) {
		return this.intFrequency - node.intFrequency;
	}
	
	public String toString()
	{
		return "{ " + this.getByteValue() + ":" + this.getIntByteFrequency() + " }";
	}

	public int getByteValue() {
		return intByte;
	}

	public void setByteValue(int intByte) {
		this.intByte = intByte;
	}

	public int getIntByteFrequency() {
		return intFrequency;
	}

	public void setIntByteFrequency(int intFrequency) {
		this.intFrequency = intFrequency;
	}

	public HuffmanTreeNode getLeft() {
		return left;
	}

	public void setLeft(HuffmanTreeNode left) {
		this.left = left;
	}

	public HuffmanTreeNode getRight() {
		return right;
	}

	public void setRight(HuffmanTreeNode right) {
		this.right = right;
	}

}
