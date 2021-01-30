package Model.Command;

import Model.Model;

public class checkIfAreProductsCommand {
	private Model model;

	public checkIfAreProductsCommand(Model model) {
		this.model = model;
	}
	
	public boolean checkIfAreProducts() {
		return this.model.checkIfAreProducts();
	}
}
