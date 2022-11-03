package main;

public class Main {
	
	public static void main(String[] args) {
		
		Receipt receipt1 = new Receipt("testInput1.txt");		
		receipt1.calculateTotal();
		System.out.println("Output 1: \n");
		receipt1.printReceipt();
		System.out.println();

		Receipt receipt2 = new Receipt("testInput2.txt");
		receipt2.calculateTotal();
		System.out.println("Output 2: \n");
		receipt2.printReceipt();
		System.out.println();
		
		Receipt receipt3 = new Receipt("testInput3.txt");
		receipt3.calculateTotal();
		System.out.println("Output 3: \n");
		receipt3.printReceipt();
	}

}
