package main;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Receipt {

	private List<Item> itemList;

	public Receipt(String textFile) {
		try {
			File file = new File(textFile);
			Scanner input = new Scanner(file);
			itemList = new ArrayList<>();
			while (input.hasNextLine()) {
				String line = input.nextLine();
				String[] words = line.split(" ");
				int qty = Integer.parseInt(words[0]);
				boolean isImported = line.contains("imported"); // check if the item is imported
				String[] exemptedItems = new String[] { "book", "chocolate", "pills" }; // check if the item in the
																						// exempted list
				int exemptedItemIndex = containsItemFromArray(line, exemptedItems); // Find which type of exemption
				String exemptedType = null;
				if (exemptedItemIndex != -1) {
					exemptedType = exemptedItems[exemptedItemIndex];
				}
				int splitIndex = line.lastIndexOf("at");
				if (splitIndex == -1) {
					System.out.println("Bad Formatting");
				} else {
					double price = Double.parseDouble((line.substring(splitIndex + 2)));
					String name = line.substring(qty, splitIndex-1); //the name is everything between the qty and at
					for (int i = 0; i < qty; i++) {
						// loop for the total quantity of the item to make that many in the list		
						Item item = null;
						if (isImported) {
							// the product is imported
							if (exemptedType != null) {
								// the product is not imported and is exempt of sales tax
								if (exemptedType == "book") {
									item = new Item(qty, ItemType.IMPORTED_BOOK, price, name);
								} else if (exemptedType == "pills") {
									item = new Item(qty, ItemType.IMPORTED_MEDICAL, price, name);
								} else if (exemptedType == "chocolate") {
									item = new Item(qty, ItemType.IMPORTED_FOOD, price, name);
								}
							} else {
								// the product is imported and sales taxed
								item = new Item(qty, ItemType.IMPORTED_OTHER, price, name);
							}
						} else {
							// the product is domestic
							if (exemptedType != null) {
								// the product is domestic and is exempt of sales tax

								if (exemptedType == "book") {
									item = new Item(qty, ItemType.BOOK, price, name);
								} else if (exemptedType == "pills") {
									item = new Item(qty, ItemType.MEDICAL, price, name);
								} else if (exemptedType == "chocolate") {
									item = new Item(qty, ItemType.FOOD, price, name);
								}
							} else {
								// the product is domestic and is sales taxed
								item = new Item(qty, ItemType.OTHER, price,name);
							}
						}

						itemList.add(item); // add the product to our receipt's list
					}
				}
			}
			input.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public List<Item> getItemList() {
		return itemList;
	}

	public Receipt(List<Item> items) {
		this.itemList = items;
	}

	public static int containsItemFromArray(String inputString, String[] items) {
		int index = -1;
		for (int i = 0; i < items.length; i++) {
			index = inputString.indexOf(items[i]);
			if (index != -1)
				return i;
		}
		return -1;

	}

	public double calculateTotal() {
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		double total = Double.valueOf(decimalFormat
				.format(itemList.stream().mapToDouble(item -> item.roundTwoDecimals(item.getSubtotal())).sum()));
		return total;
	}
	
	public void printReceipt(){
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		itemList.stream().forEach(item -> System.out.println("> " + item.getQuantity() + " " + item.getName() + ": " + Double.valueOf(decimalFormat
				.format(item.getSubtotal()))));
		System.out.printf("> Sales Tax: %.2f\n", Double.valueOf(decimalFormat
				.format(itemList.stream().mapToDouble(item -> item.roundTwoDecimals(item.getTax())).sum())));
		System.out.println("> Total: " + calculateTotal() + "\n");
	}
}
