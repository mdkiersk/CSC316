package edu.ncsu.csc316.dsa.queue;

/**
 * Serves as the abstract class for all implementations of a queue
 * @author Matthew Kierski
 *
 * @param <E> generic element in a queue
 */
public abstract class AbstractQueue<E> implements Queue<E> {

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
}