package Model;

public class Product {
	String name;
	int priceForStore;
	int priceForCustomer;
	Customer boughtBy;
	int profit;
	
	
	public Product(String name, int priceForStore, int priceForCustomer, Customer boughtBy) {
		this.name = name;
		this.priceForStore = priceForStore;
		this.priceForCustomer = priceForCustomer;
		this.boughtBy = boughtBy;
		this.profit=priceForCustomer-priceForStore;
	}


	@Override
	public String toString() {
		return "Product: " + name + ", price for store: " + priceForStore + ", price for customer: " + priceForCustomer
				+ ", bought By: " + boughtBy.toString()+"\n";
	}


	public int getProfit() {
		return profit;
	}


	public String getName() {
		return name;
	}


	public int getPriceForStore() {
		return priceForStore;
	}


	public int getPriceForCustomer() {
		return priceForCustomer;
	}


	public Customer getBoughtBy() {
		return boughtBy;
	}
	
	
	
	
}
