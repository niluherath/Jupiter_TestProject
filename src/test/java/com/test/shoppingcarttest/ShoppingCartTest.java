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
        given(" you arrive on jupiter toys page");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnStartShoppingButton();
        ShoppingMenuPage shoppingMenuPage = new ShoppingMenuPage(driver);

        when(" You Buy 2 Stuffed Frog, 5 Fluffy Bunny, 3 Valentine Bear");
        shoppingMenuPage.clickBuyStuffedFrogButton(2);
        shoppingMenuPage.clickBuyFluffyBunnyButton(5);
        shoppingMenuPage.clickBuyValentineBearButton(3);
        shoppingMenuPage.clickCartButton();

        and("Go to the cart page");
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getTitle(), "Jupiter Toys");

        then("on the cart page you will see subtotal for each item");
        Assert.assertTrue(cartPage.findIfSubtotalForItemIsCorrect(2, "Stuffed Frog"));
        Assert.assertTrue(cartPage.findIfSubtotalForItemIsCorrect(5, "Fluffy Bunny"));
        Assert.assertTrue(cartPage.findIfSubtotalForItemIsCorrect(3, "Valentine Bear"));

        and(" and price for each item will be shown on cart page");
        Assert.assertTrue(cartPage.verifyPriceForEachProduct("Stuffed Frog"));
        Assert.assertTrue(cartPage.verifyPriceForEachProduct("Fluffy Bunny"));
        Assert.assertTrue(cartPage.verifyPriceForEachProduct("Valentine Bear"));

        then("correct total of all the sub tototals will be shown on cart page");
        double subTotalForStuffedFrogs = cartPage.getSubtotal("Stuffed Frog");
        double subtotalForFluffyBunny = cartPage.getSubtotal("Fluffy Bunny");
        double subTotalForValentineBear = cartPage.getSubtotal("Valentine Bear");
        double total = subTotalForStuffedFrogs + subTotalForValentineBear + subtotalForFluffyBunny;
        Assert.assertEquals(cartPage.getTotal(), total);


    }
}
