package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * Represents the data and behavior of a Heap priority queue
 * * Citation: Data Structures and Algorithms, Michael T. Goodrich, et al. Page 377-378. 
 * @author Matthew Kierski
 *
 * @param <K> key of entry
 * @param <V> value of entry
 */
public class HeapPriorityQueue<K extends Comparable<K>, V> extends AbstractPriorityQueue<K, V> {

	/** Underlying data structure of heap priority queue */ 
	protected ArrayBasedList<Entry<K, V>> list;

	/**
	 * Constructs a HeapPriorityQueue with given comparator
	 * @param comparator comparator to set
	 */
	public HeapPriorityQueue(Comparator<K> comparator) {
		super(comparator);
		list = new ArrayBasedList<Entry<K, V>>();
	}

	/**
	 * Constructs a HeapPriorityQueue with null comparator
	 */
	public HeapPriorityQueue() {
		this(null);
	}

    //////////////////////////////////////////
    // ADT Operations
    //////////////////////////////////////////

	@Override
	public Entry<K, V> insert(K key, V value) {
		Entry<K, V> temp = createEntry(key, value);
		list.addLast(temp);
		upHeap(list.size() - 1);
		return temp;
	}

	@Override
	public Entry<K, V> min() {
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public Entry<K, V> deleteMin() {
		if (list.isEmpty()) {
			return null;
		}
		Entry<K, V> removed = min();
		swap(0, list.size() - 1);
		list.remove(list.size() - 1);
		downHeap(0);
		return removed;
	}

	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Moves the entry at index to restore heap property if necessary
	 * @param index index to evaluate for movement in heap
	 */
	protected void upHeap(int index) {
		while (index > 0) {
			int p = parent(index);
			if (super.compare(list.get(index).getKey(), list.get(p).getKey()) > 0) {
				break;
			}
			swap(index, p);
			index = p;
		}
	}

	/**
	 * Swaps two entries' position in the queue
	 * @param index1 index of entry to swap
	 * @param index2 index of entry to swap
	 */
	protected void swap(int index1, int index2) {
		Entry<K, V> temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}

	/**
	 * Moves the entry at index lower to restore heap property if necessary
	 * @param index index to evaluate for movement in heap
	 */
	protected void downHeap(int index) {
		while (hasLeft(index)) {
			int leftIndex = left(index);
			int smallChildIndex = leftIndex;
			if (hasRight(index)) {
				int rightIndex = right(index);
				if (super.compare(list.get(leftIndex).getKey(), list.get(rightIndex).getKey()) > 0) {
					smallChildIndex = rightIndex;
				}
			}
			if (super.compare(list.get(smallChildIndex).getKey(), list.get(index).getKey()) > 0) {
				break;
			}
			swap(index, smallChildIndex);
			index = smallChildIndex;
		}
	}
	
    //////////////////////////////////////////////////
    // Convenience methods to help abstract the math
    // involved in jumping to parent or children
    //////////////////////////////////////////////////	

	/**
	 * Returns the parent of a given entry
	 * @param index index of entry to evaluate
	 * @return the index of the parent of a given entry
	 */
	protected int parent(int index) {
		return (index - 1) / 2;
	}

	/**
	 * Returns the left child of a given entry
	 * @param index index of entry to evaluate
	 * @return the index of the left child of a given entry
	 */
	protected int left(int index) {
		return 2 * index + 1;
	}

	/**
	 * Returns the right child of a given entry
	 * @param index index of entry to evaluate
	 * @return the index of the right child of a given entry
	 */
	protected int right(int index) {
		return 2 * index + 2;
	}

	/**
	 * Returns true if given entry has left child, false otherwise
	 * @param index index to evaluate
	 * @return true if given entry has left child, false otherwise
	 */
	protected boolean hasLeft(int index) {
		return left(index) < list.size();
	}

	/**
	 * Returns true if given entry has right child, false otherwise
	 * @param index index to evaluate
	 * @return true if given entry has right child, false otherwise
	 */
	protected boolean hasRight(int index) {
		return right(index) < list.size();
	}
}