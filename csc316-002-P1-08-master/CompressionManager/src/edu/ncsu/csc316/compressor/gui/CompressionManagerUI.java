package edu.ncsu.csc316.compressor.gui;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.compressor.manager.CompressionManager;

/**
 * Serves as the user interface for CompressionManager. 
 * @author Matthew Kierski
 * @author Caleb Wiebe
 *
 */
public class CompressionManagerUI {
	
	/** Instance of CompressionManager */
	private static CompressionManager manager = new CompressionManager();

	/**
	 * Constructs a CompressionManagerUI, setting manager to new Compression Manager
	 */
	public CompressionManagerUI() {
		manager = new CompressionManager();
	}
	
	/**
	 * Serves as the starting point of our program. Handles all writing and reading to the terminal and calls
	 * appropriate methods in CompressionManager.
	 * @param args command line arguments
	 */
	public static void main(String args[]) {
		System.out.println("Welcome to Compression Manager.");
		System.out.println("Users have the option of compressing and decompressing a text file, or having the most frequent words"
				+ " in the text file reported.");
		System.out.println("If you would like to compress a file, enter 'C.'\nIf you would like to decompress a file, enter 'D.'\n"
				+ "If you would like to print the most frequent words in a file, enter 'F.'\n");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		if (input.toUpperCase().equals("D") || input.toUpperCase().equals("C")) {
			System.out.println("Please enter the file path of the text file to be processed: ");
			String inputFilePath = scanner.next();
			if (!(inputFilePath.contains("/"))) {
				scanner.close();
				throw new IllegalArgumentException("Illegal file path (must contain directory).");
			}
			System.out.println("Please enter the target directory for the output file: ");
			String outputDirectory = scanner.next();
			try {
				manager.processFile(inputFilePath, outputDirectory);
			} catch (FileNotFoundException e) {
				//
			}
			System.out.println("The file has been processed and saved to the appropriate location.");
		}
		else {
			System.out.println("Please enter the file path of the text file to be processed: ");
			String inputFilePath = scanner.next();
			System.out.println("Please enter the number of words to be reported: ");
			int numOfWords = scanner.nextInt();
			try {
				String frequent = manager.getMostFrequentWords(inputFilePath, numOfWords);
				System.out.println(frequent);
			} catch (FileNotFoundException e) {
				//
			}
		}
		scanner.close();
	}
}
