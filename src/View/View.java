package View;

import java.util.ArrayList;

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
	private showAllProductsView showAllProductsView;
	private showAllProfitsView showAllProfits;
	private showAllCustometsReceivedSMS allCustomersWithSMS;
	
	private Stage stage;
	private boolean readFromFile;
	private boolean searchToShow;
	//buttons
	private Button addProduct;
	private Button getAndShowProduct;
	private Button showAllProducts;
	private Button undoLastAddedProduct;
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
	private HBox thirdRow;
	private HBox quitBox;
	private HBox headLineBox;
	private HBox errorBox;
	private VBox allOptions;
	private BorderPane borders;
	private Pane centerScreen;
	
	
	

	public View(Stage primaryStage , boolean readFromFile) {
		this.stage=primaryStage;
		this.readFromFile=readFromFile;
		this.addProductView=new addProdectView(new Stage());
		this.optionsView=new saveProductsOptionsView(new Stage());
		this.searchProductByCatalogNumberView= new searchProductView(new Stage());
		this.showProductView= new showChosenProductView(new Stage());
		this.showAllProductsView=new showAllProductsView(new Stage());
		this.showAllProfits=new showAllProfitsView(new Stage());
		this.allCustomersWithSMS=new showAllCustometsReceivedSMS(new Stage());
		
		//buttons
		this.quit= new Button("Save and quit");
		this.quit.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		
		this.addProduct= new Button("Add product");
		this.addProduct.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		
		this.getAndShowProduct= new Button("find and show product");
		this.getAndShowProduct.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		
		this.showAllProducts= new Button("Show all products");
		this.showAllProducts.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		
		this.undoLastAddedProduct= new Button("Undo last operation");
		this.undoLastAddedProduct.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		
		this.deleteProductFromFile= new Button("Delete product");
		this.deleteProductFromFile.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		
		
		this.deleteAllFromFile= new Button("Delete all products");
		this.deleteAllFromFile.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		
		
		this.showProfit= new Button("Show profit");
		this.showProfit.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		
		this.sendSMS= new Button("Send SMS");
		this.sendSMS.setFont(Font.font("Verdana", FontWeight.BOLD, 16)); 
		
		this.showAllConfirmedCustomers= new Button("Show all confirmed customers");
		this.showAllConfirmedCustomers.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
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
		this.error.setTextFill(Color.RED);
		if(readFromFile==false)
			this.error.setText("All data Read from file");
		
		//layout
		this.firstRow=new HBox();
		this.firstRow.getChildren().addAll(addProduct,getAndShowProduct,showAllProducts);
		this.firstRow.setAlignment(Pos.CENTER);
		this.firstRow.setSpacing(42);
		
		this.secondRow=new HBox();
		this.secondRow.getChildren().addAll(undoLastAddedProduct,deleteProductFromFile,deleteAllFromFile);
		this.secondRow.setAlignment(Pos.CENTER);
		this.secondRow.setSpacing(37);
		
		this.thirdRow=new HBox();
		this.thirdRow.getChildren().addAll(showProfit,sendSMS,showAllConfirmedCustomers);
		this.thirdRow.setAlignment(Pos.CENTER);
		this.thirdRow.setSpacing(43);
		
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
		this.allOptions.getChildren().addAll(firstRow,secondRow,thirdRow,quitBox);
		VBox.setMargin(firstRow, new Insets(150,0, 30, 100));
		VBox.setMargin(secondRow, new Insets(0,0, 30, 100));
		VBox.setMargin(thirdRow, new Insets(0,0, 0, 100));
		VBox.setMargin(quitBox, new Insets(60,0, 0, 100));
		this.allOptions.setAlignment(Pos.CENTER);
		
		this.centerScreen=new Pane();
		centerScreen.getChildren().addAll(allOptions);
		
		if(!this.readFromFile) {
			this.optionsView.show();
		}
		else {
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
		Scene scene = new Scene(this.borders,800,550);
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
	
	public void createUnAbleToSearchProductView() {
		this.error.setText("Unable to search Product");
	}
	
	public void createShowProductView(String pName, String pPriceForStore, String pPriceForCustomer, String cName, String cPhoneNumber,
			String cIntrestedInSales) {
		this.searchProductByCatalogNumberView.closeWindow();
		this.showProductView.updateInfo( pName,  pPriceForStore,  pPriceForCustomer,  cName,  cPhoneNumber, cIntrestedInSales);
		this.showProductView.show();
	}
	
	public void closeShowNotFoundAndBackToMenu() {
		this.searchProductByCatalogNumberView.closeWindow();
		this.error.setText("No such Catalog Number");
		this.stage.show();
	}
	
	public void createShowAllProductsView(String allProducts) {
		this.stage.close();
		this.showAllProductsView.updateToAllProducts(allProducts);
		this.showAllProductsView.show();
	}
	
	public void createShowAllProfitsView(String allPrfits) {
		this.stage.close();
		this.showAllProfits.updateToAllProducts(allPrfits);
		this.showAllProfits.show();
	}
	
	public void createWindowToAllConfirmedCustomers(ArrayList<String> allConfirmedCustomers) {
		if(allConfirmedCustomers==null) {
			this.error.setText("No customers in the system");
		}else {
		this.allCustomersWithSMS.getAllCustomers(allConfirmedCustomers);
		this.allCustomersWithSMS.show();
		this.allCustomersWithSMS.showAllCustomers();
		this.error.setText("");
		}
	}
	
	public void createWindowToUnableAllConfirmedCustomers() {
		this.error.setText("Unable to show all Customers");
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
			this.error.setText("Product Added");
		}else {
			this.error.setText("Price cannot include characters, digits only. Product NOT added");
		}
		this.stage.show();
	}
	
	public void updateWindowToUndo() {
		this.error.setText("Undid Last Product");
	}
	
	public void updateWindowToUnAbleUndo() {
		this.error.setText("Unable to undo last Product");
	}
	
	public void updateWindowToDeleteProductFromFile() {
		this.searchProductByCatalogNumberView.closeWindow();
		this.error.setText("Product Deleted");
		this.stage.show();
	}
	
	public void updateWindowToUnAbleToDeleteProductFromFile() {
		this.searchProductByCatalogNumberView.closeWindow();
		this.error.setText("Unable to Delete Product");
		this.stage.show();
	}

	public void updateWindowToDeleteAllProductFromFile() {
		this.error.setText("All Products Deleted");
	}
	
	public void updateWindowToUnableDeleteAllProductFromFile() {
		this.error.setText("Unable to Delete All Products ");
	}

	
	public void updateWindowToSendSMS() {
		this.showAllConfirmedCustomers.setDisable(false);
		this.error.setText("SMS Sent");
	}
	
	public void updateWindowToUnableSendSMS() {
		this.error.setText("Unable to Send SMS");
	}
	
	public void closeShowAllConfirmedCustomersWindow() {
		this.allCustomersWithSMS.close();
		this.showAllConfirmedCustomers.setDisable(true);
	}
	
	public void closeShowProductWindow() {
		this.showProductView.closeWindow();
		this.error.setText("");
		this.stage.show();
	}
	
	public void closeShowAllProductsWindow() {
		this.showAllProductsView.closeWindow();
		this.error.setText("");
		this.stage.show();
	}
	
	public void closeShowAllProfitsWindow() {
		this.showAllProfits.closeWindow();
		this.error.setText("");
		this.stage.show();
	}
	
	public boolean getIfShowProduct() {
		return this.searchToShow;
	}
	
	public void setIfShowProductTrue() {
		this.searchToShow=true;
	}

	public void setIfShowProductFalse() {
		this.searchToShow=false;
	}

	
	//connection to controller

	public void addChangeListenerTosavingMethodPicked(ChangeListener<Toggle> savingMethodPicked) {
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


	public void addEventHandlerToshowAllProducts(EventHandler<ActionEvent> showAllProductsIsPressed) {
		this.showAllProducts.setOnAction(showAllProductsIsPressed);
	}


	public void addEventHandlerTodoneInShowAllProducts(EventHandler<ActionEvent> doneInShowAllProductsIsPressed) {
		this.showAllProductsView.getDoneButton().setOnAction(doneInShowAllProductsIsPressed);
	}


	public void addEventHandlerToQuit(EventHandler<ActionEvent> quitIsPressed) {
		this.quit.setOnAction(quitIsPressed);
	}


	public void addEventHandlerToshowAllProfits(EventHandler<ActionEvent> showAllProfitsIsPressed) {
		this.showProfit.setOnAction(showAllProfitsIsPressed);
	}


	public void addEventHandlerTodoneInShowAllProfits(EventHandler<ActionEvent> doneInShowAllProfitsIsPressed) {
		this.showAllProfits.getDoneButton().setOnAction(doneInShowAllProfitsIsPressed);
	}


	public void addEventHandlerToUndoLastAddedProduct(EventHandler<ActionEvent> undoLastAddedProductIsPressed) {
		this.undoLastAddedProduct.setOnAction(undoLastAddedProductIsPressed);
	}


	public void addEventHandlerToDeleteProductFromFile(EventHandler<ActionEvent> deleteProductFromFileIsPressed) {
		this.deleteProductFromFile.setOnAction(deleteProductFromFileIsPressed);
	}


	public void addEventHandlerToDeleteAllProductFromFile(EventHandler<ActionEvent> deleteAllProductFromFileIsPressed) {
		this.deleteAllFromFile.setOnAction(deleteAllProductFromFileIsPressed);
	}


	public void addEventHandlerToSendSMS(EventHandler<ActionEvent> sendSMSIsPressed) {
		this.sendSMS.setOnAction(sendSMSIsPressed);
	}


	public void addEventHandlerToshowAllConfirmedCustomers(EventHandler<ActionEvent> showAllConfirmedCustomers) {
		this.showAllConfirmedCustomers.setOnAction(showAllConfirmedCustomers);
	}


	public void addEventHandlerTodoneInshowAllConfirmedCustomersIsPressed(EventHandler<ActionEvent> doneInshowAllConfirmedCustomersIsPressed) {
		this.allCustomersWithSMS.getDoneButton().setOnAction(doneInshowAllConfirmedCustomersIsPressed);
	}




	

	






	

	

	

	


	


	





	


	


	





	


	


	


	

	


	


	


	




	


	


	




	




	



	


	


	

	



	
	
}
