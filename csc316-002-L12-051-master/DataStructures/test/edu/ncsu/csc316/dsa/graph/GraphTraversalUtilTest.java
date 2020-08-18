package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * This class tests the GraphTraversalUtil class
 * @author Matthew Kierski
 *
 */
public class GraphTraversalUtilTest {

	/** Graph to use for tests */
    private Graph<String, Integer> graph;
    
    /**
     * Sets the graphs to new EdgeListGraphs and builds it
     */
    @Before
    public void setUp() {
        graph = new EdgeListGraph<String, Integer>();
    }
    

	/**
	 * Tests the depthFirstSearch() method
	 */
	@Test
	public void testDepthFirstSearch() {
		Vertex<String> v1 = graph.insertVertex("Raleigh");
        Vertex<String> v2 = graph.insertVertex("Asheville");
        Vertex<String> v3 = graph.insertVertex("Wilmington");
        Vertex<String> v4 = graph.insertVertex("Durham");
        Vertex<String> v5 = graph.insertVertex("Greenville");
        
        graph.insertEdge(v1, v2, 5);
        graph.insertEdge(v1, v3, 10);
        graph.insertEdge(v1, v4, 15);
        graph.insertEdge(v1, v5, 20);
        graph.insertEdge(v2, v3, 25);
        graph.insertEdge(v2, v4, 30);
        graph.insertEdge(v2, v5, 35);
        graph.insertEdge(v3, v4, 40);
        graph.insertEdge(v3, v5, 45);
        graph.insertEdge(v4, v5, 50);
		Map<Vertex<String>, Edge<Integer>> map = GraphTraversalUtil.depthFirstSearch(graph, v1);
		Iterator<Edge<Integer>> edges = map.values().iterator();
		Iterator<Vertex<String>> vertices = map.iterator();
		
		assertTrue(edges.hasNext());
		assertTrue(vertices.hasNext());
		
		assertEquals(50, (int)edges.next().getElement());
		assertEquals(40, (int)edges.next().getElement());
		assertEquals(25, (int)edges.next().getElement());
		assertEquals(5, (int)edges.next().getElement());

		assertEquals("Greenville", vertices.next().getElement());
		assertEquals("Durham", vertices.next().getElement());
		assertEquals("Wilmington", vertices.next().getElement());
		assertEquals("Asheville", vertices.next().getElement());
		
	}

	/**
	 * Tests the breadthFirstSearch() method
	 */
	@Test
	public void testBreadthFirstSearch() {
		Vertex<String> v1 = graph.insertVertex("Raleigh");
        Vertex<String> v2 = graph.insertVertex("Asheville");
        Vertex<String> v3 = graph.insertVertex("Wilmington");
        Vertex<String> v4 = graph.insertVertex("Durham");
        Vertex<String> v5 = graph.insertVertex("Greenville");
        
        graph.insertEdge(v1, v2, 5);
        graph.insertEdge(v1, v3, 10);
        graph.insertEdge(v1, v4, 15);
        graph.insertEdge(v1, v5, 20);
        graph.insertEdge(v2, v3, 25);
        graph.insertEdge(v2, v4, 30);
        graph.insertEdge(v2, v5, 35);
        graph.insertEdge(v3, v4, 40);
        graph.insertEdge(v3, v5, 45);
        graph.insertEdge(v4, v5, 50);
		Map<Vertex<String>, Edge<Integer>> map = GraphTraversalUtil.breadthFirstSearch(graph, v1);
		Iterator<Edge<Integer>> edges = map.values().iterator();
		Iterator<Vertex<String>> vertices = map.iterator();
		
		assertTrue(edges.hasNext());
		assertTrue(vertices.hasNext());
		
		assertEquals(20, (int)edges.next().getElement());
		assertEquals(15, (int)edges.next().getElement());
		assertEquals(10, (int)edges.next().getElement());
		assertEquals(5, (int)edges.next().getElement());

		assertEquals("Greenville", vertices.next().getElement());
		assertEquals("Durham", vertices.next().getElement());
		assertEquals("Wilmington", vertices.next().getElement());
		assertEquals("Asheville", vertices.next().getElement());
	}
}
