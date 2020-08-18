package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Tests the HeapPriorityQueue class
 * @author Matthew Kierski
 *
 */
public class HeapPriorityQueueTest {

	/** Instance of heap to be used for testing */
	private PriorityQueue<Integer, String> heap;
	
	/**
	 * Sets heap to new HeapPriorityQueue
	 */
	@Before
	public void setUp() {
		heap = new HeapPriorityQueue<Integer, String>();
	}
	
	/**
	 * Tests the insert() method
	 */
	@Test
	public void testInsert() {
		assertTrue(heap.isEmpty());
		assertTrue(heap.size() == 0);
		
		heap.insert(8, "eight");
		assertEquals(1, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(8, (int)heap.min().getKey());
		
		heap.insert(4, "four");
		assertEquals(2, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(4, (int)heap.min().getKey());
		
		heap.insert(6, "six");
		assertEquals(3, heap.size());
		assertEquals(4, (int)heap.min().getKey());
		
		heap.insert(2, "two");
		assertEquals(4, heap.size());
		assertEquals(2, (int)heap.min().getKey());
	}
	
	/**
	 * Tests the min() method
	 */
	@Test
	public void testMin() {
		assertTrue(heap.isEmpty());
		assertTrue(heap.size() == 0);
		
		assertNull(heap.min());
		
		heap.insert(2, "two");
		assertEquals(2, (int)heap.min().getKey());
		heap.insert(1, "one");
		assertEquals(1, (int)heap.min().getKey());
		heap.insert(4, "four");
		assertEquals(1, (int)heap.min().getKey());
		heap.deleteMin();
		assertEquals(2, (int)heap.min().getKey());
		
		heap.deleteMin();
		heap.deleteMin();
		assertNull(heap.min());
	}
	
	/**
	 * Tests the deleteMin() method
	 */
	@Test 
	public void deleteMin() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());
		
		assertNull(heap.deleteMin());
		
		heap.insert(2, "two");
		assertEquals(2, (int)heap.min().getKey());
		heap.insert(1, "one");
		assertEquals(1, (int)heap.min().getKey());
		heap.insert(4, "four");
		assertEquals(1, (int)heap.min().getKey());
		assertEquals(3, heap.size());
		
		assertEquals(1, (int)heap.deleteMin().getKey());
		assertEquals(2, heap.size());
		assertEquals(2, (int)heap.min().getKey());
		
		assertEquals(2, (int)heap.deleteMin().getKey());
		assertEquals(1, heap.size());
		assertEquals(4, (int)heap.min().getKey());
		
		assertEquals(4, (int)heap.deleteMin().getKey());
		assertEquals(0, heap.size());
		assertNull(heap.deleteMin());
	}
	
	/**
	 * Tests the HeapPriorityQueue class using custom comparator
	 */
	@Test
	public void testStudentHeap() {
		PriorityQueue<Student, String> sHeap = new HeapPriorityQueue<Student, String>(new StudentIDComparator());
		Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
		Student s2 = new Student("J", "S", 2, 1, 2, "js2");
		Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
		Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
		Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
		
		assertTrue(sHeap.isEmpty());
		assertEquals(0, sHeap.size());
		
		sHeap.insert(s5, "s5");
		assertEquals(s5, sHeap.min().getKey());
		assertEquals(1, sHeap.size());
		
		sHeap.insert(s4, "s4");
		assertEquals(s4, sHeap.min().getKey());
		assertEquals(2, sHeap.size());
		
		sHeap.insert(s3, "s3");
		assertEquals(s3, sHeap.min().getKey());
		assertEquals(3, sHeap.size());
		
		sHeap.insert(s2, "s2");
		assertEquals(s2, sHeap.min().getKey());
		assertEquals(4, sHeap.size());
		
		sHeap.insert(s1, "s1");
		assertEquals(s1, sHeap.min().getKey());
		assertEquals(5, sHeap.size());
		
		assertEquals(s1, sHeap.deleteMin().getKey());
		assertEquals(4, sHeap.size());
		assertEquals(s2, sHeap.min().getKey());
		
		assertEquals(s2, sHeap.deleteMin().getKey());
		assertEquals(3, sHeap.size());
		assertEquals(s3, sHeap.min().getKey());
		
		assertEquals(s3, sHeap.deleteMin().getKey());
		assertEquals(2, sHeap.size());
		assertEquals(s4, sHeap.min().getKey());
		
		assertEquals(s4, sHeap.deleteMin().getKey());
		assertEquals(1, sHeap.size());
		assertEquals(s5, sHeap.min().getKey());
		
		assertEquals(s5, sHeap.deleteMin().getKey());
		assertEquals(0, sHeap.size());
		assertNull(sHeap.min());
		
	}
}