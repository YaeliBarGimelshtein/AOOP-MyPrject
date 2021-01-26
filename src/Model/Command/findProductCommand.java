package Model.Command;

import Model.Model;
import Model.Product;

public class findProductCommand {
	private Model model;
	

	public findProductCommand(Model model) {
		this.model = model;
	}


	public Product findProduct(String catalogNumberToFind) {
		return model.findProduct(catalogNumberToFind);
	}
}
