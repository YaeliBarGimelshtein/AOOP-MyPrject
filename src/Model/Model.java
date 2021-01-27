package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;

import Model.Memento.CareTaker;
import Model.Memento.ModelMemento;



public class Model {
	//fir singelton
	private static Model model;
	//for file
	private boolean readFromFile;
	private File productsFile;
	private ObjectOutputStream oOut;
	private ObjectInputStream oIn;
	//for data
	private TreeMap<String, Product> allProducts;
	private ArrayList<String> allReceivingClients;
	//private Product found;
	
	
	//model is a singelton
	public static Model getModel() {
		if(model==null) {
			model=new Model();
		}
		return model;
	}
	
	
	private Model() {
		//read from file if possible
		this.readFromFile = readInforamtionFromFile();
		this.allReceivingClients= new ArrayList<>();
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
	
	public ModelMemento save() {
		return new ModelMemento(this.allProducts);
	}
	
	public void load(ModelMemento lastModel) {
		this.allProducts=lastModel.getAllProducts();
		//delete from file last product!!!!!
	}
	
	// Notify
	public void sendSMS() {
		Set<Map.Entry<String, Product>> productSet = allProducts.entrySet();
		for (Map.Entry<String, Product> entry : productSet) {
				this.allReceivingClients.add(entry.getValue().getBoughtBy().getSMS());
			}
	}
	
	public boolean readInforamtionFromFile() {
		try {
			if (productsFile!=null && productsFile.exists()) {
//				Iterator<String> iterator = iterator();
//				if(iterator.hasNext()) {
//					int orderToSaveProducts=Integer.parseInt(iterator.next());
//				}
//				while(iterator.hasNext()) {
//					String catalogAndProduct= iterator.next();
//					String[] catalogAndProductArr= catalogAndProduct.split("&");
//					
//					//addProduct(product,catalogAndProductArr[0]);
//				}
//				
				//this is bad. needs to be with iterator
				//oIn = new ObjectInputStream(new FileInputStream(productsFile));
				//int orderToSaveProducts= oIn.readInt();
				//updateSavingMethod(orderToSaveProducts);
				//while (oIn.available() != 0) {
				//	String catalogNumber= oIn.readUTF();
				//	Product product = (Product) oIn.readObject();
					
				//}
				return true;
			}
			else {
				productsFile = new File("products.txt");
			}
		} catch (Exception e) {
			
		}
		return false;
	}
	
	public boolean getReadFromFile() {
		return this.readFromFile;
	}
	
	public Iterator<String> iterator() {
		return new ProductIterator();
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

	public void addProduct(CareTaker lastStatus,String catalogNumber, String name, int priceForStore, int priceForCustomer,
			String Cname, String CphoneNumber, boolean intrestedInSales) {
		Customer boughtBy= new Customer(Cname, CphoneNumber, intrestedInSales);
		Product p= new Product(name, priceForStore, priceForCustomer, boughtBy);
		addProduct(lastStatus,p, catalogNumber);
	}
	
	public void addProduct(CareTaker lastStatus, Product p, String catalogNumber) {
		allProducts.put(catalogNumber, p);
		lastStatus.save(this.save());
		writeProductToFile(p,catalogNumber);
	}

	public void writeProductToFile(Product p, String catalogNumber) {
		try {
			oOut.writeUTF(catalogNumber);
			oOut.writeObject(p);
		} catch (IOException e) {
			
		}
	}
		
	public Product findProduct(String catalogNumberToFind) {
		Set<Map.Entry<String, Product>> productSet = allProducts.entrySet();
		for (Map.Entry<String, Product> entry : productSet) {
			if(entry.getKey().equals(catalogNumberToFind)) {
				return entry.getValue();
			}
		}
		return null;
	}
	

	public String getAllProducts() {
		String all="All products in the system:\n";
		Set<Map.Entry<String, Product>> productSet = allProducts.entrySet();
		for (Map.Entry<String, Product> entry : productSet) {
				all+=entry.getValue().toString();
			}
		return all;
	}

	public String getAllPrfits() {
		String all="All profits in the system:\n";
		int i=1;
		int totalProfit=0;
		Set<Map.Entry<String, Product>> productSet = allProducts.entrySet();
		for (Map.Entry<String, Product> entry : productSet) {
				all+="Product "+i+" :";
				all+=entry.getValue().getProfit()+"₪\n";
				totalProfit+=entry.getValue().getProfit();
				i++;
			}
		all+="Total profit: "+totalProfit+"₪";
		return all;
	}
	
	
	
	//iterator:
	private class ProductIterator implements Iterator<String> {
		private long startOfFile = 0; // the index of element that 'next' will return
		//private int last = -1; // the index of the element to be removed
		
		public ProductIterator() {
			try {
				oIn = new ObjectInputStream(new FileInputStream(productsFile));
				startOfFile= oIn.available();
			} catch (IOException e) {

			}
			
		}

		@Override
		public boolean hasNext() {
			try {
				return 0 < oIn.available();
			} catch (IOException e) {
			
			}
			return false;
		}

		@Override
		public String next() {
			try {
				if (!hasNext())
					throw new NoSuchElementException();
				
				if (startOfFile == oIn.available()) {
					return ""+oIn.readInt();
				}else {
					String catalogNumber= oIn.readUTF();
					Product product = (Product) oIn.readObject();
					return catalogNumber+"&"+product.toString();
				}
			} catch (IOException e) {
				
			} catch (Exception e) {
				
			}
			return "";
		}

		@Override
		public void remove() {
		
		}

	}

}
