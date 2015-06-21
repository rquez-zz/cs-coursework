
public class Linear {

	int m_nTableSize = 10000;
	DataObject[] m_ObjectArray;
	int m_nNumberOfElementsInList = 0;
	
	public Linear()
	{
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	public Linear(int nTableSize)
	{
		m_nTableSize = nTableSize;
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	public int size()
	{
		return 0;
	}
	
	public void put(DataObject objData) throws Exception
	{
		
	}
	
	public void ExpandTable()
	{
		
	}
}
