package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Represents the class for sorting using the merge sorter algorithm
 * @author Matthew Kierski
 *
 * @param <E> generic element of array
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Constructs a MergeSorter using a custom comparator
	 * @param comparator comparator to be used
	 */
	public MergeSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * Constructs a default MergeSorter, setting comparator to null
	 */
	public MergeSorter() {
		this(null);
	}

	@Override
	public void sort(E[] data) {
		if (data.length < 2) {
			return;
		}
		
		int mid = data.length / 2;
		
		E[] left = Arrays.copyOfRange(data, 0, mid);
		E[] right = Arrays.copyOfRange(data, mid, data.length);
		
		sort(left);
		sort(right);
		
		merge(left, right, data);
		
	}
	
	/**
	 * Merges the left and right arrays into a sorted array
	 * @param left array containing sorted left half
	 * @param right array containing sorted right half
	 * @param data original array of elements
	 */
	private void merge(E[] left, E[] right, E[] data) {
		int leftIndex = 0;
		int rightIndex = 0;
		while (leftIndex + rightIndex < data.length) {
			if (rightIndex == right.length || (leftIndex < left.length && super.compare(left[leftIndex], right[rightIndex]) < 0)) {
				data[leftIndex + rightIndex] = left[leftIndex];
				leftIndex++;
			}
			else {
				data[leftIndex + rightIndex] = right[rightIndex];
				rightIndex++;
			}
		}
	}

}