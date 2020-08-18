package edu.ncsu.csc316.dsa.disjoint_set;


import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * Represents the implementation of an UpTree Disjoint set forest using linear probing hash map
 * as secondary data structure
 * @author Matthew Kierski
 *
 * @param <E> value of entry
 */
public class UpTreeDisjointSetForest<E> implements DisjointSetForest<E> {

	/** Secondary structure to map sets */
    private Map<E, UpTreeNode<E>> map;
    
    /**
     * Constructs an UpTreeDisjointSetForest using a LinearProbingHashMap
     */
    public UpTreeDisjointSetForest() {
        // Use an efficient map!
        map = new LinearProbingHashMap<E, UpTreeNode<E>>();
    }

    @Override
    public Position<E> makeSet(E value) {
        UpTreeNode<E> node = new UpTreeNode<E>(value);
        node.setParent(node);
        node.setCount(1);
        map.put(value, node);
        return node;
    }

    @Override
    public Position<E> find(E value) {
    	UpTreeNode<E> node = map.get(value);
    	return findHelper(node);
    }
    
    private UpTreeNode<E> findHelper(UpTreeNode<E> current) {
        if (current != current.getParent()) {
        	current.setParent(findHelper(current.getParent()));
        }
        return current.getParent();
    }

    @Override
    public void union(Position<E> s, Position<E> t) {
        UpTreeNode<E> a = validate(find(s.getElement()));
        UpTreeNode<E> b = validate(find(t.getElement()));
        
        if (a.getCount() > b.getCount()) {
        	a.setCount(a.getCount() + b.getCount());
        	b.setParent(a);
        } else {
        	b.setCount(a.getCount() + b.getCount());
        	a.setParent(b);
        }
    }
    
    /**
     * Helper method to cast a position as an UptreeNode
     * @param p position to cast
     * @return casted position
     */
    private UpTreeNode<E> validate(Position<E> p) {
    	if (!(p instanceof UpTreeNode)) {
            throw new IllegalArgumentException("Position is not a valid up tree node.");
        }
        return (UpTreeNode<E>)p;
    }
    
    /**
     * Represents the data and behavior of an UptreeNode
     * @author Matthew Kierski
     *
     * @param <E> value of node
     */
	private static class UpTreeNode<E> implements Position<E> {

		/** Element of this node */
		private E element;
		/** Parent of this node */
		private UpTreeNode<E> parent;
		/** Count of this node */
		private int count;

		/**
		 * Constructs an UpTreeNode, setting element to given, parent to itself, and count to 1
		 * @param element element to set
		 */
		public UpTreeNode(E element) {
			setElement(element);
			setParent(this);
			setCount(1);
		}

		/**
		 * Sets the element
		 * @param element element to set
		 */
		public void setElement(E element) {
			this.element = element;
		}

		@Override
		public E getElement() {
			return element;
		}

		/**
		 * Sets the parent
		 * @param parent parent to set
		 */
		public void setParent(UpTreeNode<E> parent) {
			this.parent = parent;
		}

		/**
		 * Returns the parent of this node
		 * @return the parent of this node
		 */
		public UpTreeNode<E> getParent() {
			return parent;
		}

		/**
		 * Sets the count
		 * @param count count to set
		 */
		public void setCount(int count) {
			this.count = count;
		}

		/**
		 * Returns the count of this node
		 * @param count count of this node
		 */
		public int getCount() {
			return count;
		}
	}
}