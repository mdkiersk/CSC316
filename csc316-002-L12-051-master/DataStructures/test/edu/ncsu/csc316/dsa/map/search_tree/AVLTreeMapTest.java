package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

/**
 * Tests the AVLTreeMap class
 * @author Matthew Kierski
 *
 */
public class AVLTreeMapTest {

	/** Instance of BST to use in test */
    private BinarySearchTreeMap<Integer, String> tree;    
    
    /**
     * Sets tree to new AVLTreeMap
     */
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Tests the put() method
     */
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(5, "string5"));
        assertEquals(1, tree.size());
        assertEquals(5, (int)tree.root().getElement().getKey());
        assertNull(tree.left(tree.root()).getElement());
        
        assertNull(tree.put(10, "string10"));
        assertEquals(5, (int)tree.root().getElement().getKey());
        assertNull(tree.left(tree.root()).getElement());
        assertEquals(10, (int)tree.right(tree.root()).getElement().getKey());
        assertNull(tree.left(tree.right(tree.root())).getElement());
        assertNull(tree.right(tree.right(tree.root())).getElement());
        assertEquals(2, tree.size());
        
//        	  5
//           / \
//          1  10
        
        assertNull(tree.put(1, "string1"));
        assertEquals(5, (int)tree.root().getElement().getKey());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        
//        	  5
//           / \
//          1  10
//           \
//            3
        assertNull(tree.put(3, "string3"));
        assertEquals(4, tree.size());
        assertEquals("string3", tree.right(tree.left(tree.root())).getElement().getValue());
        
        assertNull(tree.put(4, "string4"));
        assertEquals(5, tree.size());
        
        assertEquals("string5", tree.root().getElement().getValue());
        assertEquals("string3", tree.left(tree.root()).getElement().getValue());
        assertEquals("string1", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("string4", tree.right(tree.left(tree.root())).getElement().getValue());
        assertEquals("string10", tree.right(tree.root()).getElement().getValue());
        
    }
    
    /**
     * Tests the get() method
     */
    @Test
    public void testGet() {
        assertTrue(tree.isEmpty());
        assertNull(tree.put(3, "string3"));
        assertFalse(tree.isEmpty());
        
        assertEquals("string3", tree.get(3));
        assertEquals(null, tree.get(6));
        assertEquals(null, tree.get(0));
        assertEquals("string3", tree.get(3));
        
        assertEquals("string3", tree.put(3, "newString3"));
        assertEquals("newString3", tree.get(3));
        
    }
    
    /**
     * Tests the remove() method
     */
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertNull(tree.put(1, "one"));
        assertNull(tree.put(2, "two"));
        assertNull(tree.put(3, "three"));
        assertNull(tree.put(4, "four"));
        assertNull(tree.put(5, "five"));
        assertNull(tree.put(6, "six"));
        assertNull(tree.put(7, "seven"));
        assertEquals(7, tree.size());
        assertFalse(tree.isEmpty());
        
        assertNull(tree.remove(0));
        assertEquals(7, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(5, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(7, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        
       assertEquals("one", tree.remove(1));
       assertEquals(4, (int)tree.root().getElement().getKey());
       assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
       assertEquals(3, (int)tree.right(tree.left(tree.root())).getElement().getKey());
       assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
       assertEquals(5, (int)tree.left(tree.right(tree.root())).getElement().getKey());
       assertEquals(7, (int)tree.right(tree.right(tree.root())).getElement().getKey());
       
       assertEquals("two", tree.remove(2));
       assertEquals(4, (int)tree.root().getElement().getKey());
       assertEquals(3, (int)tree.left(tree.root()).getElement().getKey());
       assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
       assertEquals(5, (int)tree.left(tree.right(tree.root())).getElement().getKey());
       assertEquals(7, (int)tree.right(tree.right(tree.root())).getElement().getKey());
       
       assertEquals("three", tree.remove(3));
       assertEquals(6, (int)tree.root().getElement().getKey());
       assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
       assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());
    }
}