package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the StudentGPAComparator class
 * @author Dr. King & Matthew Kierski
 *
 */
public class StudentGPAComparatorTest {

	/** Private instance of student one */
	private Student sOne;
	/** Private instance of student two */
	private Student sTwo;
	/** Private instance of student three */
	private Student sThree;
	/** Private instance of student four */
	private Student sFour;
	/** Private instance of student five */
	private Student sFive;

	/** Instance of StudentGPAComparator */
	private StudentGPAComparator comparator;

	/**
	 * Constructs each student object to be used in tests.
	 * Sets comparator to new StudentGPAComparator.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentGPAComparator();
	}

	/**
	 * Tests the compare method.
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) > 0);
		assertFalse(comparator.compare(sTwo, sOne) > 0);
		
		assertTrue(comparator.compare(sOne, sThree) > 0);
		assertFalse(comparator.compare(sThree, sOne) > 0);
		
		assertTrue(comparator.compare(sOne, sFour) > 0);
		assertFalse(comparator.compare(sFour, sOne) > 0);
		
		assertTrue(comparator.compare(sOne, sFive) > 0);
		assertFalse(comparator.compare(sFive, sOne) > 0);
		
		
		assertTrue(comparator.compare(sOne, sOne) == 0);
	}

}
