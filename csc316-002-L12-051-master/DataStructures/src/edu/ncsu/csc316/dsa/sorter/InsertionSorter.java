package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * 
 * @param <E> generic element to be used
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	 

	/**
	 * Default constructor. Sets comparator to null.
	 */
	public InsertionSorter() {
		super(null);
	}

	/**
	 * Constructs a sorter with custom comparator
	 * @param comparator comparator to be used
	 */ 
	public InsertionSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * Sorts an array of data using insertion sort
	 */
	@Override
	public void sort(E[] array) {
		for (int i = 1; i <= array.length - 1; i++) {
			E x = array[i];
			int j = i - 1;
			while (j >= 0 && super.compare(array[j], x) > 0) {
				array[j + 1] = array[j];
				j = j - 1;
			}
			array[j + 1] = x;
		}
	}
}
