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
public class LogoutTest extends BaseTest {

    @Test(description = "TC-4: Logout User should return to Login page")
    public void logout_shouldReturnToLoginPage() {
        HomePage home = new HomePage().open();

        LoginPage login = home.goToLogin().waitLoaded();

        HomePage afterLogin = login.loginExpectSuccess(Config.uiEmail(), Config.uiPassword());

        Assert.assertTrue(afterLogin.isLoggedInBannerVisible(),
                "Expected to be logged in before logout");

        LoginPage afterLogout = afterLogin.logout();

        Assert.assertTrue(afterLogout.isLoginHeaderVisible(),
                "Expected Login page header to be visible after logout");
    }
}
