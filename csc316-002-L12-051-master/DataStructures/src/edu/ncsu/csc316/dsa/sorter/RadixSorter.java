package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {

	/**
	 * Sorts an array of data using radix sort
	 */
	@Override
	public void sort(E[] data) {
		int largestValue = 0;
		for (int i = 0; i  <= data.length - 1; i++) {
			largestValue = Math.max(largestValue, data[i].getId());
		}
		//Determine how many digits are in the last value
		double numOfDigits = Math.ceil( Math.log(largestValue + 1) / Math.log(10));
		
		int place = 1;
		for (int j = 1; j <= numOfDigits; j++) {
			int newArray[] = new int[10];
			for (int i = 0; i <= data.length - 1; i++) {
				newArray[data[i].getId() / place % 10] = newArray[data[i].getId() / place % 10] + 1;
			}
			
			for (int i = 1; i <= 9; i++) {
				newArray[i] = newArray[i - 1] + newArray[i];
			}
			
			Identifiable finalArray[] = (E[]) new Identifiable[data.length];
			for (int i = data.length - 1; i >= 0; i--) {
				finalArray[newArray[data[i].getId() / place % 10] - 1] = data[i];
				newArray[data[i].getId() / place % 10] = newArray[data[i].getId() / place % 10] - 1;
			}
			
			for (int i = 0; i <= data.length - 1; i++) {
				data[i] = (E) finalArray[i];
			}
			
			place = place * 10;
		}
	}
}

