package edu.ncsu.csc316.dsa.list;

/**
 * Serves as the Interface for all common list functions.
 * @author Dr. King
 *
 * @param <E> generic element in list
 */
public interface List<E> extends Iterable<E> {
	/**
	 * Adds a given value at given index
	 * @param index index to be added at
	 * @param value value to be added
	 */
	void add(int index, E value);

	/**
	 * Adds a given value at the front of the list
	 * @param value value to be added
	 */
	void addFirst(E value);

	/**
	 * Adds a given value at the end of the list
	 * @param value value to be added
	 */
	void addLast(E value);

	/**
	 * Returns the first value from the list
	 * @return the first value from the list
	 */
	E first();

	/**
	 * Returns the value from a given index
	 * @param index index to retrieve from
	 * @return the value from a given index
	 */
	E get(int index);

	/**
	 * Returns true if list is empty, false otherwise
	 * @return true if list is empty, false otherwise
	 */
	boolean isEmpty();

	/**
	 * Returns the last value in a list
	 * @return the last value in a list
	 */
	E last();

	/**
	 * Removes a value at a given index
	 * @param index index to be removed
	 * @return value removed from the list
	 */
	E remove(int index);

	/**
	 * Removes the first value in a list
	 * @return the value removed from the list
	 */
	E removeFirst();

	/**
	 * Removes the last value in a list
	 * @return the value removed from the list
	 */
	E removeLast();

	/**
	 * Sets the data at a given index with a given value
	 * @param index index to be set at 
	 * @param value value to be set
	 * @return returns the data that was replaced
	 */
	E set(int index, E value);

	/**
	 * Returns size of the list
	 * @return size of the list
	 */
	int size();
}