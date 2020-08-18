package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * Tests the SeparateChainingHashMap class
 * @author Matthew Kierski
 *
 */
public class SeparateChainingHashMapTest {

	/** Instance of map to be used for testing */

	private Map<Integer, String> map;
	
	/** Instance of map to be used for testing */

	private Map<Integer, String> defaultMap;
	
	/** Instance of map to be used for testing */

	private Map<Integer, String> customCapacityMap;
	
	
	/**
     * Sets map to a new SeparateChainingHashMap, setting capacity to seven and isTesting to true
     * Sets defaultMap to a new SeparateChainingHashMap, setting capacity to default and isTesting to false
     * Sets customCapacityMap to a new SeparateChainingHashMap, setting capacity to 3 and isTesting to false
     */
	@Before
	public void setUp() {
		// Use the "true" flag to indicate we are testing.
		// Remember that (when testing) alpha = 1, beta = 1, and prime = 7
		// based on our AbstractHashMap constructor.
		// That means you can draw the hash table by hand
		// if you use integer keys, since Integer.hashCode() = the integer value, itself
		// Finally, apply compression. For example:
		// for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
		// for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
		// for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
		// for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
		// for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
		// for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
		// etc.
		// Remember that our secondary map (an AVL tree) is a search
		// tree, which means the entries should be sorted in order within
		// that tree
		map = new SeparateChainingHashMap<Integer, String>(7, true);
		
		defaultMap = new SeparateChainingHashMap<Integer, String>();
        
        customCapacityMap = new SeparateChainingHashMap<Integer, String>(3);
	}
	
	/**
	 * Tests the put() method
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));

		// Since our entrySet method returns the entries in the table
		// from left to right, we can use the entrySet to check
		// that our values are in the correct order in the hash table.
		// Alternatively, you could implement a toString() method if you
		// want to check that the exact index/map of each bucket is correct
		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
		
		
		assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be index 4
        assertEquals(4, (int)it.next().getKey()); // should be index 5
        
        assertNull(map.put(5, "string5"));
        assertEquals(3, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be index 4
        assertEquals(4, (int)it.next().getKey()); // should be index 5
        assertEquals(5, (int)it.next().getKey()); //should be index 6
        
        assertNull(map.put(6, "string6"));
        assertEquals(4, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(6, (int)it.next().getKey()); //should be index 0
        assertEquals(3, (int)it.next().getKey()); // should be index 4
        assertEquals(4, (int)it.next().getKey()); // should be index 5
        assertEquals(5, (int)it.next().getKey()); //should be index 6
        
        assertNull(map.put(7, "string7"));
        assertEquals(5, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(6, (int)it.next().getKey()); //should be index 0
        assertEquals(7, (int)it.next().getKey()); //should be index 1
        assertEquals(3, (int)it.next().getKey()); // should be index 4
        assertEquals(4, (int)it.next().getKey()); // should be index 5
        assertEquals(5, (int)it.next().getKey()); //should be index 6
        
        assertNull(map.put(1, "string1"));
        assertEquals(6, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(6, (int)it.next().getKey()); //should be index 0
        assertEquals(7, (int)it.next().getKey()); //should be index 1
        assertEquals(1, (int)it.next().getKey()); //should be index 2
        assertEquals(3, (int)it.next().getKey()); // should be index 4
        assertEquals(4, (int)it.next().getKey()); // should be index 5
        assertEquals(5, (int)it.next().getKey()); //should be index 6
        
        assertNull(map.put(2, "string2"));
        assertEquals(7, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(6, (int)it.next().getKey()); //should be index 0
        assertEquals(7, (int)it.next().getKey()); //should be index 1
        assertEquals(1, (int)it.next().getKey()); //should be index 2
        assertEquals(2, (int)it.next().getKey()); //should be index 3
        assertEquals(3, (int)it.next().getKey()); //should be index 4
        assertEquals(4, (int)it.next().getKey()); //should be index 5
        assertEquals(5, (int)it.next().getKey()); //should be index 6
        
        //Test put for defaultMap and customCapacityMap
        assertNull(defaultMap.put(1, "string1"));
        assertNull(defaultMap.put(2, "string2"));
        assertNull(defaultMap.put(3, "string3"));
        assertEquals(3, defaultMap.size());
        
        assertNull(customCapacityMap.put(1, "string1"));
        assertNull(customCapacityMap.put(2, "string2"));
        assertNull(customCapacityMap.put(3, "string3"));
        assertEquals(3, customCapacityMap.size());
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
		
		assertEquals("string3", map.get(3));
		assertEquals("string5", map.get(5));
		assertEquals("string2", map.get(2));
		assertEquals("string4", map.get(4));
		assertEquals("string1", map.get(1));
		assertNull(map.get(6));
		
		map.remove(2);
		assertNull(map.get(2));
		
		//Test get for defaultMap and customCapacityMap
        assertNull(defaultMap.put(1, "string1"));
        assertNull(defaultMap.put(2, "string2"));
        assertNull(defaultMap.put(3, "string3"));
        assertEquals(3, defaultMap.size());
        assertEquals("string1", defaultMap.get(1));
        assertEquals("string2", defaultMap.get(2));
        assertEquals("string3", defaultMap.get(3));
        assertNull(defaultMap.get(4));
        defaultMap.remove(3);
        assertNull(defaultMap.get(3));
        
        assertNull(customCapacityMap.put(1, "string1"));
        assertNull(customCapacityMap.put(2, "string2"));
        assertNull(customCapacityMap.put(3, "string3"));
        assertEquals(3, customCapacityMap.size());
        assertEquals("string1", customCapacityMap.get(1));
        assertEquals("string2", customCapacityMap.get(2));
        assertEquals("string3", customCapacityMap.get(3));
        assertNull(customCapacityMap.get(4));
        customCapacityMap.remove(3);
        assertNull(customCapacityMap.get(3));
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
		
		assertNull(map.remove(6));
		assertEquals(5, map.size());
		assertEquals("string3", map.remove(3));
		assertEquals(4, map.size());
		assertEquals("string5", map.remove(5));
		assertEquals(3, map.size());
		assertEquals("string1", map.remove(1));
		assertEquals(2, map.size());
		assertEquals("string4", map.remove(4));
		assertEquals(1, map.size());
		assertEquals("string2", map.remove(2));
		
		//Test remove for defaultMap and customCapacityMap
        assertNull(defaultMap.put(1, "string1"));
        assertNull(defaultMap.put(2, "string2"));
        assertNull(defaultMap.put(3, "string3"));
        assertNull(defaultMap.remove(4));
        assertEquals(3, defaultMap.size());
        assertEquals("string1", defaultMap.remove(1));
        assertEquals(2, defaultMap.size());
        assertEquals("string3", defaultMap.remove(3));
        assertEquals(1, defaultMap.size());
        assertEquals("string2", defaultMap.remove(2));
        assertNull(defaultMap.remove(2));
        
        assertNull(customCapacityMap.put(1, "string1"));
        assertNull(customCapacityMap.put(2, "string2"));
        assertNull(customCapacityMap.put(3, "string3"));
        assertEquals(3, customCapacityMap.size());
        assertNull(customCapacityMap.remove(4));
        assertEquals("string1", customCapacityMap.remove(1));
        assertEquals(2, customCapacityMap.size());
        assertEquals("string3", customCapacityMap.remove(3));
        assertEquals(1, customCapacityMap.size());
        assertEquals("string2", customCapacityMap.remove(2));
        assertNull(customCapacityMap.remove(2));
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
		assertEquals(1, (int)it.next());
		
		assertTrue(it.hasNext());
		assertEquals(2, (int)it.next());
		
		assertTrue(it.hasNext());
		assertEquals(3, (int)it.next());
		
		assertTrue(it.hasNext());
		assertEquals(4, (int)it.next());
		
		assertTrue(it.hasNext());
		assertEquals(5, (int)it.next());
		
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
		assertEquals(1, (int)entry.getKey());
		assertEquals("string1", entry.getValue());
		
		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(2, (int)entry.getKey());
		
		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(3, (int)entry.getKey());
		
		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(4, (int)entry.getKey());
		
		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(5, (int)entry.getKey());
		
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
		
		assertTrue(it.hasNext());
		assertEquals("string5", it.next());
	}
}