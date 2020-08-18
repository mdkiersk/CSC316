package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A Linked List implementation of our ADT. Singly linked to include references to the front and tail. 
 * @author Matthew Kierski
 *
 * @param <E> generic element of list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

	/** LinkedListNode representing front of list */
	private LinkedListNode<E> front;
	
	/** LinkedListNode representing back of list */
	private LinkedListNode<E> tail;

	/** Size of list */
	private int size;

	/**
	 * Constructs a SinglyLinkedList, setting the head and tail to null and size to 0
	 */
	public SinglyLinkedList() {
		front = new LinkedListNode<E>(null);
		tail = null;
		size = 0;
	}

	@Override
	public void add(int index, E value) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (size == 0) {
			front.next = new LinkedListNode<E>(value, front.next);
			tail = front.next;
		}
		else if (index == 0) {
			front.next = new LinkedListNode<E>(value, front.next);
		}
		else if (index == size) {
			tail.next = new LinkedListNode<E>(value, tail.next);
			tail = tail.next;
		}
		else {
			LinkedListNode<E> current = front.next;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			current.next = new LinkedListNode<E>(value, current.next);
		}
		size++;
	}
	
	@Override
	public void addLast(E value) {
		if (size == 0) {
			front.next = new LinkedListNode<E>(value, front.next);
			tail = front.next;
			size++;
		}
		else {
			tail.setNext(new LinkedListNode<E>(value));
			tail = tail.next;
			size++;
		}
	}

	@Override
	public E get(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			return front.next.getElement();
		}
		else if (index == size - 1) {
			return tail.getElement();
		}
		else {
			LinkedListNode<E> current = front.next;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			return current.getElement();
		}
	}
	
	@Override
	public E last() {
		return tail.getElement();
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index > size || size == 0) {
			throw new IndexOutOfBoundsException();
		}
		E removed = null;
		if (index == 0) {
			removed = front.next.data;
			front.next = front.next.next;
		}
		else if (index == size - 1) {
			LinkedListNode<E> current = front.next;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			tail = current;
			removed = current.next.getElement();
			current.next = current.next.next;
		}
		else {
			LinkedListNode<E> current = front.next;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			removed = current.next.getElement();
			current.next = current.next.next;
		}
		size--;
		return removed;
	}

	@Override
	public E set(int index, E value) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		E replaced = null;
		if (index == 0) {
			replaced = front.next.getElement();
			front.next.setElement(value);
		}
		else if (index == size - 1) {
			replaced = tail.getElement();
			tail.setElement(value);
		}
		else {
			LinkedListNode<E> current = front.next;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			replaced = current.next.getElement();
			current.next.setElement(value);
		}
		return replaced;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
	    return new ElementIterator(front.getNext());
	}
	
	/**
	 * Class representing a node in the SinglyLinkedList.
	 * 
	 * @author Matthew Kierski
	 *
	 * @param <E> generic element of list
	 */
	private static class LinkedListNode<E> {
		/** Instance of data */
		private E data;
		/** Instance of next node */
		private LinkedListNode<E> next;
		
		/**
		 * Constructs a LinkedListNode with data passed
		 * @param data to be passed
		 */
		public LinkedListNode(E data) {
			this(data, null);
		}
		
		/**
		 * Constructs a LinkedListNode with reference to the next node
		 * @param data data to be passed
		 * @param linkedListNode next LinkedListNode
		 */
		public LinkedListNode(E data, LinkedListNode<E> linkedListNode) {
			this.data = data;
			this.next = linkedListNode;
		}
		
		/**
		 * Returns the next node
		 * @return the next node
		 */
		public LinkedListNode<E> getNext() {
			return next;
		}
		
		/**
		 * Returns data on current node
		 * @return data on current node
		 */
		public E getElement() {
			return data;
		}
		
		/**
		 * Sets next to be given node
		 * @param linkedListNode given node to set
		 */
		public void setNext(LinkedListNode<E> linkedListNode) {
			next = linkedListNode;
		}
		
		/**
		 * Sets current data to be given data
		 * @param data data to be set
		 */
		public void setElement(E data) {
			this.data = data;
		}
	}
	
	/**
	 * Inner class of SinglyLinkedList that serves as its iterator. 
	 * @author Matthew Kierski
	 *
	 */
	private class ElementIterator implements Iterator<E> {
	    // Keep track of the next node that will be processed
	    private LinkedListNode<E> current;
	    // Keep track of the node that was processed on the last call to 'next'
	    private LinkedListNode<E> previous;
	    // Keep track of the previous-previous node that was processed
	    // so that we can update 'next' links when removing
	    private LinkedListNode<E> previousPrevious;
	    //Whether or not remove is a valid operation
	    private boolean removeOK;

	    /**
	     * Constructs the ElementIterator, setting current to passed node
	     * @param start start of list
	     */
	    public ElementIterator(LinkedListNode<E> start) {
	       current = start;
	       previous = null;
	       previousPrevious = null;
	       removeOK = false;
	    }

	    /**
	     * Returns true if list has next, false otherwise
	     */
	    public boolean hasNext() {
	        return current != null;
	    }

	    /**
	     * Traverses to the next node and returns the data processed
	     */
	    public E next() {
	    	if (!hasNext()) {
	    		throw new NoSuchElementException();
	    	}
	        previousPrevious = previous;
	        previous = current;
	        current = current.next;
	        removeOK = true;
	        return previous.getElement();
	    }
	    
	    /**
	     * Removes the current node
	     */
	    public void remove() {
	        if (!removeOK) {
	        	throw new IllegalStateException();
	        }
	        previousPrevious.next = previousPrevious.next.next; 
	        removeOK = false;
	        size--;
	    }
	}
}