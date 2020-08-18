package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the TreeSet class
 * @author Matthew Kierski
 *
 */
public class TreeSetTest {

	/** Instance of set to be used in tests */
    private Set<Integer> set;
    
    /**
     * Sets the set to be a new TreeSet 
     */
    @Before
    public void setUp() {
        set = new TreeSet<Integer>();
    }
    
    /**
     * Tests the add() method
     */
    @Test
    public void testAdd() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        
        set.add(5);
        assertEquals(1, set.size());
        assertFalse(set.isEmpty());
        
        set.add(4);
        assertEquals(2, set.size());
        assertFalse(set.isEmpty());
        
        Set<Integer> setToAdd = new TreeSet<Integer>();
        setToAdd.add(1);
        setToAdd.add(2);
        setToAdd.add(3);
        assertEquals(3, setToAdd.size());
        
        set.addAll(setToAdd);
        assertEquals(5, set.size());
    }

    /**
     * Tests the contains() method
     */
    @Test
    public void testContains() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());

        assertTrue(set.contains(5));
        assertTrue(set.contains(10));
        assertTrue(set.contains(20));
        assertTrue(set.contains(15));
        assertTrue(set.contains(25));
        
        assertFalse(set.contains(1));
        
        Set<Integer> setToAdd = new TreeSet<Integer>();
        setToAdd.add(1);
        setToAdd.add(2);
        setToAdd.add(3);
        assertEquals(3, setToAdd.size());
        
        set.addAll(setToAdd);
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertTrue(set.contains(3));
    }

    /**
     * Tests the remove() method
     */
    @Test
    public void testRemove() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
       assertEquals(5, (int)set.remove(5));
       assertEquals(4, set.size());
       assertEquals(10, (int)set.remove(10));
       assertEquals(3, set.size());
       assertEquals(15, (int)set.remove(15));
       assertEquals(2, set.size());
       assertEquals(20, (int)set.remove(20));
       assertEquals(1, set.size());
       assertEquals(25, (int)set.remove(25));
       assertEquals(0, set.size());
    }
    
    /**
     * Tests the retainAll() method
     */
    @Test
    public void testRetainAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
       set.retainAll(other);
       assertEquals(2, set.size());
       assertTrue(set.contains(10));
       assertTrue(set.contains(20));
    }
    
    /**
     * Tests the removeAll() method
     */
    @Test
    public void testRemoveAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
        set.removeAll(other);
        assertEquals(3, set.size());
        assertTrue(set.contains(5));
        assertTrue(set.contains(15));
        assertTrue(set.contains(25));
        
        assertFalse(set.contains(10));
        assertFalse(set.contains(20));
    }
    
    /**
     * Tests the addAll() method
     */
    @Test
    public void testAddAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(30);
        other.add(40);
        other.add(50);
        
        set.addAll(other);
        assertEquals(8, set.size());
        assertTrue(set.contains(30));
        assertTrue(set.contains(40));
        assertTrue(set.contains(50));
        
        set.addAll(other);
        assertEquals(8, set.size());
    }

    /**
     * Tests the iterator() method
     */
    @Test
    public void testIterator() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5); 
        set.add(10);
        set.add(15);        
        set.add(20);        
        set.add(25);
        assertEquals(5, set.size());
        
        Iterator<Integer> it = set.iterator();
        assertTrue(it.hasNext());
        assertEquals(5, (int)it.next());
        assertEquals(10, (int)it.next());
        assertEquals(15, (int)it.next());
        assertEquals(20, (int)it.next());
        assertEquals(25, (int)it.next());
        
        assertFalse(it.hasNext());
    }
}