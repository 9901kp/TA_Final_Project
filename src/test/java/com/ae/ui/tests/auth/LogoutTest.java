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

    @Test(description = "TC-4: Logout User")
    public void logout_shouldReturnToLoginPage() {

        HomePage home = new HomePage();
        LoginPage login = home.goToLogin().waitLoaded();

        HomePage afterLogin = login.login(Config.uiEmail(), Config.uiPassword());
        Assert.assertTrue(afterLogin.isLoggedInBannerVisible(), "Expected to be logged in");

        LoginPage afterLogout = afterLogin.logout().waitLoaded();

        Assert.assertNotNull(afterLogout, "Expected LoginPage after logout");
    }
}
