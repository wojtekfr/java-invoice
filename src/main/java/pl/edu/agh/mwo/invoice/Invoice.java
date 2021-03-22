package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private final int number = Math.abs(new Random().nextInt());
	private Map<Product, Integer> products = new HashMap<Product, Integer>();

	public void addProduct(Product product) {
		addProduct(product, 1);
	}

	public void addProduct(Product product, Integer quantity) {
		if (product == null || quantity <= 0) {
			throw new IllegalArgumentException();
		}
		products.put(product, quantity);
	}

	public BigDecimal getNetTotal() {
		BigDecimal totalNet = BigDecimal.ZERO;
		for (Product product : products.keySet()) {
			BigDecimal quantity = new BigDecimal(products.get(product));
			totalNet = totalNet.add(product.getPrice().multiply(quantity));
		}
		return totalNet;
	}

	public BigDecimal getTaxTotal() {
		return getGrossTotal().subtract(getNetTotal());
	}

	public BigDecimal getGrossTotal() {
		BigDecimal totalGross = BigDecimal.ZERO;
		for (Product product : products.keySet()) {
			BigDecimal quantity = new BigDecimal(products.get(product));
			totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
		}
		return totalGross;
	}

	public int getNumber() {

		return number;
	}

	public List<String> preparePrintOut() {
		List<String> printOut = new ArrayList<>();
		printOut.add(String.valueOf(this.getNumber()));
		for (Product product : products.keySet()) {
			printOut.add(product.getName() + "," + String.valueOf(products.get(product)) +","
					+ String.valueOf(product.getPrice().multiply(new BigDecimal(products.get(product)))));
		}

		printOut.add("Liczba pozycji: " + String.valueOf(products.size()));
		return printOut;
	}

	public Map<Product, Integer> getProducts() {
		return products;
	}

}
