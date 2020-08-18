package edu.ncsu.csc316.dsa.stack;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the LinkedStack class
 * @author Matthew Kierski
 *
 */
public class LinkedStackTest {

	// Private instance of a String stack
	private Stack<String> stack;
	
	/**
	 * Sets the stack to be a new LinkedStack before each method
	 */
	@Before
	public void setUp() {
		stack = new LinkedStack<String>();
	}
	
	/**
	 * Tests the push() method
	 */
	@Test
	public void testPush() {
		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());
		
		stack.push("one");
		assertEquals(1, stack.size());
		assertFalse(stack.isEmpty());
		
		stack.push("two");
		assertEquals(2, stack.size());
		assertFalse(stack.isEmpty());
		
		stack.push("three");
		assertEquals(3, stack.size());
		assertFalse(stack.isEmpty());
	}

	/**
	 * Tests the pop() method
	 */
	@Test
	public void testPop() {
		assertEquals(0, stack.size());
		stack.push("one");
		stack.push("two");
		stack.push("three");
		stack.push("four");
		stack.push("five");
		stack.push("six");
		assertEquals(6, stack.size());
		
		assertEquals("six",  stack.pop());
		assertEquals(5, stack.size());
		
		assertEquals("five", stack.pop());
		assertEquals(4, stack.size());
		
		assertEquals("four", stack.pop());
		assertEquals(3, stack.size());
		
		assertEquals("three", stack.pop());
		assertEquals(2, stack.size());
		
		assertEquals("two", stack.pop());
		assertEquals(1, stack.size());
		
		assertEquals("one", stack.pop());
		assertEquals(0, stack.size());
		
		try {
			stack.pop();
		} catch (Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}
	}

	/**
	 * Tests the top() method
	 */
	@Test
	public void testTop() {	
		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());

		stack.push("one");
		assertEquals(1, stack.size());
		assertFalse(stack.isEmpty());
		assertEquals("one", stack.top());

		stack.push("two");
		assertEquals(2, stack.size());
		assertFalse(stack.isEmpty());
		assertEquals("two", stack.top());

		stack.push("three");
		assertEquals(3, stack.size());
		assertFalse(stack.isEmpty());
		assertEquals("three", stack.top());
	}
}