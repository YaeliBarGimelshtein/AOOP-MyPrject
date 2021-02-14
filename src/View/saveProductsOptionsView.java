package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class saveProductsOptionsView {
	//buttons
	private RadioButton byABCAscending;
	private RadioButton byABCDescending;
	private RadioButton byInputOrder;
	//texts 
	private Label headLine;
	private Label explanation;
	private DropShadow blurry;
	//layout
	private ToggleGroup group;
	private VBox order;
	private StackPane screen;
	private Stage stage;
	
	public saveProductsOptionsView(Stage stage) {
		//buttons
		this.group=new ToggleGroup();
		
		this.byABCAscending=new RadioButton("By Ascending Order");
		this.byABCAscending.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		this.byABCAscending.setToggleGroup(group);
		
		this.byABCDescending=new RadioButton("By Descending Order");
		this.byABCDescending.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		this.byABCDescending.setToggleGroup(group);
		
		this.byInputOrder=new RadioButton("By Order Input");
		this.byInputOrder.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		this.byInputOrder.setToggleGroup(group);
		
		//text 
		this.headLine= new Label("Welcome to the Products Saving Program");
		this.headLine.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
		this.headLine.setTextFill(Color.BLUE);
		
		this.blurry=new DropShadow();
		this.blurry.setColor(Color.BLACK);
		this.headLine.setEffect(blurry);
		
		this.explanation= new Label("Please choose in what order to save products");
		this.explanation.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		
		//layout
		this.order= new VBox();
		this.order.getChildren().addAll(headLine,explanation,byABCAscending,byABCDescending,byInputOrder);
		this.order.setSpacing(25);
		this.order.setAlignment(Pos.CENTER);
		VBox.setMargin(byABCAscending, new Insets(0, 110, 0, 0));
		VBox.setMargin(byABCDescending, new Insets(0, 102, 0, 0));
		VBox.setMargin(byInputOrder, new Insets(0, 140, 0, 0));
		VBox.setMargin(headLine, new Insets(0, 0, 40, 0));
		VBox.setMargin(explanation, new Insets(10, 0, 0, 0));
		this.screen=new StackPane();
		this.screen.getChildren().addAll(order);
		
		Scene scene = new Scene(screen,600,400);
		this.stage=stage;
		this.stage.setScene(scene);

	}
	
	public void show() {
		this.stage.show();
	}
	
	public String getChosenAndClose() {
		if(byABCAscending.isSelected()) {
			this.stage.close();;
			return "ASCENDING";
		}else if(byABCDescending.isSelected()) {
			this.stage.close();
			return "DESCENDING";
		}else {
			this.stage.close();
			return "BY_INPUT_ORDER";
		}
	}


	public ToggleGroup getGroup() {
		return group;
	}

}
