package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * Class representing the behavior and data associated with a PositionalLinkedList.
 * @author Matthew Kierski
 *
 * @param <E> generic element
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

	/** Front of list */
	private PositionalNode<E> front;
	/** Tail of list */
	private PositionalNode<E> tail;
	/** Size of list */
	private int size;

	/**
	 * Constructs a list, setting the front and tail to be null.
	 */
	public PositionalLinkedList() {
		front = new PositionalNode<E>(null);
		tail = new PositionalNode<E>(null, null, front);
		front.setNext(tail);
		size = 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator(front.getNext());
	}
	
	/**
	 * Helper method for adding between PositionalNodes
	 * @param value value to be added
	 * @param next next node
	 * @param prev previous node
	 * @return position of the new value
	 */
	private Position<E> addBetween(E value, PositionalNode<E> next, PositionalNode<E> prev) {
		PositionalNode<E> p = new PositionalNode<E>(value, next, prev);
		next.previous = p;
		prev.next = p;
		size++;
		return validate(p);
    }

	@Override
	public Position<E> addAfter(Position<E> p, E value) {
		return addBetween(value, validate(p).getNext(), validate(p));
		
	}

	@Override
	public Position<E> addBefore(Position<E> p, E value) {
		return addBetween(value, validate(p), validate(p).getPrevious());
	}

	@Override
	public Position<E> addFirst(E value) {
		return addBetween(value, front.getNext(), front);
	}

	@Override
	public Position<E> addLast(E value) {
		return addBetween(value, tail, tail.getPrevious());
	}

	@Override
	public Position<E> after(Position<E> p) {
		if (validate(p).getNext() == null) {
			throw new NoSuchElementException();
		}
		return validate(p).getNext();
	}

	@Override
	public Position<E> before(Position<E> p) {
		if (validate(p).getPrevious() == null) {
			throw new NoSuchElementException();
		}
		return validate(p).getPrevious();
	}

	@Override
	public Position<E> first() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return front.getNext();
	}

	@Override
	public Position<E> last() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return tail.getPrevious();
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public E remove(Position<E> p) {
		E removed = p.getElement();
		if (validate(p).getPrevious() == null) {
			front.setNext(validate(p).getNext());
		}
		else if (validate(p).getNext() == null) {
			tail.setPrevious(validate(p).getPrevious());
		}
		else {
			validate(p).getPrevious().setNext(validate(p).getNext());
			validate(p).getNext().setPrevious(validate(p).getPrevious());
		}
		size--;
		return removed;
	}

	@Override
	public E set(Position<E> p, E value) {
		E removed = p.getElement();
		validate(p).setElement(value);
		return removed;
	}

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

	
	/**
	 * Ensures proper casting from Position object to PositionalNode
	 * @param p position object to cast
	 * @return PositionalNode object
	 */
	private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
	
	/**
	 * A wrapper class to allow us to construct an iterable object
	 * @author Dr. King
	 *
	 */
	private class PositionIterable implements Iterable<Position<E>> {

		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionIterator(front.getNext());
		}
	}


	/**
	 * Class representing the behaviors of a PositionalNode
	 * @author Dr. King
	 *
	 * @param <E> generic element
	 */
	private static class PositionalNode<E> implements Position<E> {

		/** Element of a node */
		private E element;
		/** Next node */
		private PositionalNode<E> next;
		/** Previous node */
		private PositionalNode<E> previous;

		/**
		 * Constructs a PositionalNode with given value
		 * @param value element to instantiate
		 */
		public PositionalNode(E value) {
			this(value, null);
		}

		/**
		 * Constructs a PositionalNode before a given position
		 * @param value element data
		 * @param next next node in list
		 */
		public PositionalNode(E value, PositionalNode<E> next) {
			this(value, next, null);
		}

		/**
		 * Constructs a PositionalNode between two given positions
		 * @param value element data
		 * @param next next node in list
		 * @param prev previous node in list
		 */
		public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
			setElement(value);
			setNext(next);
			setPrevious(prev);
		}

		/**
		 * Sets previous node to given node
		 * @param prev previous node
		 */
		public void setPrevious(PositionalNode<E> prev) {
			previous = prev;
		}

		/**
		 * Returns previous node
		 * @return previous node
		 */
		public PositionalNode<E> getPrevious() {
			return previous;
		}

		public void setNext(PositionalNode<E> next) {
			this.next = next;
		}

		/** 
		 * Returns next node
		 * @return next node
		 */
		public PositionalNode<E> getNext() {
			return next;
		}

		@Override
		public E getElement() {
			return element;
		}

		/**
		 * Sets element to given element
		 * @param element element to set
		 */
		public void setElement(E element) {
			this.element = element;
		}
	}

	/**
	 * Represents iterator for traversing positions in a list
	 * @author Matthew Kierski
	 *
	 */
	private class PositionIterator implements Iterator<Position<E>> {

		/** Current position */
		private Position<E> current;
		/** True if remove() can be called, false otherwise */
		private boolean removeOK;

		/**
		 * Constructs an iterator to traverse positions
		 * @param start node to start iterator
		 */
		public PositionIterator(PositionalNode<E> start) {
			current = start;
			removeOK = false;
		}

		@Override
		public boolean hasNext() {
			return current.getElement() != null;
		}

		@Override
		public Position<E> next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Position<E> pos = current;
			current = validate(current).getNext();
			removeOK = true;
			return pos;
		}

		@Override
		public void remove() {
			if (!removeOK) {
				throw new IllegalStateException();
			}
			PositionalLinkedList.this.remove(validate(current).getPrevious());
			removeOK = false;
		}
	}
	
	/**
	 * Class representing an Element Iterator, used to traverse the elements of position nodes
	 * @author Matthew Kierski
	 *
	 */
	private class ElementIterator implements Iterator<E> {

		/** Private instance of Position iterator */
		private Iterator<Position<E>> it;

		/**
		 * Constructs an element iterator at a starting node
		 * @param start node to start at
		 */
		public ElementIterator(PositionalNode<E> start) {
			it = new PositionIterator(start);
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return it.next().getElement();
		}

		@Override
		public void remove() {
			it.remove();
		}
	}
}