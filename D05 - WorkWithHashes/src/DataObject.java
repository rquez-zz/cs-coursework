
public class DataObject {

	public DataObject(String strKey)
	{
		m_strKey = strKey;
	}
	
	String m_strKey = "";
	
	public String GetKey()
	{
		return m_strKey;
	}
}
