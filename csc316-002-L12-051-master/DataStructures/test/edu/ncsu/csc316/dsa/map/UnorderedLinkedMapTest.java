package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the UnorderedLinkedMap class
 * @author Matthew Kierski
 *
 */
public class UnorderedLinkedMapTest {
	
	/** Private instance of map with integers as key, string as value */
	private Map<Integer, String> map;
	
	/**
	 * Sets map to new UnorderedArrayMap
	 */
	@Before
	public void setUp() {
		map = new UnorderedLinkedMap<Integer, String>();
	}
	
	/**
	 * Tests the put() method
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("UnorderedLinkedMap[3]", map.toString());
		assertEquals(1, map.size());
		assertNull(map.put(5, "string5"));
		assertEquals("UnorderedLinkedMap[5, 3]", map.toString());
		assertEquals(2, map.size());
		assertNull(map.put(2, "string2"));
		assertEquals("UnorderedLinkedMap[2, 5, 3]", map.toString());
		assertEquals(3, map.size());
		assertNull(map.put(4, "string4"));
		assertEquals("UnorderedLinkedMap[4, 2, 5, 3]", map.toString());
		assertEquals(4, map.size());
		assertNull(map.put(1, "string1"));
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		assertEquals(5, map.size());
		
		assertEquals("string1", map.put(1, "newstring1"));
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		assertEquals(5, map.size());
		
		assertEquals("string5", map.put(5, "newstring5"));
		assertEquals("UnorderedLinkedMap[5, 1, 4, 2, 3]", map.toString());
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
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		
		assertEquals("string1", map.get(1));
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		
		assertEquals("string4", map.get(4));
		assertEquals("UnorderedLinkedMap[4, 1, 2, 5, 3]", map.toString());
		
		assertEquals("string3", map.get(3));
		assertEquals("UnorderedLinkedMap[3, 4, 1, 2, 5]", map.toString());
		
		assertEquals("string2", map.get(2));
		assertEquals("UnorderedLinkedMap[2, 3, 4, 1, 5]", map.toString());
		
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
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		
		assertNull(map.remove(0));
		assertEquals(5, map.size());
		assertFalse(map.isEmpty());
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		
		assertEquals("string1", map.remove(1));
		assertEquals(4, map.size());
		assertEquals("UnorderedLinkedMap[4, 2, 5, 3]", map.toString());
		
		assertEquals("string3", map.remove(3));
		assertEquals(3, map.size());
		assertEquals("UnorderedLinkedMap[4, 2, 5]", map.toString());

		assertEquals("string2", map.remove(2));
		assertEquals(2, map.size());
		assertEquals("UnorderedLinkedMap[4, 5]", map.toString());
		
		assertEquals("string4", map.remove(4));
		assertEquals(1, map.size());
		assertEquals("UnorderedLinkedMap[5]", map.toString());
		
		assertEquals("string5", map.remove(5));
		assertEquals(0, map.size());
		assertEquals("UnorderedLinkedMap[]", map.toString());
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
		
		assertEquals(Integer.valueOf(4), it.next());
		assertTrue(it.hasNext());
		
		assertEquals(Integer.valueOf(2), it.next());
		assertTrue(it.hasNext());
		
		assertEquals(Integer.valueOf(5), it.next());
		assertTrue(it.hasNext());
		
		try {
			it.remove();
		} catch (Exception e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}
		
		assertEquals(Integer.valueOf(3), it.next());		
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
		assertEquals(4, (int)(entry.getKey()));
		assertEquals("string4", (String)(entry.getValue()));
		
		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(2, (int)(entry.getKey()));
		assertEquals("string2", (String)(entry.getValue()));
		
		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(5, (int)(entry.getKey()));
		assertEquals("string5", (String)(entry.getValue()));
		
		try {
			it.remove();
		} catch (Exception e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}
		
		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(3, (int)(entry.getKey()));
		assertEquals("string3", (String)(entry.getValue()));
		
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
		assertEquals("string4", it.next());
		
		assertTrue(it.hasNext());
		assertEquals("string2", it.next());
		
		assertTrue(it.hasNext());
		assertEquals("string5", it.next());
		
		try {
			it.remove();
		} catch (Exception e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}
		
		assertTrue(it.hasNext());
		assertEquals("string3", it.next());
	}
}
