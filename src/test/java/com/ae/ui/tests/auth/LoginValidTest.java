package com.ae.ui.tests.auth;

import com.ae.config.Config;
import com.ae.pages.HomePage;
import com.ae.pages.LoginPage;
import com.ae.ui.base.BaseTest;
import com.ae.ui.listeners.AllureTestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AllureTestListener.class)
public class LoginValidTest extends BaseTest {

    @Test(description = "TC-2: Login User with correct email and password")
    public void loginWithValidCredentials_shouldLogin() {
        HomePage home = new HomePage();

        LoginPage login = home.goToLogin().waitLoaded();

        HomePage afterLogin = login.login(Config.uiEmail(), Config.uiPassword());

        Assert.assertTrue(afterLogin.isLoggedInBannerVisible(), "Expected 'Logged in as' to be visible");
        Assert.assertTrue(afterLogin.getLoggedInAsText().contains(Config.uiUsernameExpected()),
                "Expected username in 'Logged in as' banner");
    }
}
