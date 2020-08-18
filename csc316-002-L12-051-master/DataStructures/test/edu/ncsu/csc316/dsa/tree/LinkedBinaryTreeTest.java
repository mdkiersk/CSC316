package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;
/**
 * Tests the LinkedBinaryTree class
 * @author Matthew Kierski
 *
 */
public class LinkedBinaryTreeTest {

	/** Instance of a LinkedBinaryTree of type String */
	private LinkedBinaryTree<String> tree;
	/** Position to be used in tests */
	private Position<String> one;
	/** Position to be used in tests */
	private Position<String> two;
	/** Position to be used in tests */
	private Position<String> three;
	/** Position to be used in tests */
	private Position<String> four;
	/** Position to be used in tests */
	private Position<String> five;
	/** Position to be used in tests */
	private Position<String> six;
	/** Position to be used in tests */
	private Position<String> seven;
	/** Position to be used in tests */
	private Position<String> eight;
	/** Position to be used in tests */
	private Position<String> nine;
	/** Position to be used in tests */
	private Position<String> ten;
    
    /**
     * Helper class to create an invalid position to help test validate(p)
     * 
     * @param <E> generic element of position
     */
    private class InvalidPosition<E> implements Position<E> {

        @Override
        public E getElement() {
            return null;
        }
        
    }
    
    /**
     * Constructs a new LinkedBinaryTree to be used in tests
     */
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Tests the set() method
     */
    @Test
    public void testSet() {
        createTree();
        
        InvalidPosition<String> invalid = new InvalidPosition<String>();
        try {
            tree.set(invalid, "invalid");
            fail();
        } catch(Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
            assertNull(invalid.getElement());
        }
        
        assertEquals("eight", tree.set(eight, "EIGHT"));
        assertEquals("one", tree.set(one, "ONE"));
    }
    
    /**
     * Tests the size() method
     */
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertFalse(tree.isEmpty());
        assertEquals(10, tree.size());
        
        //Remove each node and check size after each removal
        tree.remove(nine);
        assertEquals(9, tree.size());
        
        tree.remove(eight);
        assertEquals(8, tree.size());
        
        tree.remove(five);
        assertEquals(7, tree.size());
        
        tree.remove(seven);
        assertEquals(6, tree.size());
        
        tree.remove(four);
        assertEquals(5, tree.size());
        
        tree.remove(six);
        assertEquals(4, tree.size());
        
        tree.remove(ten);
        assertEquals(3, tree.size());
        
        tree.remove(two);
        assertEquals(2, tree.size());
        
        tree.remove(three);
        assertEquals(1, tree.size());
        
        tree.remove(one);
        assertEquals(0, tree.size());
    }
    
    /**
     * Tests the numChildren() method
     */
    @Test
    public void testNumChildren() {
        createTree();
        assertEquals(2, tree.numChildren(one));
        assertEquals(2, tree.numChildren(two));
        assertEquals(1, tree.numChildren(three));
        assertEquals(2, tree.numChildren(four));
        assertEquals(0, tree.numChildren(five));
        assertEquals(0, tree.numChildren(six));
        assertEquals(0, tree.numChildren(seven));
        assertEquals(0, tree.numChildren(eight));
        assertEquals(0, tree.numChildren(nine));
        assertEquals(2, tree.numChildren(ten));
        
        //Remove children of four and check number of children
        tree.remove(eight);
        assertEquals(1, tree.numChildren(four));
        tree.remove(nine);
        assertEquals(0, tree.numChildren(four));

    }

    /**
     * Tests the parent() method
     */
    @Test
    public void testParent() {
        createTree();
        
        assertEquals(one, tree.parent(two));
        assertEquals(one, tree.parent(three));
        
        //Remove so that nine's parent is three
        tree.remove(eight);
        tree.remove(four);
        assertEquals(three, tree.parent(nine));
    }
    
    /**
     * Tests the iterator() method
     */
    @Test
    public void testIterator() {
        createTree();
        Iterator<String> in = tree.iterator();

        assertEquals("six", in.next());
        assertEquals("two", in.next());
        assertEquals("seven", in.next());
        assertEquals("ten", in.next());
        assertEquals("five", in.next());
        assertEquals("one", in.next());
        assertEquals("eight", in.next());
        assertEquals("four", in.next());
        assertEquals("nine", in.next());
        assertEquals("three", in.next());

        assertFalse(in.hasNext());        
        
        try {
        	in.remove();
        } catch (Exception e) {
        	assertTrue(e instanceof UnsupportedOperationException);
        }
    }
    
    /**
     * Tests the sibling() method
     */
    @Test
    public void testSibling() {
        createTree();
        
        assertNull(tree.sibling(one));
        assertEquals(two, tree.sibling(three));
        assertEquals(three, tree.sibling(two));
        assertEquals(six, tree.sibling(ten));
        assertEquals(ten, tree.sibling(six));
        assertNull(tree.sibling(four));
        assertEquals(seven, tree.sibling(five));
        assertEquals(five, tree.sibling(seven));
        assertEquals(eight, tree.sibling(nine));
        assertEquals(nine, tree.sibling(eight));
        
        //Remove eight (nine's sibling)
        tree.remove(eight);
        assertNull(tree.sibling(nine));
    }
    
    /**
     * Tests the isInternal() method
     */
    @Test
    public void testIsInternal() {
        createTree();
        assertTrue(tree.isInternal(one));
        assertTrue(tree.isInternal(two));
        assertTrue(tree.isInternal(three));
        assertTrue(tree.isInternal(four));
        assertFalse(tree.isInternal(five));
        assertFalse(tree.isInternal(six));
        assertFalse(tree.isInternal(seven));
        assertFalse(tree.isInternal(eight));
        assertFalse(tree.isInternal(nine));
        assertTrue(tree.isInternal(ten));
        
        //Remove nodes so that four is no longer internal
        tree.remove(eight);
        tree.remove(nine);
        assertFalse(tree.isInternal(four));
    }
    
    /**
     * Tests the isLeaf() method
     */
    @Test
    public void isLeaf() {
        createTree();
        assertFalse(tree.isLeaf(one));
        assertFalse(tree.isLeaf(two));
        assertFalse(tree.isLeaf(three));
        assertFalse(tree.isLeaf(four));
        assertTrue(tree.isLeaf(five));
        assertTrue(tree.isLeaf(six));
        assertTrue(tree.isLeaf(seven));
        assertTrue(tree.isLeaf(eight));
        assertTrue(tree.isLeaf(nine));
        assertFalse(tree.isLeaf(ten));
        
        //Remove nodes so four is leaf
        tree.remove(eight);
        tree.remove(nine);
        assertTrue(tree.isLeaf(four));
    }
    
    /**
     * Tests the isRoot() method
     */
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(two));
        assertFalse(tree.isRoot(three));
        assertFalse(tree.isRoot(four));
        assertFalse(tree.isRoot(five));
        assertFalse(tree.isRoot(six));
        assertFalse(tree.isRoot(seven));
        assertFalse(tree.isRoot(eight));
        assertFalse(tree.isRoot(nine));
        assertFalse(tree.isRoot(ten));
        
        //Remove all nodes to the left so that three becomes root
        tree.remove(five);
        tree.remove(six);
        tree.remove(ten);
        tree.remove(seven);
        tree.remove(two);
        tree.remove(one);
        
        assertFalse(tree.isRoot(one));
        assertTrue(tree.isRoot(three));
    }
    
    /**
     * Tests the preOrder() method
     */
    @Test
    public void testPreOrder() {
        createTree();
        Iterator<Position<String>> pre = tree.preOrder().iterator();
        assertEquals(one, pre.next());
        assertEquals(two, pre.next());
        assertEquals(six, pre.next());
        assertEquals(ten, pre.next());
        assertEquals(seven, pre.next());
        assertEquals(five, pre.next());
        assertEquals(three, pre.next());
        assertEquals(four, pre.next());
        assertEquals(eight, pre.next());
        assertEquals(nine, pre.next());

        assertFalse(pre.hasNext());        
        
        try {
        	pre.remove();
        } catch (Exception e) {
        	assertTrue(e instanceof UnsupportedOperationException);
        }
    }
    
    /**
     * Tests the postOrder() method
     */
    @Test
    public void testPostOrder() {
        createTree();
        Iterator<Position<String>> post = tree.postOrder().iterator();
        assertTrue(post.hasNext());
        
        assertEquals(six, post.next());
        assertEquals(seven, post.next());
        assertEquals(five, post.next());
        assertEquals(ten, post.next());
        assertEquals(two, post.next());
        assertEquals(eight, post.next());
        assertEquals(nine, post.next());
        assertEquals(four, post.next());
        assertEquals(three, post.next());
        assertEquals(one, post.next());
        
        assertFalse(post.hasNext());
        
        try {
        	post.remove();
        } catch (Exception e) {
        	assertTrue(e instanceof UnsupportedOperationException);
        }
    }
    
    /**
     * Tests the inOrder() method
     */
    @Test
    public void testInOrder() {
        createTree();
        Iterator<String> in = tree.iterator();

        assertEquals("six", in.next());
        assertTrue(in.hasNext());
        assertEquals("two", in.next());
        assertTrue(in.hasNext());
        assertEquals("seven", in.next());
        assertTrue(in.hasNext());
        assertEquals("ten", in.next());
        assertTrue(in.hasNext());
        assertEquals("five", in.next());
        assertTrue(in.hasNext());
        assertEquals("one", in.next());
        assertTrue(in.hasNext());
        assertEquals("eight", in.next());
        assertTrue(in.hasNext());
        assertEquals("four", in.next());
        assertTrue(in.hasNext());
        assertEquals("nine", in.next());
        assertTrue(in.hasNext());
        assertEquals("three", in.next());

        assertFalse(in.hasNext());        
        
        try {
        	in.remove();
        } catch (Exception e) {
        	assertTrue(e instanceof UnsupportedOperationException);
        }
    }
    
    /**
     * Tests the isEmpty() method
     */
    @Test
    public void testEmptyTree() {
    	assertTrue(tree.isEmpty());
    	one = tree.addRoot("one");
    	assertFalse(tree.isEmpty());
    	tree.remove(one);
    	assertTrue(tree.isEmpty());
    }
    
    /**
     * Tests the levelOrder() method
     */
    @Test
    public void testLevelOrder() {
    	createTree();
    	Iterator<Position<String>> level = tree.levelOrder().iterator();
    	assertTrue(level.hasNext());
        assertEquals(one, level.next());
        assertEquals(two, level.next());
        assertEquals(three, level.next());
        assertEquals(six, level.next());
        assertEquals(ten, level.next());
        assertEquals(four, level.next());
        assertEquals(seven, level.next());
        assertEquals(five, level.next());
        assertEquals(eight, level.next());
        assertEquals(nine, level.next());
        
        assertFalse(level.hasNext());
        
        try {
        	level.remove();
        } catch (Exception e) {
        	assertTrue(e instanceof UnsupportedOperationException);
        }
    }
    
    /**
     * Tests the addLeft() and addRight() methods
     */
    @Test
    public void testAddChildren() {
    	assertTrue(tree.isEmpty());
        one = tree.addRoot("one");
        assertEquals(1, tree.size());
        assertNull(tree.parent(one));
        assertEquals("LinkedBinaryTree[\none\n]", tree.toString());
        
        two = tree.addLeft(one, "two");
        assertEquals(2, tree.size());
        assertEquals(one, tree.parent(two));
        
        three = tree.addRight(one, "three");
        assertEquals(3, tree.size());
        assertEquals(one, tree.parent(three));
        
        four = tree.addRight(three, "four");
        assertEquals(4, tree.size());
        assertEquals(three, tree.parent(four));
    }
    
    /**
     * Tests the remove() method
     */
    @Test
    public void testRemove() {
        createTree();
        assertEquals(10, tree.size());
        assertEquals(2, tree.numChildren(four));
        tree.remove(nine);
        assertEquals("LinkedBinaryTree[\none\n two\n  six\n  ten\n   seven\n   five\n three\n  four\n   eight\n]", tree.toString());
        assertEquals(9, tree.size());
        assertEquals(1, tree.numChildren(four));
        
       tree.remove(eight);
       assertEquals(8, tree.size());
       assertEquals(0, tree.numChildren(four));
       
       assertEquals(2, tree.numChildren(ten));
       tree.remove(seven);
       assertEquals(7, tree.size());
       assertEquals(1, tree.numChildren(ten));
       tree.remove(five);
       assertEquals(6, tree.size());
       assertEquals(0, tree.numChildren(ten));
       
       assertEquals(2, tree.numChildren(two));
       tree.remove(ten);
       assertEquals(5, tree.size());
       assertEquals(1, tree.numChildren(two));
       tree.remove(six);
       assertEquals(4, tree.size());
       assertEquals(0, tree.numChildren(two));
       
       assertEquals(1, tree.numChildren(three));
       tree.remove(four);
       assertEquals(3, tree.size());
       assertEquals(0, tree.numChildren(three));
       
       assertEquals(2, tree.numChildren(one));
       tree.remove(two);
       assertEquals(2, tree.size());
       assertEquals(1, tree.numChildren(one));
       tree.remove(three);
       assertEquals(1, tree.size());
       assertEquals(0, tree.numChildren(one));
       
       tree.remove(one);
       assertEquals(0, tree.size());
    }
}