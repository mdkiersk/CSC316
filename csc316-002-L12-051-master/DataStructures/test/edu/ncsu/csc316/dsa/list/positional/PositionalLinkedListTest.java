package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Tests the PositionalLinkedList class
 * @author Matthew Kierski
 *
 */
public class PositionalLinkedListTest {

	/** Private instance of a list */
	private PositionalList<String> list;
	
	/**
	 * Sets the list to be a new PositionalLinkedList
	 */
	@Before
	public void setUp() {
		list = new PositionalLinkedList<String>();
	}
	
	/**
	 * Tests the first() method
	 */
	@Test
	public void testFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		try{
			list.first();
		} catch(Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
		
		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals(first, list.first());
		
		Position<String> second = list.addFirst("two");
		assertEquals(2, list.size());
		assertEquals(second, list.first());
		
		Position<String> third = list.addAfter(second, "three");
		assertEquals(3, list.size());
		assertEquals(second, list.first());
		assertEquals(third, list.after(second));
		
		//Remove first position and ensure proper shifting to first
		list.remove(second);
		assertEquals(2, list.size());
		assertEquals(third, list.first());
	}
	
	/**
	 * Tests the last() method
	 */
	@Test
	public void testLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		try{
			list.first();
		} catch(Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
		
		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals(first, list.last());
		
		Position<String> second = list.addFirst("two");
		assertEquals(2, list.size());
		assertEquals(first, list.last());
		assertEquals(second, list.before(first));
		
		Position<String> third = list.addLast("three");
		assertEquals(3, list.size());
		assertEquals(third, list.last());
	}
	
	/**
	 * Tests the addFirst() method
	 */
	@Test
	public void testAddFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals(first, list.first());
		
		Position<String> second = list.addFirst("two");
		assertEquals(2, list.size());
		assertFalse(list.isEmpty());
		assertEquals(second, list.first());
		
		Position<String> third = list.addFirst("three");
		assertEquals(3, list.size());
		assertFalse(list.isEmpty());
		assertEquals(third, list.first());
		
	}
	
	/**
	 * Tests the addLast() method
	 */
	@Test
	public void testAddLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addLast("one");
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		
		Position<String> second = list.addLast("two");
		assertEquals(2, list.size());
		assertEquals(first, list.first());
		assertEquals(second, list.last());
		
		Position<String> third = list.addLast("three");
		assertEquals(3, list.size());
		assertEquals(first, list.first());
		assertEquals(third, list.last());
		
	}
	
	/**
	 * Tests the before() method
	 */
	@Test
	public void testBefore() {
		Position<String> first = list.addLast("one");
		assertEquals(1, list.size());
		assertEquals(null, list.before(first).getElement());
		
		Position<String> second = list.addLast("two");
		assertEquals(2, list.size());
		assertEquals(first, list.before(second));
		
		Position<String> third = list.addLast("three");
		assertEquals(3, list.size());
		assertEquals(second, list.before(third));
		assertEquals(first, list.before(second));
		
		Position<String> middle = list.addAfter(second, "middle");
		assertEquals(4, list.size());
		assertEquals(second, list.before(middle));
		assertEquals(middle, list.before(third));
		
		list.remove(second);
		assertEquals(3, list.size());
		assertEquals(first, list.before(middle));
		assertEquals(middle, list.before(third));
	}
	
	/**
	 * Tests the after() method
	 */
	@Test
	public void testAfter() {
		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertEquals(null, list.after(first).getElement());
		
		Position<String> second = list.addLast("two");
		assertEquals(2, list.size());
		assertEquals(second, list.after(first));
		
		Position<String> third = list.addLast("three");
		assertEquals(3, list.size());
		assertEquals(second, list.after(first));
		assertEquals(third, list.after(second));
		
		Position<String> middle = list.addAfter(second, "middle");
		assertEquals(4, list.size());
		assertEquals(middle, list.after(second));
		assertEquals(third, list.after(middle));
		
		list.remove(second);
		assertEquals(3, list.size());
		assertEquals(middle, list.after(first));
		assertEquals(third, list.after(middle));
	}
	
	/**
	 * Tests the addBefore() method
	 */
	@Test
	public void testAddBefore() {
		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		
		Position<String> second = list.addBefore(first, "two");
		assertEquals(2, list.size());
		assertEquals(second, list.first());
		assertEquals(first, list.last());
		
		Position<String> third = list.addBefore(second, "three");
		assertEquals(3, list.size());
		assertEquals(third, list.first());
		assertEquals(first, list.last());
		
		Position<String> middle = list.addBefore(second, "middle");
		assertEquals(4, list.size());
		assertEquals(middle, list.after(third));
		assertEquals(middle, list.before(second));
	}
	
	/**
	 * Tests the addAfter() method
	 */
	@Test
	public void testAddAfter() {
		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		
		Position<String> second = list.addAfter(first, "two");
		assertEquals(2, list.size());
		assertEquals(second, list.last());
		assertEquals(first, list.first());
		
		Position<String> third = list.addAfter(second, "three");
		assertEquals(3, list.size());
		assertEquals(third, list.last());
		assertEquals(first, list.first());
		
		Position<String> middle = list.addAfter(second, "middle");
		assertEquals(4, list.size());
		assertEquals(middle, list.before(third));
		assertEquals(middle, list.after(second));
	}
	
	/**
	 * Tests the set() method
	 */
	@Test
	public void testSet() {
		Position<String> first = list.addFirst("one");
		
		Position<String> second = list.addAfter(first, "two");
		assertEquals(2, list.size());
		
		Position<String> third = list.addAfter(second, "three");
		assertEquals(3, list.size());
		
		Position<String> middle = list.addAfter(second, "middle");
		assertEquals(4, list.size());
		
		list.set(third, "new");
		assertEquals("new", third.getElement());
		list.set(middle, "newest");
		assertEquals("newest", middle.getElement());
	}
	
	/**
	 * Tests the remove() method
	 */
	@Test
	public void testRemove() {
		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		
		Position<String> second = list.addAfter(first, "two");
		assertEquals(2, list.size());
		assertEquals(second, list.last());
		assertEquals(first, list.first());
		
		Position<String> third = list.addAfter(second, "three");
		assertEquals(3, list.size());
		assertEquals(third, list.last());
		assertEquals(first, list.first());
		
		Position<String> middle = list.addAfter(second, "middle");
		assertEquals(4, list.size());
		assertEquals(middle, list.before(third));
		assertEquals(middle, list.after(second));
		
		list.remove(first);
		assertEquals(3, list.size());
		assertEquals(second, list.first());
		assertEquals(middle, list.after(second));
		assertEquals(third, list.after(middle));
		
		list.remove(third);
		assertEquals(2, list.size());
		assertEquals(second, list.first());
		assertEquals(middle, list.last());
		assertEquals(middle, list.after(second));
		assertEquals(second, list.before(middle));
	}
	
	/**
	 * Tests the iterator() method
	 */
	@Test
	public void testIterator() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		Iterator<String> it = list.iterator();
		
		try {
			it.remove();
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addLast("two");
		Position<String> third = list.addLast("three");
		assertEquals(3, list.size());
		
		it = list.iterator();
		assertTrue(it.hasNext());
		assertEquals("one", it.next());
		assertEquals("two", it.next());
		assertEquals("three", it.next());
		assertFalse(it.hasNext());
		
		assertEquals(third, list.last());
		
		it.remove();
		assertEquals(2, list.size());
		assertEquals(first, list.first());
		assertEquals(second, list.last());
		
		try {
			it.remove();
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		
		try {
			it.next();
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
		
	}
	
	/**
	 * Tests the positions() method
	 */
	@Test
	public void testPositions() {
		assertEquals(0, list.size());
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addLast("two");
		Position<String> third = list.addLast("three");
		assertEquals(3, list.size());
		
		Iterator<Position<String>> it = list.positions().iterator();
		assertTrue(it.hasNext());
		assertEquals(first, it.next());
		assertEquals(second, it.next());
		assertEquals(third, it.next());
		assertFalse(it.hasNext());
		
		it.remove();
		assertEquals(2, list.size());
		assertEquals(first, list.first());
		assertEquals(second, list.last());
		
		try {
			it.remove();
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		
		try {
			it.next();
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
	}
}