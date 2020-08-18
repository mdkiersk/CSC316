package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Tests the SearchTableMap class
 * @author Matthew Kierski
 *
 */
public class SearchTableMapTest {

	/** Instance of integer, string map */
	private Map<Integer, String> map;
	/** Instance of student, integer map */
	private Map<Student, Integer> studentMap;
	
	/**
	 * Sets map to new map, studentMap to new studentMap
	 */
	@Before
	public void setUp() {
		map = new SearchTableMap<Integer, String>();
		studentMap = new SearchTableMap<Student, Integer>();
	}
	
	/**
	 * Tests the put() method
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("SearchTableMap[3]", map.toString());
		assertEquals(1, map.size());
		
		assertNull(map.put(5, "string5"));
		assertEquals("SearchTableMap[3, 5]", map.toString());
		assertEquals(2, map.size());
		
		assertNull(map.put(2, "string2"));
		assertEquals("SearchTableMap[2, 3, 5]", map.toString());
		assertEquals(3, map.size());
		
		assertNull(map.put(4, "string4"));
		assertEquals("SearchTableMap[2, 3, 4, 5]", map.toString());
		assertEquals(4, map.size());
		
		assertNull(map.put(1, "string1"));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		assertEquals(5, map.size());
		
		assertEquals("string1", map.put(1, "newstring1"));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		assertEquals(5, map.size());
		
		assertEquals("string3", map.put(3, "newstring3"));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		assertEquals(5, map.size());
	}
	
	/**
	 * Tests the get() method
	 */
	@Test
	public void testGet() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		
		assertEquals("string1", map.get(1));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		
		assertEquals("string2", map.get(2));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		
		assertEquals("string3", map.get(3));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		
		assertEquals("string4", map.get(4));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		
		assertEquals("string5", map.get(5));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		
		//Attempt to get key that doesn't exist
		assertNull(map.get(6));
	}
	
	/**
	 * Tests the remove() method
	 */
	@Test
	public void testRemove() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		
		assertNull(map.remove(0));
		assertEquals(5, map.size());
		assertFalse(map.isEmpty());
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		
		assertEquals("string1", map.remove(1));
		assertEquals("SearchTableMap[2, 3, 4, 5]", map.toString());
		assertEquals(4, map.size());
		
		assertEquals("string4", map.remove(4));
		assertEquals("SearchTableMap[2, 3, 5]", map.toString());
		assertEquals(3, map.size());
		
		assertEquals("string3", map.remove(3));
		assertEquals("SearchTableMap[2, 5]", map.toString());
		assertEquals(2, map.size());
		
		assertEquals("string5", map.remove(5));
		assertEquals("SearchTableMap[2]", map.toString());
		assertEquals(1, map.size());
		
		assertEquals("string2", map.remove(2));
		assertEquals("SearchTableMap[]", map.toString());
		assertEquals(0, map.size());
		
		assertNull(map.remove(2));
	}
	
	/**
	 * Tests to see if comparator functionality is properly implemented
	 */
	@Test
	public void testStudentMap() {
		Student s1 = new Student("J", "K", 1, 0, 0, "jk");
		Student s2 = new Student("J", "S", 2, 0, 0, "js");
		Student s3 = new Student("S", "H", 3, 0, 0, "sh");
		Student s4 = new Student("J", "J", 4, 0, 0, "jj");
		Student s5 = new Student("L", "B", 5, 0, 0, "lb");
		
		studentMap.put(s1,  100);
		assertEquals(1, studentMap.size());
		
		studentMap.put(s2, 100);
		assertEquals(2, studentMap.size());
		assertEquals("SearchTableMap[J K, 1, J S, 2]", studentMap.toString());
		
		studentMap.put(s4, 100);
		assertEquals(3, studentMap.size());
		assertEquals("SearchTableMap[J J, 4, J K, 1, J S, 2]", studentMap.toString());
		
		studentMap.put(s5, 100);
		assertEquals(4, studentMap.size());
		assertEquals("SearchTableMap[L B, 5, J J, 4, J K, 1, J S, 2]", studentMap.toString());
		
		studentMap.put(s3, 100);
		assertEquals(5, studentMap.size());
		assertEquals("SearchTableMap[L B, 5, S H, 3, J J, 4, J K, 1, J S, 2]", studentMap.toString());
		
		//Test with id comparator
		Comparator<Student> comparator = new StudentIDComparator();
		studentMap = new SearchTableMap<Student, Integer>(comparator);
		
		studentMap.put(s3, 100);
		assertEquals(1, studentMap.size());
		assertEquals("SearchTableMap[S H, 3]", studentMap.toString());
		
		studentMap.put(s1, 100);
		assertEquals(2, studentMap.size());
		assertEquals("SearchTableMap[J K, 1, S H, 3]", studentMap.toString());
		
		studentMap.put(s2, 100);
		assertEquals(3, studentMap.size());
		assertEquals("SearchTableMap[J K, 1, J S, 2, S H, 3]", studentMap.toString());
		
		studentMap.put(s5, 100);
		assertEquals(4, studentMap.size());
		assertEquals("SearchTableMap[J K, 1, J S, 2, S H, 3, L B, 5]", studentMap.toString());
		
		studentMap.put(s4, 100);
		assertEquals(5, studentMap.size());
		assertEquals("SearchTableMap[J K, 1, J S, 2, S H, 3, J J, 4, L B, 5]", studentMap.toString());
	}
	
	/**
	 * Tests the iterator() method
	 */
	@Test
	public void testIterator() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		
		Iterator<Integer> it = map.iterator();
		assertTrue(it.hasNext());
		
		assertEquals(Integer.valueOf(1), it.next());
		assertTrue(it.hasNext());
		
		assertEquals(Integer.valueOf(2), it.next());
		assertTrue(it.hasNext());
		
		assertEquals(Integer.valueOf(3), it.next());
		assertTrue(it.hasNext());
		
		assertEquals(Integer.valueOf(4), it.next());
		assertTrue(it.hasNext());
		
		try {
			it.remove();
		} catch (Exception e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}
		
		assertEquals(Integer.valueOf(5), it.next());
		assertFalse(it.hasNext());
	}
	
	/**
	 * Tests the entrySet() method
	 */
	@Test
	public void testEntrySet() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		
		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		assertTrue(it.hasNext());
		Map.Entry<Integer, String> entry = it.next();
		assertEquals(1, (int)(entry.getKey()));
		assertEquals("string1", (String)(entry.getValue()));

		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(2, (int)(entry.getKey()));
		assertEquals("string2", (String)(entry.getValue()));
		
		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(3, (int)(entry.getKey()));
		assertEquals("string3", (String)(entry.getValue()));
		
		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(4, (int)(entry.getKey()));
		assertEquals("string4", (String)(entry.getValue()));
		
		try {
			it.remove();
		} catch (Exception e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}
		
		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(5, (int)(entry.getKey()));
		assertEquals("string5", (String)(entry.getValue()));
		
		assertFalse(it.hasNext());
	}
	
	/**
	 * Tests the values() method
	 */
	@Test
	public void testValues() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		
		Iterator<String> it = map.values().iterator();
		assertTrue(it.hasNext());
		assertEquals("string1", it.next());
		
		assertTrue(it.hasNext());
		assertEquals("string2", it.next());
		
		assertTrue(it.hasNext());
		assertEquals("string3", it.next());
		
		assertTrue(it.hasNext());
		assertEquals("string4", it.next());
		
		try {
			it.remove();
		} catch (Exception e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}
		
		assertTrue(it.hasNext());
		assertEquals("string5", it.next());
	}
}