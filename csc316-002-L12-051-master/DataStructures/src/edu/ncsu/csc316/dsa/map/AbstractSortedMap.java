package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;

/**
 * Represents the abstract implementation of any sorted map
 * @author Matthew Kierski
 *
 * @param <K> key of entry
 * @param <V> value of entry
 */
public abstract class AbstractSortedMap<K extends Comparable<K>, V> extends AbstractMap<K, V> {

	/** Instance of comparator to be used to sort keys */
	private Comparator<K> compare;

	/**
	 * Constructs an AbstractSortedMap with given comparator
	 * @param compare comparator to sort by
	 */
	public AbstractSortedMap(Comparator<K> compare) {
		if (compare == null) {
			this.compare = new NaturalOrder();
		} else {
			this.compare = compare;
		}
	}

	/**
	 * Compares given key with another given key
	 * @param key1 key to be compared
	 * @param key2 key to be compared
	 * @return negative integer, 0, positive integer if first argument is less than, equal to,
	 * or greater than second argument
	 */
	public int compare(K key1, K key2) {
		return compare.compare(key1, key2);
	}

	/**
	 * Represents the natural ordering of comparable objects
	 * @author Matthew Kierski
	 *
	 */
	private class NaturalOrder implements Comparator<K> {
		
		/**
		 * Delegates to Comparable compareTo to compare given keys
		 * @param first key to compare
		 * @param second key to compare
		 * @return negative integer, 0, positive integer if first argument is less than, equal to,
		 * or greater than second argument
		 */
		public int compare(K first, K second) {
			return ((Comparable<K>) first).compareTo(second);
		}
	}
}