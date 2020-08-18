package edu.ncsu.csc316.compressor.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;
import edu.ncsu.csc316.dsa.sorter.RadixMapSorter;
import edu.ncsu.csc316.compressor.factory.*;
import edu.ncsu.csc316.compressor.io.*;

/**
 * The CompressionManager class handles behaviors associated with compressing an
 * input file, decompressing an input file, and generating a report of the most
 * frequently used words in an input file. test
 * 
 * @author Dr. King
 * @author Caleb Wiebe
 * @author Matt Kierski
 *
 */
public class CompressionManager {
	/**
	 * Initializes CompressionMananger
	 */
	public CompressionManager() {
		//This is empty because we do not need any predefined data types 
		//for this class
	}

	/**
	 * Determines whether to compress or decompress the specified input file;
	 * creates the output file in the specified output directory; then returns a
	 * list of strings that represent each line of the processed output.
	 * 
	 * For example, if the input file is:
	 * 
	 *    Baby shark
	 *    I do not know the rest of this song
	 * 
	 * Then the returned list would be
	 * 
	 *    at index 0: Baby shark
	 *    at index 1: I do not know the rest of this song
	 * 
	 * @param pathToInputFile the path to the input file to be processed
	 * @param outputDirectory the directory where the processed file should be saved
	 * @return a list of strings that represent the lines of processed output
	 * @throws FileNotFoundException when no file is found
	 */
	public List<String> processFile(String pathToInputFile, String outputDirectory) throws FileNotFoundException {
		List<String> list = DSAFactory.getIndexedList();
		String inputFileName = pathToInputFile.substring(pathToInputFile.lastIndexOf('/'), pathToInputFile.lastIndexOf('.'));
		//try {
			//Scanner fileReader = new Scanner(new FileInputStream(pathToInputFile));
//			String first = fileReader.nextLine();
//			list.addLast(first);
//			while (fileReader.hasNextLine()) {
//				String nextLine = fileReader.nextLine();
//				list.addLast(nextLine);
//			}
//			fileReader.close();
			list = TextFileIO.readFileByLine(pathToInputFile);
			//PrintStream fileWriter;
//			try {
//				fileWriter = new PrintStream(new File(outputDirectory));
//			} catch (FileNotFoundException e) {
//				throw new IllegalArgumentException();
//			}
		if (list.first().equals("0")) {
			List<String> decompressed = this.getDecompressed(list);
			TextFileIO.writeFile(decompressed, outputDirectory + inputFileName + ".txt");
//				for (int i = 1; i < decompressed.size(); i++) {
//					fileWriter.println(decompressed.get(i));
//				}
				//fileWriter.close();
			return decompressed;
		} else {
			List<String> compressed = this.getCompressed(list);
			TextFileIO.writeFile(compressed, outputDirectory + inputFileName + ".316");
//				for (int i = 0; i < compressed.size(); i++) {
//					fileWriter.println(compressed.get(i));
//				}
				//fileWriter.close();
			return compressed;
		}
		//} catch (FileNotFoundException e) {
			//throw new FileNotFoundException();
		//}
	}

	/**
	 * Compresses the input list that represents the specified input file, then
	 * returns a list of strings that represent each line of the compressed output.
	 * 
	 * For example, if the file being compressed contains the text:
	 * 
	 *    Baby shark
	 *    I do not know the rest of this song
	 * 
	 * Then the input list would be
	 * 
	 *    at index 0: Baby shark
	 *    at index 1: I do not know the rest of this song
	 * 
	 * @param fileLines the list of lines of text in the input file
	 * @return a list of strings that represent the compressed output
	 */
	public List<String> getCompressed(List<String> fileLines) {
		int count = 1;
		//Creates a map with the values being maps containing the code of the word
		//and a value of whether or not it has been processed
		Map<String, Map<String, Integer>> map = DSAFactory.getUnorderedMap();
		//List returned with compressed data
		List<String> list = DSAFactory.getIndexedList();
		//Iterates through the file lines
		for (int i = 0; i < fileLines.size(); i++) {
			Scanner scan = new Scanner(fileLines.get(i));
			while (scan.hasNext()) { 
				//Gets the word with no punctuation
				String next = scan.next();
				String[] words = next.split("\\W");
				for (int j = 0; j < words.length; j++) {
					//Adds word to map if it is new
					if (!words[j].equals("") && map.get(words[j]) == null) {
						Map<String, Integer> temp = DSAFactory.getUnorderedMap();
						temp.put("code", count);
						temp.put("processed", 0);
						map.put(words[j], temp);
						count++;
					}
				}
			}
			scan.close();
		}
		//Iterates through file lines again
		for (int i = 0; i < fileLines.size(); i++) {
			String line = "";
			Scanner scan = new Scanner(fileLines.get(i));
			scan.useDelimiter("");
			String next = null;
			String word = "";
			while (scan.hasNext()) {
				//Gets the word with no punctuation but saves the punctuation
				next = scan.next();
				if (next.matches("\\W")) {
					if (map.get(word) != null) {
						if (map.get(word).get("processed").equals(0)) {
							line += word;
							map.get(word).put("processed", 1);
						} else {
							line += map.get(word).get("code");
						}
					}
					word = "";
					line += next;
				} else {
					word += next;
				}
			}
			if (map.get(word) != null) {
				if (map.get(word).get("processed").equals(0)) {
					line += word;
					map.get(word).put("processed", 1);
				} else {
					line += map.get(word).get("code");
				}
			}
			list.addLast(line);
			scan.close();
		}
		list.addFirst("0");
		return list;
	}

	
	/**
	 * Decompresses the input list that represents the specified input file, then
	 * returns a list of strings that represent each line of the decompressed output.
	 * 
	 * For example, if the file being decompressed contains the text:
	 * 
	 *    Baby shark
	 *    I do not know the rest of this song
	 * 
	 * Then the input list would be:
	 * 
	 *    at index 0: Baby shark
	 *    at index 1: I do not know the rest of this song
	 * 
	 * @param fileLines the list of lines of text in the input file
	 * @return a list of strings that represent the compressed output
	 */	
	public List<String> getDecompressed(List<String> fileLines) {
		int count = 1;
		//Creates a map with the key as the counter and the value as the word
		Map<String, String> map = DSAFactory.getUnorderedMap();
		//List returned with decompressed data
		List<String> list = DSAFactory.getIndexedList();
		//Regular expression for an integer
		String integer = "[+-]?[0-9][0-9]*";
		//Iterates through the file lines
		for (int i = 0; i < fileLines.size(); i++) {
			Scanner scan = new Scanner(fileLines.get(i));
			while (scan.hasNext()) { 
				//Gets the word with no punctuation
				String next = scan.next();
				String[] words = next.split("\\W");
				//Adds word to map if it is not an integer
				for (int j = 0; j < words.length; j++) {
					if (!words[j].equals("") && !words[j].matches(integer)) {
						String counter = "";
						counter += count;
						map.put(counter, words[j]);
						count++;
					}
				}
			}
			scan.close();
		}
		//Iterates through file lines again
		for (int i = 0; i < fileLines.size(); i++) {
			String line = "";
			Scanner scan = new Scanner(fileLines.get(i));
			scan.useDelimiter("");
			String next = null;
			String word = "";
			while (scan.hasNext()) {
				next = scan.next();
				if (next.matches("\\W")) {
					if (map.get(word) != null) {
						line += map.get(word);
					} else {
						line += word;
					}
					word = "";
					line += next;
				} else {
					word += next;
				}
			}
			if (map.get(word) != null) {
				line += map.get(word);
			} else {
				line += word;
			}
			//Adds line to list
			list.addLast(line);
			scan.close();
		}
		if (list.first().equals("0")) {
			list.removeFirst();
		}
		return list;
	}

	/**
	 * Generates a report of the most frequently appearing words
	 * in the input file.
	 * 
	 * @param pathToInputFile the path to the input file to process
	 * @param numberOfWords the number of words to include in the report
	 * @return a report of the most frequently appearing words in the input file
	 * @throws FileNotFoundException throws exception when no file is found
	 */
	public String getMostFrequentWords(String pathToInputFile, int numberOfWords) throws FileNotFoundException {
		List<String> list = DSAFactory.getIndexedList();
		String ret = "Most Frequent Words Report [\n";
		try {
			Scanner fileReader = new Scanner(new FileInputStream(pathToInputFile));
			while (fileReader.hasNextLine()) {
				String nextLine = fileReader.nextLine();
				Scanner lineScanner = new Scanner(nextLine);
				while (lineScanner.hasNext()) {
					String next = lineScanner.next();
					next = next.replaceAll("\\W", "");
					list.addLast(next);
				}
				lineScanner.close();
			}
			fileReader.close();
			List<String> newList = this.getMostFrequentWords(list, numberOfWords);
			for (int i = 0; i < newList.size(); i++) {
				ret += "   " + newList.get(i) + "\n";
			}
			ret += "]";
			return ret;
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found");
		}
	}
	
	/**
	 * Returns a list of words that appear most frequently in the input list
	 * 
	 * @param inputList the list of words to process
	 * @param numberOfWords the number of words to include in the output list of most frequent words
	 * @return a list of words that appear most frequently in the input list
	 */
	public List<String> getMostFrequentWords(List<String> inputList, int numberOfWords) {
		//Ordered map that sorts keys in reverse alphabetical order
		Map<String, Integer> orderedMap = DSAFactory.getOrderedMap();
		//Puts values in map in reverse alphabetical order
		for (int i = 0; i < inputList.size(); i++) {
			if (!inputList.get(i).equals("")) {
				if (orderedMap.get(inputList.get(i).toLowerCase()) != null) {
					orderedMap.put(inputList.get(i).toLowerCase(), orderedMap.get(inputList.get(i).toLowerCase()) + 1);
				} else {
					orderedMap.put(inputList.get(i).toLowerCase(), 1);
				}
			}
		}
		//Reverse radix sorter
		RadixMapSorter sorter = DSAFactory.getNonComparisonMapSorter();
		Iterable<Entry<String, Integer>> values = orderedMap.entrySet();
		Iterator<Entry<String, Integer>> it = values.iterator();
		List<Entry<String, Integer>> list = DSAFactory.getIndexedList();
		//Adds map entries to a list
		while (it.hasNext()) {
			list.addLast(it.next());
		}
		//Sorts list in descending order by value
		sorter.sort(list);
		//Gets the most frequent words out of the list
		List<String> newList = DSAFactory.getIndexedList();
		for (int i = 0; i < (numberOfWords < list.size() ? numberOfWords : list.size()); i++) {
			newList.addLast(list.get(i).getKey());
		}
		return newList;
	}
	
}