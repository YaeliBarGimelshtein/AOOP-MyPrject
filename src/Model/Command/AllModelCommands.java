package Model.Command;

import java.util.ArrayList;

import Model.Model;
import Model.Product;
import Model.Memento.CareTaker;

public class AllModelCommands implements ModelCommands{
	private AddProductCommand addProduct;
	private UpdateSavingMethodCommand updateSavingMethod;
	private findProductCommand findProduct;
	private getAllProductsCommand getAllProducts;
	private gelAllProfitsCommand gelAllProfits;
	private Product found;
	private undoCommand undoCommand;
	private CareTaker lastStatus;
	private sendSMSCommand sentSMS;
	private getAllConfirmedCustomers allConfirmed;
	private checkIfAreProductsCommand areProducts;

	public AllModelCommands(Model model) {
		this.addProduct= new AddProductCommand(model);
		this.updateSavingMethod= new UpdateSavingMethodCommand(model);
		this.findProduct= new findProductCommand(model);
		this.getAllProducts= new getAllProductsCommand(model);
		this.gelAllProfits=new gelAllProfitsCommand(model);
		this.lastStatus= new CareTaker();
		this.undoCommand= new undoCommand(model);
		this.sentSMS= new sendSMSCommand(model);
		this.allConfirmed= new getAllConfirmedCustomers(model);
		this.areProducts=new checkIfAreProductsCommand(model);
	}

	@Override
	public void addProductByObject(Product p, String catalogNumber) {
		addProduct.addProductByObject(lastStatus,p, catalogNumber);
	}

	@Override
	public void addProductByFields(String catalogNumber, String name, int priceForStore, int priceForCustomer,
			String Cname, String CphoneNumber, boolean intrestedInSales) {
		addProduct.addProductByFields(lastStatus,catalogNumber, name, priceForStore, priceForCustomer, Cname, CphoneNumber, intrestedInSales);
	}

	@Override
	public void updateSavingMethod(int orderToSaveProducts) {
		updateSavingMethod.updateSavingMethod(orderToSaveProducts);
	}

	@Override
	public boolean findProduct(String catalogNumberToFind) {
		found=findProduct.findProduct(catalogNumberToFind);
		if(found==null)
			return false;
		return true;
	}

	@Override
	public String getAllProducts() {
		return getAllProducts.getAllProducts();
	}

	@Override
	public String getAllPrfits() {
		return gelAllProfits.getAllPrfits();
	}
	
	
	public String getFoundPriceForStore() {
		return ""+this.found.getPriceForStore();
	}
	
	public String getFoundPriceForCustomer() {
		return ""+this.found.getPriceForCustomer();
	}
	
	public String getFoundCusName() {
		return this.found.getBoughtBy().getName();
	}
	
	public String getFoundName() {
		return this.found.getName();
	}
	
	public String getFoundCusPhone() {
		return this.found.getBoughtBy().getPhoneNumber();
	}
	
	public String getFoundCusIntrestedInSales() {
		boolean intrested=this.found.getBoughtBy().isIntrestedInSales();
		if(intrested)
			return "True";
		return "False";
	}

	public void undo() {
		this.undoCommand.undo(lastStatus.undo());
	}

	@Override
	public void sendSMS() {
		this.sentSMS.sendSMS();
	}

	@Override
	public ArrayList<String> getAllConfirmedCustomers() {
		return this.allConfirmed.getAllConfirmed();
	}

	@Override
	public boolean checkIfAreProducts() {
		return this.areProducts.checkIfAreProducts();
	}
}
