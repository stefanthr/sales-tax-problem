package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.text.DecimalFormat;
import java.util.Arrays;

import org.junit.Test;

import main.Item;
import main.ItemType;
import main.Receipt;

public class ReceiptTest {

//	@Test
//	public void getTotal() {
//		Receipt receipt = buildReceiptWithItems(new Item(1, ItemType.BOOK, 12.49), new Item(1, ItemType.FOOD, 0.85));
//		assertEquals(13.34, receipt.calculateTotal(), 0);
//	}
//
//	@Test
//	public void getTotalWhenQuantityOfOneItemMoreThanOne() {
//		Receipt receipt = buildReceiptWithItems(new Item(1, ItemType.BOOK, 12.49), new Item(2, ItemType.FOOD, 0.85));
//		assertEquals(14.19, receipt.calculateTotal(), 0);
//	}
//
//	@Test
//	public void getTotalWithSaleTax() {
//		Receipt receipt = buildReceiptWithItems(new Item(1, ItemType.OTHER, 14.99));
//		assertEquals(16.49, receipt.calculateTotal(), 0);
//	}
//
//	@Test
//	public void getTotalWithImportTaxNoSaleTax() {
//		Receipt receipt = buildReceiptWithItems(new Item(1, ItemType.IMPORTED_BOOK, 10.00));
//		assertEquals(10.50, receipt.calculateTotal(), 0);
//	}
//
//	@Test
//	public void getTotalWithImportTaxAndSaleTax() {
//		Receipt receipt = buildReceiptWithItems(new Item(1, ItemType.IMPORTED_OTHER, 47.50));
//		assertEquals(54.65, receipt.calculateTotal(), 0);
//	}
//
//	@Test
//	public void getTotalFromFirstTestInputWithoutReadingTextFile() {
//		Receipt receipt = buildReceiptWithItems(new Item(1, ItemType.IMPORTED_OTHER, 27.99),
//				new Item(1, ItemType.OTHER, 18.99), new Item(1, ItemType.MEDICAL, 9.75),
//				new Item(1, ItemType.IMPORTED_FOOD, 11.25));
//		assertEquals(74.68, receipt.calculateTotal(), 0);
//	}
	
	@Test public void readFirstTestInputTextFile(){
		Receipt receipt = buildReceiptWithItemsFromTextFile("testInput1.txt");
		assertEquals(3, receipt.getItemList().size(),0);
	}
	
	@Test public void calculateTotalFirstTestInputTextFile1(){
		Receipt receipt = buildReceiptWithItemsFromTextFile("testInput1.txt");
		assertEquals(29.83, receipt.calculateTotal(),0);
		System.out.println("Output 1: \n");
		receipt.printReceipt(); 
	}
	
	@Test public void calculateTotalTestInputTextFile2(){
		Receipt receipt = buildReceiptWithItemsFromTextFile("testInput2.txt");
		assertEquals(65.15, receipt.calculateTotal(),0);
		System.out.println("Output 2: \n");
		receipt.printReceipt();
	}
	
	@Test public void calculateTotalTestInputTextFile3(){
		Receipt receipt = buildReceiptWithItemsFromTextFile("testInput3.txt");
		assertEquals(74.68, receipt.calculateTotal(),0);
		System.out.println("Output 3: \n");
		receipt.printReceipt();
	}

	private Receipt buildReceiptWithItems(Item... items) {
		return new Receipt(Arrays.asList(items));
	}

	private Receipt buildReceiptWithItemsFromTextFile(String textFile) {
		return new Receipt(textFile);
	}
}
