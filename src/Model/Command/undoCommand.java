package Model.Command;

import Model.Model;
import Model.Memento.ModelMemento;

public class undoCommand {
private Model model;
	

	public undoCommand(Model model) {
		this.model = model;
	}
	
	public boolean undo(ModelMemento lastModel) {
		return this.model.load(lastModel);
	}

}
