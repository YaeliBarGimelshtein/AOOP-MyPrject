package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class addProdectView {
	//button
	private Button done;
	private RadioButton intrestedInSalesYes;
	private RadioButton intrestedInSalesNo;
	
	//text and field
	private Label headline;
	private Label enterName;
	private TextField name;
	private Label enterPriceForStore;
	private TextField priceForStore;
	private Label enterPriceForCustomer;
	private TextField priceForCustomer;
	private Label enterCustomerName;
	private TextField customerName;
	private Label enterPhoneNumber;
	private TextField phoneNumber;
	private Label enterIntrestedInSale;
	private Label enterCatalogNumber;
	private TextField catalogNumber;
	
	//layout
	private HBox nameBox;
	private HBox priceForStoreBox;
	private HBox priceForCustomerBox;
	private HBox customerNameBox;
	private HBox phoneNumberBox;
	private HBox intrestedInSalesBox;
	private HBox catalogNumberBox;
	private VBox allOptions;
	private ToggleGroup group;
	private StackPane screen;
	private Stage stage;

	public addProdectView(Stage stage) {
		this.group=new ToggleGroup();
		
		//buttons
		this.intrestedInSalesYes=new RadioButton("Yes");
		this.intrestedInSalesYes.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		this.intrestedInSalesYes.setToggleGroup(group);
		
		this.intrestedInSalesNo=new RadioButton("No");
		this.intrestedInSalesNo.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		this.intrestedInSalesNo.setToggleGroup(group);
		
		this.done= new Button("Done");
		this.done.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		
		//text and fields
		this.name= new TextField();
		this.priceForStore= new TextField();
		this.priceForCustomer= new TextField();
		this.customerName= new TextField();
		this.phoneNumber= new TextField();
		this.catalogNumber=new TextField();
		
		this.headline= new Label("Add Product");
		this.headline.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
		this.headline.setTextFill(Color.DARKSLATEGRAY);
		this.enterName= new Label("Enter Product Name");
		this.enterName.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
		this.enterPriceForStore= new Label("Enter Product Price For Store");
		this.enterPriceForStore.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
		this.enterPriceForCustomer= new Label("Enter Product Price For Customer");
		this.enterPriceForCustomer.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
		this.enterCustomerName= new Label("Enter Customer Name");
		this.enterCustomerName.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
		this.enterPhoneNumber= new Label("Enter Customer's Phone Number");
		this.enterPhoneNumber.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
		this.enterIntrestedInSale= new Label("Choose If Customer Intrested In Sale");
		this.enterIntrestedInSale.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
		this.enterCatalogNumber=new Label("Enter Catalog Number");
		this.enterCatalogNumber.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
		
		//layout
		this.nameBox = new HBox();
		this.nameBox.getChildren().addAll(enterName,name);
		this.nameBox.setSpacing(8);
		
		this.priceForStoreBox= new HBox();
		this.priceForStoreBox.getChildren().addAll(enterPriceForStore,priceForStore);
		this.priceForStoreBox.setSpacing(8);
		
		this.priceForCustomerBox= new HBox();
		this.priceForCustomerBox.getChildren().addAll(enterPriceForCustomer,priceForCustomer);
		this.priceForCustomerBox.setSpacing(8);
		
		this.customerNameBox= new HBox();
		this.customerNameBox.getChildren().addAll(enterCustomerName,customerName);
		this.customerNameBox.setSpacing(8);
		
		this.phoneNumberBox= new HBox();
		this.phoneNumberBox.getChildren().addAll(enterPhoneNumber,phoneNumber);
		this.phoneNumberBox.setSpacing(8);
		
		this.intrestedInSalesBox= new HBox();
		this.intrestedInSalesBox.getChildren().addAll(enterIntrestedInSale,intrestedInSalesYes,intrestedInSalesNo);
		this.intrestedInSalesBox.setSpacing(15);
		
		this.catalogNumberBox=new HBox();
		this.catalogNumberBox.getChildren().addAll(enterCatalogNumber,catalogNumber);
		
		this.allOptions= new VBox();
		this.allOptions.getChildren().addAll(headline,catalogNumberBox, nameBox, priceForStoreBox, priceForCustomerBox, customerNameBox,phoneNumberBox, intrestedInSalesBox,done);
		this.allOptions.setSpacing(15);
		this.allOptions.setAlignment(Pos.CENTER);
		VBox.setMargin(catalogNumberBox, new Insets(20, 0, 0, 20));
		VBox.setMargin(nameBox, new Insets(0, 0, 0, 20));
		VBox.setMargin(priceForStoreBox, new Insets(0, 0, 0, 20));
		VBox.setMargin(priceForCustomerBox, new Insets(0, 0, 0, 20));
		VBox.setMargin(customerNameBox, new Insets(0, 0, 0, 20));
		VBox.setMargin(phoneNumberBox, new Insets(0, 0, 0, 20));
		VBox.setMargin(intrestedInSalesBox, new Insets(0, 0, 0, 20));
		VBox.setMargin(done, new Insets(0, 0, 0, 20));
		
		this.screen=new StackPane();
		this.screen.getChildren().addAll(allOptions);
		
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
	
	public ToggleGroup getGroup() {
		return group;
	}




	public boolean checkAddProductDone() {
		if(name.getText().isEmpty() || priceForStore.getText().isEmpty() || priceForCustomer.getText().isEmpty() ||
				customerName.getText().isEmpty() || phoneNumber.getText().isEmpty() || catalogNumber.getText().isEmpty()) {
			Alert msg = new Alert(AlertType.ERROR);
			msg.setContentText("Must add all values");
			msg.setTitle("Not Valid");
			msg.show();
			return false;
		}	
		return true;
	}
	
	//need to make exceptions

	public String getName() {
		return this.name.getText();
	}
	
	
	public int getPriceForStore() {
		try {
			return Integer.parseInt(priceForStore.getText());
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	
	public String getCatalogNumber() {
		return catalogNumber.getText();
	}

	public int getPriceForCustomer() {
		try {
			return Integer.parseInt(priceForCustomer.getText());
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public String getCustomerName() {
		return customerName.getText();
	}

	public String getPhoneNumber() {
		return phoneNumber.getText();
	}
	
	public boolean intrestedInSales() {
		if(intrestedInSalesYes.isSelected())
			return true;

		return false;
	}

	public void closeWindow() {
		this.name.setText("");
		this.priceForStore.setText("");
		this.priceForCustomer.setText("");
		this.customerName.setText("");
		this.phoneNumber.setText("");
		this.catalogNumber.setText("");
		this.intrestedInSalesNo.setSelected(false);
		this.intrestedInSalesYes.setSelected(false);
		this.stage.close();
	}

}
