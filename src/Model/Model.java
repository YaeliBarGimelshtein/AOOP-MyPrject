package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import Model.Memento.CareTaker;
import Model.Memento.ModelMemento;



public class Model {
	public static final String F_NAME = "products.txt";
	//for singelton
	private static Model model;
	//for file
	private boolean readFromFile;
	private File productsFile;
	private RandomAccessFile raf;
	private long positionAfterSavingMethod;
	//for data
	private TreeMap<String, Product> allProducts;
	private ArrayList<String> allReceivingClients;
	private String savingMethod;
	
	//model is a singelton
	public static Model getModel() {
		if(model==null) {
			model=new Model();
		}
		return model;
	}
	
	private Model() {
		try {
			this.productsFile = new File(F_NAME);
			this.raf= new RandomAccessFile(productsFile, "rw");
			this.readFromFile = readInforamtionFromFile();
			this.allReceivingClients= new ArrayList<>();
			this.positionAfterSavingMethod=0;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ModelMemento save() {
		TreeMap<String, Product> copy= new TreeMap<>();
		copy.putAll(this.allProducts);
		return new ModelMemento(copy);
	}	
	
	public void load(ModelMemento lastModel) { 
		if(lastModel==null|| lastModel.getAllProducts().size()==0) {
			updateSavingMethod(this.savingMethod);
		}else {
			this.allProducts=lastModel.getAllProducts();
		}
		//rewriting to file
		deleteAll(null);
		writeAllProductsToFile();
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
			if (raf.length()>0) {
				Iterator<Entry<String, Product>> iterator=iterator();
				updateSavingMethod(this.savingMethod);
				while(iterator.hasNext()) {
					Entry<String, Product> next= iterator.next();
					allProducts.put(next.getKey(), next.getValue());
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
	
	public Iterator<Map.Entry<String,Product>> iterator() {
		return new ProductIterator();
	}

	public void updateSavingMethod(String orderToSaveProducts) {
		this.savingMethod=orderToSaveProducts;
		switch (orderToSaveProducts) {
		case "ASCENDING":
			this.allProducts= new TreeMap<>(new Comparator<String>() { 
				@Override
				public int compare(String o1, String o2) { 
					return o1.compareTo(o2);
				}
			});
			break;
		case "DESCENDING":
			this.allProducts=new TreeMap<>(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) { 
					return -(o1.compareTo(o2));
				}
			});
			break;
		case "BY_INPUT_ORDER":
			this.allProducts=new TreeMap<>(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) { 
					return 1;
				}
			});
			break;
		}
	}
	
	public void writeSavingMethodToFile(String orderToSaveProducts) {
		try {
			raf.writeUTF(orderToSaveProducts);
			this.positionAfterSavingMethod=raf.getFilePointer();
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
		lastStatus.save(this.save());
		allProducts.put(catalogNumber, p);
		writeAllProductsToFile();
	}

	public void writeAllProductsToFile() {
		try {
			this.raf.seek(positionAfterSavingMethod);
			Set<Map.Entry<String, Product>> productSet = allProducts.entrySet();
			for (Map.Entry<String, Product> entry : productSet) {
				this.raf.writeUTF(entry.getKey());
				entry.getValue().writeToFile(raf);
			}
			this.raf.setLength(this.raf.getFilePointer());
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
			this.raf.close();
		} catch (IOException e) {
			
		}
	}
	
	
	public void deleteProduct(CareTaker lastStatus,String catalogNumber) {
		if(lastStatus!=null)
			lastStatus.save(this.save());
		Iterator<Entry<String, Product>> iterator=iterator();
		while (iterator.hasNext()&& !(catalogNumber.equals(iterator.next().getKey()))) {
			iterator.next();
		}
		iterator.remove();
		if(lastStatus!=null)
			readInforamtionFromFile();
	}
	
	public void deleteAll(CareTaker lastStatus) {
		if(lastStatus!=null)
			lastStatus.save(this.save());
		Iterator<Entry<String, Product>> iterator=iterator();
		while(iterator.hasNext()) {
			deleteProduct(null, iterator.next().getKey());
		}
		readInforamtionFromFile();
	}
	
	
	//iterator
	private class ProductIterator implements Iterator<Map.Entry<String,Product>> {
		private long beforeCurPosition;
		public ProductIterator() {
			try {
				raf.seek(0);
				savingMethod=raf.readUTF();
				positionAfterSavingMethod=raf.getFilePointer();
				this.beforeCurPosition=0;
			} catch (IOException e) {
				
			}
		}

		@Override
		public boolean hasNext() {
			try {
				if(raf.getFilePointer()<raf.length())
					return true;
				return false;
			} catch (IOException e) {
			
			}
			return false;
		}

		@Override
		public Entry<String, Product> next() {
			try {
				if (!hasNext()) {
					raf.close();
					throw new NoSuchElementException();
				}
				this.beforeCurPosition=raf.getFilePointer();
				Entry<String, Product> next=new AbstractMap.SimpleEntry<String, Product>(raf.readUTF(), new Product(raf));
				return next;
			} catch (IOException e) {
				
			} catch (Exception e) {
				
			}
			return null;
		}

		@Override
		public void remove() {
			try {
				raf.seek(raf.getFilePointer());
				if(raf.getFilePointer()==raf.length()) {
					raf.setLength(beforeCurPosition); //deletes the unwanted product
				}else {
					byte[] temp = new byte[(int) (raf.length() - raf.getFilePointer())];
					raf.read(temp); //has the rest of the file without the product
					raf.setLength(beforeCurPosition); //deletes the unwanted product
					raf.write(temp); ///writes the rest back
				}
			} catch (FileNotFoundException e) {
				
			} catch (IOException e) {
				
			}
		}	
	}
}
