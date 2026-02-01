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

    @Test(description = "TC-3: Login with incorrect email and password should show error")
    public void loginWithInvalidCredentials_shouldShowError() {
        HomePage home = new HomePage().open();

        LoginPage login = home.goToLogin().waitLoaded();

        login.loginExpectInvalid("wrong_email@example.com", "wrong_password");

        Assert.assertTrue(login.isInvalidLoginErrorVisible(),
                "Expected invalid email/password message to be visible");
    }
}
