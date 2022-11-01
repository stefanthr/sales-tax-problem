package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.Receipt;

public class ReceiptTest {
	
	@Test
	public void getTotals() {
		Receipt receipt = new Receipt();
		assertEquals(0,receipt.calculateTotals(),0);
	}
}
