package Model.Command;

import Model.Model;

public class sendSMSCommand {
	private Model model;

	public sendSMSCommand(Model model) {
		this.model = model;
	}
	
	public void sendSMS() {
		this.model.sendSMS();
	}

	public boolean checkIfAreCustomersIntrested() {
		return this.model.checkIfAreCustomersIntrested();
	}
}
