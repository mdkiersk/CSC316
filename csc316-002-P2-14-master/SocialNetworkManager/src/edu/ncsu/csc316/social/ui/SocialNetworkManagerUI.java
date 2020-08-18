package edu.ncsu.csc316.social.ui;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

import edu.ncsu.csc316.social.manager.SocialNetworkManager;

/**
 * Represents the user interface for SocialNetworkManager
 * @author Matthew Kierksi
 * @author Neel Patel
 *
 */
public class SocialNetworkManagerUI {
	
	/** Instance of manager */
	private static SocialNetworkManager manager;

	/**
	 * Main function that prompts the user for input and creates a new instance of SocialNetworkManager
	 * based on this input
	 * @param args command line arguments
	 */
	public static void main(String args[]) {
		System.out.println("Welcome to SocialNetworkManager.");
		System.out.print("Please enter the input file containing the list of friends: ");
		Scanner scanner = new Scanner(System.in);
		String filename = scanner.next();
		boolean invalid = true;
		while (invalid) {
			invalid = false;
			try {
				manager = new SocialNetworkManager(filename);
			} catch (FileNotFoundException e) {
				System.out.print("The filename was invalid. Please enter a different one: ");
				invalid = true;
				filename = scanner.next();
			} catch (ParseException e) {
				System.out.print("The filename contained an invalid date. Please enter a different one: ");
				invalid = true;
				filename = scanner.next();
			}
		}
		System.out.println("If you would like generate a list of suggested friends for a user, enter 'S'.\nIf you would like"
				+ "to look at the degrees of separation between two users, enter 'D'.");
		String option = scanner.next();
		if (option.toUpperCase().equals("S")) {
			System.out.println("Please enter the email of a user to find their suggested friends: ");
			String email = scanner.next();
			System.out.println(manager.getSuggestionReport(email));
		} else if (option.toUpperCase().equals("D")) {
			System.out.println("Please enter the email of the first user: ");
			String email1 = scanner.next();
			System.out.println("Please enter the email of the second user: ");
			String email2 = scanner.next();
			System.out.println(manager.getDegreesReport(email1, email2));
		}
		scanner.close();
	}
}
