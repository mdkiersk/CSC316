
package edu.ncsu.csc316.dsa;

/**
 * Interface for a position in a linked list
 * @author Matthew Kierski
 *
 * @param <E> generic element
 */
public interface Position<E> {
	/**
	 * Returns the element of given position
	 * @return the element of given position
	 */
	E getElement();
}