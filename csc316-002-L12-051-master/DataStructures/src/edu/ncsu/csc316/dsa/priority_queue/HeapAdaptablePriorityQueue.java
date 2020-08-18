package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

/**
 * Represents the data and behavior of a Heap based Adaptable Priority Queue
 * Citation: Data Structures and Algorithms, Michael T. Goodrich, et al. Page 394. 
 * @author Matthew Kierski
 *
 * @param <K> key of value
 * @param <V> entry of value
 */
public class HeapAdaptablePriorityQueue<K extends Comparable<K>, V> extends HeapPriorityQueue<K, V>
		implements AdaptablePriorityQueue<K, V> {

    // Adaptable PQ Entries must be location-aware so that the 
    // performance of replaceKey, replaceValue, and remove are O(log n)
	/**
	 * Represents the data and behavior of an Adaptable Priority Queue entry
	 * @author Matthew Kierski
	 *
	 * @param <K> key of entry
	 * @param <V> value of entry
	 */
	public static class AdaptablePQEntry<K, V> extends PQEntry<K, V> {

		/** Index of this entry */
		private int index;

		/**
		 * Constructs an AdaptablePQEntry, setting key, value, and index
		 * @param key key of new entry
		 * @param value value of new entry
		 * @param index index of new entry
		 */
		public AdaptablePQEntry(K key, V value, int index) {
			super(key, value);
			setIndex(index);
		}

		/**
		 * Returns the index of this entry
		 * @return the index of this entry
		 */
		public int getIndex() {
			return index;
		}

		/**
		 * Sets the index
		 * @param index index to set
		 */
		public void setIndex(int index) {
			this.index = index;
		}
	}
	
	/**
	 * Constructs a HeapAdaptablePriorityQueue with given comparator
	 * @param c comparator to set
	 */
	public HeapAdaptablePriorityQueue(Comparator<K> c) {
		super(c);
	}
	
	/**
	 * Constructs a HeapAdaptablePriorityQueue with null comparator
	 */
	public HeapAdaptablePriorityQueue() {
		this(null);
	}
	
	// Factory method for creating a new adaptable PQ entry
	@Override
	protected AdaptablePQEntry<K, V> createEntry(K key, V value) {
		// A new adaptable PQ Entry added to the heap will be at index size()
		AdaptablePQEntry<K, V> temp = new AdaptablePQEntry<K, V>(key, value, size());
		return temp;
	}
	
	/**
	 * Helper method for validating an entry
	 * @param entry entry to evaluate
	 * @return validated entry
	 */
	private AdaptablePQEntry<K, V> validate(Entry<K, V> entry) {
		if (!(entry instanceof AdaptablePQEntry)) {
			throw new IllegalArgumentException("Entry is not a valid adaptable priority queue entry.");
		}
		AdaptablePQEntry<K, V> temp = (AdaptablePQEntry<K, V>)entry;
		if (temp.getIndex() >= list.size() || list.get(temp.getIndex()) != temp) {
			throw new IllegalArgumentException("Invalid Adaptable PQ Entry.");
		}
		return temp;
	}
	
	@Override
	public void swap(int index1, int index2) {
		// Delegate to the super class swap method
		super.swap(index1, index2);
		// But then update the index of each entry so that they remain location-aware
		((AdaptablePQEntry<K, V>)list.get(index1)).setIndex(index1);
		((AdaptablePQEntry<K, V>)list.get(index2)).setIndex(index2);
	}

	@Override
	public void remove(Entry<K, V> entry) {
		AdaptablePQEntry<K, V> temp = validate(entry);
		int j = temp.getIndex();
		if (j == list.size() - 1) {
			list.remove(list.size() - 1);
		} else {
			swap(j, list.size() - 1);
			list.remove(list.size() - 1);
			bubble(j);
		}
	}
	
	/**
	 * Restores the heap property by moving the entry at given index up or down
	 * @param index index to evaluate
	 */
	private void bubble(int index) {
		if (index > 0 && compare(list.get(index).getKey(), list.get(parent(index)).getKey()) < 0) {
			upHeap(index);
		} else {
			downHeap(index);
		}
	}

	@Override
	public void replaceKey(Entry<K, V> entry, K key) {
		AdaptablePQEntry<K, V> temp = validate(entry);
		temp.setKey(key);
		bubble(temp.getIndex());
	}

	@Override
	public void replaceValue(Entry<K, V> entry, V value) {
		AdaptablePQEntry<K, V> temp = validate(entry);
		temp.setValue(value);
	}
}