package Model.Command;

import java.util.ArrayList;

import Model.Model;

public class getAllConfirmedCustomers {
	private Model model;

	public getAllConfirmedCustomers(Model model) {
		this.model = model;
	}
	
	public ArrayList<String> getAllConfirmed(){
		return model.getAllConfirmedCustomers();
	}

	public void resetAllConfirmed() {
		model.resetAllConfirmedCustomers();
	}
}
