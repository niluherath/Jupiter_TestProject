package com.test.contactpagetest;

import com.base.BaseTest;
import com.pageobjects.ContactPage;
import com.pageobjects.HomePage;
import com.utils.ExtentManager;
import org.testng.Assert;

public class ContactPageTest extends BaseTest {

    @org.testng.annotations.Test(description = "This test validates the error messages on contact page")
    public void testContactPageFormErrorMessagesForMandatoryFields() {
        logger = extent.createTest("To verify contact page form error messages on mandatory fields");
        given(" you arrive on jupiter toys page");
        when(" when you go From the home page go to contact page");
        HomePage homePage = new HomePage(driver);
        homePage.clickContactTab();
        ContactPage contactPage = new ContactPage(driver);
        Assert.assertEquals(contactPage.getTitle(), "Jupiter Toys");
        contactPage.clickSubmitButton();

        then(" error messages are shown on mandatory fields");
        Assert.assertTrue(contactPage.validateForenameRequired());
        Assert.assertTrue(contactPage.validateEmailRequired());
        Assert.assertTrue(contactPage.validateMessageRequired());

        and(" inputs for mandatory fields get populated");
        contactPage.typeInForenameInputField(name);
        contactPage.typeInEmailInputField(emailAddress);
        contactPage.typeInMessageInputField(message);

        then(" error messages get disappeared");
        Assert.assertFalse(contactPage.validateForenameRequired());
        Assert.assertFalse(contactPage.validateEmailRequired());
        Assert.assertFalse(contactPage.validateMessageRequired());
    }

    @org.testng.annotations.Test(description = "This test validates successful submission on contact page form")
    public void testContactPageFormSucessfulSubmission() {
        logger = extent.createTest("To verify contact page form submission successful submission");
        given(" you arrive on jupiter toys page");
        when(" you go From the home page go to contact page");
        HomePage homePage = new HomePage(driver);
        homePage.clickContactTab();

        ContactPage contactPage = new ContactPage(driver);
        Assert.assertEquals(contactPage.getTitle(), "Jupiter Toys");
        contactPage.clickSubmitButton();

        when(" mandatory fields values are entered");
        contactPage.typeInForenameInputField(name);
        contactPage.typeInEmailInputField(emailAddress);
        contactPage.typeInMessageInputField(message);
        contactPage.clickSubmitButton();

        then(" a success message is shown");
        contactPage.waitForSuccessMessage();
    }


}
