public class Chain extends DataObject
{
    private Chain next;

    public Chain(DataObject objData) 
    {
        super(objData.GetKey());
        next = null;
    }
    
    public void setNext(Chain chainLink)
    {
    	next = chainLink;
    }
    
    public Chain getNext()
    {
    	return next;
    }
}
