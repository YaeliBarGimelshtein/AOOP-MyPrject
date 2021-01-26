package View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class searchProductView {
	//button
	private Button done;
	//text
	private Label enterCatalogNumber;
	private TextField catalogNumber; 
	//layout
	private HBox enterCatalogNumberBox;
	private VBox allInfo;
	private StackPane screen;
	private Stage stage;
	
	public searchProductView(Stage stage) {
		//buttons
		this.done=new Button("Done");
		this.done.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		//texts
		this.enterCatalogNumber=new Label("Enter Catalog Number");
		this.enterCatalogNumber.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
		this.catalogNumber=new TextField();
		//layout
		this.enterCatalogNumberBox= new HBox();
		this.enterCatalogNumberBox.getChildren().addAll(enterCatalogNumber,catalogNumber);
		this.enterCatalogNumberBox.setSpacing(15);
		this.enterCatalogNumberBox.setAlignment(Pos.CENTER);
		
		this.allInfo=new VBox();
		this.allInfo.getChildren().addAll(enterCatalogNumberBox,done);
		this.allInfo.setSpacing(15);
		this.allInfo.setAlignment(Pos.CENTER);
		
		this.screen=new StackPane();
		this.screen.getChildren().addAll(allInfo);
		
		Scene scene = new Scene(screen,600,600);
		
		this.stage=stage;
		this.stage.setScene(scene);
	}
	
	public String getCatalogNumber() {
		return this.catalogNumber.getText();
	}
	
	public boolean checkChooseProductDone() {
		if(catalogNumber.getText().isEmpty()) {
			Alert msg = new Alert(AlertType.ERROR);
			msg.setContentText("Must add all values");
			msg.setTitle("Not Valid");
			msg.show();
			return false;
		}	
		return true;
	}
	
	public void show() {
		this.stage.show();
	}
	
	public Button getDoneButton() {
		return this.done;
	}
	
	public void closeWindow() {
		this.catalogNumber.setText("");
		this.stage.close();
	}
	
}
