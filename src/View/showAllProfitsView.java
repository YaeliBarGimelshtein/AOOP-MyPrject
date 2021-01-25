package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class showAllProfitsView {
	//button
	private Button done;
	//text and field
	private Label headline;
	private Label allProfits;
	//layout
	private VBox allDetails;
	private StackPane screen;
	private Stage stage;
	
	public showAllProfitsView(Stage stage) {
		//buttons
		this.done=new Button("Done");
		this.done.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		
		//texts
		this.headline= new Label("Showing Profits");
		this.headline.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
		this.headline.setTextFill(Color.DARKSLATEGRAY);
		
		this.allProfits=new Label();
		this.allProfits.setFont(new Font("Arial",20));
		
		//layout
		this.allDetails = new VBox();
		this.allDetails.getChildren().addAll(headline,allProfits,done);
		this.allDetails.setSpacing(30);
		this.allDetails.setAlignment(Pos.CENTER_LEFT);
		
		VBox.setMargin(this.headline, new Insets(0, 0, 100, 160));
		VBox.setMargin(this.allProfits, new Insets(0, 0, 0, 20));
		VBox.setMargin(this.done, new Insets(50, 0, 0, 200));
		
		this.screen=new StackPane();
		this.screen.getChildren().addAll(allDetails);
		
		Scene scene = new Scene(screen,600,600);
		
		this.stage=stage;
		this.stage.setScene(scene);
	}
	
	public void updateToAllProducts(String allProducts) {
		this.allProfits.setText(allProducts);
	}
	
	public void show() {
		this.stage.show();
	}
	
	public Button getDoneButton() {
		return this.done;
	}
	
	public void closeWindow() {
		this.stage.close();
	}
	
}
