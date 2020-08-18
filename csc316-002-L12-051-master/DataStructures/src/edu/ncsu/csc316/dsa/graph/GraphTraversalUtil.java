package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.UnorderedArrayMap;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * This class holds methods for traversing a graph using DFS and BFS
 * @author Matthew Kierski
 *
 */
public class GraphTraversalUtil {
    
	/**
	 * Traverses a given graph using DFS, returning a map of the traversed vertices
	 * @param <V> vertex of graph
	 * @param <E> edge of graph
	 * @param graph graph to traverse
	 * @param start starting vertex for traversal
	 * @return map containing traversal order
	 */
    public static <V, E> Map<Vertex<V>, Edge<E>> depthFirstSearch(Graph<V, E> graph, Vertex<V> start) {
    	Set<Vertex<V>> set = new HashSet<Vertex<V>>();
    	Map<Vertex<V>, Edge<E>> map = new UnorderedArrayMap<Vertex<V>, Edge<E>>();
    	dfsHelper(graph, start, set, map);
    	return map;
    }
    
    /**
     * Helper method for DFS, tracking the discovered vertices and edges
     * @param <V> vertex of graph
     * @param <E> edge of graph
     * @param graph graph to traverse
     * @param u newly discovered vertex
     * @param known set of known vertices
     * @param forest forest of discovered edges
     */
    private static <V, E> void dfsHelper(Graph<V, E> graph, Vertex<V> v, Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest) {
        known.add(v);
        for (Edge<E> e: graph.outgoingEdges(v)) {
        	Vertex<V> u = graph.opposite(v, e);
        	if (!known.contains(u)) {
        		forest.put(u, e);
        		dfsHelper(graph, u, known, forest);
        	}
        }
    }
    
    /**
     * Traverses a given graph using BFS, returning a map of the ordered vertices and edges
     * @param <V> vertex of graph
     * @param <E> edge of graph
     * @param graph graph to traverse
     * @param start starting point for traversal
     * @return map containing traversal order
     */
    public static <V, E> Map<Vertex<V>, Edge<E>> breadthFirstSearch(Graph<V, E> graph, Vertex<V> start) {
    	Set<Vertex<V>> set = new HashSet<Vertex<V>>();
    	Map<Vertex<V>, Edge<E>> map = new UnorderedArrayMap<Vertex<V>, Edge<E>>();
    	Queue<Vertex<V>> queue = new ArrayBasedQueue<Vertex<V>>();
    	set.add(start);
    	queue.enqueue(start);
    	while (!queue.isEmpty()) {
    		Vertex<V> u = queue.dequeue();
    		for (Edge<E> e: graph.outgoingEdges(u)) {
    			Vertex<V> w = graph.opposite(u, e);
    			if (!set.contains(w)) {
    				set.add(w);
    				map.put(w, e);
    				queue.enqueue(w);
    			}
    		}
    	}
    	return map;
    }
}