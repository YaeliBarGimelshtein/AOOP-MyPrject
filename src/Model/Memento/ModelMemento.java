package Model.Memento;

import java.util.TreeMap;
import Model.Product;

public class ModelMemento {
	private TreeMap<String, Product> allProducts;

	public ModelMemento(TreeMap<String, Product> allProducts) {
		this.allProducts = allProducts;
	}

	public TreeMap<String, Product> getAllProducts() {
		return allProducts;
	}
}
