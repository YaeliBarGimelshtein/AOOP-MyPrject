package Model.Command;

import Model.Model;
import Model.Memento.ModelMemento;

public class saveForMementoCommand {
	private Model model;

	public saveForMementoCommand(Model model) {
		this.model = model;
	}
	
	public ModelMemento saveForMementoFirstTime() {
		return this.model.save();
	}
}
