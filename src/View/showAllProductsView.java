package View;

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

public class showAllProductsView {
	//button
	private Button done;
	//text and field
	private Label headline;
	private Label allProducts;
	//layout
	private VBox allDetails;
	private StackPane screen;
	private Stage stage;
	
	public showAllProductsView(Stage stage) {
		//buttons
		this.done=new Button("Done");
		this.done.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		
		//texts
		this.headline= new Label("Showing All Products ");
		this.headline.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
		this.headline.setTextFill(Color.DARKSLATEGRAY);
		
		this.allProducts=new Label();
		this.allProducts.setFont(new Font("Arial",20));
		
		//layout
		this.allDetails = new VBox();
		this.allDetails.getChildren().addAll(headline,allProducts,done);
		this.allDetails.setSpacing(30);
		this.allDetails.setAlignment(Pos.CENTER);
		
//		VBox.setMargin(this.headline, new Insets(0, 0, 100, 160));
//		VBox.setMargin(this.allProducts, new Insets(0, 0, 0, 20));
//		VBox.setMargin(this.done, new Insets(50, 0, 0, 200));
//		
		this.screen=new StackPane();
		this.screen.getChildren().addAll(allDetails);
		
		Scene scene = new Scene(screen,1300,600);
		
		this.stage=stage;
		this.stage.setScene(scene);
		//this.stage.setFullScreen(true);

	}
	
	public void updateToAllProducts(String allProducts) {
		this.allProducts.setText(allProducts);
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
