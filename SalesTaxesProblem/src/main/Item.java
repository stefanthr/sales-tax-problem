package main;

public class Item {

	private int quantity;
	private ItemType itemType;
	private double unitPrice;

	public Item(int quantity, ItemType itemType, double price) {
		this.quantity = quantity;
		this.itemType = itemType;
		this.unitPrice = price;
	}

	public ItemType getItemType() {
		return itemType;
	}
	
	public double getSubtotal() {
		return unitPrice * (double) quantity;
	}
	
}
