package com.test.homepage;

import com.base.BaseTest;
import com.pageobjects.HomePage;
import org.testng.Assert;

public class JupiterHomePageTest extends BaseTest {
    @org.testng.annotations.Test(description="This test validates the Trade me landing page title")
    public void testHomePage()
    {
        logger = extent.createTest("To verify Jupiter toys page title");
        given("Driver calls the url");
        when(" Home page pops up");
        HomePage homePage   = new HomePage(driver);
        then("driver gets the title of the page");
        Assert.assertEquals(homePage.getTitle(), "Jupiter Toys");

    }
}
