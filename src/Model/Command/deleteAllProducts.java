package Model.Command;

import Model.Model;
import Model.Memento.CareTaker;

public class deleteAllProducts {
	private Model model;

	public deleteAllProducts(Model model) {
		this.model = model;
	}
	
	public void deleteAll(CareTaker lastStatus) {
		this.model.deleteAll(lastStatus);
	}
}
