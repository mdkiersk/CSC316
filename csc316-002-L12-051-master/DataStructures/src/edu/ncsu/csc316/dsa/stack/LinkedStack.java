package edu.ncsu.csc316.dsa.stack;

import java.util.EmptyStackException;

import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * A SinglyLinkedList implementation of a stack
 * @author Matthew Kierski
 *
 * @param <E> generic element of stack
 */
public class LinkedStack<E> extends AbstractStack<E> {
	
	// Private instance of SinglyLinkedList
	private SinglyLinkedList<E> list;
	
	/**
	 * Constructs a new stack using a SinglyLinkedList
	 */
	public LinkedStack()
	{
		list = new SinglyLinkedList<E>();
	}

	@Override
	public void push(E value) {
		list.addFirst(value);
	}

	@Override
	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return list.removeFirst();
	}

	@Override
	public E top() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return list.first();
	}

	@Override
	public int size() {
		return list.size();
	}	
}