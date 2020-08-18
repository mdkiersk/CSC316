package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * Serves as the interface for all Tree implementations
 * @author Matthew Kierski
 *
 * @param <E> generic element of tree
 */
public interface Tree<E> {

	/**
	 * Returns the root of the tree
	 * @return the root of the tree
	 */
	Position<E> root();

	/**
	 * Returns the parent of a given node
	 * @param p node to evaluate
	 * @return the parent of a given node
	 */
	Position<E> parent(Position<E> p);

	/**
	 * Returns a list of the children of a given node
	 * @param p node to evaluate
	 * @return a list of the children of a given node
	 */
	Iterable<Position<E>> children(Position<E> p);

	/**
	 * Returns number of children of given node
	 * @param p node to evaluate
	 * @return number of children of given node
	 */
	int numChildren(Position<E> p);

	/**
	 * Returns true if given node is internal (has children), false otherwise
	 * @param p node to evaluate
	 * @return true if given node is internal (has children), false otherwise
	 */
	boolean isInternal(Position<E> p);

	/**
	 * Returns true if given node is a leaf (no children), false otherwise
	 * @param p node to evaluate
	 * @return true if given node is leaf (no children), false otherwise
	 */
	boolean isLeaf(Position<E> p);

	/**
	 * Returns true if given node is root, false otherwise
	 * @param p node to evaluate
	 * @return true if given node is root, false otherwise
	 */
	boolean isRoot(Position<E> p);

	/**
	 * Returns number of nodes in a tree
	 * @return number of nodes in a tree
	 */
	int size();

	/**
	 * Returns whether tree is empty or not
	 * @return true if tree is empty, false otherwise
	 */
	boolean isEmpty();

	/**
	 * Returns a pre order traversal list of nodes
	 * @return a pre order traversal list of nodes
	 */
	Iterable<Position<E>> preOrder();

	/**
	 * Returns a post order traversal list of nodes
	 * @return a post order traversal list of nodes
	 */
	Iterable<Position<E>> postOrder();

	/**
	 * Returns a level order traversal list of nodes
	 * @return a level order traversal list of nodes
	 */
	Iterable<Position<E>> levelOrder();
}
