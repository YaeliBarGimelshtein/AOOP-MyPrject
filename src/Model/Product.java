package Model;

public class Product {
	String name;
	int priceForStore;
	int priceForCustomer;
	Customer boughtBy;
	
	
	public Product(String name, int priceForStore, int priceForCustomer, Customer boughtBy) {
		this.name = name;
		this.priceForStore = priceForStore;
		this.priceForCustomer = priceForCustomer;
		this.boughtBy = boughtBy;
	}


	@Override
	public String toString() {
		return "Product: " + name + ", price for store: " + priceForStore + ", price for customer: " + priceForCustomer
				+ ", bought By: " + boughtBy.toString()+"\n";
	}
	
	
	
	
}
