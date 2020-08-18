package edu.ncsu.csc316.dsa.queue;

/**
 * Serves as the interface for all Queue implementations
 * @author Matthew Kierski
 *
 * @param <E> generic element in queue
 */
public interface Queue<E> {

	/**
	 * Adds an element to the back of the list
	 * @param value value to be added
	 */
	void enqueue(E value);
	
	/**
	 * Removes and returns the element at the front of the list
	 * @return the element removed
	 */
	E dequeue();
	
	/**
	 * Returns the element next to be removed (front of the list)
	 * @return the element at the front of the list
	 */
	E front();
	
	/**
	 * Returns the size of the queue
	 * @return the size of the queue
	 */
	int size();
	
	/** 
	 * Returns true if queue is empty, false otherwise
	 * @return true if queue is empty, false otherwise
	 */
	boolean isEmpty();
}