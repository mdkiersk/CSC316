package edu.ncsu.csc316.dsa.priority_queue;

/**
 * Serves as the interface for all implementations of Priority queues
 * @author Matthew Kierski
 *
 * @param <K> key of entry
 * @param <V> value of entry
 */
public interface PriorityQueue<K, V> {

	/**
	 * A nested interface for all implementations of an entry
	 * @author Matthew Kierski
	 *
	 * @param <K> key of entry
	 * @param <V> value of entry
	 */
	interface Entry<K, V> {
		/**
		 * Returns the key of this entry
		 * @return the key of this entry
		 */
		K getKey();

		/**
		 * Returns the value of this entry
		 * @return value of this entry
		 */
		V getValue();
	}
	
	/**
	 * Inserts a key into the queue 
	 * @param key key of new entry
	 * @param value value of new entry
	 * @return the new value if key existed, null otherwise
	 */
	Entry<K, V> insert(K key, V value);

	/**
	 * Returns the entry with the smallest key
	 * @return the entry with the smallest key
	 */
	Entry<K, V> min();

	/**
	 * Deletes the entry with the smallest key
	 * @return the entry with the smallest key
	 */
	Entry<K, V> deleteMin();

	/**
	 * Returns the size of the queue
	 * @return the size of the queue
	 */
	int size();

	/**
	 * Returns true if empty, false otherwise
	 * @return true if empty, false otherwise
	 */
	boolean isEmpty();
}