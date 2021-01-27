package Model.Memento;

import java.util.Stack;


public class CareTaker {
	private Stack<ModelMemento> stack;
	
	
	public CareTaker() {
		this.stack= new Stack<>();
	}


	public void save(ModelMemento lastModel) {
		this.stack.push(lastModel);
	}
	
	public ModelMemento undo() {
		return this.stack.pop();
	}
}
