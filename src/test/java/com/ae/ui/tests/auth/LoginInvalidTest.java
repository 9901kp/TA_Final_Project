package com.ae.ui.tests.auth;

import com.ae.pages.HomePage;
import com.ae.pages.LoginPage;
import com.ae.ui.base.BaseTest;
import com.ae.ui.listeners.AllureTestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AllureTestListener.class)
public class LoginInvalidTest extends BaseTest {

    @Test(description = "TC-3: Login User with incorrect email and password")
    public void loginWithInvalidCredentials_shouldShowError() {

        LoginPage login = new HomePage()
                .goToLogin()
                .waitLoaded();

        login.login("invalid_email@test.com", "wrong_password");

        Assert.assertTrue(login.isInvalidLoginErrorVisible(),
                "Expected error message for invalid login.");
    }
}
