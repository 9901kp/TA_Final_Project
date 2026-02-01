package com.ae.pages;

import com.ae.core.base.BasePage;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final By signupLoginLink = By.cssSelector("a[href='/login']");
    private final By productsLink = By.cssSelector("a[href='/products']");
    private final By logoutLink = By.cssSelector("a[href='/logout']");
    private final By loggedInAs = By.xpath("//*[contains(text(),'Logged in as')]");

    // Contact Us locators
    private final By contactUsLink = By.cssSelector("a[href='/contact_us']");
    private final By nameInput = By.name("name");
    private final By emailInput = By.name("email");
    private final By subjectInput = By.name("subject");
    private final By messageTextarea = By.id("message");
    private final By submitButton = By.name("submit");
    private final By successMessage = By.cssSelector(".status.alert-success");

    public By getLoggedInAsLocator() {
        return loggedInAs;
    }

    public By getLogoutLocator() {
        return logoutLink;
    }

    public HomePage open(String url) {
        driver.get(url);
        return this;
    }

    public HomePage open() {
        driver.get("https://automationexercise.com/");
        return this;
    }

    public String getLoggedInAsText() {
        return text(loggedInAs);
    }

    public HomePage waitLoggedInBanner() {
        visible(loggedInAs);
        return this;
    }

    public boolean isLoggedInBannerVisible() {
        return isVisible(loggedInAs, 10);
    }

    public boolean isLogoutVisible() {
        return isVisible(logoutLink, 10);
    }

    public LoginPage goToLogin() {
        click(signupLoginLink);
        return new LoginPage().waitLoaded();
    }

    public ProductsPage goToProducts() {
        click(productsLink);

        if (driver.getCurrentUrl().contains("google_vignette")) {
            driver.get("https://automationexercise.com/products");
        }

        return new ProductsPage();
    }


    public LoginPage logout() {
        click(logoutLink);
        return new LoginPage().waitLoaded();
    }

    public HomePage goToContactUs() {
        click(contactUsLink);
        return this;
    }

    public HomePage waitLoaded() {
        waitUrlContains("contact");
        return this;
    }

    public void fillContactForm(String name, String email, String subject, String message) {
        type(nameInput, name);
        type(emailInput, email);
        type(subjectInput, subject);
        type(messageTextarea, message);
    }

    public void submitContactForm() {
        click(submitButton);
        acceptAlertIfPresent();
    }

    public boolean isContactSuccessMessageVisible() {
        return isVisible(successMessage, 10);
    }
}
