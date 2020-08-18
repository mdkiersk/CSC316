package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Tests the HeapAdaptablePriorityQueue class
 * @author Matthew Kierski
 *
 */
public class HeapAdaptablePriorityQueueTest {

	/** Instance of heap used for testing */
	private HeapAdaptablePriorityQueue<Integer, String> heap;
	
	/**
	 * Sets heap to new HeapAdaptablePriorityQueue
	 */
	@Before
	public void setUp() {
		heap = new HeapAdaptablePriorityQueue<Integer, String>();
	}
	
	/**
	 * Tests the replaceKey() method
	 */
	@Test
	public void testReplaceKey() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());
		
		Entry<Integer, String> e8 = heap.insert(8, "eight");
//		Entry<Integer, String> e7 = heap.insert(7, "seven");
//		Entry<Integer, String> e6 = heap.insert(6, "six");
//		Entry<Integer, String> e5 = heap.insert(5, "five");
		Entry<Integer, String> e4 = heap.insert(4, "four");
//		Entry<Integer, String> e3 = heap.insert(3, "three");
//		Entry<Integer, String> e2 = heap.insert(2, "two");
//		Entry<Integer, String> e1 = heap.insert(1, "one");
		Entry<Integer, String> e0 = heap.insert(0, "zero");
		assertEquals(3, heap.size());
		assertFalse(heap.isEmpty());
		
		heap.replaceKey(e8,  -5);
		assertEquals(-5, (int)heap.min().getKey());
		assertEquals("eight", heap.min().getValue());
		assertEquals(3, heap.size());
		
		heap.replaceKey(e8, 5);
		assertEquals(0, (int)heap.min().getKey());
		assertEquals(3, heap.size());
		
		heap.replaceKey(e0, -1);
		assertEquals(-1, (int)heap.min().getKey());
		assertEquals(3, heap.size());
		
		heap.replaceKey(e4, 20);
		assertEquals(-1, (int)heap.min().getKey());
		assertEquals(3, heap.size());
	}
	
	/**
	 * Tests the replaceValue() method
	 */
	@Test
	public void testReplaceValue() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());
		
		Entry<Integer, String> e8 = heap.insert(8, "eight");
		Entry<Integer, String> e7 = heap.insert(7, "seven");
		Entry<Integer, String> e6 = heap.insert(6, "six");
		Entry<Integer, String> e5 = heap.insert(5, "five");
		Entry<Integer, String> e4 = heap.insert(4, "four");
		Entry<Integer, String> e3 = heap.insert(3, "three");
		Entry<Integer, String> e2 = heap.insert(2, "two");
		Entry<Integer, String> e1 = heap.insert(1, "one");
		Entry<Integer, String> e0 = heap.insert(0, "zero");
		assertEquals(9, heap.size());
		
		heap.replaceValue(e8,  "EIGHT");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("zero", heap.min().getValue());
		assertEquals(9, heap.size());
		assertEquals("EIGHT",  e8.getValue());
		
		heap.replaceValue(e7, "SEVEN");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("zero", heap.min().getValue());
		assertEquals(9, heap.size());
		assertEquals("SEVEN",  e7.getValue());
		
		heap.replaceValue(e6, "SIX");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("zero", heap.min().getValue());
		assertEquals(9, heap.size());
		assertEquals("SIX",  e6.getValue());
		
		heap.replaceValue(e5, "five");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("zero", heap.min().getValue());
		assertEquals(9, heap.size());
		assertEquals("five",  e5.getValue());
		
		heap.replaceValue(e4, "fOur");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("zero", heap.min().getValue());
		assertEquals(9, heap.size());
		assertEquals("fOur",  e4.getValue());
		
		heap.replaceValue(e3, "THREE");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("zero", heap.min().getValue());
		assertEquals(9, heap.size());
		assertEquals("THREE",  e3.getValue());
		
		heap.replaceValue(e2, "TWO");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("zero", heap.min().getValue());
		assertEquals(9, heap.size());
		assertEquals("TWO",  e2.getValue());
		
		heap.replaceValue(e1, "one");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("zero", heap.min().getValue());
		assertEquals(9, heap.size());
		assertEquals("one",  e1.getValue());
		
		heap.replaceValue(e0, "ZERO");
		assertEquals("ZERO", heap.min().getValue());
	}
	
	/**
	 * Tests the remove() method
	 */
	@Test
	public void testRemove() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());
		
		Entry<Integer, String> e8 = heap.insert(8, "eight");
		Entry<Integer, String> e7 = heap.insert(7, "seven");
		Entry<Integer, String> e6 = heap.insert(6, "six");
		Entry<Integer, String> e5 = heap.insert(5, "five");
		Entry<Integer, String> e4 = heap.insert(4, "four");
		Entry<Integer, String> e3 = heap.insert(3, "three");
		Entry<Integer, String> e2 = heap.insert(2, "two");
		Entry<Integer, String> e1 = heap.insert(1, "one");
		Entry<Integer, String> e0 = heap.insert(0, "zero");
		assertFalse(heap.isEmpty());
		assertEquals(9, heap.size());
		
		heap.remove(e0);
		assertEquals(1, (int)heap.min().getKey());
		assertEquals("one", heap.min().getValue());
		assertEquals(8, heap.size());
		
		heap.remove(e2);
		assertEquals(1, (int)heap.min().getKey());
		assertEquals("one", heap.min().getValue());
		assertEquals(7, heap.size());
		
		heap.remove(e1);
		assertEquals(3, (int)heap.min().getKey());
		assertEquals("three", heap.min().getValue());
		assertEquals(6, heap.size());
		
		heap.remove(e8);
		assertEquals(3, (int)heap.min().getKey());
		assertEquals("three", heap.min().getValue());
		assertEquals(5, heap.size());
		
		heap.remove(e4);
		assertEquals(3, (int)heap.min().getKey());
		assertEquals("three", heap.min().getValue());
		assertEquals(4, heap.size());
		
		heap.remove(e3);
		assertEquals(5, (int)heap.min().getKey());
		assertEquals("five", heap.min().getValue());
		assertEquals(3, heap.size());
		
		heap.remove(e7);
		assertEquals(5, (int)heap.min().getKey());
		assertEquals("five", heap.min().getValue());
		assertEquals(2, heap.size());
		
		heap.remove(e5);
		assertEquals(6, (int)heap.min().getKey());
		assertEquals("six", heap.min().getValue());
		assertEquals(1, heap.size());
		
		heap.remove(e6);
		assertNull(heap.min());
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
	}
	
	/**
	 * Tests the HeapAdaptablePriorityQueue class using custom comparator
	 */
	@Test
	public void testStudentHeap() {
		AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(new StudentIDComparator());
		Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
		Student s2 = new Student("J", "S", 2, 1, 2, "js2");
		Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
		Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
		Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
		
		assertTrue(sHeap.isEmpty());
		assertEquals(0, sHeap.size());
		
		sHeap.insert(s5, "s5");
		assertEquals("s5", sHeap.min().getValue());
		assertEquals(1, sHeap.size());
		
		sHeap.insert(s3, "s3");
		assertEquals("s3", sHeap.min().getValue());
		assertEquals(2, sHeap.size());
		
		sHeap.insert(s4, "s4");
		assertEquals("s3", sHeap.min().getValue());
		assertEquals(3, sHeap.size());
		
		sHeap.insert(s2, "s2");
		assertEquals("s2", sHeap.min().getValue());
		assertEquals(4, sHeap.size());
		
		sHeap.insert(s1, "s1");
		assertEquals("s1", sHeap.min().getValue());
		assertEquals(5, sHeap.size());
		
		assertEquals("s1", sHeap.deleteMin().getValue());
		assertEquals("s2", sHeap.min().getValue());
		assertEquals(4, sHeap.size());
	}
}