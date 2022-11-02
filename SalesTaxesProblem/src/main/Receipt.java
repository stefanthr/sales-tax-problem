package main;

import java.util.List;

public class Receipt {
	
	private double totalAmount = 0;
	private List<Item> items;

	public Receipt(List<Item> items) {
		this.items = items;

	}

	public double calculateTotal() {
		return items.stream().mapToDouble(item -> item.getSubtotal()).sum();
	}

}
