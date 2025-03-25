package com.contactpagetest;

import com.base.BaseTest;
import com.pageobjects.ContactPage;
import com.pageobjects.HomePage;
import org.testng.Assert;

public class ContactPageTest extends BaseTest {
    @org.testng.annotations.Test(description="This test validates the error messages on contact page")
    public void testContactPageForm()
    {
        logger = extent.createTest("To verify contact page form error messages on mandatory fields");
        given("user arrives on contact page");
        when(" submit button on contact page is clicked");
        HomePage homePage   = new HomePage(driver);
        homePage.clickContactTab();
        ContactPage contactPage = new ContactPage(driver);
        contactPage.clickSubmitButton();
        then(" error messages are shown on mandatory fields");
        Assert.assertTrue(contactPage.validateForenameRequired());
        Assert.assertTrue(contactPage.validateEmailRequired());
        Assert.assertTrue(contactPage.validateMessageRequired());
        contactPage.typeInForenameInputField(name);
        contactPage.typeInEmailInputField(emailAddress);
        contactPage.typeInMessageInputField(message);
        Assert.assertFalse(contactPage.validateForenameRequired());
        Assert.assertFalse(contactPage.validateEmailRequired());
        Assert.assertFalse(contactPage.validateMessageRequired());

    }
    @org.testng.annotations.Test(description="")
    public void testContactPageFormSubmission()
    {
        logger = extent.createTest("To verify contact page form submission");
        given("user arrives on contact page");
        when(" submit button on contact page is clicked");
        HomePage homePage   = new HomePage(driver);
        homePage.clickContactTab();
        ContactPage contactPage = new ContactPage(driver);
        contactPage.clickSubmitButton();
        then("a success message is shown");

        contactPage.typeInForenameInputField(name);
        contactPage.typeInEmailInputField(emailAddress);
        contactPage.typeInMessageInputField(message);
        contactPage.clickSubmitButton();
        contactPage.waitForSuccessMessage();


    }
}
