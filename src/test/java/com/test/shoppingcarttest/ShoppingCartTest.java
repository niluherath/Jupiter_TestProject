package com.test.shoppingcarttest;

import com.base.BaseTest;
import com.pageobjects.CartPage;
import com.pageobjects.HomePage;
import com.pageobjects.ShoppingMenuPage;
import org.testng.Assert;

public class ShoppingCartTest extends BaseTest {
    @org.testng.annotations.Test(description = "This test validates ")
    public void testShoppingCart() {
        logger = extent.createTest("To verify Jupiter toys page title");
        when(" Driver call the url");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnStartShoppingButton();
        ShoppingMenuPage shoppingMenuPage = new ShoppingMenuPage(driver);
        shoppingMenuPage.clickBuyStuffedFrogButton(2);
        shoppingMenuPage.clickBuyFluffyBunnyButton(5);
        shoppingMenuPage.clickBuyValentineBearButton(3);
        shoppingMenuPage.clickCartButton();
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getTitle(), "Jupiter Toys");
        Assert.assertTrue(cartPage.findIfSubtotalForItemIsCorrect(3, "Stuffed Frog"));



    }
}
