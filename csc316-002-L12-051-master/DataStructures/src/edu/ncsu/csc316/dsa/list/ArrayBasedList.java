package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the an AbstractList as an Array-based list.
 * @author Matthew Kierski
 *
 * @param <E> generic element in list
 */
public class ArrayBasedList<E> extends AbstractList<E> {
	/** Default capacity of array */
	private final static int DEFAULT_CAPACITY = 10;
	/** Instance of a generic array for storing data */
	private E[] data;
	/** Size of the array */
	private int size;

	/**
	 * Constructs an ArrayBasedList with default capacity
	 */
	public ArrayBasedList() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructs an ArrayBasedList with given capacity
	 * @param capacity capacity of initial ArrayBasedList
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedList(int capacity) {
		data = (E[]) (new Object[capacity]);
		size = 0;
	}

	@Override
	public void add(int index, E value) {
		ensureCapacity(size + 1);
		checkIndexForAdd(index);
		for (int i = size; i >= index + 1; i--) {
			data[i] = data[i - 1];
		}
		data[index] = value;
		size++;
	}

	@Override
	public E get(int index) {
		checkIndex(index);
		return data[index];
	}

	@Override
	public E remove(int index) {
		checkIndex(index);
		E removed = data[index];
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		size--;
		return removed;
	}

	@Override
	public E set(int index, E value) {
		checkIndex(index);
		E replaced = data[index];
		data[index] = value;
		return replaced;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
	    return new ElementIterator();
	}
	
	/**
	 * Ensures that our list can be added to by resizing when necessary
	 * @param minCapacity minimum required capacity
	 */
	private void ensureCapacity(int minCapacity) {
		int oldCapacity = data.length;
		if (minCapacity > oldCapacity) {
			int newCapacity = (oldCapacity * 2) + 1;
			if (newCapacity < minCapacity) {
				newCapacity = minCapacity;
			}
			data = Arrays.copyOf(data, newCapacity);
		}
	}
	
	/**
	 * Iterator for ArrayBasedList.
	 * CITATION: Builidng Java Programs, 4th Edition. Pages 952-953. 
	 * @author Stuart Reges, Marty Stepp
	 *
	 */
	private class ElementIterator implements Iterator<E> {
		/** Instance of ArrayBasedList */
		//private ArrayBasedList<E> list;
		/** Iterator's position */
		private int position;
		/** Whether element can be removed */
		private boolean removeOK;

		/**
		 * Constructs iterator to traverse elements
		 */
		public ElementIterator() {
			position = 0;
			removeOK = false;	
		}

		/**
		 * Returns true if there is a next, false otherwise
		 */
		public boolean hasNext() {
			return position < size;
		}

		/**
		 * Traverses to the next element and returns processed element
		 */
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E result = ArrayBasedList.this.get(position);
			removeOK = true;
			position++;
			return result;
		}

		/**
		 * Removes the last element returned by next()
		 */
		public void remove() {
			if (!removeOK) {
				throw new IllegalStateException();
			}
			ArrayBasedList.this.remove(position - 1);
			position--;
			removeOK = false;
		}
	}
}