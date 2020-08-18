package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * Represents the interface of all Binary Tree implementations
 * @author Matthew Kierski
 *
 * @param <E> generic element of node
 */
public interface BinaryTreeCollection<E> extends BinaryTree<E>, Iterable<E> {
	/**
	 * Adds a new root with given value
	 * @param value value of new root
	 * @return the new root
	 */
	Position<E> addRoot(E value);

	/**
	 * Adds a left child node to a given node
	 * @param p node to add left child to
	 * @param value value of new node
	 * @return the new node
	 */
	Position<E> addLeft(Position<E> p, E value);

	/**
	 * Adds a right child node to a given node
	 * @param p node to add right child to
	 * @param value value of new node
	 * @return the new node
	 */
	Position<E> addRight(Position<E> p, E value);

	/**
	 * Removes a given node from the tree
	 * @param p node to remove from tree
	 * @return value of removed node
	 */
	E remove(Position<E> p);

	/**
	 * Sets a given node to new given value
	 * @param p node to set
	 * @param value new value of node
	 * @return the old value of the node
	 */
	E set(Position<E> p, E value);
}