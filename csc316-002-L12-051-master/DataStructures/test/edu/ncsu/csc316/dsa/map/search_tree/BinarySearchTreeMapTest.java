package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map.Entry;


/**
 * Tests the BinarySearchTreeMap class
 * @author Matthew Kierski
 *
 */
public class BinarySearchTreeMapTest {

	/** Instance of BSTMap to use in tests */
	BinarySearchTreeMap<Integer, String> tree;

	/**
	 * Sets tree to new BSTMap
	 */
	@Before
	public void setUp() {
		tree = new BinarySearchTreeMap<Integer, String>();
	}
	
	/**
	 * Tests the put() method
	 */
	@Test
	public void testPut() {
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
		assertEquals("BalanceableBinaryTree[\nnull\n]", tree.toString());
		
		assertNull(tree.put(1, "one"));
		assertEquals(1, tree.size());
		assertFalse(tree.isEmpty());
		assertTrue(tree.isRoot(tree.root()));
		assertEquals(1, (int) tree.root().getElement().getKey());

		assertNull(tree.put(2, "two"));
		assertEquals(2, tree.size());
		assertEquals("two", tree.get(2));
		
		assertEquals("two", tree.put(2, "newTwo"));
		assertEquals(2, tree.size());
		assertEquals("newTwo", tree.get(2));
		
		assertNull(tree.put(3, "three"));
		assertEquals(3, tree.size());
		assertEquals("three", tree.get(3));
		
		//Add fourth key with same value as third key
		assertNull(tree.put(4, "four"));
		assertEquals(4, tree.size());
		assertEquals("four", tree.get(4));

		//Tests iterator for currently constructed tree
        Iterator<Position<Entry<Integer, String>>> pre = tree.preOrder().iterator();
        assertTrue(pre.hasNext());
        assertEquals(tree.root(), pre.next());
        
        Iterator<Position<Entry<Integer, String>>> post = tree.postOrder().iterator();
        assertTrue(post.hasNext());
        assertEquals(null, post.next().getElement());
        
        Iterator<Position<Entry<Integer, String>>> in = tree.inOrder().iterator();
        assertTrue(in.hasNext());
        
        Iterator<Position<Entry<Integer, String>>> level = tree.levelOrder().iterator();
        assertTrue(level.hasNext());
        assertEquals(tree.root(), level.next());
        
        Iterator<Entry<Integer, String>> entries = tree.entrySet().iterator();
        assertTrue(entries.hasNext());
        
        Iterator<Position<Entry<Integer, String>>> children = tree.children(tree.root()).iterator();
        assertTrue(children.hasNext());

        assertEquals(2, tree.numChildren(tree.root()));
		assertTrue(tree.isRoot(tree.root()));
		tree.setProperty(tree.root(), 1);
		assertEquals(1, tree.getProperty(tree.root()));
		assertEquals(1, (int)tree.root().getElement().getKey());
	}

	/**
	 * Tests the get() method
	 */
	@Test
	public void testGet() {
		tree.put(1, "one");
		assertEquals(1, tree.size());
		assertEquals("one", tree.get(1));

		assertNull(tree.put(2, "two"));
		assertEquals(2, tree.size());
		assertEquals("two", tree.get(2));
		
		assertEquals("two", tree.put(2, "newTwo"));
		assertEquals(2, tree.size());
		assertEquals("newTwo", tree.get(2));
		
		assertNull(tree.put(3, "three"));
		assertEquals(3, tree.size());
		assertEquals("three", tree.get(3));
		
		//Add fourth key with same value as third key
		assertNull(tree.put(4, "three"));
		assertEquals(4, tree.size());
		assertEquals("three", tree.get(4));
	}

	/**
	 * Tests the remove() method
	 */
	@Test
	public void testRemove() {
		tree.put(1, "one");
		assertEquals(1, tree.size());

		assertNull(tree.remove(10));
		assertEquals(1, tree.size());

		assertEquals("one", tree.remove(1));
		assertEquals(0, tree.size());
		
		tree.put(2, "two");
		tree.put(1, "one");
		tree.put(3, "three");
		tree.put(4, "four");
		
//			2
//		   / \ 
//		  1   3
//		       \
//		        4
		assertEquals(4, tree.size());
		assertEquals("two", tree.remove(2));
		assertEquals(3, tree.size());
		assertEquals("three", tree.root().getElement().getValue());
		
//			3
//		   / \
//		  1   4
		tree.put(2, "two");
		assertEquals(4, tree.size());
		
//			3
//		   / \
//		  1   4
//		   \
//		    2
		assertEquals("one", tree.remove(1));
		assertEquals(3, tree.size());
		assertEquals("three", tree.root().getElement().getValue());
		
//			3
//		   / \ 
//		  2   4
		assertEquals("three", tree.remove(3));
		assertEquals(2, tree.size());
		assertEquals("four", tree.root().getElement().getValue());
		
//			4
//		   /
//		  2
		
		tree.put(3, "three");
		assertEquals(3, tree.size());
		
//			4
//		   /
//		  2
//		   \
//		    3
		assertEquals("two", tree.remove(2));
		assertEquals(2, tree.size());
		assertEquals("four", tree.remove(4));
		assertEquals("three", tree.root().getElement().getValue());
	}
	
	/**
	 * Tests the restructure and rotate methods
	 */
	@Test
	public void testRotateRestructure() {
		tree.put(1, "one");
		tree.put(2, "two");
		tree.put(3, "three");
		tree.put(4, "four");
		
		tree.rotate(tree.right(tree.right(tree.right(tree.root()))));
		assertEquals("one", tree.root().getElement().getValue());
		assertEquals("two", tree.right(tree.root()).getElement().getValue());
		assertEquals("four", tree.right(tree.right(tree.root())).getElement().getValue());
		
		tree = new BinarySearchTreeMap<Integer, String>();
		tree.put(1, "one");
		tree.put(2, "two");
		tree.put(3, "three");
		tree.put(4, "four");
		
		tree.restructure(tree.right(tree.right(tree.root())));
		assertEquals("two", tree.root().getElement().getValue());
		assertEquals("three", tree.right(tree.root()).getElement().getValue());
		assertEquals("four", tree.right(tree.right(tree.root())).getElement().getValue());
	}
}