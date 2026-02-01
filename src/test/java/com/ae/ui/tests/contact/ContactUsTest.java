package com.ae.ui.tests.contact;

import com.ae.pages.HomePage;
import com.ae.ui.base.BaseTest;
import com.ae.ui.listeners.AllureTestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AllureTestListener.class)
public class ContactUsTest extends BaseTest {

    @Test(description = "TC-6: Contact Us Form")
    public void contactUsForm_shouldSubmitSuccessfully() {

        HomePage home = new HomePage()
                .open()
                .goToContactUs()
                .waitLoaded();

        home.fillContactForm(
                "Test User",
                "test@mail.com",
                "Test Subject",
                "This is a test message"
        );

        home.submitContactForm();

        Assert.assertTrue(
                home.isContactSuccessMessageVisible(),
                "Success message should be visible after submitting Contact Us form"
        );
    }
}
