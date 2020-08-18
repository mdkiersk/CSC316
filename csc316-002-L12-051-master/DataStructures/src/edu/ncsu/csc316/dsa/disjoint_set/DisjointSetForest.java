package edu.ncsu.csc316.dsa.disjoint_set;

import edu.ncsu.csc316.dsa.Position;

/**
 * Serves as the interface for all DisjointSetForest implementations
 * @author Matthew Kierski
 *
 * @param <E> generic value of entry
 */
public interface DisjointSetForest<E> {

	/**
	 * Creates a disjoint set that contains element given, then returns the position that identifies the set
	 * @param value value to be added
	 * @return the position that identifies the set
	 */
	Position<E> makeSet(E value);

	/**
	 * Returns the position of the identifier for the disjoint set containing the value
	 * @param value value to find
	 * @return the position of the identifier for the disjoint set containing the value
	 */
	Position<E> find(E value);

	/**
	 * Merges the disjoint sets that contain the given positions
	 * @param s position of disjoint set to merge
	 * @param t position of disjoint set to merge
	 */
	void union(Position<E> s, Position<E> t);

}