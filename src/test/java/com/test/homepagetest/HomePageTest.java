package com.test.homepagetest;

import com.base.BaseTest;
import com.pageobjects.HomePage;
import org.testng.Assert;

public class HomePageTest extends BaseTest {
    @org.testng.annotations.Test(description = "This test validates the Jupiter toys hope page title")
    public void testHomePage() {
        logger = extent.createTest("To verify Jupiter toys page title");
        given(" you arrive on jupiter toys page");
        HomePage homePage = new HomePage(driver);
        then(" Driver gets the title of the page");
        Assert.assertEquals(homePage.getTitle(), "Jupiter Toys");

    }
}
