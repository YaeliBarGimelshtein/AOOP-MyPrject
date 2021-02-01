package Model.Command;

import Model.Model;

public class deleteProductCommand {
	private Model model;

	public deleteProductCommand(Model model) {
		this.model = model;
	}
	
	public void deleteProduct(String catalogNumber) {
		this.model.deleteProduct(catalogNumber);
	}
}
