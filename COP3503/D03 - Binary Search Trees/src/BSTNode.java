
public class BSTNode 
{
	BSTNode m_objLeftNode, m_objRightNode;
	int m_nKeyValue, m_nRank, m_nSubTreeSize;
	
	public BSTNode()
	{
		// Explicit set to null may not be necessary,
		//   but provided for clarity.
		m_objLeftNode = m_objRightNode = null;
		
		// Set this node's key value to default of 0.
		m_nKeyValue = 0;
		m_nRank = 0;
		m_nSubTreeSize = 0;
	}

	public BSTNode(int nKeyValue)
	{
		// Explicit set to null may not be necessary,
		//   but provided for clarity.
		m_objLeftNode = m_objRightNode = null;
		
		// Set this node's key value
		m_nKeyValue = nKeyValue;
		m_nRank = 0;
		m_nSubTreeSize = 0;
	}

	// Accessor method to set the left node.
	public void SetLeftNode( BSTNode objLeftNode)
	{
		// Assign the left node object reference.
		m_objLeftNode = objLeftNode;
	}
	
	// Accessor method to set the right node.
	public void SetRightNode( BSTNode objRightNode)
	{
		// Assign the right node object reference.
		m_objRightNode = objRightNode;
	}
	
	// Accessor method to get the left node object.
	public BSTNode GetLeftNode()
	{
		// Return the object.
		return( m_objLeftNode );
	}
	
	// Accessor method to get the right node object.
	public BSTNode GetRightNode()
	{
		// Return the object.
		return( m_objRightNode );
	}

	// Accessor method to set the node's key value.
	public void SetKeyValue( int nKeyValue )
	{
		// Set the value.
		m_nKeyValue = nKeyValue;
	}
	
	// Accessor method to get the node's key value.
	public int GetKeyValue()
	{
		// Return the value.
		return( m_nKeyValue );
	}
	
	public int GetRank() 
	{
		return ( m_nRank );
	}
	// Accessor method to set the node's rank value.
	public void SetRank( int nRank )
	{
		// Set the value.
		m_nRank = nRank;
	}
	
	public int GetSubTreeSize()
	{
		return ( m_nSubTreeSize );
	}
	
	public void SetSubTreeSize( int nSize ) 
	{
		m_nSubTreeSize = nSize;
	}
}
