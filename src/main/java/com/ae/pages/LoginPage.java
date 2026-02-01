package com.ae.pages;

import com.ae.core.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    // Header
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

    public boolean isLoginHeaderVisible() {
        return isVisible(loginHeader, 5);
    }

    @Step("Login expect SUCCESS with email={email}")
    public HomePage loginExpectSuccess(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);
        click(loginBtn);

        HomePage home = new HomePage();

        boolean ok = waitAnyVisible(12,
                home.getLoggedInAsLocator(),
                invalidLoginError
        );

        if (isVisible(invalidLoginError, 1)) {
            throw new AssertionError("Login failed: invalid email/password message appeared.");
        }

        if (!ok) {
            throw new AssertionError("Login result not detected: neither 'Logged in as' nor error appeared.");
        }

        home.waitLoggedInBanner();
        return home;
    }

    @Step("Login expect INVALID creds with email={email}")
    public LoginPage loginExpectInvalid(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);
        click(loginBtn);

        visible(invalidLoginError);
        return this;
    }

    @Step("Is invalid login error visible?")
    public boolean isInvalidLoginErrorVisible() {
        return isVisible(invalidLoginError, 3);
    }
}
