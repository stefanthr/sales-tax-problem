package main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		double tax = 0;
		if (getItemType().equals(ItemType.OTHER) || getItemType().equals(ItemType.IMPORTED_OTHER)) {
			tax += calculateTax(".10").doubleValue();
		}
		if (getItemType().isImported()) {
			tax += calculateTax(".05").doubleValue();
		}
		return Double.valueOf(decimalFormat.format((unitPrice + tax) * (double) quantity));
	}

	public static BigDecimal round(BigDecimal value, BigDecimal increment, RoundingMode roundingMode) {
		if (increment.signum() == 0) {
			return value;
		} else {
			BigDecimal divided = value.divide(increment, 0, roundingMode);
			BigDecimal result = divided.multiply(increment);
			result.setScale(2, RoundingMode.UNNECESSARY);
			return result;
		}
	}

	public BigDecimal calculateTax(String taxPercent) {
		BigDecimal salesTaxPercent = new BigDecimal(taxPercent);
		BigDecimal salesTax = salesTaxPercent.multiply(new BigDecimal(unitPrice));
		return salesTax = round(salesTax, BigDecimal.valueOf(0.05), RoundingMode.UP);
	}
}
