package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the CountingSorter class
 * @author Dr. King & Matthew Kierski
 *
 */
public class RadixSorterTest {
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
	
	/** Instance of CountingSorter */
	private RadixSorter<Student> sorter;

	/**
	 * Constructs each Student to be used in tests
	 * Sets sorter to new CountingSorter
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		sorter = new RadixSorter<Student>();
	}
	
	/**
	 * Tests sortStudent() method
	 */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
		
		Student[] inOrder = { sOne, sTwo, sThree, sFour, sFive };
		sorter.sort(inOrder);
		assertEquals(sOne, inOrder[0]);
		assertEquals(sTwo, inOrder[1]);
		assertEquals(sThree, inOrder[2]);
		assertEquals(sFour, inOrder[3]);
		assertEquals(sFive, inOrder[4]);
		
		Student[] reverseOrder = { sFive, sFour, sThree, sTwo, sOne };
		sorter.sort(reverseOrder);
		assertEquals(sOne, reverseOrder[0]);
		assertEquals(sTwo, reverseOrder[1]);
		assertEquals(sThree, reverseOrder[2]);
		assertEquals(sFour, reverseOrder[3]);
		assertEquals(sFive, reverseOrder[4]);
	}
}