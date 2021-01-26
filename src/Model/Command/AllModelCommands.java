package Model.Command;

import Model.Model;
import Model.Product;

public class AllModelCommands implements ModelCommands{
	private AddProductCommand addProduct;
	private UpdateSavingMethodCommand updateSavingMethod;
	private findProductCommand findProduct;
	private getAllProductsCommand getAllProducts;
	private gelAllProfitsCommand gelAllProfits;
	

	public AllModelCommands(Model model) {
		this.addProduct= new AddProductCommand(model);
		this.updateSavingMethod= new UpdateSavingMethodCommand(model);
		this.findProduct= new findProductCommand(model);
		this.getAllProducts= new getAllProductsCommand(model);
		this.gelAllProfits=new gelAllProfitsCommand(model);
	}

	@Override
	public void addProductByObject(Product p, String catalogNumber) {
		addProduct.addProductByObject(p, catalogNumber);
	}

	@Override
	public void addProductByFields(String catalogNumber, String name, int priceForStore, int priceForCustomer,
			String Cname, String CphoneNumber, boolean intrestedInSales) {
		addProduct.addProductByFields(catalogNumber, name, priceForStore, priceForCustomer, Cname, CphoneNumber, intrestedInSales);
	}

	@Override
	public void updateSavingMethod(int orderToSaveProducts) {
		updateSavingMethod.updateSavingMethod(orderToSaveProducts);
	}

	@Override
	public void findProduct(String catalogNumberToFind) {
		findProduct.findProduct(catalogNumberToFind);
	}

	@Override
	public String getAllProducts() {
		return getAllProducts.getAllProducts();
	}

	@Override
	public String getAllPrfits() {
		return gelAllProfits.getAllPrfits();
	}

}
