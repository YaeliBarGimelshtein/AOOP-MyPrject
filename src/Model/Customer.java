package Model;

import Model.Observer.Observer;

public class Customer implements Observer {
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

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public boolean isIntrestedInSales() {
		return intrestedInSales;
	}

	@Override
	public String getSMS() {
		return this.name;
	}
	
	
}
