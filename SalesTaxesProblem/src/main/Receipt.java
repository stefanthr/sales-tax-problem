package main;

import java.io.File;
import java.util.Scanner;

public class Receipt {

	public Receipt(String inputFileName) {
		try {
			Scanner input = new Scanner(System.in);
			File file = new File(inputFileName);
			input = new Scanner(file);
			while (input.hasNextLine()) {
				String line = input.nextLine();
				String[] words = line.split(" ");
				int qty = Integer.parseInt(words[0]); // first token is the quantity
				boolean isImported = line.contains("imported"); // check if the item is imported
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public double calculateTotals() {
		return 0;
	}
}
