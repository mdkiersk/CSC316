package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;

/**
 * Represents the implementation of a RedBlack tree, delegating to BST map
 * @author Matthew Kierski
 *
 * @param <K> key of entry
 * @param <V> value of entry
 */
public class RedBlackTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

	/**
	 * Constructs a RedBlackTreeMap, setting comparator to null
	 */
	public RedBlackTreeMap() {
		super(null);
	}

	/**
	 * Constructs a RedBlackTreeMap, setting comparator to given comparator
	 * @param compare comparator to sort by
	 */
	public RedBlackTreeMap(Comparator<K> compare) {
		super(compare);
	}

    /**
     * Helper method to define "blackness" of a node
     * @param p node to evaluate
     * @return true if property = 0, false otherwise
     */
	private boolean isBlack(Position<Entry<K, V>> p) {
		return getProperty(p) == 0;
	}

	/**
	 * Helper method to define "redness" of a node
	 * @param p node to evaluate
	 * @return true if property = 1, false otherwise
	 */
	private boolean isRed(Position<Entry<K, V>> p) {
		return getProperty(p) == 1;
	}

    /**
     * Sets the color of a node to black
     * @param p node to set
     */
	private void makeBlack(Position<Entry<K, V>> p) {
		setProperty(p, 0);
	}

	/**
     * Sets the color of a node to red
     * @param p node to set
     */	private void makeRed(Position<Entry<K, V>> p) {
		setProperty(p, 1);
	}

     /**
      * Helper method to resolve a double red
      * @param node node to evaluate
      */
	private void resolveRed(Position<Entry<K, V>> node) {
		Position<Entry<K, V>> parent, uncle, middle, grandparent;
		parent = parent(node);
		if (isRed(parent)) {
			uncle = sibling(parent);
			if (isBlack(uncle)) {
				middle = restructure(node);
				makeBlack(middle);
				makeRed(left(middle));
				makeRed(right(middle));
			} else {
				makeBlack(parent);
				makeBlack(uncle);
				grandparent = parent(parent);
				if (!isRoot(grandparent)) {
					makeRed(grandparent);
					resolveRed(grandparent);
				}
			}
		}
	}

	/**
	 * Helper method to resolve a double black
	 * @param p node to evaluate
	 */
	private void remedyDoubleBlack(Position<Entry<K, V>> p) {
		Position<Entry<K, V>> parent = parent(p);
		Position<Entry<K, V>> sibling = sibling(p);
		if (isBlack(sibling)) {
			if (isRed(left(sibling)) || isRed(right(sibling))) {
				Position<Entry<K, V>> temp = (isRed(left(sibling)) ? left(sibling) : right(sibling));
				Position<Entry<K, V>> middle = restructure(temp);
				if (isRed(parent)) {
					makeRed(middle);
				} else {
					makeBlack(middle);
				}
				makeBlack(left(middle));
				makeBlack(right(middle));
 			} else {
 				makeRed(sibling);
 				if (isRed(parent)) {
 					makeBlack(parent);
 				} else if (!isRoot(parent)) {
 					remedyDoubleBlack(parent);
 				}
 			}
		} else {
			rotate(sibling);
			makeBlack(sibling);
			makeRed(parent);
			remedyDoubleBlack(p);
		}
	}

	@Override
	protected void actionOnInsert(Position<Entry<K, V>> p) {
		if (!isRoot(p)) {
			makeRed(p);
			resolveRed(p);
		}
	}

	@Override
	protected void actionOnDelete(Position<Entry<K, V>> p) {
		if (isRed(p)) {
			makeBlack(p);
		} else if (!isRoot(p)) {
			Position<Entry<K, V>> sib = sibling(p);
			if (isInternal(sib) && (isBlack(sib) || isInternal(left(sib)))) {
				remedyDoubleBlack(p);
			}
		}
	}
}