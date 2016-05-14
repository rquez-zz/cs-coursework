/**
 * @author Ricardo Vasquez
 *
 */
public class LinearProbingHashTable extends HashTable{

	public LinearProbingHashTable()
	{
		super();
	}
	
	public LinearProbingHashTable(int nTableSize)
	{
		super(nTableSize);
	}
	
	/**
	 * @param objData
	 * @throws Exception
	 * Inserts objData into the hashTable using linear probing for collision resolution
	 */
	public void put(DataObject objData) throws Exception
	{
		boolean isSet = false;
        long hash = Utils.HashFromString(objData.GetKey()) % m_nTableSize;

        if (m_nNumberOfElementsInList == m_nTableSize)
            throw new Exception("No space for object");

		while(!isSet)
		{
            if (m_ObjectArray[(int) hash] == null)
            {
            	m_nNumberOfElementsInList++;
                m_ObjectArray[(int) hash] = objData;
                isSet = true;
            }
            else
            {
                hash = hash + 1;
                hash = hash % m_nTableSize;
            }
		}
	}
}
