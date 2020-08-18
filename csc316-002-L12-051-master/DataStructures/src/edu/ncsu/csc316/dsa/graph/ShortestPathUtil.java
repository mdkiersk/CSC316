package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * This class implements algorithms for finding the shortest path in a graph.
 * Citation: Data Structures & Algorithms, Goodrich et. al, p. 660-1
 * @author Matthew Kierski
 *
 */
public class ShortestPathUtil {
    
	/**
	 * Finds the shortest path in a graph from the source using Dijkstra's algorithm
	 * @param <V> vertex of graph
	 * @param <E> edge of graph
	 * @param g graph to evaluate
	 * @param src vertex to find shortest paths from
	 * @return map containing the shortest paths from src to every other vertex
	 */
    public static <V, E extends Weighted> Map<Vertex<V>, Integer> dijkstra(Graph<V, E> g, Vertex<V> src) {
        Map<Vertex<V>, Integer> d = new LinearProbingHashMap<>();
        Map<Vertex<V>, Integer> cloud = new LinearProbingHashMap<>();
        AdaptablePriorityQueue<Integer, Vertex<V>> pq = new HeapAdaptablePriorityQueue<>();
        Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqTokens = new LinearProbingHashMap<>();
        
        //For each vertex of the graph, add an entry to the priority queue
        for (Vertex<V> v: g.vertices()) {
        	if (v == src) {
        		d.put(v, 0);
        	} else {
        		d.put(v, Integer.MAX_VALUE);
        	}
        	pqTokens.put(v, pq.insert(d.get(v), v));
        }
        while (!pq.isEmpty()) {
        	Entry<Integer, Vertex<V>> entry = pq.deleteMin();
        	int key = entry.getKey();
        	Vertex<V> u = entry.getValue();
        	cloud.put(u, key);
        	pqTokens.remove(u);
        	for (Edge<E> e: g.outgoingEdges(u)) {
        		Vertex<V> v = g.opposite(u, e);
        		if (cloud.get(v) == null) {
        			int weight = e.getElement().getWeight();
        			if (d.get(u) + weight < d.get(v)) { 
        				d.put(v, d.get(u) + weight);
        				pq.replaceKey(pqTokens.get(v), d.get(v));
        			}
        		}
        	}
        }
        return cloud;
    }
    
    /**
     * Reconstructs a shortest-path tree rooted at vertex s, given distance map distances.
     * @param <V> vertex of graph
     * @param <E> edge of graph
     * @param g graph to evaluate
     * @param s 'root' of graph
     * @param distances map of distances between vertices
     * @return map of the shortest-path tree rooted at s
     */
    public static <V, E extends Weighted> Map<Vertex<V>, Edge<E>> shortestPathTree(Graph<V, E> g, Vertex<V> s, Map<Vertex<V>, Integer> distances) {
    	Map<Vertex<V>, Edge<E>> map = new LinearProbingHashMap<>();
    	for (Vertex<V> v: distances) {
    		if (v != s) {
    			for (Edge<E> e: g.incomingEdges(v)) {
    				Vertex<V> u = g.opposite(v, e);
    				if (distances.get(v) == distances.get(u) + e.getElement().getWeight()) {
    					map.put(v, e);
    				}
    			}
    		}
    	}
    	return map;
    }
}