package main;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Receipt {

	private List<Item> itemList;

	public Receipt(String textFileName) {
		try {
			File file = new File(textFileName);
			Scanner input = new Scanner(file);
			itemList = new ArrayList<>();
			while (input.hasNextLine()) {
				readTextFile(input);
			}
			input.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void readTextFile(Scanner input) {
		String line = input.nextLine();
		String[] words = line.split(" ");
		int itemQuantity = Integer.parseInt(words[0]);
		boolean isImported = line.contains("imported");
		String[] exemptItems = new String[] { "book", "chocolate", "pills" };
		int exemptItemIndex = containsItemFromExemptList(line, exemptItems);
		String exemptType = null;
		if (exemptItemIndex != -1) {
			exemptType = exemptItems[exemptItemIndex];
		}
		int splitIndex = line.lastIndexOf("at");
		if (splitIndex == -1) {
			System.out.println("Wrong Input Format");
		} else {
			double itemPrice = Double.parseDouble((line.substring(splitIndex + 2)));
			String itemName = line.substring(itemQuantity, splitIndex - 1);
			for (int i = 0; i < itemQuantity; i++) {
				Item item = null;
				item = readItemData(itemQuantity, isImported, exemptType, itemPrice, itemName, item);
				itemList.add(item);
			}
		}
	}

	private Item readItemData(int itemQuantity, boolean isImported, String exemptType, double itemPrice,
			String itemName, Item item) {
		if (isImported) {
			if (exemptType != null) {
				if (exemptType == "book") {
					item = new Item(itemQuantity, ItemType.IMPORTED_BOOK, itemPrice, itemName);
				} else if (exemptType == "pills") {
					item = new Item(itemQuantity, ItemType.IMPORTED_MEDICAL, itemPrice, itemName);
				} else if (exemptType == "chocolate") {
					item = new Item(itemQuantity, ItemType.IMPORTED_FOOD, itemPrice, itemName);
				}
			} else {
				item = new Item(itemQuantity, ItemType.IMPORTED_OTHER, itemPrice, itemName);
			}
		} else {
			if (exemptType != null) {
				if (exemptType == "book") {
					item = new Item(itemQuantity, ItemType.BOOK, itemPrice, itemName);
				} else if (exemptType == "pills") {
					item = new Item(itemQuantity, ItemType.MEDICAL, itemPrice, itemName);
				} else if (exemptType == "chocolate") {
					item = new Item(itemQuantity, ItemType.FOOD, itemPrice, itemName);
				}
			} else {
				item = new Item(itemQuantity, ItemType.OTHER, itemPrice, itemName);
			}
		}
		return item;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public Receipt(List<Item> items) {
		this.itemList = items;
	}

	public static int containsItemFromExemptList(String inputString, String[] items) {
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

	public void printReceipt() {
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		itemList.stream().forEach(item -> System.out.println("> " + item.getQuantity() + " " + item.getName() + ": "
				+ String.format("%.2f", Double.valueOf(decimalFormat.format(item.getSubtotal())))));
		System.out.println("> Sales Tax: " + String.format("%.2f", Double.valueOf(decimalFormat
				.format(itemList.stream().mapToDouble(item -> item.roundTwoDecimals(item.getTax())).sum()))));
		System.out.println("> Total: " + calculateTotal() + "\n");
	}
}
