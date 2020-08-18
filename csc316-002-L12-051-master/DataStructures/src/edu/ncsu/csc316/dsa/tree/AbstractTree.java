package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;

/**
 * Serves as the abstract implementation of all trees. 
 * Citation: Data Structures & Algorithms, p. 342
 * @author Matthew Kierski
 *
 * @param <E> generic element of tree
 */
public abstract class AbstractTree<E> implements Tree<E> {
    
    @Override
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }
    
    @Override
    public boolean isLeaf(Position<E> p) {
        return numChildren(p) == 0;
    }
    
    @Override
    public boolean isRoot(Position<E> p) {
        return p == root();
    }
    
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    @Override
	public Iterable<Position<E>> preOrder() {
		List<Position<E>> traversal = new SinglyLinkedList<Position<E>>();
		if (!isEmpty()) {
			preOrderHelper(root(), traversal);
		}
		return traversal;
	}

    /**
     * Private helper method for pre order traversal of a tree
     * @param p node to evaluate
     * @param traversal list to add to
     */
	private void preOrderHelper(Position<E> p, List<Position<E>> traversal) {
		traversal.addLast(p);
		for (Position<E> c : children(p)) {
			preOrderHelper(c, traversal);
		}
	}  
	
	@Override
	public Iterable<Position<E>> postOrder() {
		List<Position<E>> list = new SinglyLinkedList<Position<E>>();
		if (!isEmpty()) {
			postOrderHelper(root(), list);
		}
		return list;
	}
	
	/**
	 * Private helper method for post order traversal of a tree
	 * @param p node to evaluate
	 * @param list list to add to
	 */
	private void postOrderHelper(Position<E> p, List<Position<E>> list) {
		for (Position<E> c : children(p)) {
			postOrderHelper(c, list);
		}
		list.addLast(p);
	}
	
	@Override
	public Iterable<Position<E>> levelOrder() {
		List<Position<E>> list = new SinglyLinkedList<Position<E>>();
		if (!isEmpty()) {
			Queue<Position<E>> queue = new ArrayBasedQueue<Position<E>>();
			queue.enqueue(root());
			while (!queue.isEmpty()) {
				Position<E> p = queue.dequeue();
				list.addLast(p);
				for (Position<E> c : children(p)) {
					queue.enqueue(c);
				}
			}
		}
		return list;
	}
    
	/**
	 * Represents the data and behavior of a node
	 * @author Matthew Kierski
	 *
	 * @param <E> generic element of node
	 */
    protected abstract static class AbstractNode<E> implements Position<E> {

    	/** Element of this node */
        private E element;
        
        /**
         * Constructs an AbstractNode with given element
         * @param element element of new node
         */
        public AbstractNode(E element) {
            setElement(element);
        }
        
        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * Sets the element to new given element
         * @param element new element of this node
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
        toStringHelper(sb, "", root());
        sb.append("]");
        return sb.toString();
    }
    
    private void toStringHelper(StringBuilder sb, String indent, Position<E> root) {
        if(root == null) {
            return;
        }
        sb.append(indent).append(root.getElement()).append("\n");
        for(Position<E> child : children(root)) {
            toStringHelper(sb, indent + " ", child);
        }
    }
    
    /**
     * Iterator to iterate over the elements of nodes in a tree
     * @author Matthew Kierski
     *
     */
	protected class ElementIterator implements Iterator<E> {

		/** Instance of a position iterator */
		private Iterator<Position<E>> it;

		/**
		 * Constructs an ElementIterator, setting this iterator to a given iterator
		 * @param iterator iterator to set to
		 */
		public ElementIterator(Iterator<Position<E>> iterator) {
			it = iterator;
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public E next() {
			return it.next().getElement();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("The remove operation is not supported yet.");
		}
	}
}