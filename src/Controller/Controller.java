package Controller;

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
		
		
		
		//window if not reading from file
		if(!readFromFile) {
			ChangeListener<Toggle> savingMethodPicked= new ChangeListener<Toggle>() {
				@Override
				public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
					commands.updateSavingMethod(view.getOrderToSaveProducts());
				}
			};
			view.addChangeListenerToGames(savingMethodPicked);
		}
		
		
		
		
		
		
		//adding product
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
		
		
		
		
		
		
		
		
		//showing searched product
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
					boolean found=commands.findProduct(view.getCatalogNumberToFind());
					if(found)
						view.createShowProductView( commands.getFoundName(), commands.getFoundPriceForStore() ,
								commands.getFoundPriceForCustomer(), commands.getFoundCusName(), commands.getFoundCusPhone(), 
								commands.getFoundCusIntrestedInSales());
					else {
						view.closeShowNotFoundAndBackToMenu();
					}
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
		
		
		
		
		
		
		
		
		//show all products
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
		
		
		
		
		
		
		
		//to end the program
		EventHandler<ActionEvent> quitIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			    System.exit(0);
			}
		};
		view.addEventHandlerToQuit(quitIsPressed);
		
		
		
		
		
		
		
		//show all profits
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
		
		
		
		
		
		
		
		
		//undo last added product
		EventHandler<ActionEvent> undoLastAddedProductIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				commands.undo();
				view.updateWindowToUndo();
			}
		};
		view.addEventHandlerToUndoLastAddedProduct(undoLastAddedProductIsPressed);
		
		
		
		
		
		
		
		
		//delete product
		EventHandler<ActionEvent> deleteProductFromFileIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//model.deleteFromFile();
				view.updateWindowToDeleteProductFromFile();
			}
		};
		view.addEventHandlerToDeleteProductFromFile(deleteProductFromFileIsPressed);
		
		
		
		
		
		
		
		
		
		//delete all products
		EventHandler<ActionEvent> deleteAllProductFromFileIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//model.deleteFromFile();
				view.updateWindowToDeleteAllProductFromFile();
			}
		};
		view.addEventHandlerToDeleteAllProductFromFile(deleteAllProductFromFileIsPressed);
		
		
		
		
		
		
		
		
		
		//send sms
		EventHandler<ActionEvent> sendSMSIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				commands.sendSMS();
				view.updateWindowToToSendSMS();
			}
		};
		view.addEventHandlerToSendSMS(sendSMSIsPressed);
		
		
		
		
		
		
		
		
		//show all customers that got the sms
		EventHandler<ActionEvent> showAllConfirmedCustomers= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.createWindowToAllConfirmedCustomers(commands.getAllConfirmedCustomers());
				
			}
		};
		view.addEventHandlerToshowAllConfirmedCustomers(showAllConfirmedCustomers);
		
		EventHandler<ActionEvent> doneInshowAllConfirmedCustomersIsPressed= new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.closeShowAllConfirmedCustomersWindow();
			}
		};
		view.addEventHandlerTodoneInshowAllConfirmedCustomersIsPressed(doneInshowAllConfirmedCustomersIsPressed);
	}
}
