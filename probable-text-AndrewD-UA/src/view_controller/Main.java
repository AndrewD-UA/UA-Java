package view_controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.ProbableText;

/**
 * This is the primary program, and calls ProbableText to perform its tasks
 * 
 * @author Andrew Dennison
 */
public class Main {

	public static void main(String[] args) {

		// Collect our user input per the assignment page
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter book name: ");
		String book = scanner.next();
		System.out.print("Enter ngram length: ");
		int length = scanner.nextInt();
		System.out.print("How many letters? ");
		int letters = scanner.nextInt();

		scanner.close();

		// Try opening our file and executing the requisite methods on the text
		File file = new File("./books/" + book);
		try {
			Scanner bookRead = new Scanner(file);
			ProbableText pt = new ProbableText(bookRead, length);
			bookRead.close();

			System.out.println("\n" + pt.generateText(letters));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}