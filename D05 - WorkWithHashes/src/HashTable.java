/**
 * @author Ricardo Vasquez
 *
 */
public class HashTable {

	int m_nTableSize = 10000;
	DataObject[] m_ObjectArray;
	int m_nNumberOfElementsInList = 0;
	
	public HashTable()
	{
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	public HashTable(int nTableSize)
	{
		m_nTableSize = nTableSize;
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	public int size() 
	{ 
		return m_nNumberOfElementsInList; 
	}

	/**
	 * Doubles the size of the table 
	 */
	public void ExpandTable()
	{
		DataObject[] tempTable = m_ObjectArray;
		m_nTableSize *= 2;
		m_ObjectArray = new DataObject[m_nTableSize];
		for (int i = 0; i < tempTable.length; i++)
		{
			m_ObjectArray[i] = tempTable[i];
		}
	}

	/**
	 *	Print the hash table to the console 
	 */
	public void PrintTable()
	{
		for (int i = 0; i < m_ObjectArray.length; i++)
		{
			if (m_ObjectArray[i] != null)
			{
				System.out.println("INDEX:" + i + "\tKEY: " + m_ObjectArray[i].GetKey());

				if (m_ObjectArray[i] instanceof Chain && ((Chain)m_ObjectArray[i]).getNext() != null)
				{
					Chain currentChainLink = (Chain)m_ObjectArray[i];
					while(currentChainLink.getNext() != null)
					{
                        System.out.println("\tINDEX:" + i + "\tKEY: " + m_ObjectArray[i].GetKey());
						currentChainLink = currentChainLink.getNext();
					}
				}
			}
		}
	}
}
