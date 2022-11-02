package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.Item;
import main.ItemType;

public class ItemTypeTest {
	@Test
	public void checkIfItemTypeExempt() {
		Item item = new Item(1, ItemType.BOOK);
		if (item.getItemType().equals(ItemType.BOOK) || item.getItemType().equals(ItemType.FOOD) || item.getItemType().equals(ItemType.MEDICAL)) {
			assertTrue(item.getItemType().isExempt());
		}
	}
	
	@Test
	public void checkIfItemImported() {
		Item item = new Item(1, ItemType.IMPORTED_BOOK);
		if (item.getItemType().equals(ItemType.IMPORTED_BOOK) || item.getItemType().equals(ItemType.IMPORTED_FOOD) || item.getItemType().equals(ItemType.IMPORTED_MEDICAL)) {
			assertTrue(item.getItemType().isImported());
		}
	}
}
