package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;


import edu.ncsu.csc316.dsa.Position;

/**
 * Represents the implementation of a SplayTree, delegating to BST map
 * @author Matthew Kierski
 *
 * @param <K> key of entry
 * @param <V> value of entry
 */
public class SplayTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

	/**
	 * Constructs a SplayTreeMap, setting comparator to null
	 */
	public SplayTreeMap() {
		super(null);
	}

	/**
	 * Constructs a SplayTreeMap, setting comparator to given comparator
	 * @param compare comparator to sort by
	 */
	public SplayTreeMap(Comparator<K> compare) {
		super(compare);
	}

	/**
	 * Helper method for splaying a given node
	 * @param p node to splay
	 */
	private void splay(Position<Entry<K, V>> p) {
		while (!isRoot(p)) {
			Position<Entry<K, V>> parent = parent(p);
			Position<Entry<K, V>> grandparent = parent(parent);
			if (grandparent == null) {
				rotate(p);
			} else if ((parent == left(grandparent)) == (p == left(parent))) {
				rotate(parent);
				rotate(p);
			} else {
				rotate(p);
				rotate(p);
			}
		}	
	}

	@Override
	protected void actionOnAccess(Position<Entry<K, V>> p) {
		// If p is a dummy/sentinel node, move to the parent
		if(isLeaf(p)) {
			p = parent(p);
		}
		if(p != null) {
			splay(p);
		}
	}

	@Override
	protected void actionOnInsert(Position<Entry<K, V>> node) {
		splay(node);
	}

	@Override
	protected void actionOnDelete(Position<Entry<K, V>> p) {
		if(!isRoot(p)) {
			splay(parent(p));
		}
	}
}