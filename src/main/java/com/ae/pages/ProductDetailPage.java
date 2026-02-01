package com.ae.pages;

import com.ae.core.base.BasePage;
import org.openqa.selenium.By;

public class ProductDetailPage extends BasePage {

    private final By productInformation =
            By.cssSelector(".product-information");

    private final By productName =
            By.cssSelector(".product-information h2");

    private final By category =
            By.xpath("//p[contains(text(),'Category')]");

    private final By price =
            By.cssSelector(".product-information span span");

    private final By availability =
            By.xpath("//b[contains(text(),'Availability')]");

    public ProductDetailPage waitLoaded() {
        visible(productInformation);
        return this;
    }

    public boolean isNameVisible() {
        return isVisible(productName);
    }

    public boolean isCategoryVisible() {
        return isVisible(category);
    }

    public boolean isPriceVisible() {
        return isVisible(price);
    }

    public boolean isAvailabilityVisible() {
        return isVisible(availability);
    }
}
