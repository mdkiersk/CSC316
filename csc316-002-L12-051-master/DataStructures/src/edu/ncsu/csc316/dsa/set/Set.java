package edu.ncsu.csc316.dsa.set;

/**
 * Serves as the interface for all Set implementations
 * @author Matthew Kierski
 *
 * @param <E> generic element of set
 */
public interface Set<E> extends Iterable<E> {
	/**
	 * Adds a given value to the set
	 * @param value value to add
	 */
	void add(E value);

	/**
	 * Returns true if given value is in set, false otherwise
	 * @param value value to evaluate
	 * @return true if given value is in set, false otherwise
	 */
	boolean contains(E value);

	/**
	 * Removes the given value from the set
	 * @param value value to remove
	 * @return the value removed
	 */
	E remove(E value);

	/**
	 * Returns true if the set is empty, false otherwise
	 * @return true if the set is empty, false otherwise
	 */
	boolean isEmpty();

	/**
	 * Returns the size of the set
	 * @return the size of the set
	 */
	int size();

	/**
	 * Updates the current set to include all elements of given set
	 * @param t set to add to current set
	 */
	void addAll(Set<E> t);

	/**
	 * Updates the current set to only include those elements also contained in
	 * given set
	 * @param t set to retain 
	 */
	void retainAll(Set<E> t);

	/**
	 * Updates the current set to remove any elements contained in given set
	 * @param t set to remove all
	 */
	void removeAll(Set<E> t);
}