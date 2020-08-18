package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted.Highway;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * This class tests the ShortestPathUtil class
 * @author Matthew Kierski
 *
 */
public class ShortestPathUtilTest {

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
     * Tests the dikjstra() method
     */
	@Test
	public void testDijkstra() {
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
        
        Map<Vertex<String>, Integer> map = ShortestPathUtil.dijkstra(graph, v1);
		Iterator<Integer> edges = map.values().iterator();
		Iterator<Vertex<String>> vertices = map.iterator();
		
		assertTrue(edges.hasNext());
		assertTrue(vertices.hasNext());
		
		assertEquals(0, (int)map.get(v1));
		assertEquals(e1.getElement().getWeight(), (int)map.get(v2));
		assertEquals(e2.getElement().getWeight(), (int)map.get(v3));
		assertEquals(e3.getElement().getWeight(), (int)map.get(v4));
		assertEquals(e4.getElement().getWeight(), (int)map.get(v5));

	}

	/**
	 * Tests the shortestPathTree() method
	 */
	@Test
	public void testShortestPathTree() {
		Vertex<String> v1 = graph.insertVertex("Raleigh");
        Vertex<String> v2 = graph.insertVertex("Asheville");
        Vertex<String> v3 = graph.insertVertex("Wilmington");
        Vertex<String> v4 = graph.insertVertex("Durham");
        Vertex<String> v5 = graph.insertVertex("Greenville");
        Highway h1 = new Highway("h1", 1);
        Highway h2 = new Highway("h2", 2);
        Highway h3 = new Highway("h3", 3);
        Highway h4 = new Highway("h4", 4);
        Highway h5 = new Highway("h5", 5);
        
        Edge<Highway> e1 = graph.insertEdge(v1, v2, h1);
        Edge<Highway> e2 = graph.insertEdge(v1, v3, h2);
        Edge<Highway> e3 = graph.insertEdge(v1, v4, h3);
        Edge<Highway> e4 = graph.insertEdge(v1, v5, h4);
        Edge<Highway> e5 = graph.insertEdge(v2, v3, h5);
        
        Map<Vertex<String>, Integer> dijkstra = ShortestPathUtil.dijkstra(graph, v1);
        
        Map<Vertex<String>, Edge<Highway>> map = ShortestPathUtil.shortestPathTree(graph, v1, dijkstra);
		
        Iterator<Edge<Highway>> edges = map.values().iterator();
        assertTrue(edges.hasNext());
        assertEquals(1, e1.getElement().getWeight());
        assertEquals(2, e2.getElement().getWeight());
        assertEquals(3, e3.getElement().getWeight());
        assertEquals(4, e4.getElement().getWeight());
        assertEquals(5, e5.getElement().getWeight());
	}
}
