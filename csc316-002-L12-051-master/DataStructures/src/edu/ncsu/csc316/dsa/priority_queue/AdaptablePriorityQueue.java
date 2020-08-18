package edu.ncsu.csc316.dsa.priority_queue;

/**
 * Serves as the interface for all adaptable priority queues
 * @author Matthew Kierski
 *
 * @param <K> key of entry
 * @param <V> value of entry
 */
public interface AdaptablePriorityQueue<K, V> extends PriorityQueue<K, V> {

	/**
	 * Removes the entry from the queue
	 * @param entry entry to remove
	 */
	void remove(Entry<K, V> entry);

	/**
	 * Replaces the key of a given entry with a given key
	 * @param entry entry to evaluate
	 * @param key key to replace
	 */
	void replaceKey(Entry<K, V> entry, K key);

	/**
	 * Replaces the value of a given entry with given value
	 * @param entry entry to evaluate
	 * @param value value to set
	 */
	void replaceValue(Entry<K, V> entry, V value);
}