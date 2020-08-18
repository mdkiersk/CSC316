package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

/**
 * Tests the SplayTreeMap class
 * @author Matthew Kierski
 *
 */
public class SplayTreeMapTest {

	/** Instance of BST to use in tests */
    private BinarySearchTreeMap<Integer, String> tree;
    
     
    /**
     * Sets tree to new SplayTreeMap
     */
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
    }
    
    /**
     * Tests the put() method
     */
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(3, "string3"));
        assertEquals(1, tree.size());
        assertEquals(3, (int)tree.root().getElement().getKey());
        
        assertNull(tree.put(2, "string2"));
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals("string3", tree.right(tree.root()).getElement().getValue());
        
        //Zig
        assertNull(tree.put(1, "string1"));
        assertEquals(1, (int)tree.root().getElement().getKey());
        assertEquals("string2", tree.right(tree.root()).getElement().getValue());
        assertEquals("string3", tree.right(tree.right(tree.root())).getElement().getValue());
        
        //Zig-zig
        assertEquals("string3", tree.put(3, "newString3"));
        assertEquals("newString3", tree.root().getElement().getValue());
        assertEquals("string2", tree.left(tree.root()).getElement().getValue());
        assertEquals("string1", tree.left(tree.left(tree.root())).getElement().getValue());
        
        tree.remove(1);
        tree.remove(2);
        tree.remove(3);
        
        //Zig-zag
        assertNull(tree.put(30, "thirty"));
        assertNull(tree.put(10, "ten"));
        assertNull(tree.put(20, "twenty"));
        assertEquals("twenty", tree.root().getElement().getValue());
        assertEquals("ten", tree.left(tree.root()).getElement().getValue());
        assertEquals("thirty", tree.right(tree.root()).getElement().getValue());
        
    }
    
    /**
     * Tests the get() method
     */
    @Test
    public void testGet() {
        tree.put(3, "three");
        tree.put(1, "one");
        tree.put(2, "two");
        
        assertEquals("three", tree.get(3));
        assertEquals("three", tree.root().getElement().getValue());
        assertEquals("two", tree.left(tree.root()).getElement().getValue());
        assertEquals("one", tree.left(tree.left(tree.root())).getElement().getValue());
    }
    
    /**
     * Tests the remove() method
     */
    @Test
    public void testRemove() {
    	tree.put(3, "three");
        tree.put(1, "one");
        tree.put(2, "two");
        tree.put(4, "four");
        
        assertEquals("three", tree.left(tree.root()).getElement().getValue());
        assertEquals("two", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("one", tree.remove(1));
        assertEquals("two", tree.root().getElement().getValue());
        assertEquals("three", tree.right(tree.root()).getElement().getValue());
        assertEquals("four", tree.right(tree.right(tree.root())).getElement().getValue());
        
        assertEquals("two", tree.remove(2));
        assertEquals("three", tree.root().getElement().getValue());
        assertEquals("four", tree.right(tree.root()).getElement().getValue());
    }
}