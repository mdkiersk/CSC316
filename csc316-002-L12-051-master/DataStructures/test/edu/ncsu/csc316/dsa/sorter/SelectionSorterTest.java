package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Tests the BubbleSorter class
 * @author Dr. King & Matthew Kierski
 *
 */
public class SelectionSorterTest {
	/** Instance of ascending integer data */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/** Instance of descending integer data */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/** Instance of random data */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	/** Instance of data with repeated values */
	private Integer[] dataRepeated = { 4, 1, 2, 3, 1 };
	

	/** Instance of student one */
	private Student sOne;
	/** Instance of student two */
	private Student sTwo;
	/** Instance of student three */
	private Student sThree;
	/** Instance of student four */
	private Student sFour;
	/** Instance of student five */
	private Student sFive;

	/** Instance of AbstractComparisonSorter */
	private AbstractComparisonSorter<Integer> integerSorter;
	
	/** Instance of AbstractComparisonSorter to sort Students */
	private AbstractComparisonSorter<Student> studentSorter; 

	/**
	 * Constructs integerSorter as a BubbleSorter object
	 */
	@Before
	public void setUp() {
		integerSorter = new SelectionSorter<Integer>();
	}

	/**
	 * Tests sort() method
	 */
	@Test
	public void testsort() {
		integerSorter.sort(dataAscending);
		assertEquals(Integer.valueOf(1), dataAscending[0]);
		assertEquals(Integer.valueOf(2), dataAscending[1]);
		assertEquals(Integer.valueOf(3), dataAscending[2]);
		assertEquals(Integer.valueOf(4), dataAscending[3]);
		assertEquals(Integer.valueOf(5), dataAscending[4]);

		integerSorter.sort(dataDescending);
		assertEquals(Integer.valueOf(1), dataDescending[0]);
		assertEquals(Integer.valueOf(2), dataDescending[1]);
		assertEquals(Integer.valueOf(3), dataDescending[2]);
		assertEquals(Integer.valueOf(4), dataDescending[3]);
		assertEquals(Integer.valueOf(5), dataDescending[4]);

		integerSorter.sort(dataRandom);
		assertEquals(Integer.valueOf(1), dataRandom[0]);
		assertEquals(Integer.valueOf(2), dataRandom[1]);
		assertEquals(Integer.valueOf(3), dataRandom[2]);
		assertEquals(Integer.valueOf(4), dataRandom[3]);
		assertEquals(Integer.valueOf(5), dataRandom[4]);
		
		integerSorter.sort(dataRepeated);
		assertEquals(Integer.valueOf(1), dataRepeated[0]);
		assertEquals(Integer.valueOf(1), dataRepeated[1]);
		assertEquals(Integer.valueOf(2), dataRepeated[2]);
		assertEquals(Integer.valueOf(3), dataRepeated[3]);
		assertEquals(Integer.valueOf(4), dataRepeated[4]);
	}
	
	/**
	 * Tests sorting Students
	 */
	@Test
	public void testSortStudent() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		Comparator<Student> studentGPAComparator = new StudentGPAComparator();
		studentSorter = new InsertionSorter<Student>(studentGPAComparator);
		
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		studentSorter.sort(original);
		assertEquals(sFive, original[0]);
		assertEquals(sFour, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sTwo, original[3]);
		assertEquals(sOne, original[4]);
		
		Comparator<Student> studentIDComparator = new StudentIDComparator();
		studentSorter = new InsertionSorter<Student>(studentIDComparator);
		Student[] newArray = { sTwo, sOne, sFour, sThree, sFive };
		studentSorter.sort(newArray);
		assertEquals(sOne, newArray[0]);
		assertEquals(sTwo, newArray[1]);
		assertEquals(sThree, newArray[2]);
		assertEquals(sFour, newArray[3]);
		assertEquals(sFive, newArray[4]);

	}

}
