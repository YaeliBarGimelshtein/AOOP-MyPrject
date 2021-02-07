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
	public static final int ASCENDING = 0;
	public static final int DESCENDING = 1;
	public static final int BY_INPUT_ORDER = 2; 
	public static final String F_NAME = "products.txt";
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
		this.productsFile = new File(F_NAME);
		this.readFromFile = readInforamtionFromFile();
		this.allReceivingClients= new ArrayList<>();
		resetOutputStream();
	}
	
	public void resetOutputStream() {
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
	
	public ModelMemento saveFirstTime() {
		TreeMap<String, Product> copy= new TreeMap<>();
		Set<Map.Entry<String, Product>> productSet = allProducts.entrySet();
		int counter=0;
		
		for (Map.Entry<String, Product> entry : productSet) {
			copy.put(entry.getKey(), entry.getValue());
			counter++;
			if(counter==allProducts.size()-1) {
				return new ModelMemento(copy);
			}
		}
		return null;
	}	
	
	public void load(ModelMemento lastModel) { 
		if(lastModel==null|| lastModel.getAllProducts().size()==0) {
			updateSavingMethod(this.savingMethod);
		}else {
			this.allProducts=lastModel.getAllProducts();
		}
		//removing from file
		Iterator<Product> iterator= iterator();
		while(iterator.hasNext()) {
			iterator.next();
		}
		iterator.remove();
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
			if (productsFile.exists()) {
				Iterator<Product> iterator=iterator();
				Product savingMethodP= iterator.next();
				updateSavingMethod(savingMethodP.getPriceForStore());
				while(iterator.hasNext()) {
					Product catalog= iterator.next();
					Product p= iterator.next();
					allProducts.put(catalog.getName(), p);
				}
			return true;
			}
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
		case ASCENDING:
			this.allProducts= new TreeMap<>(new Comparator<String>() { 
				@Override
				public int compare(String o1, String o2) { 
					return o1.compareTo(o2);
				}
			});
			
			break;
		case DESCENDING:
			this.allProducts=new TreeMap<>(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) { 
					return -(o1.compareTo(o2));
				}
			});
			
			break;
		case BY_INPUT_ORDER:
			this.allProducts=new TreeMap<>(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) { 
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
	
	public void close() {
		try {
			this.oOut.close();
		} catch (IOException e) {
			
		}
	}
	
	
	public void deleteProduct(CareTaker lastStatus,String catalogNumber) {
		lastStatus.save(this.save());
		Iterator<Product> iterator=iterator();
		Product p;
		p=iterator.next(); //for the saving method
		do {
			p=iterator.next(); 
		} while (iterator.hasNext() && !(p.getName().equals(catalogNumber)));
		p=iterator.next(); //to be after product itself
		iterator.remove();
		readInforamtionFromFile();
	}
	
	public void deleteAll(CareTaker lastStatus) {
		lastStatus.save(this.save());
		Iterator<Product> iterator=iterator();
		while(iterator.hasNext()) {
			iterator.remove();
		}
		updateSavingMethod(this.savingMethod);
	}
	
	
	//iterator:
	private class ProductIterator implements Iterator<Product> {
		private ObjectInputStream oIn;
		//private DataInputStream dIn;
		private FileInputStream fIn;
		private RandomAccessFile raf;
		private long fileLenght;
		private long curPosition;
		private long beforeCurPosition;
		private int counter;
		
		public ProductIterator() {
			resetInputStream();
			this.fileLenght=productsFile.length();
			this.curPosition=0;
			this.beforeCurPosition=0;
			this.counter=0;
		}

		@Override
		public boolean hasNext() {
			try {
				if(fIn.available()>0)
					return true;
				return false;
			} catch (IOException e) {
			
			}
			return false;
		}

		@Override
		public Product next() {
			try {
				if (!hasNext()) {
					oIn.close();
					throw new NoSuchElementException();
				}
				counter++;
				if(counter%2==0) {
					this.beforeCurPosition=fileLenght-fIn.available();
				}
				Product product = (Product) oIn.readObject();
				this.curPosition=fileLenght-fIn.available();
				return product;
			} catch (IOException e) {
				
			} catch (Exception e) {
				
			}
			return null;
		}

		@Override
		public void remove() {
			try {
				raf = new RandomAccessFile(productsFile, "rw");
				raf.seek(curPosition);
				if(curPosition==fileLenght) {
					raf.setLength(beforeCurPosition); //deletes the unwanted product
				}else {
					byte[] temp = new byte[(int) (raf.length() - raf.getFilePointer())];
					raf.read(temp); //has the rest of the file without the product
					raf.setLength(beforeCurPosition); //deletes the unwanted product
					raf.seek(beforeCurPosition);
					raf.write(temp); ///writes the rest back
					this.fileLenght= productsFile.length();
					this.curPosition=this.beforeCurPosition;
				}
			} catch (FileNotFoundException e) {
				
			} catch (IOException e) {
				
			}
		}
		
		
		private void resetInputStream() {
			try {
				fIn=new FileInputStream(productsFile);
				oIn = new ObjectInputStream(fIn);
				//dIn= new DataInputStream(new FileInputStream(productsFile));
			} catch (IOException e) {

			}
		}	
	}


	
}
