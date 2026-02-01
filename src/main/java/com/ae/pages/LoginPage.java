package com.ae.pages;

import com.ae.core.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By loginHeader = By.xpath("//*[contains(text(),'Login to your account')]");

    private final By emailInput = By.cssSelector("input[data-qa='login-email']");
    private final By passwordInput = By.cssSelector("input[data-qa='login-password']");
    private final By loginBtn = By.cssSelector("button[data-qa='login-button']");

    private final By invalidLoginError = By.xpath("//*[contains(text(),'Your email or password is incorrect')]");

    @Step("Wait Login page loaded")
    public LoginPage waitLoaded() {
        visible(loginHeader);
        return this;
    }

    public boolean isLoginHeaderVisible() {
        return isVisible(loginHeader, 8);
    }

    @Step("Login expect SUCCESS with email={email}")
    public HomePage loginExpectSuccess(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);

        // 1st attempt
        click(loginBtn);
        waitPageReady();

        HomePage home = new HomePage();

        boolean detected = waitAnyVisible(25,
                home.getLoggedInAsLocator(),
                home.getLogoutLocator(),
                invalidLoginError
        );

        // If error appeared -> fail immediately
        if (isVisible(invalidLoginError, 2)) {
            throw new AssertionError("Login failed: invalid email/password message appeared.");
        }

        // If nothing detected -> 2nd attempt (sometimes click doesn't register)
        if (!detected) {
            click(loginBtn);
            waitPageReady();

            detected = waitAnyVisible(25,
                    home.getLoggedInAsLocator(),
                    home.getLogoutLocator(),
                    invalidLoginError
            );

            if (isVisible(invalidLoginError, 2)) {
                throw new AssertionError("Login failed: invalid email/password message appeared.");
            }
        }

        if (!detected) {
            throw new AssertionError("Login result not detected: neither 'Logged in as'/'Logout' nor error appeared.");
        }

        // Final confirmation
        if (!home.isLoggedInBannerVisible() && !home.isLogoutVisible()) {
            throw new AssertionError("Login not confirmed: banner/logout not visible.");
        }

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
        return isVisible(invalidLoginError, 8);
    }
}
