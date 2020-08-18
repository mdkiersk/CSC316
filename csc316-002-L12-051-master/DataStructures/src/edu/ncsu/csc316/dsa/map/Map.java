package edu.ncsu.csc316.dsa.map;

/**
 * Serves as the interface for all Map implementations
 * @author Matthew Kierski
 *
 * @param <K> key of entry
 * @param <V> value of entry
 */
public interface Map<K, V> extends Iterable<K> {
	
	/**
	 * Returns an iterable over the entire set of entries
	 * @return an iterable over the entire set of entries
	 */
	Iterable<Entry<K, V>> entrySet();

	/**
	 * Returns the value associated with given key
	 * @param key key to search for
	 * @return associated with given key
	 */
	V get(K key);

	/**
	 * Returns whether map is empty 
	 * @return true if empty, false otherwise
	 */
	boolean isEmpty();

	/**
	 * Inserts a new entry into the map. If key already exists, replaces value and returns old value.
	 * If key doesn't exist, adds entirely new entry.
	 * @param key key of new entry
	 * @param value value of new entry
	 * @return null if key doesn't exist, old value if key does exist
	 */
	V put(K key, V value);

	/**
	 * Removes an entry searched by key
	 * @param key key to be searched by
	 * @return the value of the removed entry, null if no matching key
	 */
	V remove(K key);

	/**
	 * Returns size of the map
	 * @return size of the map
	 */
	int size();

	/**
	 * Returns an iterator for the values of each entry
	 * @return an iterator for the values of each entry
	 */
	Iterable<V> values();

	/**
	 * Serves as the interface for all entries in a map
	 * @author Matthew Kierski
	 *
	 * @param <K> key of entry
	 * @param <V> value of entry
	 */
	interface Entry<K, V> {
		
		/**
		 * Returns key of entry
		 * @return key of entry
		 */
		K getKey();

		/**
		 * Returns value of entry
		 * @return value of entry
		 */
		V getValue();

		/**
		 * Sets key of entry to be new key
		 * @param key new key to set
		 * @return key that was replaced
		 */
		K setKey(K key);

		/**
		 * Sets value of entry to be new value
		 * @param value new value to set
		 * @return value that was replaced
		 */
		V setValue(V value);
	}
}
