package com.pageobjects;

import com.base.BasePage;
import com.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class CartPage extends BasePage {

    private WebDriver driver;
    private By buyStuffedFrogButton = By.xpath("//li[@id='product-2']//a[@class='btn btn-success'][normalize-space()='Buy']");
    private By buyFluffyBunnyButton = By.xpath("//li[@id='product-4']//a[@class='btn btn-success'][normalize-space()='Buy']");
    private By buyValentineBearButton = By.xpath("//li[@id='product-7']//a[@class='btn btn-success'][normalize-space()='Buy']");
    private By cartButton = By.xpath("//a[@href='#/cart']");

    public CartPage(WebDriver driver) {
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

    public boolean findIfSubtotalForItemIsCorrect(int noOfItems, String itemName) {
        double unitPrice = 0.00;
        double subTotalCalculation = 0.00;

        switch(itemName) {
            case "Stuffed Frog":
                itemName = "Stuffed Frog";
                unitPrice = 10.99;

                break;
            case "Fluffy Bunny":
                itemName = "Fluffy Bunny";
                unitPrice = 9.99;
                break;
            case "Valentine Bear":
                itemName = "Valentine Bear";
                unitPrice = 14.99;
                break;
            default:
                logger.info("could not find item name");
        }
        subTotalCalculation = unitPrice * noOfItems;
        String subTotal = "$"+subTotalCalculation;
        logger.info("sub total total for "+ noOfItems +" "+ itemName + " is: " + subTotal);

        System.out.println(noOfItems);
        By rowElements = By.xpath("//tbody");
        WaitUtil.waitForElementToLoad(driver, rowElements);
        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-striped cart-items']/tbody/tr"));
        System.out.println(rows.size()+ " row size");
        boolean foundSubtotal = false;
        for (WebElement row : rows) {
            System.out.println(row.getText());
            if(row.getText().contains(itemName)&& row.getText().contains(subTotal)){
                foundSubtotal = true;
                break;
            }
        }
        return foundSubtotal;
    }







}
