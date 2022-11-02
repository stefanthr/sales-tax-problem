package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import org.junit.Test;

import main.Item;
import main.ItemType;

public class ItemTest {

	@SuppressWarnings("resource")
	@Test
	public void getFirstItemQuantity() {
		Item item = new Item(1, null);
		try {
			Scanner input = new Scanner(System.in);
			File file = new File("testInput1.txt");
			input = new Scanner(file);
			String line = input.nextLine();
			String[] words = line.split(" ");
			int qty = Integer.parseInt(words[0]); // first token is the quantity
			assertEquals(item.getQuantity(), qty, 0);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	@Test
	public void getFirstItemType() {
		boolean itemTypeBook = false;
		ItemType itemType = null;
		Item item = new Item(1, ItemType.BOOK);
		try {
			Scanner input = new Scanner(System.in);
			File file = new File("testInput1.txt");
			input = new Scanner(file);
			String line = input.nextLine();
			String[] words = line.split(" ");
			String itemTypeInput = words[1];
			if(itemTypeInput.equals("book")) {
				itemType = ItemType.BOOK;
			}
			if(itemType.equals(item.getItemType())) {
				itemTypeBook = true;
			}
			assertTrue(itemTypeBook);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
