/**
 * @author Ricardo Vasquez
 *
 */
public class SeparateChainingHashTable extends HashTable{

	public SeparateChainingHashTable()
	{
		super();
	}
	
	public SeparateChainingHashTable(int nTableSize)
	{
		super(nTableSize);
	}
	
	/**
	 * @param objData
	 * @throws Exception
	 * Inserts objData into the hashTable using separate chaining for collision resolution
	 */
	public void put(DataObject objData) throws Exception
	{
		boolean isSet = false;
        long hash = Utils.HashFromString(objData.GetKey()) % m_nTableSize;
        Chain chainLink = new Chain(objData);

        if (m_nNumberOfElementsInList == m_nTableSize)
            throw new Exception("Empty space in hash table not found");

		while(!isSet)
		{
            if (m_ObjectArray[(int) hash] == null)
            {
            	m_nNumberOfElementsInList++;
                m_ObjectArray[(int) hash] = chainLink;
                isSet = true;
            }
            else
            {
            	Chain currentChainLink = (Chain) m_ObjectArray[(int) hash];
            	while ( currentChainLink.getNext() != null )
            		currentChainLink = currentChainLink.getNext();

            	currentChainLink.setNext(chainLink);
            	isSet = true;
            }
		}
	}
}
