
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

	public boolean isLeaf() 
	{
		return ( left == null && right == null );
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

	@Override
	public int compareTo(HuffmanTreeNode node) {
		return this.intFrequency - node.intFrequency;
	}
	
	public String toString()
	{
		return "{ intByte: " + intByte + ", intFrequency: " + intFrequency + " }";
	}
}
