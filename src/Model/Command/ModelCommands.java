package Model.Command;

import java.util.ArrayList;

public interface ModelCommands {
	//void addProductByObject(Product p, String catalogNumber);
	void addProductByFields(String catalogNumber, String name, int priceForStore, int priceForCustomer,
			String Cname, String CphoneNumber, boolean intrestedInSales);
	void updateSavingMethod(String orderToSaveProducts);
	boolean findProduct(String catalogNumberToFind);
	String getAllProducts();
	String getAllPrfits();
	void undo();
	void sendSMS();
	ArrayList<String> getAllConfirmedCustomers();
	boolean checkIfAreProducts();
	void deleteProduct(String catalogNumber);
	void deleteAllProducts();
	void close();



	
	
	



}
