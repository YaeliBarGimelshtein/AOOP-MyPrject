package Model.Command;

import Model.Model;

public class deleteAllProducts {
	private Model model;

	public deleteAllProducts(Model model) {
		this.model = model;
	}
	
	public void deleteAll() {
		this.model.deleteAll();
	}
}
