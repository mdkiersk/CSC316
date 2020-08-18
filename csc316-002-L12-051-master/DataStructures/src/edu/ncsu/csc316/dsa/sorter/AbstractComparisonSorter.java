package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Holds common functionality between all comparison-based sorting classes.
 * Pulled from directions provided on Github. 
 * @author Dr. King
 *
 * @param <E> generic element
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * Instance of custom comparator to be used
	 */
	private Comparator<E> comparator;

	/**
	 * Constructs any given comparison sorter, setting the comparator.
	 * @param comparator custom comparator given
	 */
	public AbstractComparisonSorter(Comparator<E> comparator) {
		setComparator(comparator);
	}

	/**
	 * Sets this given comparator. If null, follows natural ordering.
	 * @param comparator comparator to set
	 */
	private void setComparator(Comparator<E> comparator) {
		if (comparator == null) {
			comparator = new NaturalOrder();
		}
		this.comparator = comparator;
	}

	/**
	 * Private class that controls default means of sorting
	 * @author Dr. King
	 *
	 */
	private class NaturalOrder implements Comparator<E> {
		public int compare(E first, E second) {
			return ((Comparable<E>) first).compareTo(second);
		}
	}

	/**
	 * Compares two pieces of data
	 * @param data1 data to be compared
	 * @param data2 data to be compared
	 * @return If data1 < data2, return -1
	 * 		   If data1 > data2, return 1
	 * 		   If data1 = data 2, return 0
	 */
	public int compare(E data1, E data2) {
		return comparator.compare(data1, data2);
	}
}