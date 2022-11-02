package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import main.Item;
import main.ItemType;
import main.Receipt;

public class ReceiptTest {
	
	@Test
	public void getTotal() {
		Receipt receipt = buildReceiptWithItems(new Item(1,ItemType.BOOK, 12.49), new Item(1,ItemType.FOOD, 0.85));
		assertEquals(13.34,receipt.calculateTotal(),0);
	}
	
	@Test
	public void getTotalWhenQuantityOfOneItemMoreThanOne() {
		Receipt receipt = buildReceiptWithItems(new Item(1,ItemType.BOOK, 12.49), new Item(2,ItemType.FOOD, 0.85));
		assertEquals(14.19,receipt.calculateTotal(),0);
	}
	
	@Test
	public void getTotalWithSaleTax() {
		Receipt receipt = buildReceiptWithItems(new Item(1,ItemType.OTHER, 14.99));
		assertEquals(16.49,receipt.calculateTotal(),0);
	}
	
	@Test
	public void getTotalWithImportTaxNoSaleTax() {
		Receipt receipt = buildReceiptWithItems(new Item(1,ItemType.IMPORTED_BOOK, 10.00));
		assertEquals(10.50,receipt.calculateTotal(),0);
	}
	
	@Test
	public void getTotalWithImportTaxAndSaleTax() {
		Receipt receipt = buildReceiptWithItems(new Item(1,ItemType.IMPORTED_OTHER, 47.50));
		assertEquals(54.65,receipt.calculateTotal(),0);
	}
	
	private Receipt buildReceiptWithItems(Item... items) {
		return new Receipt(Arrays.asList(items));
	}
}
