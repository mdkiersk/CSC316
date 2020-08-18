package edu.ncsu.csc316.social.manager;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Vertex;

/**
 * Tests SocialNetworkManager
 * 
 * @author neelpatel
 *
 */
public class SocialNetworkManagerTest {

	private SocialNetworkManager manager;

	/**
	 * Set-up
	 * 
	 * @throws Exception FileNotFoundException thrown
	 */
	@Before
	public void setUp() throws Exception {
		manager = new SocialNetworkManager("input/sample.csv");
	}

	/**
	 * Tests the SocialNetworkManager() method
	 */
	@Test
	public void testSocialNetworkManager() {
		assertEquals(8, manager.graph.numVertices());
		assertEquals(11, manager.graph.numEdges());

		// Test to ensure correct data in vertices
		Iterator<Vertex<String>> it = manager.graph.vertices().iterator();
		assertTrue(it.hasNext());
		assertEquals("jason@email.com", it.next().getElement());
		assertEquals("jessica@email.com", it.next().getElement());
		assertEquals("shawnique@email.com", it.next().getElement());
		assertEquals("jamie@email.com", it.next().getElement());
		assertEquals("david@email.com", it.next().getElement());
		assertEquals("dustin@email.com", it.next().getElement());
		assertEquals("akond@email.com", it.next().getElement());
		assertEquals("maria@email.com", it.next().getElement());
		assertFalse(it.hasNext());
	}

	/**
	 * Tests the getSuggested method
	 * 
	 * @throws FileNotFoundException if the file is not found
	 * @throws ParseException        if the date cannot be parsed
	 */
	@Test
	public void testGetSuggested() throws FileNotFoundException, ParseException {
		String result = manager.getSuggestionReport("jason@email.com");
		assertEquals("Friend Suggestions for jason@email.com [\n" + "   akond@email.com\n" + "   david@email.com\n"
				+ "   dustin@email.com\n" + "]\n", result);
	}

	/**
	 * Tests the getDegree method
	 */
	@Test
	public void testGetDegrees() {
		String result = manager.getDegreesReport("jamie@email.com", "dustin@email.com");
		assertEquals("3 degrees of separation between jamie@email.com and dustin@email.com [\n"
				+ "       jamie@email.com\n" + "   --> jason@email.com\n" + "   --> shawnique@email.com\n"
				+ "   --> dustin@email.com\n" + "]\n", result);
	}
}
