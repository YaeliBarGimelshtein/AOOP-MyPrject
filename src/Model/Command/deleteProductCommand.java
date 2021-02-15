package Model.Command;

import Model.Model;
import Model.Memento.CareTaker;

public class deleteProductCommand {
	private Model model;

	public deleteProductCommand(Model model) {
		this.model = model;
	}
	
	public void deleteProduct(String catalogNumber,CareTaker lastStatus) {
		this.model.deleteProduct(catalogNumber,lastStatus);
	}
}
