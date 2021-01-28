package Model.Command;

import java.util.ArrayList;
import Model.Product;

public interface ModelCommands {
	void addProductByObject(Product p, String catalogNumber);
	void addProductByFields(String catalogNumber, String name, int priceForStore, int priceForCustomer,
			String Cname, String CphoneNumber, boolean intrestedInSales);
	void updateSavingMethod(int orderToSaveProducts);
	boolean findProduct(String catalogNumberToFind);
	String getAllProducts();
	String getAllPrfits();
	void undo();
	void sendSMS();
	ArrayList<String> getAllConfirmedCustomers();
//	void getProduct();

//	void deleteLastProduct();
//	boolean readDataFromFile();
//	void deleteProductFromFile();
//	void deleteAllFromFile();
	



}
