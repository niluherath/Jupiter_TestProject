package com.pageobjects;

import com.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage {

    private WebDriver driver;
    private By jupiterToysHeader = By.xpath("//h1[normalize-space()='Jupiter Toys']");
    private By contactTab = By.xpath("//a[normalize-space()='Contact']");
    public HomePage(WebDriver driver) {
        this.driver = driver;
        WaitUtil.waitForElementToLoad(driver, jupiterToysHeader);
    }
    public String getTitle() {
        return driver.getTitle();
    }

    public String clickContactTab() {

        driver.findElement(contactTab).click();
        return driver.getTitle();
    }



}
