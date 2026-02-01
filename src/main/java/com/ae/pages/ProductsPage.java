package com.ae.pages;

import com.ae.core.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends BasePage {

    private final By allProductsTitle =
            By.xpath("//h2[contains(text(),'All Products')]");

    private final By productsList =
            By.cssSelector(".features_items .product-image-wrapper");

    private final By firstViewProduct =
            By.cssSelector("a[href^='/product_details/']");

    private final By searchInput = By.id("search_product");
    private final By searchButton = By.id("submit_search");

    private final By searchedHeader =
            By.xpath("//h2[contains(text(),'Searched Products')]");

    private final By productNames =
            By.cssSelector(".features_items .productinfo p");

    public ProductsPage waitLoaded() {
        ensureNotOnVignette("https://automationexercise.com/products");
        waitUrlContains("products");
        visible(allProductsTitle);
        return this;
    }


    public ProductDetailPage openFirstProductDetails() {
        visible(productsList);
        click(firstViewProduct);
        return new ProductDetailPage();
    }

    public ProductsPage search(String query) {
        type(searchInput, query);
        click(searchButton);
        return this;
    }

    public boolean isSearchedHeaderVisible() {
        return isVisible(searchedHeader);
    }

    public int shownProductsCount() {
        return driver.findElements(productsList).size();
    }

    public boolean anyProductNameContains(String query) {
        String q = query.toLowerCase();
        List<WebElement> names = driver.findElements(productNames);

        for (WebElement el : names) {
            if (el.getText().toLowerCase().contains(q)) {
                return true;
            }
        }
        return false;
    }


}
