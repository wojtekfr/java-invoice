package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class FuelCanisterProduct extends Product {
	BigDecimal excise;

//ze względu na niestabilność decyzji rządu i nagłe znoszenie podatków w z okazji dziwnych świąt,
//vat dla paliwa nie będzie zaszyty w kodzie, tylko należy go każdorazowo podawać przy tworzenie produktu z tej kategorii  
	protected FuelCanisterProduct(String name, BigDecimal price, BigDecimal excise, BigDecimal vat) {
		super(name, price, vat);
		this.excise=excise;

	}

	@Override
	 public BigDecimal getPriceWithTax() {
	        return price.multiply(taxPercent).add(price).add(excise);
	    }
}
