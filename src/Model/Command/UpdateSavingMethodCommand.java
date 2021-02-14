package Model.Command;

import Model.Model;

public class UpdateSavingMethodCommand {
	private Model model;
	
	public UpdateSavingMethodCommand(Model model) {
		this.model = model;
	}
	
	public void updateSavingMethod(String orderToSaveProducts) {
		model.updateSavingMethod(orderToSaveProducts);
		model.writeSavingMethodToFile(orderToSaveProducts);
	}
}
