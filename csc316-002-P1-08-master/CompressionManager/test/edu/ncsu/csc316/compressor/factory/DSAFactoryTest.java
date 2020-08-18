package edu.ncsu.csc316.compressor.factory;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Identifiable;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.SearchTableMap;
import edu.ncsu.csc316.dsa.map.UnorderedArrayMap;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;
import edu.ncsu.csc316.dsa.sorter.MergeSorter;
import edu.ncsu.csc316.dsa.sorter.RadixMapSorter;
import edu.ncsu.csc316.dsa.sorter.RadixSorter;
import edu.ncsu.csc316.dsa.sorter.Sorter;
import edu.ncsu.csc316.dsa.stack.LinkedStack;
import edu.ncsu.csc316.dsa.stack.Stack;

/**
 * Tests the DSAFactory class
 * @author Matthew Kierski
 * @author Caleb Wiebe
 */
public class DSAFactoryTest {

	/**
	 * Tests the getUnorderedMap method
	 */
	@Test
	public void testGetUnorderedMap() {
		Map<String, Integer> map = DSAFactory.getUnorderedMap();
		assertTrue(map instanceof UnorderedArrayMap);
	}

	/**
	 * Tests the getOrderedMap method
	 */
	@Test
	public void testGetOrderedMap() {
		Map<String, Integer> map = DSAFactory.getOrderedMap();
		assertTrue(map instanceof SearchTableMap);
	}

	/**
	 * Tests the getIndexedList method
	 */
	@Test
	public void testGetIndexedList() {
		List<String> list = DSAFactory.getIndexedList();
		assertTrue(list instanceof ArrayBasedList);
	}
	
	/**
	 * Tests the getPositionalList method
	 */
	@Test
	public void testGetPositionalList() {
		PositionalList<String> list = DSAFactory.getPositionalList();
		assertTrue(list instanceof PositionalLinkedList);
	}

	/**
	 * Tests the getComparisonSorter method
	 */
	@Test
	public void testGetComparisonSorter() {
		Sorter<Integer> sorter = DSAFactory.getComparisonSorter();
		assertTrue(sorter instanceof MergeSorter);
	}

	/**
	 * Tests the getNonComparisonSorter method
	 */
	@Test
	public void testGetNonComparisonSorter() {
		Sorter<Identifiable> sorter = DSAFactory.getNonComparisonSorter();
		assertTrue(sorter instanceof RadixSorter);
	}

	/**
	 * Tests the getNonComparisonMapSorter method
	 */
	@Test
	public void testGetNonComparisonMapSorter() {
		RadixMapSorter sorter = DSAFactory.getNonComparisonMapSorter();
		assertTrue(sorter instanceof RadixMapSorter);
	}

	/**
	 * Tests the getStack method
	 */
	@Test
	public void testGetStack() {
		Stack<String> stack = DSAFactory.getStack();
		assertTrue(stack instanceof LinkedStack);
	}

	/**
	 * Tests the getQueue method
	 */
	@Test
	public void testGetQueue() {
		Queue<String> queue = DSAFactory.getQueue();
		assertTrue(queue instanceof ArrayBasedQueue);
	}

}
