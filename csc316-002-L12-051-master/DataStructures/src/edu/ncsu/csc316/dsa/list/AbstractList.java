package edu.ncsu.csc316.dsa.list;

/**
 * This class represents the implementation of the Abstract List and its functions. 
 * @author Dr. King
 *
 * @param <E> generic element in list
 */
public abstract class AbstractList<E> implements List<E> {

	@Override
	public void addFirst(E value) {
		add(0, value);
	}

	@Override
	public void addLast(E value) {
		add(size(), value);
	}

	/**
	 * Checks to ensure index is valid 
	 * @param index index to be checked
	 */
	protected void checkIndex(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
		}
	}

	/**
	 * Checks to ensure an element can be added to index
	 * @param index index to be added at
	 */
	protected void checkIndexForAdd(int index) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
		}
	}

	@Override
	public E first() {
		return get(0);
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public E last() {
		return get(size() - 1);
	}

	@Override
	public E removeFirst() {
		return remove(0);
	}

	@Override
	public E removeLast() {
		return remove(size() - 1);
	}
}