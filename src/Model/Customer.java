package Model;

public class Customer {
	String name;
	String phoneNumber;
	boolean intrestedInSales;
	
	public Customer(String name, String phoneNumber, boolean intrestedInSales) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.intrestedInSales = intrestedInSales;
	}

	@Override
	public String toString() {
		return "Customer: " + name + ", phone Number: " + phoneNumber + ", intrested in sales: " + intrestedInSales;
	}
	
	
}
