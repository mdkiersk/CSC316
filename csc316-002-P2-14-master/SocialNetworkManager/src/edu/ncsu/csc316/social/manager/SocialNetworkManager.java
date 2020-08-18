package edu.ncsu.csc316.social.manager;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;

import edu.ncsu.csc316.dsa.graph.Graph;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.graph.ShortestPathUtil;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.sorter.Sorter;
import edu.ncsu.csc316.social.data.Friendship;
import edu.ncsu.csc316.social.factory.DSAFactory;
import edu.ncsu.csc316.social.io.TextFileIO;

/**
 * SocialNetworkManager manages details related to social networks, including
 * calculating degrees of separation and suggesting friends.
 * 
 * @author Dr. King
 * @author Neel Patel
 * @author Matthew Kierski
 *
 */
public class SocialNetworkManager {

	/** Graph used in this class */
	public Graph<String, Friendship> graph = DSAFactory.getUndirectedGraph();

	/**
	 * Initializes the SocialNetworkManager
	 * 
	 * @param pathToFriendshipFile the path to the input friendship information
	 * @throws FileNotFoundException if the input file is not found
	 * @throws ParseException        if the input file contains an unparsable date
	 */
	public SocialNetworkManager(String pathToFriendshipFile) throws FileNotFoundException, ParseException {
		Map<String, Vertex<String>> map = DSAFactory.getMap();
		List<Friendship> list = TextFileIO.readFriendships(pathToFriendshipFile);
		for (int i = 0; i < list.size(); i++) {
			String email1 = list.get(i).getEmail1();
			String email2 = list.get(i).getEmail2();
			Date timeStamp = list.get(i).getFriendedDate();
			Vertex<String> p1 = map.get(email1);
			Vertex<String> p2 = map.get(email2);
			if (p1 == null) {
				p1 = graph.insertVertex(email1);
				map.put(email1, p1);
			}
			if (p2 == null) {
				p2 = graph.insertVertex(email2);
				map.put(email2, p2);
			}
			Edge<Friendship> edge = graph.getEdge(p1, p2);
			if (edge == null) {
				Friendship f = new Friendship(email1, email2, timeStamp);
				edge = graph.insertEdge(p1, p2, f);
			}
		}
	}

	/**
	 * Returns a report of the degrees of separation between the two provided email
	 * addresses
	 * 
	 * @param emailOne the email address of the first user
	 * @param emailTwo the email address of the second user
	 * @return the report of the degrees of separation between the two provided
	 *         email addresses
	 */
	public String getDegreesReport(String emailOne, String emailTwo) {
		if (emailOne == emailTwo) {
			return "You must enter two different email addresses.";
		}
		Iterator<Vertex<String>> it = graph.vertices().iterator();
		Vertex<String> v = null;
		boolean ma = true;
		while (it.hasNext() && ma) {
			v = it.next();
			if (v.getElement().equals(emailOne)) {
				ma = false;
				break;
			}
		}
		it = graph.vertices().iterator();
		Vertex<String> v2 = null;
		boolean mat = true;
		while (it.hasNext()) {
			v2 = it.next();
			if (v2.getElement().equals(emailTwo)) {
				mat = false;
				break;
			}
		}
		if (ma) {
			return emailOne + " does not exist in the social network.";
		}
		if (mat) {
			return emailTwo + " does not exist in the social network.";
		}
		Map<Vertex<String>, Integer> dijkstra = ShortestPathUtil.dijkstra(graph, v);
		Map<Vertex<String>, Edge<Friendship>> shortest = ShortestPathUtil.shortestPathTree(graph, v, dijkstra);

		String toRet = dijkstra.get(v2) + " degrees of separation between " + v.getElement() + " and " + v2.getElement()
				+ " [\n";
		List<String> list = DSAFactory.getIndexedList();
		toRet += "       " + v.getElement() + "\n";
		boolean notFinished = true;
		while (notFinished) {
			list.addFirst(v2.getElement());
			Edge<Friendship> e = shortest.get(v2);
			if (e == null) {
				return emailOne + " and " + emailTwo + " are not connected in the social network.";
			}
			v2 = graph.opposite(v2, e);
			if (v2.equals(v)) {
				notFinished = false;
			}
		}
		for (int i = 0; i < list.size(); i++) {
			toRet += "   --> " + list.get(i) + "\n";
		}

		toRet += "]\n";

		return toRet;
	}

	/**
	 * Returns a report of the friend suggestions for the provided email address
	 * 
	 * @param email the email address for which to provide friend suggestions
	 * @return a report of the friend suggestions for the provided email address
	 */
	public String getSuggestionReport(String email) {
		Map<Vertex<String>, Vertex<String>> suggested = DSAFactory.getMap();
		Iterator<Vertex<String>> i = graph.vertices().iterator();
		Vertex<String> v = null;
		boolean noMatch = true;
		while (i.hasNext() && noMatch) {
			v = i.next();
			if (v.getElement().equals(email)) {
				noMatch = false;
			}
		}
		if (noMatch) {
			return email + " does not exist in the social network.";
		}
		Iterator<Edge<Friendship>> j = graph.outgoingEdges(v).iterator();
		Map<Vertex<String>, Vertex<String>> friends = DSAFactory.getMap();
		while (j.hasNext()) {
			Vertex<String> toDo = graph.opposite(v, j.next());
			friends.put(toDo, toDo);
		}
		Iterator<Vertex<String>> iter = friends.iterator();
		while (iter.hasNext()) {
			Vertex<String> var = iter.next();
			Iterator<Edge<Friendship>> k = graph.outgoingEdges(var).iterator();

			while (k.hasNext()) {
				Vertex<String> newFriend = graph.opposite(var, k.next());
				if (suggested.get(newFriend) == null && friends.get(newFriend) == null
						&& !newFriend.getElement().equals(email)) {
					suggested.put(newFriend, newFriend);
				}
			}
		}
		return printSuggested(suggested, email);
	}

	/**
	 * Helper method used for printing the suggested list, uses mergesort to sort
	 * it.
	 * 
	 * @param suggested map that contains the suggested email's
	 * @param email     belongs to the user who's suggestions you are looking for
	 * @return String formatted in the correct way for suggestions
	 */
	private String printSuggested(Map<Vertex<String>, Vertex<String>> suggested, String email) {
		Iterator<Vertex<String>> other = suggested.iterator();
		String vert[] = new String[suggested.size()];
		int count = 0;
		while (other.hasNext()) {
			vert[count++] = other.next().getElement();
		}
		Sorter<String> m = DSAFactory.getComparisonSorter();
		m.sort(vert);
		String toRet = "Friend Suggestions for " + email + " [\n";
		for (int i = 0; i < vert.length; i++) {
			toRet += "   " + vert[i] + "\n";
		}
		toRet += "]\n";
		return toRet;
	}
}
