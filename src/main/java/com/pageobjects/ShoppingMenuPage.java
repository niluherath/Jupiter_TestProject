package com.pageobjects;

import com.base.BasePage;
import com.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ShoppingMenuPage extends BasePage {

    private WebDriver driver;
    private final By buyStuffedFrogButton = By.xpath("//li[@id='product-2']//a[@class='btn btn-success'][normalize-space()='Buy']");
    private final By buyFluffyBunnyButton = By.xpath("//li[@id='product-4']//a[@class='btn btn-success'][normalize-space()='Buy']");
    private final By buyValentineBearButton = By.xpath("//li[@id='product-7']//a[@class='btn btn-success'][normalize-space()='Buy']");
    private final By cartButton = By.xpath("//a[@href='#/cart']");

    public ShoppingMenuPage(WebDriver driver) {
        this.driver = driver;
        WaitUtil.waitForElementToLoad(driver, buyStuffedFrogButton);
    }
    public String getTitle() {
        return driver.getTitle();
    }

    public void clickBuyFluffyBunnyButton(int noOfTimes) {

        for (int i = 0; i < noOfTimes; i++) {
            driver.findElement(buyFluffyBunnyButton).click();
            logger.info("Clicked button " + (i + 1) + " times");
        }
    }

    public void clickBuyStuffedFrogButton(int noOfTimes) {

        for (int i = 0; i < noOfTimes; i++) {
            driver.findElement(buyStuffedFrogButton).click();
            logger.info("Clicked button " + (i + 1) + " times");
        }
    }

    public void clickBuyValentineBearButton(int noOfTimes) {

        for (int i = 0; i < noOfTimes; i++) {
            driver.findElement(buyValentineBearButton).click();
            logger.info("Clicked button " + (i + 1) + " times");
        }
    }

    public void clickCartButton() {
        driver.findElement(cartButton).click();
    }





}
