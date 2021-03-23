package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class BottleOfWineProduct extends Product {
	BigDecimal excise;
	protected BottleOfWineProduct(String name, BigDecimal price, BigDecimal excise) {
		super(name, price, new BigDecimal("0.23"));
		this.excise = excise;
	}

	 @Override
	 public BigDecimal getPriceWithTax() {
	        return price.multiply(taxPercent).add(price).add(excise);
	    }
	}
	

