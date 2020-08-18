package edu.ncsu.csc316.dsa.stack;

/**
 * Serves as the abstract class for all stack implementations
 * @author Matthew Kierski
 *
 * @param <E> generic element in stack
 */
public abstract class AbstractStack<E> implements Stack<E> {
	
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
}