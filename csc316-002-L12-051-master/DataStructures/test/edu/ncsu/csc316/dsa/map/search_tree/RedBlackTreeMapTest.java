package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the RedBlackTreeMap class
 * @author Matthew Kierski
 *
 */
public class RedBlackTreeMapTest {

	/** Instance of BSt to use in tests */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Sets tree to new RedBlackTreeMap
     */
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Tests the put() method
     */
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        //Case 1 : violating red property; uncle is black
        assertNull(tree.put(4,  "four"));
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(4, (int)tree.root().getElement().getKey());
        
        assertNull(tree.put(7,  "seven"));
        assertEquals(2, tree.size());
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());
        
        assertNull(tree.put(12,  "twelve"));
        assertEquals(3, tree.size());
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
        
       assertNull(tree.put(5, "five"));
       assertEquals(4, tree.size());
       assertEquals(7, (int)tree.root().getElement().getKey());
       assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
       assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
       assertEquals(5, (int)tree.right(tree.left(tree.root())).getElement().getKey());
       
       assertNull(tree.put(11, "eleven"));
       assertEquals(7, (int)tree.root().getElement().getKey());
       assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
       assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
       assertEquals(5, (int)tree.right(tree.left(tree.root())).getElement().getKey());
       assertEquals(11, (int)tree.left(tree.right(tree.root())).getElement().getKey());
    }
    
    /**
     * Tests the get() method
     */
    @Test
    public void testGet() {
    	assertNull(tree.put(4,  "four"));
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals("four", tree.get(4));
        
        assertNull(tree.put(7,  "seven"));
        assertEquals(2, tree.size());
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals("seven", tree.get(7));
        
        assertNull(tree.put(12,  "twelve"));
        assertEquals(3, tree.size());
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals("twelve", tree.get(12));
    }
    
    /**
     * Tests the remove() method
     */
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(4,  "four"));
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(4, (int)tree.root().getElement().getKey());
        
        assertNull(tree.put(7,  "seven"));
        assertEquals(2, tree.size());
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());
        
        assertNull(tree.put(12,  "twelve"));
        assertEquals(3, tree.size());
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
        
       assertNull(tree.put(5, "five"));
       assertEquals(4, tree.size());
       assertEquals(7, (int)tree.root().getElement().getKey());
       assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
       assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
       assertEquals(5, (int)tree.right(tree.left(tree.root())).getElement().getKey());
       
       assertNull(tree.put(11, "eleven"));
       assertEquals(7, (int)tree.root().getElement().getKey());
       assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
       assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
       assertEquals(5, (int)tree.right(tree.left(tree.root())).getElement().getKey());
       assertEquals(11, (int)tree.left(tree.right(tree.root())).getElement().getKey());
       
       assertEquals("eleven", tree.remove(11));
       assertEquals(4, tree.size());
       assertEquals(7, (int)tree.root().getElement().getKey());
       assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
       assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
       assertEquals(5, (int)tree.right(tree.left(tree.root())).getElement().getKey());
       
       assertEquals("four", tree.remove(4));
       assertEquals(3, tree.size());
       assertEquals(7, (int)tree.root().getElement().getKey());
       assertEquals(5, (int)tree.left(tree.root()).getElement().getKey());
       assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
       
       assertEquals("seven", tree.remove(7));
       assertEquals(2, tree.size());
       assertEquals(12, (int)tree.root().getElement().getKey());
       assertEquals(5, (int)tree.left(tree.root()).getElement().getKey());
       
       //Test remedy double black
       tree = new RedBlackTreeMap<Integer, String>();
       tree.put(1, "one");
       tree.put(2, "two");
       tree.put(3, "three");
       tree.put(4, "four");
       
       assertEquals("two", tree.root().getElement().getValue());
       assertEquals("one", tree.left(tree.root()).getElement().getValue());
       assertEquals("one", tree.remove(1));
       assertEquals("four", tree.right(tree.root()).getElement().getValue());
       assertEquals(0, tree.getProperty(tree.right(tree.root())));
       
       tree = new RedBlackTreeMap<Integer, String>();
       for (int i = 0; i < 10; i++) {
    	   tree.put(i, i + "");
       }
       
       for (int i = 9; i >= 1; i--) {
    	   tree.remove(i);
       }
       assertEquals(1, tree.size());
       assertEquals("0", tree.root().getElement().getValue());
    }
}