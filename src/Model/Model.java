package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
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
	//frr singelton
	private static Model model;
	//for file
	private boolean readFromFile;
	private File productsFile;
	private ObjectOutputStream oOut;
	//for data
	private TreeMap<String, Product> allProducts;
	private ArrayList<String> allReceivingClients;
	private int savingMethod;
	
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
		TreeMap<String, Product> copy= new TreeMap<>();
		copy.putAll(this.allProducts);
		return new ModelMemento(copy);
	}
	
	public void load(ModelMemento lastModel) { 
		if(lastModel.getAllProducts().size()==0) {
			updateSavingMethod(this.savingMethod);
		}else {
			this.allProducts=lastModel.getAllProducts();
		}
		
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
			productsFile = new File("products.txt");
//			if (productsFile.exists()) {
//				Iterator<Product> iterator=iterator();
//				Product savingMethodP= iterator.next();
//				updateSavingMethod(savingMethodP.getPriceForStore());
//				while(iterator.hasNext()) {
//					Product catalog= iterator.next();
//					Product p= iterator.next();
//					allProducts.put(catalog.getName(), p);
//				}
//				
//			}
//			return true;
		} catch (Exception e) {
			
		}
		return false;
	}
	
	


	public boolean getReadFromFile() {
		return this.readFromFile;
	}
	
	public Iterator<Product> iterator() {
		return new ProductIterator();
	}

	public void updateSavingMethod(int orderToSaveProducts) {
		this.savingMethod=orderToSaveProducts;
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
			Product savingMethod= new Product("", orderToSaveProducts, 0, null);
			oOut.writeObject(savingMethod);
		} catch (IOException e) {
			
		}
	}

	public void addProduct(CareTaker lastStatus,String catalogNumber, String name, int priceForStore, int priceForCustomer,
			String Cname, String CphoneNumber, boolean intrestedInSales) {
		if(priceForCustomer<0) 
			priceForCustomer=0;
		
		if(priceForStore<0) 
			priceForStore=0;
		
		Customer boughtBy= new Customer(Cname, CphoneNumber, intrestedInSales);
		Product p= new Product(name, priceForStore, priceForCustomer, boughtBy);
		addProduct(lastStatus,p, catalogNumber);
	}
	
	public void addProduct(CareTaker lastStatus, Product p, String catalogNumber) {
		lastStatus.save(this.save());
		allProducts.put(catalogNumber, p);
		writeProductToFile(p,catalogNumber);
	}

	public void writeProductToFile(Product p, String catalogNumber) {
		try {
			Product catalog= new Product(catalogNumber, 0, 0, null);
			oOut.writeObject(catalog);
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
		if(allProducts.size()==0) {
			all="No products";
			return all;
		}
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
	
	public ArrayList<String> getAllConfirmedCustomers() {
		if(this.allReceivingClients.size()==0) 
			return null;
		return this.allReceivingClients;
	}


	public boolean checkIfAreProducts() {
		if(this.allProducts.size()==0)
			return false;
		return true;
	}


	public void resetAllConfirmedCustomers() {
		this.allReceivingClients=new ArrayList<>();
	}
	
	
	//iterator:
	private class ProductIterator implements Iterator<Product> {
		private ObjectInputStream oIn;
		private RandomAccessFile raf;
		private long fileLenght;
		private long curPosition;
		private long beforeCurPosition;
		
		public ProductIterator() {
			try {
				resetInputStream();
				fileLenght=oIn.available();
				curPosition=0;
				beforeCurPosition=0;
			} catch (IOException e) {
				
			}
			
		}

		@Override
		public boolean hasNext() {
			try {
				if(oIn.available()>0)
					return true;
				return false;
			} catch (IOException e) {
			
			}
			return false;
		}

		@Override
		public Product next() {
			try {
				if (!hasNext())
					throw new NoSuchElementException();
				this.beforeCurPosition=fileLenght-oIn.available();
				Product product = (Product) oIn.readObject();
				this.curPosition=fileLenght-oIn.available();
				return product;
			} catch (IOException e) {
				
			} catch (Exception e) {
				
			}
			return null;
		}

		@Override
		public void remove() {
			try {
				raf = new RandomAccessFile("productsFile", "rw");
				raf.seek(curPosition);
				byte[] temp = new byte[(int) (raf.length() - raf.getFilePointer())];
				raf.read(temp); //has the rest of the file without the product
				raf.setLength(beforeCurPosition); //deletes the unwanted product
				raf.write(temp); ///writes the rest back
				
			} catch (FileNotFoundException e) {
				
			} catch (IOException e) {
				
			}
		}
		
		private void resetInputStream() {
			try {
				oIn = new ObjectInputStream(new FileInputStream(productsFile));
			} catch (IOException e) {

			}
		}

	}


	

}
