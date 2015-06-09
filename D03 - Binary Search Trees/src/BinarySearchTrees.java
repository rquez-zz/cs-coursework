
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
		m_objBST.Delete(15);
		m_objBST.Search(15);
		System.out.println(m_objBST.GetMin());
	}
	
}
