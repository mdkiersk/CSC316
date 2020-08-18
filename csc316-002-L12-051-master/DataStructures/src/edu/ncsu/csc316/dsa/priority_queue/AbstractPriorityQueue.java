package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

/**
 * Serves as the abstract implementation of all Priority Queues
 * @author Matthew Kierski
 *
 * @param <K> key of entry
 * @param <V> value of entry
 */
public abstract class AbstractPriorityQueue<K extends Comparable<K>, V> implements PriorityQueue<K, V> {

	/** Comparator to be used in queue */
	private Comparator<K> comparator;

	/**
	 * Constructs an AbstractPriorityQueue, setting comparator to given comparator
	 * @param c comparator to set
	 */
	public AbstractPriorityQueue(Comparator<K> c) {
		setComparator(c);
	}
	
	/**
	 * Sets the given comparator to be this comparator
	 * @param c comparator to set
	 */
	private void setComparator(Comparator<K> c) {
		if (c == null) {
			c = new NaturalOrder();
		}
		comparator = c;
	}

	/**
	 * Defines the natural order of this queue
	 * @author Matthew Kierski
	 *
	 */
	public class NaturalOrder implements Comparator<K> {
		
		/**
		 * Compares the first and second element
		 * @param first first element to compare
		 * @param second second element to compare
		 * @return values associated with comparable
		 */
		public int compare(K first, K second) {
			return ((Comparable<K>) first).compareTo(second);
		}
	}

	/**
	 * Compares given data, delegating to Comparator.compare().
	 * @param data1 data to be compared
	 * @param data2 data to be compared
	 * @return negative value if first element is less than second, positive value otherwise, and 0 if equal
	 */
	public int compare(K data1, K data2) {
		return comparator.compare(data1, data2);
	}
	
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

    // Make sure you import PriorityQueue.Entry and NOT Map.Entry!
	/**
	 * Represents the behavior and data of a PriorityQueue entry
	 * @author Matthew Kierski
	 *
	 * @param <K> key of entry
	 * @param <V> value of entry
	 */
	public static class PQEntry<K, V> implements Entry<K, V> {

		/** Key of this entry */
		private K key;
		/** Value of this entry */
		private V value;

		/**
		 * Constructs a PQEntry with given data
		 * @param key key to set
		 * @param value value to set
		 */
		public PQEntry(K key, V value) {
			setKey(key);
			setValue(value);
		}

		/**
		 * Sets the key 
		 * @param key key to set
		 */
		public void setKey(K key) {
			this.key = key;
		}

		/**
		 * Sets the value
		 * @param value value to set
		 */
		public void setValue(V value) {
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
	}
	
	/**
	 * A factory method for constructing a new priority queue entry
	 * @param key key of new entry
	 * @param value value of new entry
	 * @return the value of the new entry, null if key exists
	 */
	protected Entry<K, V> createEntry(K key, V value) {
		return new PQEntry<K, V>(key, value);
	}
}