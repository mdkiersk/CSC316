package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * 
 * @param <E> generic element to be used
 */
public interface Sorter<E> {
	
	/**
	 * Accepts an array of values to sort and sorts them
	 * @param data array of values to sort
	 */
	public abstract void sort(E[] data);
	
}
