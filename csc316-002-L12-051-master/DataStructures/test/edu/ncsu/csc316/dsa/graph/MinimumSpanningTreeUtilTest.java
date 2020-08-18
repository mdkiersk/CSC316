package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted.Highway;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * This class tests the MinimumSpanningTreeUtil class
 * @author Matthew Kierski
 *
 */
public class MinimumSpanningTreeUtilTest {

	/** Graph to use for tests */
    private Graph<String, Highway> graph;
    
    /**
     * Sets the graphs to new EdgeListGraphs and builds it
     */
    @Before
    public void setUp() {
        graph = new EdgeListGraph<String, Highway>();
    }
    
    /**
     * Tests the kruskal() method
     */
	@Test
	public void testKruskal() {
		Vertex<String> v1 = graph.insertVertex("Raleigh");
        Vertex<String> v2 = graph.insertVertex("Asheville");
        Vertex<String> v3 = graph.insertVertex("Wilmington");
        Vertex<String> v4 = graph.insertVertex("Durham");
        Vertex<String> v5 = graph.insertVertex("Greenville");
        Highway h1 = new Highway("h1", 1);
        Highway h2 = new Highway("h2", 2);
        Highway h3 = new Highway("h3", 3);
        Highway h4 = new Highway("h4", 4);
        
        Edge<Highway> e1 = graph.insertEdge(v1, v2, h1);
        Edge<Highway> e2 = graph.insertEdge(v1, v3, h2);
        Edge<Highway> e3 = graph.insertEdge(v1, v4, h3);
        Edge<Highway> e4 = graph.insertEdge(v1, v5, h4);
        
        PositionalList<Edge<Highway>> tree = MinimumSpanningTreeUtil.kruskal(graph);
        Iterator<Edge<Highway>> it = tree.iterator();
        assertTrue(it.hasNext());
        assertEquals(e4.getElement().getWeight(), it.next().getElement().getWeight());
        assertEquals(e3.getElement().getWeight(), it.next().getElement().getWeight());
        assertEquals(e2.getElement().getWeight(), it.next().getElement().getWeight());
        assertEquals(e1.getElement().getWeight(), it.next().getElement().getWeight());
        
	}

	/**
	 * Tests the primJarnik() method
	 */
	@Test
	public void testPrimJarnik() {
		Vertex<String> v1 = graph.insertVertex("Raleigh");
        Vertex<String> v2 = graph.insertVertex("Asheville");
        Vertex<String> v3 = graph.insertVertex("Wilmington");
        Vertex<String> v4 = graph.insertVertex("Durham");
        Vertex<String> v5 = graph.insertVertex("Greenville");
        Highway h1 = new Highway("h1", 1);
        Highway h2 = new Highway("h2", 2);
        Highway h3 = new Highway("h3", 3);
        Highway h4 = new Highway("h4", 4);
        
        Edge<Highway> e1 = graph.insertEdge(v1, v2, h1);
        Edge<Highway> e2 = graph.insertEdge(v1, v3, h2);
        Edge<Highway> e3 = graph.insertEdge(v1, v4, h3);
        Edge<Highway> e4 = graph.insertEdge(v1, v5, h4);
        
        PositionalList<Edge<Highway>> tree = MinimumSpanningTreeUtil.primJarnik(graph);
        Iterator<Edge<Highway>> it = tree.iterator();
        assertTrue(it.hasNext());
        assertEquals(e1.getElement().getWeight(), it.next().getElement().getWeight());
        assertEquals(e2.getElement().getWeight(), it.next().getElement().getWeight());
        assertEquals(e3.getElement().getWeight(), it.next().getElement().getWeight());
        assertEquals(e4.getElement().getWeight(), it.next().getElement().getWeight());
	}

}
