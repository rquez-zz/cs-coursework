import java.util.Random;


public class DataStruct 
{
	static Random rnd = new Random(5);
	
	DataStruct Next;
	DataStruct Prev;
	public static DataStruct Last;

	public int KeyValue;
	
	DataStruct()
	{
		KeyValue = rnd.nextInt() & 0xffff;
	}

	public void AddToStartOfList( DataStruct ds2add )
	{
		ds2add.Next = this;
		Prev = ds2add;
	}
	
	public void AddToEndOfList( DataStruct ds2add )
	{
		if( Next != null )
		{
			Next.AddToEndOfList( ds2add );
		}
		else
		{
			ds2add.Prev = this;
			Next = ds2add;
			Last = ds2add;
		}
	}
	
}
