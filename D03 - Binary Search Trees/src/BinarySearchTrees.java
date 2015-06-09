
public class BinarySearchTrees 
{
	
	static BST m_objBST = new BST();
	
	public static void main(String[] args) 
	{
		
		m_objBST.Insert(10);
		m_objBST.Insert(15);
		m_objBST.Insert(5);
		m_objBST.Insert(3);
		m_objBST.Insert(6);
		m_objBST.Insert(18);
		m_objBST.Insert(13);
		m_objBST.Insert(120);
		m_objBST.Insert(1);
		m_objBST.Delete(5);
		m_objBST.PrintInOrder(m_objBST.m_objRootNode);
	}
	
}
