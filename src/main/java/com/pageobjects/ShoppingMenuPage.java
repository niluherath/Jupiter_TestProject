package com.pageobjects;

import com.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ShoppingMenuPage {

    private WebDriver driver;
    private By buyStuffedFrogButton = By.xpath("//li[@id='product-2']//a[@class='btn btn-success'][normalize-space()='Buy']");
    private By buyFluffyBunnyButton = By.xpath("//li[@id='product-4']//a[@class='btn btn-success'][normalize-space()='Buy']");
    private By buyValentineBearButton = By.xpath("//li[@id='product-7']//a[@class='btn btn-success'][normalize-space()='Buy']");
    private By cartButton = By.xpath("//a[@href='#/cart']");

    public ShoppingMenuPage(WebDriver driver) {
        this.driver = driver;
        WaitUtil.waitForElementToLoad(driver, buyStuffedFrogButton);
    }
    public String getTitle() {
        return driver.getTitle();
    }

    public void clickBuyFluffyButton() {
        driver.findElement(buyFluffyBunnyButton).click();
    }

    public void clickBuyStuffedFrogButton() {
        driver.findElement(buyStuffedFrogButton).click();
    }

    public void clickBuyValentineBearButton() {
        driver.findElement(buyValentineBearButton).click();
    }

    public void clickCartButton() {
        driver.findElement(cartButton).click();
    }





}
