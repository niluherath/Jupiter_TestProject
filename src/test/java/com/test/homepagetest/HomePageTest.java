package com.test.homepagetest;

import com.base.BaseTest;
import com.pageobjects.HomePage;
import com.utils.ExtentManager;
import org.testng.Assert;

public class HomePageTest extends BaseTest {
    @org.testng.annotations.Test(description = "This test validates the Jupiter toys hope page title")
    public void testHomePage() {
        logger = extent.createTest("To verify Jupiter toys page title");
        when(" Driver call the url");
        HomePage homePage = new HomePage(driver);
        then(" Driver gets the title of the page");
        Assert.assertEquals(homePage.getTitle(), "Jupiter Toys");

    }
}
