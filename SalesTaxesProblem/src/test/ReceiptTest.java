package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import main.Item;
import main.ItemType;
import main.Receipt;

public class ReceiptTest {
	
	@Test
	public void getTotals() {
		Receipt receipt = buildReceiptWithItems(new Item(1,ItemType.BOOK, 12.49), new Item(1,ItemType.FOOD, 0.85));
		assertEquals(13.34,receipt.calculateTotal(),0);
	}
	
	@Test
	public void getTotalsWhenQuantityOfOneItemMoreThanOne() {
		Receipt receipt = buildReceiptWithItems(new Item(1,ItemType.BOOK, 12.49), new Item(2,ItemType.FOOD, 0.85));
		assertEquals(14.19,receipt.calculateTotal(),0);
	}
	
	private Receipt buildReceiptWithItems(Item... items) {
		return new Receipt(Arrays.asList(items));
	}
}
