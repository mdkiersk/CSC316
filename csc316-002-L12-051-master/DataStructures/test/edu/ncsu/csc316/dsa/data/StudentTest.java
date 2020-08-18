package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Student class
 * @author Dr. King & Matthew Kierski
 *
 */
public class StudentTest {

	/** Instance of student one */
	private Student sOne;
	/** Instance of student two */
	private Student sTwo;
	/** Instance of student three */
	private Student sThree;
	/** Instance of student four */
	private Student sFour;

	/**
	 * Constructs all student objects to be used in tests.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sFour = new Student("OneFirst", "OneLast", 2, 1, 1.0, "sevenUnityID");
		
	}

	/**
	 * Tests the setFirst() method.
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Tests the setLast() method.
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * Tests the setId() method.
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * Tests the setGpa() method.
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * Tests the setUnityID() method
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityId("oneUnity");
		assertEquals("oneUnity", sOne.getUnityId());
	}

	/**
	 * Tests compareTo() method
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sOne.compareTo(sOne) == 0);
		assertTrue(sTwo.compareTo(sTwo) == 0);
	}
	
	/**
	 * Tests the equals() method
	 */
	@Test
	public void testEquals() {
		assertTrue(sOne.equals(sThree));
		assertFalse(sOne.equals(sTwo));
		assertFalse(sOne.equals(sFour));
	}
	
	/**
	 * Tests the toString() method
	 */
	@Test
	public void testToString() {
		assertEquals("OneFirst OneLast, 1", sOne.toString());
		assertEquals("TwoFirst TwoLast, 2", sTwo.toString());
		assertEquals("OneFirst OneLast, 2", sFour.toString());
	}
	
	/**
	 * Tests hashCode() method
	 */
	@Test
	public void testHashCode() {
		Student s1 = new Student("first", "last", 1, 1, 1, "flast");
		Student s2 = new Student("first", "last", 1, 1, 1, "flast");
		
		assertEquals(s1.hashCode(), s2.hashCode());
		s2.setFirst("second");
		assertNotEquals(s1.hashCode(), s2.hashCode());
	}
}
