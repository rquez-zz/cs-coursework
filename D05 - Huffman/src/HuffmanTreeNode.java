
public class HuffmanTreeNode {

	private byte byteValue;
	private int intByteFrequency;
	private HuffmanTreeNode left;
	private HuffmanTreeNode right;

	public HuffmanTreeNode(int intByteFrequency, byte byteValue)
	{
		this.setByteValue(byteValue);
		this.setIntByteFrequency(intByteFrequency);
		this.setLeft(null);
		this.setRight(null);
	}

	public byte getByteValue() {
		return byteValue;
	}

	public void setByteValue(byte byteValue) {
		this.byteValue = byteValue;
	}

	public int getIntByteFrequency() {
		return intByteFrequency;
	}

	public void setIntByteFrequency(int intByteFrequency) {
		this.intByteFrequency = intByteFrequency;
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
