package Model.Command;

import Model.Product;

public interface ModelCommands {
	void addProductByObject(Product p, String catalogNumber);
	void addProductByFields(String catalogNumber, String name, int priceForStore, int priceForCustomer,
			String Cname, String CphoneNumber, boolean intrestedInSales);
	void updateSavingMethod(int orderToSaveProducts);
	boolean findProduct(String catalogNumberToFind);
	String getAllProducts();
	String getAllPrfits();
	
//	void getProduct();

//	void deleteLastProduct();
//	boolean readDataFromFile();
//	void deleteProductFromFile();
//	void deleteAllFromFile();
//	void sendSMS();
//	void showAllConfirmedCustomers();
//	void updateSavingMethod();
//	void writeSavingMethodToFile();
}
