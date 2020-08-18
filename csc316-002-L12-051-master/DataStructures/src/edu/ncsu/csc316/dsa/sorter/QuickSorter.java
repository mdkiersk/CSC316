package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
import java.util.Random;

/**
 * Represents the implementation of the quick sorter algorithm for sorting
 * @author Matthew Kierski
 *
 * @param <E> generic element of array
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/** Instance of the selector that uses the first element as a pivot */
	public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
	/** Instance of the selector that uses the last element as a pivot */
	public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
	/** Instance of the selector that uses the middle element as a pivot */
	public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
	/** Instance of the selector that uses a random element as a pivot */
	public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();

	// Private instance of whichever selector is chosen 
	private PivotSelector selector;

	/**
	 * Constructs the QuickSorter with a custom comparator and selector
	 * @param comparator comparator to be used
	 * @param selector selector to be used
	 */
	public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
		super(comparator);
		setSelector(selector);
	}

	/**
	 * Constructs the QuickSorter with a custom comparator, setting selector to null
	 * @param comparator comparator to be used
	 */
	public QuickSorter(Comparator<E> comparator) {
		this(comparator, null);
	}

	/**
	 * Constructs the QuickSorter with a custom selector, setting comparator to null
	 * @param selector selector to be used
	 */
	public QuickSorter(PivotSelector selector) {
		this(null, selector);
	}

	/**
	 * Constructs a default QuickSorter, setting both comparator and selector to null
	 */
	public QuickSorter() {
		this(null, null);
	}

	/**
	 * Sets the selector for pivot index
	 * @param selector selector to be used
	 */
	private void setSelector(PivotSelector selector) {
		if (selector == null) {
			selector = new RandomElementSelector();
		}
		this.selector = selector;
	}

	@Override
	public void sort(E[] data) {
		quickSort(data, 0, data.length - 1);

	}
	
	/**
	 * Sorts the given array using the indexes specified
	 * @param data data to sort
	 * @param low lowest index to sort
	 * @param high highest index to sort
	 */
	private void quickSort(E[] data, int low, int high) {
		if (low < high) {
			int pivotLocation = partition(data, low, high);
			quickSort(data, low, pivotLocation - 1);
			quickSort(data, pivotLocation + 1, high);
		}
	}
	
	/**
	 * Returns the index of the pivot element after moving values less than the pivot
	 * before the pivot and values greater than the pivot after the pivot
	 * @param data array to be sorted
	 * @param low lowest index being sorted 
	 * @param high highest index being sorted
	 * @return index of the pivot element
	 */
	private int partition(E[] data, int low, int high) {
		int pivotIndex = selector.selectPivot(low, high);
		swap(data, pivotIndex, high);
		return partitionHelper(data, low, high);
	}
	
	/**
	 * Returns the index of the pivot element after moving values less than the pivot
	 * before the pivot and values greater than the pivot after the pivot
	 * @param data array to be sorted
	 * @param low lowest index being sorted 
	 * @param high highest index being sorted
	 * @return index of the pivot element
	 */
	private int partitionHelper(E[] data, int low, int high) {
		E pivot = data[high];
		int index = low;
		for (int j = low; j <= high - 1; j++) {
			if (super.compare(data[j], pivot) <= 0) {
				swap(data, index, j);
				index++;
			}
		}
		swap(data, index, high);
		return index;
	}
	
	/**
	 * Helper method responsible for swapping to indexes of an array
	 * @param data array for swap to occur in
	 * @param low the lower index to be swapped 
	 * @param high the higher index to be swapped
	 */
	private void swap (E[] data, int low, int high) {
		E swap = data[high];
		data[high] = data[low];
		data[low] = swap;
	}
	
	/**
	 * Serves as the interface for all pivot selectors
	 * @author Matthew Kierski
	 *
	 */
	private interface PivotSelector {
		/**
		 * Returns the index of the selected pivot element
		 * 
		 * @param low  - the lowest index to consider
		 * @param high - the highest index to consider
		 * @return the index of the selected pivot element
		 */
		int selectPivot(int low, int high);
	}
	
	/**
	 * Represents a Selector that chooses the first element as the pivot
	 * @author Matthew Kierski
	 *
	 */
	public static class FirstElementSelector implements PivotSelector {
		
		@Override
		public int selectPivot(int low, int high) {
			return low; 
		}
	}
	
	/**
	 * Represents a Selector that chooses the middle element as the pivot
	 * @author Matthew Kierski
	 *
	 */
	public static class MiddleElementSelector implements PivotSelector {
		
		@Override
		public int selectPivot(int low, int high) {
			return (high + low) / 2; 
		}	
	}
	
	/**
	 * Represents a Selector that chooses the last element as the pivot
	 * @author Matthew Kierski
	 *
	 */
	public static class LastElementSelector implements PivotSelector {
		
		@Override
		public int selectPivot(int low, int high) {
			return high; 
		}
	}
	
	/**
	 * Represents a Selector that chooses a random element as the pivot
	 * @author Matthew Kierski
	 *
	 */
	public static class RandomElementSelector implements PivotSelector {
		
		@Override
		public int selectPivot(int low, int high) {
			Random rand = new Random();
			return rand.nextInt((high - low) + 1) + low;
		}
	}
}