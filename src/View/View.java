package View;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class View {
	//other views
	private saveProductsOptionsView optionsView;
	private addProdectView addProductView;
	private showChosenProductView showProductView;
	private searchProductView searchProductByCatalogNumberView;
	
	private Stage stage;
	private boolean readFromFile;
	private boolean noProducts;
	//buttons
	private Button addProduct;
	private Button getAndShowProduct;
	private Button showAllProducts;
	private Button undoLastAddedProduct;
	private Button readAllFromFile;
	private Button deleteProductFromFile;
	private Button deleteAllFromFile;
	private Button showProfit;
	private Button sendSMS;
	private Button showAllConfirmedCustomers;
	private Button quit;
	
	//texts
	private Label headLine;
	private DropShadow blurry;
	private Label error;
	
	//layout
	private HBox firstRow;
	private HBox secondRow;
	private HBox quitBox;
	private HBox headLineBox;
	private HBox errorBox;
	private VBox allOptions;
	private BorderPane borders;
	private Pane centerScreen;
	
	
	

	public View(Stage primaryStage , boolean readFromFile) {
		this.stage=primaryStage;
		this.readFromFile=readFromFile;
		if(!readFromFile)
			noProducts=true;
		else
			noProducts=false;
		this.addProductView=new addProdectView(new Stage());
		this.optionsView=new saveProductsOptionsView(new Stage());
		this.searchProductByCatalogNumberView= new searchProductView(new Stage());
		this.showProductView= new showChosenProductView(new Stage());
		
		//buttons
		this.quit= new Button("Save and quit");
		this.quit.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		
		this.addProduct= new Button("Add product");
		this.addProduct.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		
		this.getAndShowProduct= new Button("Get and show product");
		this.getAndShowProduct.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		if(noProducts)
			this.getAndShowProduct.setDisable(true);
		
		this.showAllProducts= new Button("Show all products");
		this.showAllProducts.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		if(noProducts)
			this.showAllProducts.setDisable(true);
		
		this.undoLastAddedProduct= new Button("Undo last added product");
		this.undoLastAddedProduct.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		if(noProducts)
			this.undoLastAddedProduct.setDisable(true);
		
		this.readAllFromFile= new Button("Read all from file");
		this.readAllFromFile.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		if(!this.readFromFile)
			this.readAllFromFile.setDisable(true);
		
		this.deleteProductFromFile= new Button("Delete product from file");
		this.deleteProductFromFile.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		if(!this.readFromFile)
			this.deleteProductFromFile.setDisable(true);
		
		this.deleteAllFromFile= new Button("Delete all from file");
		this.deleteAllFromFile.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		if(!this.readFromFile)
			this.deleteAllFromFile.setDisable(true);
		
		this.showProfit= new Button("Show profit");
		this.showProfit.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		if(noProducts)
			this.showProfit.setDisable(true);
		
		this.sendSMS= new Button("Send SMS");
		this.sendSMS.setFont(Font.font("Verdana", FontWeight.BOLD, 16)); 
		if(noProducts)
			this.sendSMS.setDisable(true);
		
		this.showAllConfirmedCustomers= new Button("Show all confirmed customers");
		this.showAllConfirmedCustomers.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		if(noProducts)
			this.showAllConfirmedCustomers.setDisable(true);
		
		//text
		this.headLine= new Label("Saving Products Program");
		this.headLine.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
		this.headLine.setTextFill(Color.BROWN);
		
		this.blurry=new DropShadow();
		this.blurry.setColor(Color.BLACK);
		this.headLine.setEffect(blurry);
		
		this.error= new Label("");
		this.error.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		
		//layout
		this.firstRow=new HBox();
		this.firstRow.getChildren().addAll(addProduct,getAndShowProduct,showAllProducts,undoLastAddedProduct,readAllFromFile);
		this.firstRow.setAlignment(Pos.CENTER);
		this.firstRow.setSpacing(15);
		
		this.secondRow=new HBox();
		this.secondRow.getChildren().addAll(deleteProductFromFile,deleteAllFromFile,showProfit,sendSMS,showAllConfirmedCustomers);
		this.secondRow.setAlignment(Pos.CENTER);
		this.secondRow.setSpacing(15);
		
		this.quitBox=new HBox();
		this.quitBox.getChildren().addAll(quit);
		this.quitBox.setAlignment(Pos.CENTER);
		this.quitBox.setSpacing(15);
		
		this.headLineBox=new HBox();
		this.headLineBox.getChildren().addAll(headLine);
		this.headLineBox.setAlignment(Pos.CENTER);
		
		this.errorBox=new HBox();
		this.errorBox.getChildren().addAll(error);
		this.errorBox.setAlignment(Pos.CENTER);
		HBox.setMargin(error, new Insets(0,0, 30, 0));

		this.allOptions= new VBox();
		this.allOptions.getChildren().addAll(firstRow,secondRow,quitBox);
		VBox.setMargin(firstRow, new Insets(150,0, 30, 200));
		VBox.setMargin(secondRow, new Insets(0,0, 0, 200));
		VBox.setMargin(quitBox, new Insets(60,0, 0, 200));
		this.allOptions.setAlignment(Pos.CENTER);
		
		this.centerScreen=new Pane();
		centerScreen.getChildren().addAll(allOptions);
		
		if(!this.readFromFile) {
			this.optionsView.show();
		}
		else {
			this.noProducts=false;
			createMenuView();
		}
	}
	
	
	public int getOrderToSaveProducts() {
		int order= this.optionsView.getChosenAndClose();
		createMenuView();
		return order;
	}
	
	
	private void createMenuView() {
		this.borders=new BorderPane();
		borders.setTop(headLineBox);
		borders.setCenter(centerScreen);
		borders.setBottom(errorBox);
		Scene scene = new Scene(this.borders,1400,500);
		this.stage.setScene(scene);
		this.stage.show();
	}

	
	
	//from menu:
	public void createAddProductView() {  //to add product
		this.stage.close();
		this.addProductView.show();
	}
	
	public void createShowProductView() {
		
		
		this.showProductView.show();
	}
	
	public void createSearchProductView() {
		this.stage.close();
		this.searchProductByCatalogNumberView.show();
	}
	
	public void createShowProductView(String pName, String pPriceForStore, String pPriceForCustomer, String cName, String cPhoneNumber,
			String cIntrestedInSales) {
		this.searchProductByCatalogNumberView.closeWindow();
		this.showProductView.updateInfo("yoyo","30","20","koko","0503299910","true");
		this.showProductView.show();
	}
	
	public boolean checkAddProductDone() { //to add product	
		return this.addProductView.checkAddProductDone();
	}
	

	
	public boolean checkChoosingCatalogNumberDone() {
		return this.searchProductByCatalogNumberView.checkChooseProductDone();
	}
	
	public String getCatalogNumberToFind() {
		return this.searchProductByCatalogNumberView.getCatalogNumber();
	}
	
	

	
	public String getNewProductName() { //to add product
		return this.addProductView.getName();
	}
	
	public int getNewProductPriceForStore() { //to add product
		return this.addProductView.getPriceForStore();
	}
	
	public int getNewProductPriceForCustomer() { //to add product
		return this.addProductView.getPriceForCustomer();
	}

	public String getCatalogNumber() {
		return this.addProductView.getCatalogNumber();
	}

	public String getCustomerName() { //to add product
		return this.addProductView.getCustomerName();
	}
	
	public String getCustomerNumber() { //to add product
		return this.addProductView.getPhoneNumber();
	}
	
	public boolean getIntrestedInSales() { //to add product
		return this.addProductView.intrestedInSales();
	}
	
	public boolean checkInputToAddProductExceptions() { //to add product
		if(getNewProductPriceForStore()!=-1 && getNewProductPriceForCustomer()!=-1)
			return false;
		return true;
	}
	
	public void closeAddProductWindow(boolean isException) { //to add product
		this.addProductView.closeWindow();
		if(!isException) {
			this.noProducts=false;
			setAllOptionsables();
			this.error.setText("");
		}else if (isException && this.noProducts) {
			this.noProducts=true;
			setAllOptionsDisables();
			this.error.setText("Price cannot include characters, digits only. Product NOT added");
		}else if (isException && this.noProducts==false) {
			this.noProducts=false;
			setAllOptionsDisables();
			this.error.setText("Price cannot include characters, digits only. Product NOT added");
		}
		this.stage.show();
	}
	
	public void setAllOptionsDisables() {
		this.getAndShowProduct.setDisable(true);
		this.showAllProducts.setDisable(true);
		this.undoLastAddedProduct.setDisable(true);
		this.readAllFromFile.setDisable(true);
		this.deleteProductFromFile.setDisable(true);
		this.deleteAllFromFile.setDisable(true);
		this.showProfit.setDisable(true);
		this.sendSMS.setDisable(true);
		this.showAllConfirmedCustomers.setDisable(true);
	}
	
	public void setAllOptionsables() {
		this.getAndShowProduct.setDisable(false);
		this.showAllProducts.setDisable(false);
		this.undoLastAddedProduct.setDisable(false);
		this.readAllFromFile.setDisable(false);
		this.deleteProductFromFile.setDisable(false);
		this.deleteAllFromFile.setDisable(false);
		this.showProfit.setDisable(false);
		this.sendSMS.setDisable(false);
		this.showAllConfirmedCustomers.setDisable(false);
	}
	
	
	public void closeShowProductWindow() {
		this.showProductView.closeWindow();
		this.stage.show();
	}
	
	//connection to controller

	public void addChangeListenerToGames(ChangeListener<Toggle> savingMethodPicked) {
		this.optionsView.getGroup().selectedToggleProperty().addListener(savingMethodPicked);
	}


	public void addEventHandlerToAddProduct(EventHandler<ActionEvent> addProductIsPressed) {
		this.addProduct.setOnAction(addProductIsPressed);
	}


	public void addEventHandlerTodoneInAddProduct(EventHandler<ActionEvent> doneInAddProductIsPressed) {
		this.addProductView.getDoneButton().setOnAction(doneInAddProductIsPressed);
	}


	public void addEventHandlerToShowProduct(EventHandler<ActionEvent> showProductIsPressed) {
		this.getAndShowProduct.setOnAction(showProductIsPressed);
	}


	public void addEventHandlerTodoneInShowProduct(EventHandler<ActionEvent> showProductIsPressed) {
		this.showProductView.getDoneButton().setOnAction(showProductIsPressed);
	}


	public void addEventHandlerTodoneInSearchProduct(EventHandler<ActionEvent> doneInSearchProductIsPressed) {
		this.searchProductByCatalogNumberView.getDoneButton().setOnAction(doneInSearchProductIsPressed);
	}


	




	


	


	




	




	



	


	


	

	



	
	
}
