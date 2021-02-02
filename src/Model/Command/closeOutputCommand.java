package Model.Command;

import Model.Model;

public class closeOutputCommand {
	private Model model;

	public closeOutputCommand(Model model) {
		this.model = model;
	}
	
	public void close() {
		this.model.close();
	}
}
