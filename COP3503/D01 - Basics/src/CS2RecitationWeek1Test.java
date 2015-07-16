import static org.junit.Assert.*;

import org.junit.Test;


public class CS2RecitationWeek1Test {

	@Test
	public void testEqualLists() {

		DataStruct ds1 = new DataStruct();
		for( int i=0; i<4; i++ )
			ds1.AddToEndOfList(new DataStruct());
		ds1.KeyValue = 1;
		ds1.Next.KeyValue = 2;
		ds1.Next.Next.KeyValue = 3;
		ds1.Next.Next.Next.KeyValue = 4;
		ds1.Next.Next.Next.Next.KeyValue = 5;

		DataStruct ds2 = new DataStruct();
		for( int i=0; i<4; i++ )
			ds2.AddToEndOfList(new DataStruct());
		ds2.KeyValue = 1;
		ds2.Next.KeyValue = 2;
		ds2.Next.Next.KeyValue = 3;
		ds2.Next.Next.Next.KeyValue = 4;
		ds2.Next.Next.Next.Next.KeyValue = 5;
		
		DataStruct ds3 = new DataStruct();
		for( int i=0; i<3; i++ )
			ds3.AddToEndOfList(new DataStruct());
		ds3.KeyValue = 1;
		ds3.Next.KeyValue = 2;
		ds3.Next.Next.KeyValue = 3;
		ds3.Next.Next.Next.KeyValue = 4;

		DataStruct ds4 = new DataStruct();
		ds4.KeyValue = 10;

		assertEquals(1, CS2RecitationWeek1.EqualLists(ds1, ds2));
		assertEquals(0, CS2RecitationWeek1.EqualLists(ds1, ds3));
		assertEquals(0, CS2RecitationWeek1.EqualLists(ds1, ds4));
	}

	@Test
	public void testInOrder() {
		DataStruct ds1 = new DataStruct();
		for( int i=0; i<4; i++ )
			ds1.AddToEndOfList(new DataStruct());
		ds1.KeyValue = 1;
		ds1.Next.KeyValue = 2;
		ds1.Next.Next.KeyValue = 3;
		ds1.Next.Next.Next.KeyValue = 4;
		ds1.Next.Next.Next.Next.KeyValue = 5;

		DataStruct ds2 = new DataStruct();
		for( int i=0; i<4; i++ )
			ds2.AddToEndOfList(new DataStruct());
		ds2.KeyValue = 1;
		ds2.Next.KeyValue = 1;
		ds2.Next.Next.KeyValue = 3;
		ds2.Next.Next.Next.KeyValue = 3;
		ds2.Next.Next.Next.Next.KeyValue = 5;

		DataStruct ds3 = new DataStruct();
		for( int i=0; i<3; i++ )
			ds3.AddToEndOfList(new DataStruct());
		ds3.KeyValue = 1;
		ds3.Next.KeyValue = 2;
		ds3.Next.Next.KeyValue = 5;
		ds3.Next.Next.Next.KeyValue = 4;
		
		
		assertEquals(1, CS2RecitationWeek1.InOrder(ds1));
		assertEquals(1, CS2RecitationWeek1.InOrder(ds2));
		assertEquals(0, CS2RecitationWeek1.InOrder(ds3));
	}

}
