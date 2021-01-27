package Model.Command;

import Model.Model;
import Model.Product;
import Model.Memento.CareTaker;

public class AddProductCommand {
	private Model model;

	public AddProductCommand(Model model) {
		this.model = model;
	}
	
	public void addProductByObject(CareTaker lastStatus, Product p, String catalogNumber) {
		model.addProduct(lastStatus,p, catalogNumber);
	}
	
	public void addProductByFields(CareTaker lastStatus,String catalogNumber, String name, int priceForStore, int priceForCustomer,
			String Cname, String CphoneNumber, boolean intrestedInSales) {
		model.addProduct(lastStatus,catalogNumber, name, priceForStore, priceForCustomer, Cname, CphoneNumber, intrestedInSales);
	}
	
}

//the addProduct automatticly writes to file and updates last saves