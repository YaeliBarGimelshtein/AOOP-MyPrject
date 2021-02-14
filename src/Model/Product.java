package Model;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Product {
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


	public Product(RandomAccessFile raf) {
		try {
			this.name = raf.readUTF();
			this.priceForStore = raf.readInt();
			this.priceForCustomer = raf.readInt();;
			this.boughtBy = new Customer(raf);
			this.profit=priceForCustomer-priceForStore;
		} catch (IOException e) {
			e.printStackTrace();
		}
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


	public void writeToFile(RandomAccessFile raf) {
		try {
			raf.writeUTF(name);
			raf.writeInt(priceForStore);
			raf.writeInt(priceForCustomer);
			boughtBy.writeToFile(raf);
			raf.writeInt(profit);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
