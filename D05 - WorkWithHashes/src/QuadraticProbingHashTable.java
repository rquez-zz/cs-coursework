/**
 * @author Ricardo Vasquez
 *
 */
public class QuadraticProbingHashTable extends HashTable {

	public QuadraticProbingHashTable()
	{
		super();
	}
	
	public QuadraticProbingHashTable(int nTableSize)
	{
		super(nTableSize);
	}
	
	/**
	 * @param objData
	 * @throws Exception
	 * Inserts objData into the hashTable using quadratic probing for collision resolution
	 */
	public void put(DataObject objData) throws Exception
	{
		boolean isSet = false;
        long hash = Utils.HashFromString(objData.GetKey()) % m_nTableSize;
        int intQuadraticIncrement = 1;

        if (m_nNumberOfElementsInList == m_nTableSize)
            throw new Exception("Empty space in hash table not found");

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
            	if (intQuadraticIncrement == m_nTableSize)
            		throw new Exception("Empty space in hash table not found");

                hash = hash + intQuadraticIncrement * intQuadraticIncrement;
                hash = hash % m_nTableSize;
                intQuadraticIncrement++;
            }
		}
	}
}
