import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 recitation assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2RecitationWeek1 
{

	///////////////////////////////////////////
	//
	// Start of assignment code.
	//
	///////////////////////////////////////////

	/**
	 * Returns the last name, first name, and PID of the student.
	 * 
	 * This is required in order to get credit for the programming assignment.
	 */
	static String GetNameAndPID()
	{
		return( "Vasquez,Ricardo,R2905752");
	}
	
	// Write a method that recursively prints out all the 
	//   items in a linked list, in regular order. Fill in 
	//   the prototype below:

	static void PrintRec(DataStruct ds) 
	{
		do {
			System.out.println(ds.KeyValue);
			ds = ds.Next;
		} while (ds != null);
	}

	// Write a function that recursively prints out all 
	//   the items in a linked list, in reverse order. 
	//   Fill in the prototype below:

	static void PrintBackRec(DataStruct ds) 
	{
		ds = ds.Last;
		do 
		{
			System.out.println(ds.KeyValue);
			ds = ds.Prev;
		} while (ds != null);
	}

	// Write a function that iterates through a linked list 
	//   and adds 5 to each even number in the list and 
	//   subtracts 4 from each odd number in the list. For
	//   each member of the list print the before and after values.

	static void EditList(DataStruct ds) 
	{
		while (ds != null) 
		{
			System.out.print("Before: " + ds.KeyValue);
			if (ds.KeyValue % 2 == 0)
				ds.KeyValue += 5;
			else 
				ds.KeyValue -= 4;
			System.out.println(" After: " + ds.KeyValue);
			ds = ds.Next;
		}
	}
	
	// Write a recursive function that takes in two linked 
	//   lists and determines if the lists are equivalent. 
	//   For two lists to be equivalent, they must have the 
	//   same number of items and each corresponding item must 
	//   be equal. Thus, the lists 3, 5, 7 and 3, 5, 7 are 
	//   equal, but 3, 7, 5 does not equal 3, 5, 7 or 3, 7. 
	//   (Hint: As a base case, two lists are equal if they 
	//   are both NULL, and not equal if one is NULL and the 
	//   other isn’t.) Return 1 if the two lists passed in are 
	//   equal, and 0 otherwise.

	static int EqualLists(DataStruct list1, DataStruct list2) 
	{
		if (list1 == null && list2 == null)
			return 1;
		else if (list1 == null || list2 == null)
			return 0;
		else if (list1.KeyValue == list2.KeyValue) 
			return EqualLists(list1.Next, list2.Next);
		else
			return 0;
	}

	// Write a function that takes in a pointer to the front 
	//   of a linked list and returns 1 if all the nodes in 
	//   the linked list are in sorted order (from smallest 
	//   to largest, with repeats allowed), and 0 otherwise. 
	//   The prototype is given below:

	static int InOrder(DataStruct list) 
	{
		while (list != null && list.Next != null) {
			if (list.KeyValue <= list.Next.KeyValue)
                list = list.Next;
			else
				return 0;
		}
		return 1;
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
		DataStruct ds = new DataStruct();
		for( int i=0; i<4; i++ )
		{
			ds.AddToEndOfList(new DataStruct());
		}
		
		DataStruct ds2 = new DataStruct();
		ds2.KeyValue = 1;
		for( int i=0; i<4; i++ )
		{
			DataStruct ds3 = new DataStruct();
			ds3.KeyValue = 2 + i;
			ds2.AddToEndOfList(ds3);
		}

		// Test code here...
		PrintRec( ds );

		PrintBackRec( DataStruct.Last );

		EditList( ds );

		int nEqual = EqualLists( ds, ds );

		int nInOrder = InOrder(ds);

		nInOrder = InOrder(ds2);


	}

}
