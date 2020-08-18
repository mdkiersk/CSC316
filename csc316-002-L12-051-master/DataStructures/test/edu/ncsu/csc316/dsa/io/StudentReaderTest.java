package edu.ncsu.csc316.dsa.io;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the StudentReader class.
 * @author Dr. King & Matthew Kierski
 *
 */
public class StudentReaderTest {
	
	/**
	 * Tests readFile() method
	 */
	@Test
	public void testReadFile() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals("Amber", contents[0].getFirst());
		assertEquals("Ara", contents[1].getFirst());
		assertEquals("Lacie", contents[2].getFirst());
		assertEquals("Idalia", contents[3].getFirst());
		assertEquals("Evelin", contents[4].getFirst());
		assertEquals("Lewis", contents[5].getFirst());
		assertEquals("Alicia", contents[6].getFirst());
		assertEquals("Tyree", contents[7].getFirst());
		assertEquals("Loise", contents[8].getFirst());
		assertEquals("Roxann", contents[9].getFirst());
		assertEquals("Nichole", contents[10].getFirst());
		assertEquals("Charlene", contents[11].getFirst());
		assertEquals("Shanti", contents[12].getFirst());
		assertEquals("Cristine", contents[13].getFirst());
		assertEquals("Tanner", contents[14].getFirst());
		assertEquals("Dante", contents[15].getFirst());
	
		Student[] contents1 = StudentReader.readInputAsArray("input/student_descendingID.csv");
		assertEquals("Dante", contents1[0].getFirst());
		assertEquals("Tanner", contents1[1].getFirst());
		assertEquals("Cristine", contents1[2].getFirst());
		assertEquals("Shanti", contents1[3].getFirst());
		assertEquals("Charlene", contents1[4].getFirst());
		assertEquals("Nichole", contents1[5].getFirst());
		assertEquals("Roxann", contents1[6].getFirst());
		assertEquals("Loise", contents1[7].getFirst());
		assertEquals("Tyree", contents1[8].getFirst());
		assertEquals("Alicia", contents1[9].getFirst());
		assertEquals("Lewis", contents1[10].getFirst());
		assertEquals("Evelin", contents1[11].getFirst());
		assertEquals("Idalia", contents1[12].getFirst());
		assertEquals("Lacie", contents1[13].getFirst());
		assertEquals("Ara", contents1[14].getFirst());
		assertEquals("Amber", contents1[15].getFirst());
	}
}
