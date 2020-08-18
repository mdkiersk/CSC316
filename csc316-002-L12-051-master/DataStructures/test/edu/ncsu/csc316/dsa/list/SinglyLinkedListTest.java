package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the SinglyLinkedList class
 * @author Matthew Kierski
 *
 */
public class SinglyLinkedListTest {

	/** Private instance of list */
	private List<String> list;

	/**
	 * Sets the list to be a new SinglyLinkedList
	 */
	@Before
	public void setUp() {
		list = new SinglyLinkedList<String>();
	}

	/**
	 * Tests the addIndex() method
	 */
	@Test
	public void testAddIndex() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());
		
		list.add(1, "two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(1));
		assertFalse(list.isEmpty());
		
		list.add(2, "three");
		assertEquals(3, list.size());
		assertEquals("three", list.get(2));
		assertFalse(list.isEmpty());
		
		list.add(3, "four");
		assertEquals(4, list.size());
		assertEquals("four", list.get(3));
		assertFalse(list.isEmpty());
		
		list.add(4, "five");
		assertEquals(5, list.size());
		assertEquals("five", list.get(4));
		assertFalse(list.isEmpty());
		
		list.add(5, "six");
		assertEquals(6, list.size());
		assertEquals("six", list.get(5));
		assertFalse(list.isEmpty());
		
		list.add(6, "seven");
		assertEquals(7, list.size());
		assertEquals("seven", list.get(6));
		assertFalse(list.isEmpty());
		
		list.add(7, "eight");
		assertEquals(8, list.size());
		assertEquals("eight", list.get(7));
		assertFalse(list.isEmpty());
		
		list.add(8, "nine");
		assertEquals(9, list.size());
		assertEquals("nine", list.get(8));
		assertFalse(list.isEmpty());
		
		list.add(9, "ten");
		assertEquals(10, list.size());
		assertEquals("ten", list.get(9));
		assertFalse(list.isEmpty());
		
		//Should grow in capacity (when testing array)
		list.add(10, "eleven");
		assertEquals(11, list.size());
		assertEquals("eleven", list.get(10));
		assertFalse(list.isEmpty());
		
		//Check that adding in the middle does not break anything
		list.add(9, "extra");
		assertEquals(12, list.size());
		assertEquals("extra", list.get(9));
		
		//Ensure proper shifting
		assertEquals("ten", list.get(10));
		assertEquals("eleven", list.get(11));
		
		// Use the statements above to help guide your test cases
		// for data structures: Start with an empty data structure, then
		// add an element and check the accessor method return values.
		// Then add another element and check again. Continue to keep checking
		// for special cases. For example, for an array-based list, you might
		// continue adding until you trigger a resize operation to make sure
		// the resize operation worked as expected.
		
		try {
			list.add(15,  "fifteen");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
		try { 
			list.add(13, "fourteen");
		} catch(Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
	}

	/**
	 * Tests the addLast() method
	 */
	@Test
	public void testAddLast() {
		// Ensure using method on empty list does not break anything
		list.addLast("one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		
		// Continue adding more data using method and conduct checks to ensure proper adding
		list.addLast("two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(1));
		
		list.addLast("three");
		assertEquals(3, list.size());
		assertEquals("three", list.get(2));
	}

	/**
	 * Tests the addFirst() method
	 */
	@Test
	public void testAddFirst() {
		//Ensure using method on empty list does not break anything
		list.addFirst("one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		
		//Continue adding more data using method and conduct checks to ensure proper adding
		list.addFirst("two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(0));
		assertEquals("one", list.get(1));
		
		list.addFirst("three");
		assertEquals(3, list.size());
		assertEquals("three", list.get(0));
		assertEquals("two", list.get(1));
		assertEquals("one", list.get(2));
		
	}

	/**
	 * Tests the first() method
	 */
	@Test
	public void testFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");
		list.add(3, "four");
		list.add(4, "five");
		
		assertEquals("one", list.first());
		list.removeFirst();
		assertEquals("two", list.first());
	}
	
	/**
	 * Tests the last() method
	 */
	@Test
	public void testLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");
		list.add(3, "four");
		list.add(4, "five");
		
		assertEquals("five", list.last());
		list.removeLast();
		assertEquals("four", list.last());
		list.removeLast();
		assertEquals("three", list.last());
		list.removeLast();
		assertEquals("two", list.last());
	}

	/**
	 * Tests the iterator() method
	 */
	@Test
	public void testIterator() {
		// Start with an empty list
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		// Create an iterator for the empty list
		Iterator<String> it = list.iterator();
		
		// Try different operations to make sure they work
		// as expected for an empty list (at this point)
		try{
			it.remove();
		} catch(Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		assertFalse(it.hasNext());

		// Now add an element
		list.addLast("one");
		
		// Use accessor methods to check that the list is correct
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals("one", list.get(0));
		
		// Create an iterator for the list that has 1 element
		it = list.iterator();
		
		// Try different iterator operations to make sure they work
		// as expected for a list that contains 1 element (at this point)
		assertTrue(it.hasNext());
		assertEquals("one", it.next());
		assertFalse(it.hasNext());
		try {
			it.next();
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}

		list.addLast("two");
		assertEquals(2, list.size());
		
		Iterator<String> newIt = list.iterator();
		
		assertTrue(newIt.hasNext());
		assertEquals("one", newIt.next());
		assertTrue(newIt.hasNext());
		assertEquals("two", newIt.next());
		
		newIt.remove();
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
	}

	/**
	 * Tests the remove() method
	 */
	@Test
	public void testRemoveIndex() {
		// Ensure removing from empty list generates exception
		try {
			list.remove(0);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");
		list.add(3, "four");
		list.add(4, "five");
		
		//Remove from end of list
		list.remove(4);
		assertEquals(4, list.size());
		assertEquals("one", list.get(0));
		assertEquals("two", list.get(1));
		assertEquals("three", list.get(2));
		assertEquals("four", list.get(3));
		
		//Remove from middle of list
		list.remove(1);
		assertEquals(3, list.size());
		assertEquals("one", list.get(0));
		assertEquals("three", list.get(1));
		assertEquals("four", list.get(2));
		
		//Remove from beginning of list
		list.remove(0);
		assertEquals(2, list.size());
		assertEquals("three", list.get(0));
		assertEquals("four", list.get(1));
	}

	/**
	 * Tests the removeFirst() method
	 */
	@Test
	public void testRemoveFirst() {
		//Ensure removing from empty list does not break anything
		try {
			list.removeFirst();
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");
		list.add(3, "four");
		list.add(4, "five");
		
		//Repeatedly remove first element and check contents of list
		list.removeFirst();
		assertEquals(4, list.size());
		assertEquals("two", list.get(0));
		assertEquals("three", list.get(1));
		assertEquals("four", list.get(2));
		assertEquals("five", list.get(3));
		
		list.removeFirst();
		assertEquals(3, list.size());
		assertEquals("three", list.get(0));
		assertEquals("four", list.get(1));
		assertEquals("five", list.get(2));
		
		list.removeFirst();
		assertEquals(2, list.size());
		assertEquals("four", list.get(0));
		assertEquals("five", list.get(1));
		
		list.removeFirst();
		assertEquals(1, list.size());
		assertEquals("five", list.get(0));
		
		list.removeFirst();
		assertEquals(0, list.size());
	}

	/**
	 * Tests the removeLast() method
	 */
	@Test
	public void testRemoveLast() {
		// Ensure removing from empty list does not break anything
		try {
			list.removeLast();
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}

		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");
		list.add(3, "four");
		list.add(4, "five");
		
		//Repeatedly remove last and check contents of list
		list.removeLast();
		assertEquals(4, list.size());
		assertEquals("one", list.get(0));
		assertEquals("two", list.get(1));
		assertEquals("three", list.get(2));
		assertEquals("four", list.get(3));
		
		list.removeLast();
		assertEquals(3, list.size());
		assertEquals("one", list.get(0));
		assertEquals("two", list.get(1));
		assertEquals("three", list.get(2));
		
		list.removeLast();
		assertEquals(2, list.size());
		assertEquals("one", list.get(0));
		assertEquals("two", list.get(1));
		
		list.removeLast();
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		
		list.removeLast();
		assertEquals(0, list.size());
		
	}

	/**
	 * Tests the set() method
	 */
	@Test
	public void testSet() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");
		list.add(3, "four");
		list.add(4, "five");
		
		assertEquals("one", list.set(0, "new"));
		assertEquals("new", list.get(0));
		
		assertEquals("three", list.set(2, "newer"));
		assertEquals("newer", list.get(2));
	}
}