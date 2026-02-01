package com.ae.ui.tests.products;

import com.ae.config.ConfigLoader;
import com.ae.pages.HomePage;
import com.ae.pages.ProductsPage;
import com.ae.ui.base.BaseTest;
import com.ae.ui.listeners.AllureTestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AllureTestListener.class)
public class SearchProductUiTest extends BaseTest {

    @Test(description = "TC-9: Search Product")
    public void searchProduct_shouldReturnMatchingResults() {
        String baseUrl = ConfigLoader.get("baseUrl");
        String query = "dress";

        ProductsPage products = new HomePage()
                .open(baseUrl)
                .goToProducts()
                .waitLoaded()
                .search(query);

        Assert.assertTrue(products.isSearchedHeaderVisible(), "Expected 'Searched Products' header");
        Assert.assertTrue(products.shownProductsCount() > 0, "Expected at least 1 product shown after search");
        Assert.assertTrue(products.anyProductNameContains(query),
                "Expected at least one product name to contain query: " + query);
    }
}
