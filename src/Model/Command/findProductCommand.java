package Model.Command;

import Model.Model;

public class findProductCommand {
	private Model model;
	

	public findProductCommand(Model model) {
		this.model = model;
	}


	public void findProduct(String catalogNumberToFind) {
		model.findProduct(catalogNumberToFind);
	}
}
