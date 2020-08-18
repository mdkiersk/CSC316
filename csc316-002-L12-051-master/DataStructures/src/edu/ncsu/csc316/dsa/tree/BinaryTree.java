package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * Represents the interface for all Binary Tree implementations
 * @author Matthew Kierski
 *
 * @param <E> generic element of node
 */
public interface BinaryTree<E> extends Tree<E> {
	
	/**
	 * Returns the left child of a given node 
	 * @param p node to evaluate
	 * @return the left child of a given node
	 */
	Position<E> left(Position<E> p);

	/**
	 * Returns the right child of a given node 
	 * @param p node to evaluate
	 * @return the right child of a given node
	 */
	Position<E> right(Position<E> p);

	/**
	 * Returns the sibling of a given node 
	 * @param p node to evaluate
	 * @return the sibling of a given node
	 */
	Position<E> sibling(Position<E> p);

	/**
	 * Returns an in order traversal of the tree
	 * @return an in order traversal of the tree
	 */
	Iterable<Position<E>> inOrder();
}
