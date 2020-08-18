package edu.ncsu.csc316.compressor.manager;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;
/**
 * Tests the CompressionManager class
 * @author CalebWiebe
 *
 */
public class CompressionManagerTest {
	/**
	 * Tests the getMostFrequentWords method
	 */
	@Test
	public void testGetMostFrequentWords() {
		List<String> list = new ArrayBasedList<String>();
		CompressionManager manager = new CompressionManager();
		list.addLast("apple");
		list.addLast("bear");
		list.addLast("caleb");
		list.addLast("caleb");
		list.addLast("apple");
		list.addLast("water");
		list.addLast("left");
		list.addLast("pie");
		list.addLast("jeff");
		list.addLast("steve");
		list.addLast("next");
		list.addLast("chicken");
		list.addLast("hi");
		List<String> newList = manager.getMostFrequentWords(list, 3);
		assertEquals("apple", newList.get(0));
		assertEquals("caleb", newList.get(1));
		assertEquals("bear", newList.get(2));
		
		//Test alphabetical ordering when words have same frequency 
		list = new ArrayBasedList<String>();
		manager = new CompressionManager();
		//Add "Matt" five times
		for (int i = 0; i < 5; i++) {
			list.addLast("Matt");
		}
		//Add "A" five times
		for (int i = 0; i < 5; i++) {
			list.addLast("A");
		}
		//Add "B" five times
		for (int i = 0; i < 5; i++) {
			list.addLast("B");
		}
		//Add "Kierski" five times
		for (int i = 0; i < 5; i++) {
			list.addLast("Kierski");
		}
		
		newList = manager.getMostFrequentWords(list, 3);
		assertEquals("a", newList.get(0));
		assertEquals("b", newList.get(1));
		assertEquals("kierski", newList.get(2));
		
		//Test alphabetical ordering when only some words have same frequency
		list = new ArrayBasedList<String>();
		manager = new CompressionManager();
		//Add "Matt" five times
		for (int i = 0; i < 5; i++) {
			list.addLast("Matt");
		}
		//Add "A" four times
		for (int i = 0; i < 4; i++) {
			list.addLast("A");
		}
		//Add "B" four times
		for (int i = 0; i < 4; i++) {
			list.addLast("B");
		}
		//Add "Kierski" five times
		for (int i = 0; i < 5; i++) {
			list.addLast("Kierski");
		}
		
		newList = manager.getMostFrequentWords(list, 3);
		assertEquals("kierski", newList.get(0));
		assertEquals("matt", newList.get(1));
		assertEquals("a", newList.get(2));
		
		//Test alphabetical ordering when words are similar and have same frequency
		list = new ArrayBasedList<String>();
		manager = new CompressionManager();
		//Add "Bear" five times
		for (int i = 0; i < 5; i++) {
			list.addLast("Bear");
		}
		//Add "Bean" five times
		for (int i = 0; i < 5; i++) {
			list.addLast("Bean");
		}
		//Add "Beans" five times
		for (int i = 0; i < 5; i++) {
			list.addLast("Beans");
		}
		//Add "Bears" five times
		for (int i = 0; i < 5; i++) {
			list.addLast("Bears");
		}
		
		newList = manager.getMostFrequentWords(list, 3);
		assertEquals("bean", newList.get(0));
		assertEquals("beans", newList.get(1));
		assertEquals("bear", newList.get(2));
		
		newList = manager.getMostFrequentWords(list, 4);
		assertEquals("bears", newList.get(3));
		
		//Test alphabetical ordering with differing capitals
		list = new ArrayBasedList<String>();
		manager = new CompressionManager();
		//Add "Matt" five times
		for (int i = 0; i < 5; i++) {
			list.addLast("Matt");
		}
		//Add "matt" five times
		for (int i = 0; i < 5; i++) {
			list.addLast("matt");
		}
		//Add "kierski" five times
		for (int i = 0; i < 5; i++) {
			list.addLast("kierski");
		}
		//Add "Kierski" five times
		for (int i = 0; i < 5; i++) {
			list.addLast("Kierski");
		}
		
//		newList = manager.getMostFrequentWords(list, 4);
//		assertEquals("kierski", newList.get(0));
//		assertEquals("matt", newList.get(1));
//		assertEquals("kierski", newList.get(2));
//		assertEquals("matt", newList.get(3));
//		
//		//Test to see if there is thrown exception when num of words > size
//		newList = manager.getMostFrequentWords(list, 10);
//		assertEquals("Kierski", newList.get(0));
//		assertEquals("Matt", newList.get(1));
//		assertEquals("kierski", newList.get(2));
//		assertEquals("matt", newList.get(3));
		
//		try {
//			newList.get(4);
//		} catch (Exception e){
//			assertTrue(e instanceof IndexOutOfBoundsException);
//		}
	}
	/**
	 * Tests the getCompressed method
	 */
	@Test
	public void testGetCompressed() {
		//Check repeating words and capital variations of words
		List<String> list = new ArrayBasedList<String>();
		CompressionManager manager = new CompressionManager();
		list.addLast("There there there there There theree");
		List<String> newList = manager.getCompressed(list);
		assertEquals("0", newList.get(0));
		assertEquals("There there 2 2 1 theree", newList.get(1));
		
		//Test maintaining original whitespace
		list = new ArrayBasedList<String>();
		manager = new CompressionManager();
		list.addLast("MY  my  My  mY  MY");
		newList = manager.getCompressed(list);
		assertEquals("0", newList.get(0));
		assertEquals("MY  my  My  mY  1", newList.get(1));
		
		//Test maintaining placement of punctuation
		list = new ArrayBasedList<String>();
		manager = new CompressionManager();
		list.addLast("!!!H,i,m!!! !Hi Hi!");
		list.addLast("Hi Hi Hi");
		newList = manager.getCompressed(list);
		assertEquals("!!!H,i,m!!! !Hi 4!", newList.get(1));
		assertEquals("4 4 4", newList.get(2));
		
		// Test maintaining punctuation with repeating words
		list = new ArrayBasedList<String>();
		manager = new CompressionManager();
		list.addLast("!!Hell,o!! !!my!! !!name!! !!is!! !!Matthew!!");
		list.addLast("!!Hello!! !!my!! !!name!! !!is!! !!M,atthew!!");
		list.addLast("!!Hello!! !!my!! !!name!! !!is!! !!Matthew!!");
		newList = manager.getCompressed(list);
		assertEquals("0", newList.get(0));
		assertEquals("!!Hell,o!! !!my!! !!name!! !!is!! !!Matthew!!", newList.get(1));
		assertEquals("!!Hello!! !!3!! !!4!! !!5!! !!M,atthew!!", newList.get(2));
		assertEquals("!!7!! !!3!! !!4!! !!5!! !!6!!", newList.get(3));
	}
	/**
	 * Tests the getDecompressed method
	 */
	@Test
	public void testGetDecompressed() {
		List<String> list = new ArrayBasedList<String>();
		CompressionManager manager = new CompressionManager();
		list.addLast("Hi, 1 1!");
		List<String> newList = manager.getDecompressed(list);
		assertEquals("Hi, Hi Hi!", newList.get(0));
		
		//Test maintaining punctuation before, between, and after words
		list = new ArrayBasedList<String>();
		manager = new CompressionManager();
		list.addLast("!!Hell,o!! !!my!! !!name!! !!is!! !!Matthew!!");
		list.addLast("!!Hello!! !!3!! !!4!! !!5!! !!M,atthew!!");
		list.addLast("!!7!! !!3!! !!4!! !!5!! !!6!!");
		newList = manager.getDecompressed(list);
		assertEquals("!!Hell,o!! !!my!! !!name!! !!is!! !!Matthew!!", newList.get(0));
		assertEquals("!!Hello!! !!my!! !!name!! !!is!! !!M,atthew!!", newList.get(1));
		assertEquals("!!Hello!! !!my!! !!name!! !!is!! !!Matthew!!", newList.get(2));
		
		list = new ArrayBasedList<String>();
		manager = new CompressionManager();
		list.addLast("MY  my  My  mY  1");
		newList = manager.getDecompressed(list);
		assertEquals("MY  my  My  mY  MY", newList.get(0));
		
		//Test repeating words with different capitalizations
		list = new ArrayBasedList<String>();
		manager = new CompressionManager();
		list.addLast("There there 2 2 1 theree");
		newList = manager.getDecompressed(list);
		assertEquals("There there there there There theree", newList.get(0));
		
		//Test to ensure proper placement of punctuation
		list = new ArrayBasedList<String>();
		manager = new CompressionManager();
		list.addLast("Hi  1  1  1  1  1  h,i, 2 2 2 2 2 1 3");
		newList = manager.getDecompressed(list);
		assertEquals("Hi  Hi  Hi  Hi  Hi  Hi  h,i, h h h h h Hi i" , newList.get(0));
	}
	/**
	 * Tests the get most frequent words method with a file
	 */
	@Test
	public void testGetMostFrequentWordsFile() {
		CompressionManager manager = new CompressionManager();
		String output = null;
		try {
			output = manager.getMostFrequentWords("input/fleaFly.txt", 5);
		} catch (FileNotFoundException e) {
			assertNull(output);
		}
		String test = "Most Frequent Words Report [\n   a\n   fly\n   the\n   flea\n   flue\n]";
		assertEquals(test, output);
	}
	/**
	 * Tests the process file method
	 */
	@Test
	public void testProcessFile() {
		CompressionManager manager = new CompressionManager();
		List<String> output = null;
		try {
			output = manager.processFile("input/fleaFly.txt", "output/");
		} catch (FileNotFoundException e) {
			assertNull(output);
		}
		assertEquals("0", output.get(0));
		assertEquals("A flea and a fly in 4 flue", output.get(1));
		assertEquals("Were imprisoned, so what could they do?", output.get(2));
		assertEquals("Said the 5, \"let us flee!\"", output.get(3));
		assertEquals("\"Let 18 5!\" said 16 2.", output.get(4));
		assertEquals("So 13 flew through 4 flaw 6 16 7. ", output.get(5));
		
		try {
			output = manager.processFile("input/fleaFly.316", "output/");
		} catch (FileNotFoundException e) {
			assertNull(output);
		}
		assertEquals("A flea and a fly in a flue", output.get(0));
		assertEquals("Were imprisoned, so what could they do?", output.get(1));
		assertEquals("Said the fly, \"let us flee!\"", output.get(2));
		assertEquals("\"Let us fly!\" said the flea.", output.get(3));
		assertEquals("So they flew through a flaw in the flue. ", output.get(4));
	}
}