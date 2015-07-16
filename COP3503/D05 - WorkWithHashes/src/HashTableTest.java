import static org.junit.Assert.*;

import org.junit.Test;


public class HashTableTest {

	@Test
	public void testLinearHash() {
		LinearProbingHashTable linearHashTable = new LinearProbingHashTable(5);
		DataObject obj1 = new DataObject("test1");
		DataObject obj2 = new DataObject("test2");
		DataObject obj3 = new DataObject("test2");
		DataObject obj4 = new DataObject("test2");
		DataObject obj5 = new DataObject("test2");
		DataObject obj6 = new DataObject("test2");
		try {
			linearHashTable.put(obj1);
			linearHashTable.put(obj2);
			linearHashTable.put(obj3);
			linearHashTable.put(obj4);
			linearHashTable.put(obj5);
			linearHashTable.put(obj6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals(5, linearHashTable.size());
		
		linearHashTable.ExpandTable();
		
		try {
			linearHashTable.put(obj1);
			linearHashTable.put(obj2);
			linearHashTable.put(obj3);
			linearHashTable.put(obj4);
			linearHashTable.put(obj5);
			linearHashTable.put(obj5);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(10, linearHashTable.size());
		
		linearHashTable.PrintTable();
	}

	@Test
	public void testQuadraticHash() {
		QuadraticProbingHashTable quadraticHashTable = new QuadraticProbingHashTable(1000);
		DataObject obj1 = new DataObject("test1");
		DataObject obj2 = new DataObject("test2");
		DataObject obj3 = new DataObject("test2");
		DataObject obj4 = new DataObject("test2");
		DataObject obj5 = new DataObject("test2");
		DataObject obj6 = new DataObject("test2");
		try {
			quadraticHashTable.put(obj1);
			quadraticHashTable.put(obj2);
			quadraticHashTable.put(obj3);
			quadraticHashTable.put(obj4);
			quadraticHashTable.put(obj5);
			quadraticHashTable.put(obj6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals(6, quadraticHashTable.size());
		
		quadraticHashTable.ExpandTable();
		
		try {
			quadraticHashTable.put(obj1);
			quadraticHashTable.put(obj2);
			quadraticHashTable.put(obj3);
			quadraticHashTable.put(obj4);
			quadraticHashTable.put(obj5);
			quadraticHashTable.put(obj5);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(12, quadraticHashTable.size());
		
		quadraticHashTable.PrintTable();
	}

	@Test
	public void testSeparateChainingHash() {
		SeparateChainingHashTable SeparateChainingHashTable = new SeparateChainingHashTable(1000);
		DataObject obj1 = new DataObject("test1");
		DataObject obj2 = new DataObject("test2");
		DataObject obj3 = new DataObject("test3");
		DataObject obj4 = new DataObject("test4");
		DataObject obj5 = new DataObject("test5");
		DataObject obj6 = new DataObject("test6");
		DataObject obj7 = new DataObject("test6");
		try {
			SeparateChainingHashTable.put(obj1);
			SeparateChainingHashTable.put(obj2);
			SeparateChainingHashTable.put(obj3);
			SeparateChainingHashTable.put(obj4);
			SeparateChainingHashTable.put(obj5);
			SeparateChainingHashTable.put(obj6);
			SeparateChainingHashTable.put(obj7);
			SeparateChainingHashTable.put(obj7);
			SeparateChainingHashTable.put(obj7);
			SeparateChainingHashTable.put(obj7);
			SeparateChainingHashTable.put(obj7);
			SeparateChainingHashTable.put(obj7);
			SeparateChainingHashTable.put(obj7);
			SeparateChainingHashTable.put(obj7);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		assertEquals(6, SeparateChainingHashTable.size());
		System.out.println();
		SeparateChainingHashTable.PrintTable();
	}
}
