package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import pl.edu.agh.mwo.invoice.product.Product;

public class ProductTest {
    @Test
    public void testProductNameIsCorrect() {
        Product product = new OtherProduct("buty", new BigDecimal("100.0"));
        Assert.assertEquals("buty", product.getName());
    }

    @Test
    public void testProductPriceAndTaxWithDefaultTax() {
        Product product = new OtherProduct("Ogorki", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("100"), Matchers.comparesEqualTo(product.getPrice()));
        Assert.assertThat(new BigDecimal("0.23"), Matchers.comparesEqualTo(product.getTaxPercent()));
    }

    @Test
    public void testProductPriceAndTaxWithDairyProduct() {
        Product product = new DairyProduct("Szarlotka", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("100"), Matchers.comparesEqualTo(product.getPrice()));
        Assert.assertThat(new BigDecimal("0.08"), Matchers.comparesEqualTo(product.getTaxPercent()));
    }

    @Test
    public void testPriceWithTax() {
        Product product = new DairyProduct("Oscypek", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("108"), Matchers.comparesEqualTo(product.getPriceWithTax()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNullName() {
        new OtherProduct(null, new BigDecimal("100.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithEmptyName() {
        new TaxFreeProduct("", new BigDecimal("100.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNullPrice() {
        new DairyProduct("Banany", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNegativePrice() {
        new TaxFreeProduct("Mandarynki", new BigDecimal("-1.00"));
    }


@Test
public void testBottleOfWineProductPriceWithVatAndExcise() {
    Product product = new BottleOfWineProduct("Arizona", new BigDecimal("15.0"), new BigDecimal("5.56"));
    Assert.assertThat(new BigDecimal("24.01"), Matchers.comparesEqualTo(product.getPriceWithTax()));
}

@Test
public void testFuelCanisterProductPriceWithVatAndExcise() {
    Product product = new FuelCanisterProduct("Obajtek", new BigDecimal("55"), new BigDecimal("5.56"), new BigDecimal("0.23"));
    Assert.assertThat(new BigDecimal("73.21"), Matchers.comparesEqualTo(product.getPriceWithTax()));
}

@Test
public void testFuelCanisterProductPriceWithTaxZeroAndExcise() {
    Product product = new FuelCanisterProduct("Obajtek", new BigDecimal("55"), new BigDecimal("5.56"),BigDecimal.ZERO);
    Assert.assertThat(new BigDecimal("60.56"), Matchers.comparesEqualTo(product.getPriceWithTax()));
}


}