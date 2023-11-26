package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class contains the necessary methods to process a given Scanner into a
 * HashMap, as well as predictively generate a sample text of any length.
 * 
 * @author Andrew Dennison
 */
public class ProbableText {

	public int ngram;
	StringBuilder sb = new StringBuilder();
	OurHashMap<String, ArrayList<Character>> hashMap;

	public String output;

	/**
	 * Create a new Probable Text object. This constructor intiates the processing
	 * of the input and stores it in a HashMap.
	 * 
	 * @param input Text to be read in.
	 * @param ngram Integer representation of the length of n-grams to generate
	 */
	public ProbableText(Scanner input, int ngram) {
		this.ngram = ngram;

		// Generate a StringBuilder object with the text
		while (input.hasNextLine()) {
			String nextLine = input.nextLine();
			sb.append(nextLine + " ");
		}

		// Pass the StringBuilder to be processed and store
		hashMap = processText(sb);
	}

	/**
	 * This method contains the formal processing of a single long string into a
	 * HashMap for predictive generation
	 * 
	 * @param sb StringBuilder representation of text to be processed.
	 * @return OurHashMap representation of the characters that follow each ngram.
	 */
	public OurHashMap<String, ArrayList<Character>> processText(StringBuilder sb) {
		OurHashMap<String, ArrayList<Character>> hash = new OurHashMap<String, ArrayList<Character>>();

		// For each possible ngram...
		for (int i = 0; i < sb.length() - ngram; i++) {
			String sub = sb.substring(i, i + ngram);

			// Check if this ngram already exists. If it doesn't, create a new ArrayList
			if (!hash.containsKey(sub)) {
				hash.put(sub, new ArrayList<Character>());
			}

			// Add our given next character to the provided ArrayList
			hash.get(sb.substring(i, i + ngram)).add(sb.charAt(i + ngram));
		}

		return hash;
	}

	/**
	 * This method contains the predictive generation and assumes the OurHashMap
	 * object has already been built, and is ready to access.
	 * 
	 * @param length Integer representation of the number of chars that should be in
	 *               the result
	 */
	public String generateText(int length) {
		// Initialize our result
		StringBuilder result = new StringBuilder();

		// Choose our random ngram to begin with
		Random rand = new Random();
		int randomIndex = rand.nextInt(sb.length() - ngram - 1);
		result.append(sb.substring(randomIndex, randomIndex + ngram));

		// Until we reach the desired length...
		for (int i = 0; i < length - ngram; i++) {
			// Get the ending ngram of the current result and its corresponding ArrayList
			String ending = result.substring(result.length() - ngram);
			ArrayList<Character> possChars = hashMap.get(ending);

			// Generate a random next character from the ArrayList and append it
			int randCharIndex = rand.nextInt(possChars.size());
			result.append(possChars.get(randCharIndex));
		}

		return formatText(result.toString());
	}

	/**
	 * This method formats the output from generateText with line breaks. This is
	 * done by adding breaks when a word ends after 60 characters from the last line
	 * break.
	 * 
	 * @param builtOutput String to format with breaks every 60 chars.
	 * @return String representation of formatted version of builtOutput
	 */
	public String formatText(String builtOutput) {
		String result = "";
		Scanner input = new Scanner(builtOutput);

		int counter = 0;
		while (input.hasNext()) {
			String next = input.next();
			result += next + " ";
			counter += next.length();
			if (counter > 55) {
				result += "\n";
				counter = 0;
			}
		}

		input.close();
		return result;
	}

	/**
	 * @return String representation of the stored Hash Map
	 */
	public String toString() {
		return hashMap.toString();
	}

}
