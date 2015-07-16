public class DataObject {

	public DataObject(String strKey)
	{
		m_strKey = strKey;
	}
	
	private String m_strKey = "";
	
	public String GetKey()
	{
		return m_strKey;
	}
	
	public void SetKey(String strString)
	{
		m_strKey = strString;
	}
}
