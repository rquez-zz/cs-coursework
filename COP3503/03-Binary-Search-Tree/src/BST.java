
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
    	m_objRootNode = SetSubTreeSizes( SetRanks(Insert( nKeyValue, m_objRootNode, 3 ), 0));
    }    

    // Class protected (internal) method to insert nodes. This method
    //   will be called recursively.
    protected BSTNode Insert( int nKeyValue, BSTNode objNode, int k ) 
    {
 
    	// This node is null and simply needs to be allocated.
        if( objNode == null )
        {
        	objNode = new BSTNode( nKeyValue );
        }
        
        // Here we need to walk left.
        else if( nKeyValue < objNode.GetKeyValue() )
        {
        	if (objNode.GetLeftNode() == null) 
        	{
        		if ( Math.abs(nKeyValue - objNode.GetKeyValue()) >= k )
        			objNode.SetLeftNode( Insert( nKeyValue, objNode.GetLeftNode(), k ) );
        		else
        			return objNode;
        	}
        	else 
        	{
        		objNode.SetLeftNode( Insert( nKeyValue, objNode.GetLeftNode(), k ) );
        	}
        }
        
        // Here we need to talk right.
        else if( nKeyValue > objNode.GetKeyValue() )
        {
        	if (objNode.GetRightNode() == null) 
        	{
        		if ( Math.abs(nKeyValue - objNode.GetKeyValue()) >= k )
        			objNode.SetRightNode( Insert( nKeyValue, objNode.GetRightNode(), k ) );
        		else
        			return objNode;
        	}
        	else 
        	{
        		objNode.SetRightNode( Insert( nKeyValue, objNode.GetRightNode(), k ) );
        	}
        }
        
        return( objNode );
    }
    
    public void Delete( int nKeyValue)
    {
    	m_objRootNode = SetSubTreeSizes( SetRanks(Delete(nKeyValue, m_objRootNode), 0));
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
    	if (objNode.GetLeftNode() == null) 
    		return objNode;
    	return GetMin(objNode.GetLeftNode());
    }
    
    public int GetCount(BSTNode objNode) 
    {
    	if (objNode == null) return 0;
    	
    	return 1 + GetCount(objNode.GetLeftNode()) + GetCount(objNode.GetRightNode());
    }
   
   public void PrintInOrder(BSTNode objNode) 
   {
	   if ( objNode == null) 
		   return;
	   PrintInOrder(objNode.GetLeftNode()); 
	   System.out.print("( Value:" + objNode.GetKeyValue() + ", Rank:" + objNode.GetRank() + ", SubTreeSize: " + objNode.GetSubTreeSize() + ") ");
	   PrintInOrder(objNode.GetRightNode());
   }

   private BSTNode SetRanks(BSTNode objNode, int n) 
   {
	   if ( objNode == null) 
		   return null;

	   SetRanks(objNode.GetLeftNode(), n); 

	   if ( n == 0 && objNode.GetLeftNode() == null)
		   n = 0;
	   else if ( n != 0 )
	   {
		   if (objNode.GetLeftNode() != null)
			   n = GetMaxRank(objNode.GetLeftNode()) + 1;
		   else 
			   n = n + 1;
	   }
	   else
		   n = GetMaxRank(objNode.GetLeftNode()) + 1;

	   objNode.SetRank(n);

	   SetRanks(objNode.GetRightNode(), n);

	   return objNode;
   }
   
   private int GetMaxRank(BSTNode objNode) 
   {
	   if (objNode == null) return 0;
	   if (objNode.GetRightNode() == null)
		   return objNode.GetRank();
	   else
		   return GetMaxRank(objNode.GetRightNode());
   }
   
   private BSTNode SetSubTreeSizes( BSTNode objNode )
   {
	  if (objNode == null) return null;
	  SetSubTreeSizes(objNode.GetLeftNode());
	  objNode.SetSubTreeSize(GetCount(objNode));
	  SetSubTreeSizes(objNode.GetRightNode());
	  return objNode;
   }
}
