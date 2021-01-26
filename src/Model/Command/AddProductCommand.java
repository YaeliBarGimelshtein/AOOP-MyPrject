package Model.Command;

import Model.Model;
import Model.Product;

public class AddProductCommand {
	private Model model;

	public AddProductCommand(Model model) {
		this.model = model;
	}
	
	public void addProductByObject(Product p, String catalogNumber) {
		model.addProduct(p, catalogNumber);
	}
	
	public void addProductByFields(String catalogNumber, String name, int priceForStore, int priceForCustomer,
			String Cname, String CphoneNumber, boolean intrestedInSales) {
		model.addProduct(catalogNumber, name, priceForStore, priceForCustomer, Cname, CphoneNumber, intrestedInSales);
	}
	
}

//the addProduct automatticly writes to file