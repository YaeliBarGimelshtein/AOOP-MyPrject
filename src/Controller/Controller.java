package Controller;

import Model.Model;
import View.View;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Toggle;

public class Controller {

	private Model model;
	private View view;
	
	public Controller(Model theModel, View theView, boolean readFromFile) {
		this.model=theModel;
		this.view=theView;
		
		if(!readFromFile) {
			ChangeListener<Toggle> savingMethodPicked= new ChangeListener<Toggle>() {
				@Override
				public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
					model.updateSavingMethod(view.getOrderToSaveProducts());
					model.writeSavingMethodToFile(view.getOrderToSaveProducts());
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
						model.addProduct(view.getCatalogNumber(),view.getNewProductName(), view.getNewProductPriceForStore(),
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
		
		
		
		
		
	}
}
