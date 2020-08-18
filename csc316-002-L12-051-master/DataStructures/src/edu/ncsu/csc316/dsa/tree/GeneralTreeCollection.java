package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * Serves as the interface for all General Tree implementations
 * @author Matthew Kierski
 *
 * @param <E> generic element of tree
 */
public interface GeneralTreeCollection<E> extends Tree<E>, Iterable<E> {
	/**
	 * Adds a new root with given value
	 * @param value value of new root
	 * @return the new root
	 */
	Position<E> addRoot(E value);

	/**
	 * Adds a given node with given value as a child
	 * @param p node to add
	 * @param value value of new child
	 * @return the new child node
	 */
	Position<E> addChild(Position<E> p, E value);

	/**
	 * Removes a given node from the tree
	 * @param p node to remove
	 * @return value of the removed node
	 */
	E remove(Position<E> p);

	/**
	 * Sets a given node with a given value
	 * @param p node to change
	 * @param value value to set
	 * @return the old value of the node
	 */
	E set(Position<E> p, E value);
}