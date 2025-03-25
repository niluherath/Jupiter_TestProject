package com.test.shoppingcarttest;

import com.base.BaseTest;
import com.pageobjects.HomePage;
import org.testng.Assert;

public class ShoppingCartTest extends BaseTest {
    @org.testng.annotations.Test(description = "This test validates ")
    public void testHomePage() {
        logger = extent.createTest("To verify Jupiter toys page title");
        when(" Driver call the url");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnStartShoppingButton();


    }
}
