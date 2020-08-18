package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Tests the GeneralTree class
 * @author Matthew Kierski
 *
 */
public class GeneralTreeTest {

	/** Instance of GeneralTree of type String */
    private GeneralTree<String> tree;
	/** Instance of empty GeneralTree of type String */
    private GeneralTree<String> emptyTree;
	/** Instance of GeneralTree of type String */
    private GeneralTree<String> tree2;
    
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
     * An invalid Position to help test validate(p)
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
     * Sets all trees to new empty trees for tests
     */
    @Before
    public void setUp() {
        tree = new GeneralTree<String>();
        emptyTree = new GeneralTree<String>();
        tree2 = new GeneralTree<String>();
    }
    
    /**
     * Helper method to construct a sample tree
     *
     * One
     * -> Two
     *   -> Six
     *   -> Five
     *   -> Ten
     *     -> Seven
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     *
     * Or, visually:
     *                 one
     *            /            \
     *         two                three
     *      /   |     \             |   
     *   six   five   ten          four
     *                  |         /    \
     *                seven     eight  nine              
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addChild(one, "two");
        three = tree.addChild(one,  "three");
        six = tree.addChild(two, "six");
        five = tree.addChild(two, "five");
        ten = tree.addChild(two,  "ten");
        seven = tree.addChild(ten,  "seven");
        four = tree.addChild(three,  "four");
        eight = tree.addChild(four,  "eight");
        nine = tree.addChild(four,  "nine");
    }
    
    /**
     * Tests the set() method
     */
    @Test
    public void testSet() {
        createTree();
        assertEquals("one", tree.set(one, "ONE"));
        
        InvalidPosition<String> invalid = new InvalidPosition<String>();
        try {
            tree.set(invalid, "invalid");
            fail();
        } catch(Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
            assertNull(invalid.getElement());
        }
        
        assertEquals("two", tree.set(two, "TWO"));
        assertEquals("three", tree.set(three, "THREE"));
    }
    
    /**
     * Tests the size() method
     */
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertEquals(10, tree.size());
        assertFalse(tree.isEmpty());
        
        tree.remove(nine);
        assertEquals(9, tree.size());
        
        tree.remove(five);
        assertEquals(8, tree.size());
        
        tree.remove(six);
        assertEquals(7, tree.size());
        
        tree.remove(four);
        assertEquals(6, tree.size());
        
        tree.remove(seven);
        assertEquals(5, tree.size());
        
        tree.remove(eight);
        assertEquals(4, tree.size());
        
        tree.remove(two);
        assertEquals(3, tree.size());
        
        tree.remove(ten);
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
        assertEquals(3, tree.numChildren(two));
        assertEquals(1, tree.numChildren(three));
        assertEquals(2, tree.numChildren(four));
        assertEquals(0, tree.numChildren(five));
        assertEquals(0, tree.numChildren(six));
        assertEquals(0, tree.numChildren(seven));
        assertEquals(0, tree.numChildren(eight));
        assertEquals(0, tree.numChildren(nine));
        assertEquals(1, tree.numChildren(ten));
        
        //Remove a node and see if number of children updates
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
        assertEquals(two, tree.parent(five));
        
        //Remove so that nine's parent is three -- check to see if tree updates properly
        tree.remove(eight);
        tree.remove(four);
        assertEquals(three, tree.parent(nine));
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
     * Tests the isEmpty() method
     */
    @Test
    public void testIsEmpty() {
        assertTrue(emptyTree.isEmpty());
        
        createTree();
        assertFalse(tree.isEmpty());
        
        tree.remove(nine);
        assertFalse(tree.isEmpty());
        
        tree.remove(five);
        assertFalse(tree.isEmpty());
        
        tree.remove(six);
        assertFalse(tree.isEmpty());
        
        tree.remove(four);
        assertFalse(tree.isEmpty());
        
        tree.remove(seven);
        assertFalse(tree.isEmpty());
        
        tree.remove(eight);
        assertFalse(tree.isEmpty());
        
        tree.remove(two);
        assertFalse(tree.isEmpty());
        
        tree.remove(ten);
        assertFalse(tree.isEmpty());
        
        tree.remove(three);
        assertFalse(tree.isEmpty());
        
        tree.remove(one);
        assertTrue(tree.isEmpty());
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
        assertEquals(five, pre.next());
        assertEquals(ten, pre.next());
        assertEquals(seven, pre.next());
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
     * Tests the iterator() method
     */
    @Test
    public void testIterator() {
        createTree();
        Iterator<String> pre = tree.iterator();
        assertEquals("one", pre.next());
        assertEquals("two", pre.next());
        assertEquals("six", pre.next());
        assertEquals("five", pre.next());
        assertEquals("ten", pre.next());
        assertEquals("seven", pre.next());
        assertEquals("three", pre.next());
        assertEquals("four", pre.next());
        assertEquals("eight", pre.next());
        assertEquals("nine", pre.next());

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
        assertEquals(five, post.next());
        assertEquals(seven, post.next());
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
        assertEquals(five, level.next());
        assertEquals(ten, level.next());
        assertEquals(four, level.next());
        assertEquals(seven, level.next());
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
     * Tests the addChild() method
     */
    @Test
    public void testAddChild() {
        assertTrue(tree.isEmpty());
        one = tree.addRoot("one");
        assertEquals(1, tree.size());
        assertNull(tree.parent(one));
        assertEquals("GeneralTree[\none\n]", tree.toString());
        
        two = tree.addChild(one, "two");
        assertEquals(2, tree.size());
        assertEquals(one, tree.parent(two));
        
        three = tree.addChild(one, "three");
        assertEquals(3, tree.size());
        assertEquals(one, tree.parent(three));
        
        four = tree.addChild(three, "four");
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
        assertEquals("GeneralTree[\none\n two\n  six\n  five\n  ten\n   seven\n three\n  four\n   eight\n]", tree.toString());
        assertEquals(9, tree.size());
        assertEquals(1, tree.numChildren(four));
        
       tree.remove(eight);
       assertEquals(8, tree.size());
       assertEquals(0, tree.numChildren(four));
       
       assertEquals(1, tree.numChildren(ten));
       tree.remove(seven);
       assertEquals(7, tree.size());
       assertEquals(0, tree.numChildren(ten));
       
       assertEquals(3, tree.numChildren(two));
       tree.remove(ten);
       assertEquals(6, tree.size());
       assertEquals(2, tree.numChildren(two));
       tree.remove(five);
       assertEquals(5, tree.size());
       assertEquals(1, tree.numChildren(two));
       tree.remove(six);
       assertEquals(4, tree.size());
       assertEquals(0, tree.numChildren(two));
    }
    
    /**
     * Tests the isEmpty() method
     */
    @Test
    public void testEmptyTree() {
        assertTrue(tree2.isEmpty());
        
        one = tree2.addRoot("one");
        assertFalse(tree2.isEmpty());
        
        tree2.remove(one);
        assertTrue(tree2.isEmpty());
    }
}