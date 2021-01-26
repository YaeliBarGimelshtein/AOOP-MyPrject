package Controller;

import Model.Model;
import Model.Command.AllModelCommands;
import View.View;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Toggle;

public class Controller {

	private AllModelCommands commands;
	private View view;
	
	public Controller(AllModelCommands theCommands, View theView, boolean readFromFile) {
		this.commands=theCommands;
		this.view=theView;
		
		if(!readFromFile) {
			ChangeListener<Toggle> savingMethodPicked= new ChangeListener<Toggle>() {
				@Override
				public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
					commands.updateSavingMethod(view.getOrderToSaveProducts());
				}
			};
			view.addChangeListenerToGames(savingMethodPicked);
		}
		
		EventHandler<ActionEvent> addProductIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.createAddProductView();
			}
		};
		view.addEventHandlerToAddProduct(addProductIsPressed);
		
		EventHandler<ActionEvent> doneInAddProductIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				boolean finished=view.checkAddProductDone();
				
				if(finished) {
					boolean isException=view.checkInputToAddProductExceptions();
					if(!isException) {
						commands.addProductByFields(view.getCatalogNumber(),view.getNewProductName(), view.getNewProductPriceForStore(),
								view.getNewProductPriceForCustomer(), view.getCustomerName(), view.getCustomerNumber(), 
								view.getIntrestedInSales());
						view.closeAddProductWindow(isException);
					}else {
						view.closeAddProductWindow(isException);
					}
				}
			}
		};
		view.addEventHandlerTodoneInAddProduct(doneInAddProductIsPressed);
		
		EventHandler<ActionEvent> showProductIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.createSearchProductView();
			}
		};
		view.addEventHandlerToShowProduct(showProductIsPressed);
		
		
		EventHandler<ActionEvent> doneInSearchProductIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				boolean finished=view.checkChoosingCatalogNumberDone();
				
				if(finished) {
					commands.findProduct(view.getCatalogNumberToFind());
					//model.getChosenProdect();
					view.createShowProductView( "pName", "pPriceForStore" , "pPriceForCustomer", "cName", "cPhoneNumber", "cIntrestedInSales");
					
				}
			}
		};
		view.addEventHandlerTodoneInSearchProduct(doneInSearchProductIsPressed);
		
		
		EventHandler<ActionEvent> doneInShowProductIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.closeShowProductWindow();
			}
		};
		view.addEventHandlerTodoneInShowProduct(doneInShowProductIsPressed);
		
		EventHandler<ActionEvent> showAllProductsIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.createShowAllProductsView(commands.getAllProducts());
			}
		};
		view.addEventHandlerToshowAllProducts(showAllProductsIsPressed);
		
		EventHandler<ActionEvent> doneInShowAllProductsIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.closeShowAllProductsWindow();
			}
		};
		view.addEventHandlerTodoneInShowAllProducts(doneInShowAllProductsIsPressed);
		
		EventHandler<ActionEvent> quitIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			    System.exit(0);
			}
		};
		view.addEventHandlerToQuit(quitIsPressed);
		
		EventHandler<ActionEvent> showAllProfitsIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.createShowAllProfitsView(commands.getAllPrfits());
			}
		};
		view.addEventHandlerToshowAllProfits(showAllProfitsIsPressed);
		
		EventHandler<ActionEvent> doneInShowAllProfitsIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.closeShowAllProfitsWindow();
			}
		};
		view.addEventHandlerTodoneInShowAllProfits(doneInShowAllProfitsIsPressed);
		
		
		EventHandler<ActionEvent> undoLastAddedProductIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//model.undo();
				view.updateWindowToUndo();
			}
		};
		view.addEventHandlerToUndoLastAddedProduct(undoLastAddedProductIsPressed);
		
		EventHandler<ActionEvent> deleteProductFromFileIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//model.deleteFromFile();
				view.updateWindowToDeleteProductFromFile();
			}
		};
		view.addEventHandlerToDeleteProductFromFile(deleteProductFromFileIsPressed);
		
		EventHandler<ActionEvent> deleteAllProductFromFileIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//model.deleteFromFile();
				view.updateWindowToDeleteAllProductFromFile();
			}
		};
		view.addEventHandlerToDeleteAllProductFromFile(deleteAllProductFromFileIsPressed);
		
		EventHandler<ActionEvent> sendSMSIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//model.deleteFromFile();
				view.updateWindowToToSendSMS();
			}
		};
		view.addEventHandlerToSendSMS(sendSMSIsPressed);
	}
}
