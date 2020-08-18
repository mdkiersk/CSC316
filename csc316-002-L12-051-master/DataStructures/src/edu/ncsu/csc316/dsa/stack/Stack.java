package edu.ncsu.csc316.dsa.stack;

/**
 * Serves as the Interface for all Stack implementations
 * @author Matthew Kierski
 *
 * @param <E> generic element of stack 
 */
public interface Stack<E> {

	/**
	 * Adds an element to the top of the stack
	 * @param value value to be added
	 */
	void push(E value);
	/**
	 * Removes and returns the top element in the stack
	 * @return the element that was removed
	 */
	E pop();
	/**
	 * Returns the element at the top of the stack
	 * @return the element at the top of the stack
	 */
	E top();
	/**
	 * Returns the size of the stack
	 * @return size of the stack
	 */
	int size();
	/**
	 * Returns true if stack is empty, false otherwise
	 * @return true if stack is empty, false otherwise
	 */
	boolean isEmpty();
}