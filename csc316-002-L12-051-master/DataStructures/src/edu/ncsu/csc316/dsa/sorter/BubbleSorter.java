package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Represents algorithm for bubble sorting
 * @author Matthew Kierski
 *
 * @param <E> generic elements
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Default constructor. Sets comparator to null.
	 */
	public BubbleSorter() {
		super(null);
	}
	
	/**
	 * Custom constructor in which comparator is that which is passed
	 * @param comparator comparator to be used
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}

	
	/**
	 * Sorts an array of data using the bubble sort algorithm.
	 */
	@Override
	public void sort(E[] data) {
		boolean swap = true;
		while (swap) {
			swap = false;
			for (int i = 1; i <= data.length - 1; i++) {
				if (super.compare(data[i], data[i - 1]) < 0) {
					E x = data[i - 1];
					data[i - 1] = data[i];
					data[i] = x;
					swap = true;
				}
			}
		}
	}
}
