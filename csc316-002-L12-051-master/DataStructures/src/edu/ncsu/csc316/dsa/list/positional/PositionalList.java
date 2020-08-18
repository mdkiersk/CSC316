package edu.ncsu.csc316.dsa.list.positional;

import edu.ncsu.csc316.dsa.Position;

/**
 * Interface providing methods for a position list
 * @author Matthew Kierski
 *
 * @param <E> generic element
 */
public interface PositionalList<E> extends Iterable<E> {
	/**
	 * Adds a value after given position
	 * @param p position
	 * @param value value to be added
	 * @return returns position of new element
	 */
	Position<E> addAfter(Position<E> p, E value);

	/**
	 * Adds a value before given position
	 * @param p position
	 * @param value value to be added
	 * @return returns position of new element
	 */
	Position<E> addBefore(Position<E> p, E value);

	/**
	 * Adds a value at the beginning of the list
	 * @param value value to be added
	 * @return position of new value
	 */
	Position<E> addFirst(E value);

	/**
	 * Adds a value to the end of the list
	 * @param value value to be added
	 * @return position of new value
	 */
	Position<E> addLast(E value);

	/**
	 * Returns position immediately after given position
	 * @param p given position to check
	 * @return position immediately after given position
	 */
	Position<E> after(Position<E> p);

	/**
	 * Returns position immediately before given position
	 * @param p position to check before
	 * @return position immediately before given position
	 */
	Position<E> before(Position<E> p);

	/**
	 * Returns position of first element in list
	 * @return position of first element in list
	 */
	Position<E> first();
	
	/**
	 * Returns position of last element in list
	 * @return position of last element in list
	 */
	Position<E> last();

	/**
	 * Returns true if list is empty, false otherwise
	 * @return true if list is empty, false otherwise
	 */
	boolean isEmpty();

	/**
	 * Returns new iterator for position list
	 * @return new iterator for position list
	 */
	Iterable<Position<E>> positions();

	/**
	 * Removes and returns the element at given position
	 * @param p given position to remove
	 * @return element that was removed
	 */
	E remove(Position<E> p);

	/**
	 * Replaces the element at given position, and returns original element
	 * @param p position to set
	 * @param value value to set 
	 * @return original element
	 */
	E set(Position<E> p, E value);

	/**
	 * Returns size of the list
	 * @return size of the list
	 */
	int size();
}