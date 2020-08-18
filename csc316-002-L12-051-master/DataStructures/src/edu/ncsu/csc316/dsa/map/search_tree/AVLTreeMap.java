package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;
import edu.ncsu.csc316.dsa.Position;

/**
 * Represents the implmentation of an AVL Tree, delegating to BinarySearchTreeMap
 * @author Matthew Kierski
 *
 * @param <K> key of entry
 * @param <V> value of entry
 */
public class AVLTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

	/**
	 * Constructs an AVLTreeMap, setting comparator to null
	 */
	public AVLTreeMap() {
		super(null);
	}

	/**
	 * Constructs an AVLTreeMap, setting comparator to given comparator
	 * @param compare comparator to sort by
	 */
	public AVLTreeMap(Comparator<K> compare) {
		super(compare);
	}

    /**
     * Helper method to trace upward from position p checking to make
     * sure each node on the path is balanced. If a rebalance is necessary,
     * trigger a trinode resturcturing
     * @param p node to evaluate
     */
	private void rebalance(Position<Entry<K, V>> p) {
		int oldHeight = 0;
		int newHeight = 0;
		do {
			if (!isBalanced(p)) {
				//Find the child with the "taller" height
				Position<Entry<K, V>> child = tallerChild(p);
				//Find the grandchild with "taller" height
				Position<Entry<K, V>> grandchild = tallerChild(child);
				//Perform trinode restructuring at the grandchild
				//Save the root of the restructured subtree as x
				p = restructure(grandchild);
				recomputeHeight(left(p));
				recomputeHeight(right(p));
			}
			recomputeHeight(p);
			newHeight = getProperty(p);
			p = parent(p);
		} while (oldHeight != newHeight && p != null);
	}
	
    /**
     * Returns the child of p that has the greater height
     * If both children have the same height, use the child that 
     * matches the parent's orientation	
     * @param p node to evaluate
     * @return the child of the node with greater height
     */
	private Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> p) {
		if (getProperty(left(p)) > getProperty(right(p))) {
			return left(p);
		}
		if (getProperty(left(p)) < getProperty(right(p))) {
			return right(p);
		}
		if (isRoot(p)) {
			return left(p);
		}
		if (left(parent(p)) == p) {
			return left(p);
		}
		else {
			return right(p);
		}
	}	

	/**
	 * Helper method to determine if tree is balanced
	 * @param p node to evaluate
	 * @return true if balanced, false otherwise
	 */
	private boolean isBalanced(Position<Entry<K, V>> p) {
		return Math.abs(getProperty(left(p)) - getProperty(right(p))) <= 1;
	}
	
	/**
	 * Helper method for determining the new height after restructuring
	 * @param p node to evaluate
	 */
	private void recomputeHeight(Position<Entry<K, V>> p) {
		int h = 1 + Math.max(getProperty(left(p)), getProperty(right(p)));
		setProperty(p, h);
	}

	@Override
	protected void actionOnInsert(Position<Entry<K, V>> node) {
		rebalance(node);
	}

	@Override
	protected void actionOnDelete(Position<Entry<K, V>> node) {
		if(!isRoot(node))
		{
			rebalance(parent(node));
		}
	}
}