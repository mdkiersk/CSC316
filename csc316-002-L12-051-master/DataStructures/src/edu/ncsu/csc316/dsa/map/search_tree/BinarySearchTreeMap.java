package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.AbstractSortedMap;
import edu.ncsu.csc316.dsa.map.Map.Entry;
import edu.ncsu.csc316.dsa.tree.BinaryTree;
import edu.ncsu.csc316.dsa.tree.LinkedBinaryTree;

/**
 * Represents the data and behavior of a BinarySearchTree. Map implemented.
 *
 * @author Matthew Kierski
 *
 * @param <K> key of entry
 * @param <V> value of entry
 */
public class BinarySearchTreeMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V>
		implements BinaryTree<Entry<K, V>> {

	/** Instance of inner class below */
	private BalanceableBinaryTree<K, V> tree;

	/**
	 * Constructs a BinarySearchTreeMap, setting comparator to null
	 */
	public BinarySearchTreeMap() {
		this(null);
	}

	/**
	 * Constructs a BinarySearchTreeMap, setting comparator to given comparator
	 * @param compare comparator to sort with
	 */
	public BinarySearchTreeMap(Comparator<K> compare) {
		super(compare);
		tree = new BalanceableBinaryTree<K, V>();
		tree.addRoot(null);
	}

	@Override
	public int size() {
		// Our search trees will all use dummy/sentinel leaf nodes,
		// so the actual number of elements in the tree will be (size-1)/2
		return (tree.size() - 1) / 2;
	}

	/**
	 * Adds left/right sentinel nodes to leaves of tree
	 * @param p position to set
	 * @param entry entry of node
	 */
	private void expandLeaf(Position<Entry<K, V>> p, Entry<K, V> entry) {
		// initially, p is a dummy/sentinel node,
		// so replace the null entry with the new actual entry
		tree.set(p, entry);
		// Then add new dummy/sentinel children
		tree.addLeft(p, null);
		tree.addRight(p, null);
	}

	/**
	 * This helper method traces a path down the tree to locate the position
	 * that contains an entry with the given key
	 * @param p position to look up
	 * @param key to search for
	 * @return the position with given key, or last position traversed
	 */
	private Position<Entry<K, V>> lookUp(Position<Entry<K, V>> p, K key) {
		// If we have reached a dummy/sentinel node (a leaf), return that sentinel node
		if (isLeaf(p)) {
			return p;
		}
		int comp = compare(key, p.getElement().getKey());
		if (comp == 0) {
			// Return the position that contains the entry with the key
			return p;
		} else if (comp < 0) {
			return lookUp(left(p), key);
		} else {
			return lookUp(right(p), key);
		}
	}

	@Override
	public V get(K key) {
		Position<Entry<K, V>> p = lookUp(tree.root(), key);
		// actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use
		actionOnAccess(p);
		if (isLeaf(p)) {
			return null;
		}
		return p.getElement().getValue();
	}

	@Override
	public V put(K key, V value) {
		// Create the new map entry
		Entry<K, V> newEntry = new MapEntry<K, V>(key, value);
		// Get the last node visited when looking for the key
		Position<Entry<K, V>> p = lookUp(root(), key);
		// If the last node visited is a dummy/sentinel node
		if (isLeaf(p)) {
			expandLeaf(p, newEntry);
			// actionOnInsert is a "hook" for our AVL, Splay, and Red-Black Trees to use
			actionOnInsert(p);
			return null;
		} else {
			V original = p.getElement().getValue();
			set(p, newEntry);
			// actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use
			actionOnAccess(p);
			return original;
		}
	}

	@Override
	public V remove(K key) {
		// Get the last node visited when looking for the key
		Position<Entry<K, V>> p = lookUp(root(), key);
		// If p is a dummy/sentinel node
		if (isLeaf(p)) {
			// actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use
			actionOnAccess(p);
			return null;
		} else {
			V original = p.getElement().getValue();
			// If the node has two children (that are not dummy/sentinel nodes)
			if (isInternal(left(p)) && isInternal(right(p))) {
				// Replace with the inorder successor
				Position<Entry<K, V>> replacement = treeMin(right(p));
				set(p, replacement.getElement());
				// Move p to the replacement node in the right subtree
				p = replacement;
			}
			// Get the dummy/sentinel node (in case the node has an actual entry as a
			// child)...
			Position<Entry<K, V>> leaf = (isLeaf(left(p)) ? left(p) : right(p));
			// ... then get its sibling (will be another sentinel or an actual entry node)
			Position<Entry<K, V>> sib = sibling(leaf);
			// Remove the leaf NODE (this is your binary tree remove method)
			remove(leaf);
			// Remove the NODE (this is your binary tree remove method)
			// which will "promote" the sib node to replace p
			remove(p);
			// actionOnDelete is a "hook" for our AVL, Splay, and Red-Black Trees to use
			actionOnDelete(sib);
			return original;
		}
	}

	/**
	 * Returns the in order successor of a given node
	 * @param node node to act as root of subtree
	 * @return in order successor of given node
	 */
	private Position<Entry<K, V>> treeMin(Position<Entry<K, V>> node) {
		Position<Entry<K, V>> current = node;
		while (isInternal(current)) {
			current = left(current);
		}
		return parent(current);
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		List<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>(size());
		for (Position<Entry<K, V>> n : tree.inOrder()) {
			set.addLast(n.getElement());
		}
		return set;
	}

	@Override
	public String toString() {
		return tree.toString();
	}

	/**
	 * This is a "hook" method that will be overridden in
	 * AVL, Splay, and Red-Black tree implementations
	 * @param node node to access 
	 */
	protected void actionOnAccess(Position<Entry<K, V>> node) {
		// Do nothing for BST
	}

	/**
	 * This is a "hook" method that will be overridden in
	 * AVL, Splay, and Red-Black tree implementations
	 * @param node node to insert 
	 */
	protected void actionOnInsert(Position<Entry<K, V>> node) {
		// Do nothing for BST
	}

	/**
	 * This is a "hook" method that will be overridden in
	 * AVL, Splay, and Red-Black tree implementations
	 * @param node node to delete 
	 */
	protected void actionOnDelete(Position<Entry<K, V>> node) {
		// Do nothing for BST
	}

	/**
	 * Respresents data and behaviors of a Balanceable Binary Tree
	 * @author Matthew Kierski
	 *
	 * @param <K> key of entry
	 * @param <V> value of entry
	 */
	protected static class BalanceableBinaryTree<K, V> extends LinkedBinaryTree<Entry<K, V>> {

		/**
		 * A helper method for trinode restructuring
		 * @param parent parent of node to rotate
		 * @param child child of node to rotate
		 * @param makeLeftChild true if need to set left child, false otherwise
		 */
		private void relink(Node<Entry<K, V>> parent, Node<Entry<K, V>> child, boolean makeLeftChild) {
			child.setParent(parent);
			if (makeLeftChild) {
				parent.setLeft(child);
			}
			else {
				parent.setRight(child);
			}
		}

		/**
		 * A helper method for rotating nodes around a given node
		 * @param p node to rotate around
		 */
		public void rotate(Position<Entry<K, V>> p) {
			Node<Entry<K, V>> node = validate(p);
			Node<Entry<K, V>> parent = node.getParent();
			Node<Entry<K, V>> grandparent = parent.getParent();
			
			//Check whether the rotation is a single rotation (no grandparent exists)
			if (grandparent == null) {
				setRoot(node);
				node.setParent(null);
			} else {
				//Otherwise, link the node as a child of the grandparent
				if (parent == grandparent.getLeft()) {
					relink(grandparent, node, true);
				} else {
					relink(grandparent, node, false);
				}
			}
			//Regardless of whether parent exists, relink the parent and node and transfer node's subtree
			if (node == parent.getLeft()) {
				relink(parent, node.getRight(), true);
				relink(node, parent, false);
			} else {
				relink(parent, node.getLeft(), false);
				relink(node, parent, true);
			}
		}

		/**
		 * Helper method for trinode restructuring
		 * @param x node to rotate around
		 * @return the new parent of rotation
		 */
		public Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
			Node<Entry<K, V>> node = validate(x);
			Node<Entry<K, V>> parent = node.getParent();
			Node<Entry<K, V>> grandparent = parent.getParent();
			if ((parent.getLeft() == node && grandparent.getLeft() == parent) || 
					(parent.getRight() == node && grandparent.getRight() == parent)) {
				rotate(parent);
				return parent;
			} else {
				rotate(node);
				rotate(node);
				return node;
			}
		}

		@Override
		protected Node<Entry<K, V>> createNode(Entry<K, V> element, Node<Entry<K, V>> parent, Node<Entry<K, V>> left,
				Node<Entry<K, V>> right) {
			BSTNode<Entry<K, V>> newNode = new BSTNode<Entry<K, V>>(element);
			newNode.setParent(parent);
			newNode.setLeft(left);
			newNode.setRight(right);
			newNode.setProperty(0);
			return newNode;
		}

		/**
		 * An inner class that represents the data and behavior of a BST node
		 * @author Matthew Kierski
		 *
		 * @param <E> generic element of node
		 */
		protected static class BSTNode<E> extends Node<E> {

			/** Private instance of any data needed to be stored by different trees */
			private int property;
			
			/**
			 * Constructs a BST Node with given element
			 * @param element element to set
			 */
			public BSTNode(E element) {
				super(element);
				setProperty(0);
			}

			/**
			 * Sets the property of a node
			 * @param height property to set
			 */
			public void setProperty(int height) {
				this.property = height;
			}

			/**
			 * Returns the property of this node
			 * @return the propery of this node
			 */
			public int getProperty() {
				return property;
			}
		}

		/**
		 * Returns the property of a given node
		 * @param p node to evaluate
		 * @return the property of a given node
		 */
		public int getProperty(Position<Entry<K, V>> p) {
			if (p == null) {
				return 0;
			}
			BSTNode<Entry<K, V>> node = (BSTNode<Entry<K, V>>) p;
			return node.getProperty();
		}

		/**
		 * Sets the property of a given node
		 * @param p node to set 
		 * @param value value to set to
		 */
		public void setProperty(Position<Entry<K, V>> p, int value) {
			BSTNode<Entry<K, V>> node = (BSTNode<Entry<K, V>>) (p);
			node.setProperty(value);
		}
	}

	/////////////////////////////////////////////////////////////////////////////
	// All the methods below delegate to the BalanceableBinaryTree class, which
	///////////////////////////////////////////////////////////////////////////// extends
	// your linked binary tree implementation
	/////////////////////////////////////////////////////////////////////////////

	@Override
	public Position<Entry<K, V>> root() {
		return tree.root();
	}

	@Override
	public Position<Entry<K, V>> parent(Position<Entry<K, V>> p) {
		return tree.parent(p);
	}

	@Override
	public Iterable<Position<Entry<K, V>>> children(Position<Entry<K, V>> p) {
		return tree.children(p);
	}

	@Override
	public int numChildren(Position<Entry<K, V>> p) {
		return tree.numChildren(p);
	}

	@Override
	public boolean isInternal(Position<Entry<K, V>> p) {
		return tree.isInternal(p);
	}

	/**
	 * Sets the the entry to a given node
	 * @param p node to set
	 * @param entry entry to set to
	 * @return the new node
	 */
	public Entry<K, V> set(Position<Entry<K, V>> p, Entry<K, V> entry) {
		return tree.set(p, entry);
	}

	@Override
	public boolean isLeaf(Position<Entry<K, V>> p) {
		return tree.isLeaf(p);
	}

	@Override
	public boolean isRoot(Position<Entry<K, V>> p) {
		return tree.isRoot(p);
	}

	@Override
	public Iterable<Position<Entry<K, V>>> preOrder() {
		return tree.preOrder();
	}

	@Override
	public Iterable<Position<Entry<K, V>>> postOrder() {
		return tree.postOrder();
	}

	@Override
	public Iterable<Position<Entry<K, V>>> levelOrder() {
		return tree.levelOrder();
	}

	@Override
	public Position<Entry<K, V>> left(Position<Entry<K, V>> p) {
		return tree.left(p);
	}

	/**
	 * Removes the given node
	 * @param p node to remove
	 * @return value of removed node
	 */
	protected Entry<K, V> remove(Position<Entry<K, V>> p) {
		return tree.remove(p);
	}

	@Override
	public Position<Entry<K, V>> right(Position<Entry<K, V>> p) {
		return tree.right(p);
	}

	@Override
	public Position<Entry<K, V>> sibling(Position<Entry<K, V>> p) {
		return tree.sibling(p);
	}

	@Override
	public Iterable<Position<Entry<K, V>>> inOrder() {
		return tree.inOrder();
	}

	/**
	 * Performs a rotation on a given node
	 * @param p node to evaluate
	 */
	protected void rotate(Position<Entry<K, V>> p) {
		tree.rotate(p);
	}

	/**
	 * Restructures the tree around given node
	 * @param x node to restructure
	 * @return new subtree
	 */
	protected Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
		return tree.restructure(x);
	}

	/**
	 * Returns the property of a given node
	 * @param p node to evaluate
	 * @return the property of a given node
	 */
	public int getProperty(Position<Entry<K, V>> p) {
		return tree.getProperty(p);
	}

	/**
	 * Sets the property of a given node
	 * @param p node to set
	 * @param value value to set to
	 */
	public void setProperty(Position<Entry<K, V>> p, int value) {
		tree.setProperty(p, value);
	}
	
}