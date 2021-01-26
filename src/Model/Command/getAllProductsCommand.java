package Model.Command;

import Model.Model;

public class getAllProductsCommand {
	private Model model;

	public getAllProductsCommand(Model model) {
		this.model = model;
	}
	
	public String getAllProducts() {
		return model.getAllProducts();
	}
}
