
public class BST 
{
	// This is the root node, which starts off as null
	//   when the BST is empty.
	BSTNode m_objRootNode;
	
	// Class constructor.
	public BST()
	{
		// Not really necessary, provided for clarity.
		m_objRootNode = null;
	}

	// Method to see if the tree is empty.
	public boolean IsEmpty()
	{
		// Return a boolean indicating whether the
		//   three is empty or not.
		return( m_objRootNode == null );
	}

	/* Functions to search for an element */
    public BSTNode Search( int nKeyValue )
    {
        return( Search( m_objRootNode, nKeyValue ) );
    }
    
    // Method to search for an element recursively.
    private BSTNode Search( BSTNode objNode, int nKeyValue )
    {
    	
    	if( objNode == null )
    	{
    		return( null );
    	}
    	
    	// First, we get the key value for this node.
    	int nThisKeyValue = objNode.GetKeyValue();
            
    	// See if the passed in key value is less. If so,
    	//   this indicates that we need to go left.
    	if( nKeyValue < nThisKeyValue )
    	{
    		// Get the left node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetLeftNode();
    	}
            
    	// See if the passed in key value is greater. If so,
    	//   this indicates that we need to go right.
    	else if( nKeyValue > nThisKeyValue )
    	{
    		// Get the right node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetRightNode();
    	}

    	// Here we have found the node with the key
    	//   value that was passed in.
    	else
    	{
    		return( objNode );
    	}
            
    	// Now call Search recursively.
    	return( Search( objNode, nKeyValue ) );
	}
    
    // This method inserts a node based on the key value.
    public void Insert( int nKeyValue ) 
    {
    	// The root node is returned to m_objRootNode from Insert()
    	m_objRootNode = Insert( nKeyValue, m_objRootNode );
    }    

    // Class protected (internal) method to insert nodes. This method
    //   will be called recursively.
    protected BSTNode Insert( int nKeyValue, BSTNode objNode ) 
    {
 
    	// This node is null and simply needs to be allocated.
        if( objNode == null )
        {
        	objNode = new BSTNode( nKeyValue );
        }
        
        // Here we need to walk left.
        else if( nKeyValue < objNode.GetKeyValue() )
        {
        	// Set the left node of this object by recursively walking left.
        	objNode.SetLeftNode( Insert( nKeyValue, objNode.GetLeftNode() ) );
        }
        
        // Here we need to talk right.
        else if( nKeyValue > objNode.GetKeyValue() )
        {
        	// Set the right node of this object by recursively walking right.
        	objNode.SetRightNode( Insert( nKeyValue, objNode.GetRightNode() ) );
        }
        
        return( objNode );
    }
    
    public void Delete( int nKeyValue)
    {
    	m_objRootNode = Delete(nKeyValue, m_objRootNode);
    }

    private BSTNode Delete( int nKeyValue, BSTNode objNode ) {

    	// This node is null and simply needs to be allocated.
        if( objNode.GetKeyValue() == nKeyValue )
        {
            // If Node is a leaf
            if (objNode.GetLeftNode() == null && objNode.GetRightNode() == null)
            {
                    objNode = null;
            }
            // If Node has 1 child
            if (objNode.GetLeftNode() != null ^ objNode.GetRightNode() != null)
            {
                    if (objNode.GetLeftNode() != null) 
                            objNode = objNode.GetLeftNode();
                    if (objNode.GetRightNode() != null)
                            objNode = objNode.GetRightNode();
            }
            // If Node has 2 children
            if (objNode.GetLeftNode() != null && objNode.GetRightNode() != null)
            {
                    BSTNode tempNode = objNode.GetRightNode();
                    objNode = objNode.GetLeftNode();
                    objNode.SetRightNode(tempNode);
            }
        }
        
        // Here we need to walk left.
        else if( nKeyValue < objNode.GetKeyValue() )
        {
        	// Set the left node of this object by recursively walking left.
        	objNode.SetLeftNode( Delete( nKeyValue, objNode.GetLeftNode() ) );
        }
        
        // Here we need to talk right.
        else if( nKeyValue > objNode.GetKeyValue() )
        {
        	// Set the right node of this object by recursively walking right.
        	objNode.SetRightNode( Delete( nKeyValue, objNode.GetRightNode() ) );
        }
        
        return( objNode );

    }
    
    public int GetMax() 
    {
    	return m_objRootNode.GetKeyValue();
    }
    
    public int GetMin() 
    {
    	return GetMin(m_objRootNode).GetKeyValue();
    }

    private BSTNode GetMin(BSTNode objNode) 
    { 
    	System.out.println(objNode.GetKeyValue());
    	if (objNode.GetLeftNode() == null) 
    		return objNode;
    	return GetMin(objNode.GetLeftNode());
    }
    
    public int GetCount(objNode) 
    {
    	if (objNode != null)
    	{
    		return 1 + GetCount(objNode);
    	} else {
    		return 0;
    	}
    }
    
    private BSTNode	SetRanks(BSTNode objNode, int n) 
    {
    	if ( objNode.GetLeftNode() != null) 
    	{
    		return SetRanks(objNode.GetLeftNode(), n - 1);
    	}
    	if ( objNode.GetRightNode() != null)
    	{
    		return SetRanks(objNode.GetRightNode(), n - 1);
    	}
    	objNode.SetRank(n);
    	return objNode; 
    }
    
}
