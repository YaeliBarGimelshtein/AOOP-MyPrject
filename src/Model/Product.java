package Model;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int priceForStore;
	private int priceForCustomer;
	private Customer boughtBy;
	private int profit;
	
	
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


	public boolean equals(Product p2) {
		if(!(boughtBy.equals(p2.boughtBy))){
			return false;
		}
		if (!name.equals(p2.name))
			return false;
		if (priceForCustomer != p2.priceForCustomer)
			return false;
		if (priceForStore != p2.priceForStore)
			return false;
		if (profit != p2.profit)
			return false;
		
		return true;
	}
}
