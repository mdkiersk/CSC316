package edu.ncsu.csc316.dsa.queue;


import static org.junit.Assert.*;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the ArrayBasedQueue test
 * @author Matthew Kierski
 *
 */
public class ArrayBasedQueueTest {

	// Private instance of a String queue
    private Queue<String> queue;
    
    /**
     * Sets queue to be a new ArrayBased queue
     */
    @Before
    public void setUp() {
        queue = new ArrayBasedQueue<String>();
    }
    
    /**
	 * Tests the enqueue() method
	 */
    @Test
    public void testEnqueue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("two");
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("three");
        assertEquals(3, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("four");
        assertEquals(4, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("five");
        assertEquals(5, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("six");
        assertEquals(6, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("seven");
        assertEquals(7, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("eight");
        assertEquals(8, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("nine");
        assertEquals(9, queue.size());
        assertFalse(queue.isEmpty());
        
        //Ensure capacity grows when adding tenth element
        queue.enqueue("ten");
        assertEquals(10, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("eleven");
        assertEquals(11, queue.size());
        assertFalse(queue.isEmpty());
    }
    
    
    /**
	 * Tests the dequeue() method
	 */
    @Test
    public void testDequeue() {
        assertEquals(0, queue.size());
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        queue.enqueue("four");
        queue.enqueue("five");
        queue.enqueue("six");
        assertEquals(6, queue.size());
        
        assertEquals("one",  queue.dequeue());
        assertEquals(5, queue.size());
        
       assertEquals("two", queue.dequeue());
       assertEquals(4, queue.size());
       
       assertEquals("three", queue.dequeue());
       assertEquals(3, queue.size());
       
       assertEquals("four", queue.dequeue());
       assertEquals(2, queue.size());
       
       assertEquals("five", queue.dequeue());
       assertEquals(1, queue.size());
       
       assertEquals("six", queue.dequeue());
       assertEquals(0, queue.size());
        
        try {
            queue.dequeue();
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
    }
    
    
    /**
	 * Tests the front() method
	 */
    @Test
    public void testFront() {
    	 assertEquals(0, queue.size());
         assertTrue(queue.isEmpty());
         
         queue.enqueue("one");
         assertEquals(1, queue.size());
         assertEquals("one", queue.front());
         
         queue.enqueue("two");
         assertEquals(2, queue.size());
         assertEquals("one", queue.front());
         
         queue.enqueue("three");
         assertEquals(3, queue.size());
         assertEquals("one", queue.front());

         
         queue.enqueue("four");
         assertEquals(4, queue.size());
         assertEquals("one", queue.front());

         
         queue.enqueue("five");
         assertEquals(5, queue.size());
         assertEquals("one", queue.front());
         
         assertEquals("one", queue.dequeue());
         assertEquals(4, queue.size());
         assertEquals("two", queue.front());
         
         assertEquals("two", queue.dequeue());
         assertEquals(3, queue.size());
         assertEquals("three", queue.front());
         
         assertEquals("three", queue.dequeue());
         assertEquals(2, queue.size());
         assertEquals("four", queue.front());
         
         assertEquals("four", queue.dequeue());
         assertEquals(1, queue.size());
         assertEquals("five", queue.front());
         
         assertEquals("five", queue.dequeue());
         assertEquals(0, queue.size());
         
         try {
        	 queue.front();
         } catch (Exception e) {
        	 assertTrue(e instanceof NoSuchElementException);
         }
    }
}