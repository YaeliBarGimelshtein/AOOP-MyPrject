package Program;

import Controller.Controller;
import Model.Model;
import Model.Command.AllModelCommands;
import View.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class program extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Model theModel = new Model();
		boolean readFromFile= theModel.getReadFromFile();
		AllModelCommands commands= new AllModelCommands(theModel);
		View theView = new View(primaryStage,readFromFile);
		Controller TheController = new Controller(commands, theView, readFromFile);
	}
}
