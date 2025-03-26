package com.pageobjects;

import com.base.BasePage;
import com.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CartPage extends BasePage {

    private WebDriver driver;
    private By buyStuffedFrogButton = By.xpath("//li[@id='product-2']//a[@class='btn btn-success'][normalize-space()='Buy']");
    private By total = By.xpath("//strong[@class='total ng-binding']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        WaitUtil.waitForElementToLoad(driver, buyStuffedFrogButton);
    }

    public String getTitle() {
        return driver.getTitle();
    }


    public boolean findIfSubtotalForItemIsCorrect(int noOfItems, String itemName) {
        double unitPrice = 0.00;
        double subTotalCalculation;

        switch (itemName) {
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
        String subTotal = "$" + subTotalCalculation;
        logger.info("sub total total for " + noOfItems + " " + itemName + " is: " + subTotal);

        By rowElements = By.xpath("//tbody");
        WaitUtil.waitForElementToLoad(driver, rowElements);

        List<WebElement> headerElements = driver.findElements(By.xpath("//table[@class='table table-striped cart-items']/thead/tr/th"));
        List<String> headers = new ArrayList<>();
        for (WebElement header : headerElements) {
            headers.add(header.getText().trim());  // Store headers in a List
        }


        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-striped cart-items']/tbody/tr"));
        Map<String, String> rowMap = new HashMap<>();
        boolean found = false;

        for (int i = 1; i <= rows.size(); i++) {
            List<WebElement> cells = driver.findElements(By.xpath("//table[@class='table table-striped cart-items']/tbody/tr[" + i + "]/td"));

            for (int j = 0; j < headers.size(); j++) {
                rowMap.put(headers.get(j), cells.get(j).getText().trim());
            }
            logger.info("Row " + i + " Data: " + rowMap);

            if (rowMap.get("Item").equals(itemName) && rowMap.get("Subtotal").equals(subTotal)) {
                found = true;
                break;
            }
        }
        return found;
    }


    public boolean verifyPriceForEachProduct(String itemName) {
        double unitPrice = 0.00;

        switch (itemName) {
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

        By rowElements = By.xpath("//tbody");
        WaitUtil.waitForElementToLoad(driver, rowElements);


        List<WebElement> headerElements = driver.findElements(By.xpath("//table[@class='table table-striped cart-items']/thead/tr/th"));
        List<String> headers = new ArrayList<>();
        for (WebElement header : headerElements) {
            headers.add(header.getText().trim());  // Store headers in a List
        }

        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-striped cart-items']/tbody/tr"));
        Map<String, String> rowMap = new HashMap<>();
        boolean found = false;

        for (int i = 1; i <= rows.size(); i++) {
            List<WebElement> cells = driver.findElements(By.xpath("//table[@class='table table-striped cart-items']/tbody/tr[" + i + "]/td"));

            for (int j = 0; j < headers.size(); j++) {
                rowMap.put(headers.get(j), cells.get(j).getText().trim());
            }
            logger.info("Row " + i + " Data: " + rowMap);

            if (rowMap.get("Item").equals(itemName) && rowMap.get("Price").equals("$" + unitPrice)) {
                found = true;
                break;
            }
        }
        return found;
    }


    public double findSubtotal(int noOfItems, String itemName) {
        double unitPrice = 0.00;
        double subTotalCalculation;

        switch (itemName) {
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
        String subTotal = "$" + subTotalCalculation;
        logger.info("sub total total for " + noOfItems + " " + itemName + " is: " + subTotal);
        return subTotalCalculation;

    }

    public double getTotal() {
        String totalValue = driver.findElement(total).getText();
        String value = totalValue.replace("Total: ", "");
        return Double.parseDouble(value);
    }

}
