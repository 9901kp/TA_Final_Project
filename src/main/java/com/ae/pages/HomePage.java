package com.ae.pages;

import com.ae.core.base.BasePage;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final By signupLoginLink = By.cssSelector("a[href='/login']");
    private final By productsLink = By.cssSelector("a[href='/products']");
    private final By logoutLink = By.cssSelector("a[href='/logout']");
    private final By loggedInAs = By.xpath("//*[contains(text(),'Logged in as')]");

    public HomePage open(String url) {
        driver.get(url);
        return this;
    }

    public String getLoggedInAsText() {
        return text(loggedInAs);
    }

    public LoginPage goToLogin() {
        click(signupLoginLink);
        return new LoginPage().waitLoaded();
    }

    public ProductsPage goToProducts() {
        click(productsLink);
        return new ProductsPage();
    }

    public LoginPage logout() {
        click(logoutLink);
        return new LoginPage().waitLoaded();
    }

    public boolean isLoggedInBannerVisible() {
        return isVisible(loggedInAs);
    }
}
