package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.TreeMap;

public class Model {
	//for file
	private boolean readFromFile;
	private File productsFile;
	private ObjectOutputStream oOut;
	private ObjectInputStream oIn;
	//for data
	private TreeMap<String, Product> allProducts;
	private Product found;
	
	
	public Model() {
		//read from file if possible
		this.readFromFile = readInforamtionFromFile();
		// to be able to write
		try {
			if (readFromFile) {
				oOut = new ObjectOutputStream(new FileOutputStream(productsFile, readFromFile)) {
					@Override
					protected void writeStreamHeader() throws IOException {
						return;
					}
				};
			} else {
				oOut = new ObjectOutputStream(new FileOutputStream(productsFile, readFromFile));
			}
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
	}
	
	public boolean readInforamtionFromFile() {
		try {
			if (productsFile!=null && productsFile.exists()) {
				
				//this is bad. needs to be with iterator
				oIn = new ObjectInputStream(new FileInputStream(productsFile));
				int orderToSaveProducts= oIn.readInt();
				updateSavingMethod(orderToSaveProducts);
				while (oIn.available() != 0) {
					String catalogNumber= oIn.readUTF();
					Product product = (Product) oIn.readObject();
					addProduct(product,catalogNumber);
				}
				return true;
			}
			else {
				productsFile = new File("products.txt");
			}
		} catch (IOException e) {
		
		} catch (Exception e) {
			
		}
		return false;
	}
	
	public boolean getReadFromFile() {
		return this.readFromFile;
	}


	public void updateSavingMethod(int orderToSaveProducts) {
		switch (orderToSaveProducts) {
		case 0:
			this.allProducts= new TreeMap<>(new Comparator<String>() { //Ascending
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			
			break;
		case 1:
			this.allProducts=new TreeMap<>(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) { //Descending
					return -(o1.compareTo(o2));
				}
			});
			
			break;
		case 2:
			this.allProducts=new TreeMap<>(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) { //By input order
					return 1;
				}
			});
			break;
		}
	}
	
	public void writeSavingMethodToFile(int orderToSaveProducts) {
		try {
			oOut.writeInt(orderToSaveProducts);
		} catch (IOException e) {
			
		}
	}

	public void addProduct(String catalogNumber, String name, int priceForStore, int priceForCustomer,
			String Cname, String CphoneNumber, boolean intrestedInSales) {
		Customer boughtBy= new Customer(Cname, CphoneNumber, intrestedInSales);
		Product p= new Product(name, priceForStore, priceForCustomer, boughtBy);
		addProduct(p, catalogNumber);
	}
	
	public void addProduct(Product p, String catalogNumber) {
		allProducts.put(catalogNumber, p);
		writeProductToFile(p,catalogNumber);
	}

	public void writeProductToFile(Product p, String catalogNumber) {
		try {
			oOut.writeUTF(catalogNumber);
			oOut.writeObject(p);
		} catch (IOException e) {
			
		}
	}
		
	public void findProduct(String catalogNumberToFind) {
		this.found=null;
	}
	
	public int getFoundPriceForStore() {
		return this.found.priceForStore;
	}
	
	public int getFoundPriceForCustomer() {
		return this.found.priceForCustomer;
	}
	
	public String getFoundCusName() {
		return this.found.boughtBy.name;
	}
	
	public String getFoundCusPhone() {
		return this.found.boughtBy.phoneNumber;
	}
	
	public String getFoundCusIntrestedInSales() {
		boolean intrested=this.found.boughtBy.intrestedInSales;
		if(intrested)
			return "True";
		return "False";
	}

	public String getAllProducts() {
		return "abc down\n needs to be down\n is down?";
	}

	public String getAllPrfits() {
		return "Profit 1 ....\nProfit 2 .... \nAll Profits ...";
	}

}
