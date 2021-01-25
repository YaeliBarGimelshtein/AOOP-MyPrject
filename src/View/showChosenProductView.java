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

public class showChosenProductView {
	//button
	private Button done;
	//text and field
	private Label headline;
	private Label pName;
	private Label pPriceForStore;
	private Label pPriceForCustomer;
	private Label cName;
	private Label cPhoneNumber;
	private Label cIntrestedInSales;
	

	
	//layout
	private VBox allDetails;
	private StackPane screen;
	private Stage stage;
	
	public showChosenProductView(Stage stage) {
		//buttons
		this.done=new Button("Done");
		this.done.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		
		//texts
		this.headline= new Label("Product Showing");
		this.headline.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
		this.headline.setTextFill(Color.DARKSLATEGRAY);
		
		this.pName= new Label("Product name: ");
		this.pName.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
		
		this.pPriceForStore= new Label("Product price for store: ");
		this.pPriceForStore.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
		
		this.pPriceForCustomer= new Label("Product price for customer: ");
		this.pPriceForCustomer.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
		
		this.cName= new Label("Buying customer name: ");
		this.cName.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
		
		this.cPhoneNumber= new Label("Buying customer phone: ");
		this.cPhoneNumber.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
		
		this.cIntrestedInSales= new Label("Is buying customer intrested in sales messages: ");
		this.cIntrestedInSales.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
		
		this.allDetails = new VBox();
		this.allDetails.getChildren().addAll(this.headline, this.pName,this.pPriceForStore,this.pPriceForCustomer,this.cName,this.cPhoneNumber,this.cIntrestedInSales,done);
		this.allDetails.setSpacing(15);
		this.allDetails.setAlignment(Pos.CENTER_LEFT);
		
		VBox.setMargin(this.headline, new Insets(0, 0, 0, 200));
		VBox.setMargin(this.pName, new Insets(20, 0, 0, 20));
		VBox.setMargin(this.pPriceForStore, new Insets(0, 0, 0, 20));
		VBox.setMargin(this.pPriceForCustomer, new Insets(0, 0, 0, 20));
		VBox.setMargin(this.cName, new Insets(0, 0, 0, 20));
		VBox.setMargin(this.cPhoneNumber, new Insets(0, 0, 0, 20));
		VBox.setMargin(this.cIntrestedInSales, new Insets(0, 0, 0, 20));
		VBox.setMargin(done, new Insets(50, 0, 0, 260));
		
	
		this.screen=new StackPane();
		this.screen.getChildren().addAll(allDetails);
		
		Scene scene = new Scene(screen,600,600);
		
		this.stage=stage;
		this.stage.setScene(scene);
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

	public void updateInfo(String pName, String pPriceForStore, String pPriceForCustomer, String cName, String cPhoneNumber,
			String cIntrestedInSales) {
		this.pName.setText("Product name: "+pName);
		this.pPriceForStore.setText("Product price for store: "+pPriceForStore);
		this.pPriceForCustomer.setText("Product price for customer: "+pPriceForCustomer);
		this.cName.setText("Buying customer name: "+cName);
		this.cPhoneNumber.setText("Buying customer phone: "+cPhoneNumber);
		this.cIntrestedInSales.setText("Is buying customer intrested in sales messages: "+cIntrestedInSales);
	}
	
}
