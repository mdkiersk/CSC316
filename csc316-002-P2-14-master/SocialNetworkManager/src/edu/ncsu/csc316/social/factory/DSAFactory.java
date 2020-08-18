package edu.ncsu.csc316.social.factory;

import edu.ncsu.csc316.dsa.graph.AdjacencyListGraph;
import edu.ncsu.csc316.dsa.graph.Graph;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.sorter.MergeSorter;
import edu.ncsu.csc316.dsa.sorter.Sorter;

/**
 * Factory for creating new data structure and algorithm instances
 * 
 * @author Dr. King
 *
 */
public class DSAFactory {

	/**
	 * Returns a data structure that implements a map
	 * 
	 * @param <K> - the key type
	 * @param <V> - the value type
	 * @return a data structure that implements a map
	 */
	public static <K, V> Map<K, V> getMap() {
		return new LinearProbingHashMap<K, V>();
	}

	/**
	 * Returns a data structure that implements an index-based list
	 * 
	 * @param <E> - the element type
	 * @return an index-based list
	 */
	public static <E> List<E> getIndexedList() {
		return getSinglyLinkedList();
	}

	/**
	 * Returns a comparison based sorter
	 * 
	 * @param <E> - the element type
	 * @return a comparison based sorter
	 */
	public static <E extends Comparable<E>> Sorter<E> getComparisonSorter() {
		return getMergeSorter();
	}

	/**
	 * Returns a data structure that implements an Undirected Graph
	 * 
	 * @param <V> - the vertex type
	 * @param <E> - the edge type
	 * @return an undirected graph
	 */
	public static <V, E> Graph<V, E> getUndirectedGraph() {
		return new AdjacencyListGraph<V, E>();
	}

	/**
	 * Returns a singly linked list with front pointer
	 * 
	 * @return a singly linked list with front pointer
	 */
	private static <E> SinglyLinkedList<E> getSinglyLinkedList() {
		return new SinglyLinkedList<E>();
	}

	/**
	 * Returns a mergesorter
	 * 
	 * @return a mergesorter
	 */
	private static <E extends Comparable<E>> Sorter<E> getMergeSorter() {
		return new MergeSorter<E>();
	}
}