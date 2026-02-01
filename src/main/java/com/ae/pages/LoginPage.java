package com.ae.pages;

import com.ae.core.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    // Headers
    private final By loginHeader = By.xpath("//*[contains(text(),'Login to your account')]");

    // Inputs
    private final By emailInput = By.cssSelector("input[data-qa='login-email']");
    private final By passwordInput = By.cssSelector("input[data-qa='login-password']");
    private final By loginBtn = By.cssSelector("button[data-qa='login-button']");

    // Error
    private final By invalidLoginError = By.xpath("//*[contains(text(),'Your email or password is incorrect')]");

    @Step("Wait Login page loaded")
    public LoginPage waitLoaded() {
        visible(loginHeader);
        return this;
    }

    @Step("Login with email={email}")
    public HomePage login(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);
        click(loginBtn);
        return new HomePage();
    }

    @Step("Is invalid login error visible?")
    public boolean isInvalidLoginErrorVisible() {
        try {
            return visible(invalidLoginError).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
