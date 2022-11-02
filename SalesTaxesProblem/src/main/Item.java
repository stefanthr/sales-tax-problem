package main;

public class Item {

	private int quantity;
	private ItemType itemType;

	public Item(int quantity, ItemType itemType) {
		this.quantity = quantity;
		this.itemType = itemType;
	}

	public int getQuantity() {
		return quantity;
	}

	public ItemType getItemType() {
		return itemType;
	}
	
}
