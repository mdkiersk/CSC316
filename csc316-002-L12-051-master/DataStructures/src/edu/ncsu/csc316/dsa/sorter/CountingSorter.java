package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {
	
	/**
	 * Sorts an array of data using the counting sort algorithm.
	 */
	@Override
	public void sort(E[] data) {
		int min = data[0].getId();
		int max = data[0].getId();
		for (int i = 0; i <= data.length - 1; i++) {
			min = Math.min(data[i].getId(), min);
			max = Math.max(data[i].getId(), max);
		}
		int range = max - min + 1;
		
		
		int rangeArray[] = new int[range];
		for (int i = 0; i <= data.length - 1; i++) {
			rangeArray[data[i].getId() - min] = rangeArray[data[i].getId() - min] + 1;
		}
		
		for (int i = 1; i <= range - 1; i++) {
			rangeArray[i] = rangeArray[i - 1] + rangeArray[i];
		}
		
		Identifiable finalArray[] = (E[]) new Identifiable[data.length];
		for (int i = 0; i <= data.length - 1; i++) {
			finalArray[rangeArray[data[i].getId() - min] - 1] = data[i];
			rangeArray[data[i].getId() - min] = rangeArray[data[i].getId() - min] - 1;
		}
		
		for (int i = 0; i <= data.length - 1; i++) {
			data[i] = (E) finalArray[i];
		}
	}
	
}
