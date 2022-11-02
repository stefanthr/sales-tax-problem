package main;

public enum ItemType {
	BOOK(true, false), 
	MEDICAL(true, false), 
	FOOD(true, false), 
	OTHER(false, false), 
	IMPORTED_BOOK(true, true),
	IMPORTED_MEDICAL(true, true), 
	IMPORTED_FOOD(true, true), 
	IMPORTED_OTHER(false, true);

	private boolean isExempt;
	private boolean isImported;

	private ItemType(boolean exempted, boolean imported) {
		isExempt = exempted;
		isImported = imported;
	}

	public boolean isImported() {
		return isImported;
	}

	public boolean isExempt() {
		return isExempt;
	}
}
