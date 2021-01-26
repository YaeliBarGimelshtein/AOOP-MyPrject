package Model.Command;

import Model.Model;

public class gelAllProfitsCommand {
	private Model model;

	public gelAllProfitsCommand(Model model) {
		this.model = model;
	}
	
	public String getAllPrfits() {
		return model.getAllPrfits();
	}
}
