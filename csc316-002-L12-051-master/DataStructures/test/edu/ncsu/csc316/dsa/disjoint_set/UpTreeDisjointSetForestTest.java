package edu.ncsu.csc316.dsa.disjoint_set;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Tests the UpTreeDisjointForest class
 * @author Matthew Kierski
 *
 */
public class UpTreeDisjointSetForestTest {

	/** Set to be used in tests */
    private DisjointSetForest<String> set;
    
    /**
     * Sets the set to a new UTDSF
     */
    @Before
    public void setUp() {
        set = new UpTreeDisjointSetForest<>();
    }
    
    /**
     * Tests the makeSet() method
     */
    @Test
    public void testMakeSet() {
        Position<String> one = set.makeSet("one");
        assertEquals("one", one.getElement());
        Position<String> two = set.makeSet("two");
        assertEquals("two", two.getElement());
        Position<String> three = set.makeSet("three");
        assertEquals("three", three.getElement());
        
        assertEquals(one, set.find("one"));
        assertEquals(two, set.find("two"));
        assertEquals(three, set.find("three"));
    }
    
    /**
     * Tests the unionFind() method
     */
    @Test
    public void testUnionFind() {
        Position<String> a = set.makeSet("a");
        Position<String> b = set.makeSet("b");
        Position<String> c = set.makeSet("c");
        Position<String> d = set.makeSet("d");
        Position<String> e = set.makeSet("e");
        Position<String> f = set.makeSet("f");
        Position<String> g = set.makeSet("g");
        Position<String> h = set.makeSet("h");
        Position<String> i = set.makeSet("i");
        Position<String> j = set.makeSet("j");
        Position<String> k = set.makeSet("k");
        Position<String> l = set.makeSet("l");
        
        assertEquals(a, set.find("a"));
        assertEquals(b, set.find("b"));
        assertEquals(c, set.find("c"));
        assertEquals(d, set.find("d"));
        assertEquals(e, set.find("e"));
        assertEquals(f, set.find("f"));
        assertEquals(g, set.find("g"));
        assertEquals(h, set.find("h"));
        assertEquals(i, set.find("i"));
        assertEquals(j, set.find("j"));
        assertEquals(k, set.find("k"));
        assertEquals(l, set.find("l"));
        // you should draw out examples by hand (or use the examples
        // in the lecture slides or textbook) to help guide your test cases.
        // Be sure to perform union operations followed by find
        // operations to make sure the appropriate root notes are
        // returned
        
        // a U c
        set.union(a, c);
        assertEquals(c, set.find("a"));
        assertEquals(c, set.find("c"));
        
        // b U d
        set.union(b, d);
        assertEquals(d, set.find("b"));
        assertEquals(d, set.find("d"));
        
        // f U i
        set.union(f, i);
        assertEquals(i, set.find("f"));
        assertEquals(i, set.find("i"));
        
        // l U k
        set.union(l, k);
        assertEquals(k, set.find("k"));
        assertEquals(k, set.find("l"));
        
        // j U l
        set.union(j, l);
        assertEquals(k, set.find("j"));
        assertEquals(k, set.find("l"));
        
        // h U l
        set.union(h, l);
        assertEquals(k, set.find("h"));
        assertEquals(k, set.find("l"));
        
        // a U l
        set.union(a, l);
        assertEquals(k, set.find("a"));
        assertEquals(k, set.find("l"));
    }
}