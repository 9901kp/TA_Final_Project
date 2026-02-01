package com.ae.ui.tests.products;

import com.ae.pages.HomePage;
import com.ae.pages.ProductDetailPage;
import com.ae.pages.ProductsPage;
import com.ae.ui.base.BaseTest;
import com.ae.ui.listeners.AllureTestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AllureTestListener.class)
public class AllProductsTest extends BaseTest {

    @Test(description = "TC-8: Verify All Products and product detail page")
    public void verifyAllProductsAndDetails_shouldShowProductInfo() {

        ProductsPage products = new HomePage()
                .goToProducts()
                .waitLoaded();

        ProductDetailPage details = products.openFirstProductDetails().waitLoaded();

        Assert.assertTrue(details.isNameVisible(), "Product name should be visible");
        Assert.assertTrue(details.isCategoryVisible(), "Category should be visible");
        Assert.assertTrue(details.isPriceVisible(), "Price should be visible");
        Assert.assertTrue(details.isAvailabilityVisible(), "Availability should be visible");
    }
}
