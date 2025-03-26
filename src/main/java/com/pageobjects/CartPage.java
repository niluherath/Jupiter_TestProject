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
    private final By buyStuffedFrogButton = By.xpath("//li[@id='product-2']//a[@class='btn btn-success'][normalize-space()='Buy']");
    private final By total = By.xpath("//strong[@class='total ng-binding']");
    private static final String TBODY = "//tbody";
    private static final String ROW = "//table[@class='table table-striped cart-items']/tbody/tr";
    private static final String CELL_PATH = "//table[@class='table table-striped cart-items']/tbody/tr[";

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
            case "Stuffed Frog", "Fluffy Bunny", "Valentine Bear":
                unitPrice = this.getPrice(itemName);
                break;
            default:
                logger.info("could not find item name");
                return false;
        }
        subTotalCalculation = unitPrice * noOfItems;
        String subTotal = "$" + subTotalCalculation;
        logger.info("Subtotal "+subTotal+" for item name "+itemName + " with no of items "+ noOfItems);

        By rowElements = By.xpath(TBODY);
        WaitUtil.waitForElementToLoad(driver, rowElements);

        List<String> headers = getHeaderStringList();

        List<WebElement> rows = driver.findElements(By.xpath(ROW));
        Map<String, String> rowMap = new HashMap<>();
        boolean found = false;

        for (int i = 1; i <= rows.size(); i++) {
            List<WebElement> cells = driver.findElements(By.xpath(CELL_PATH + i + "]/td"));

            for (int j = 0; j < headers.size(); j++) {
                rowMap.put(headers.get(j), cells.get(j).getText().trim());
            }
            logger.info("Row is: "+ i + "Data is: " + rowMap);

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
                unitPrice = 10.99;
                break;
            case "Fluffy Bunny":
                unitPrice = 9.99;
                break;
            case "Valentine Bear":
                unitPrice = 14.99;
                break;
            default:
                logger.info("could not find item name");
        }

        By rowElements = By.xpath(TBODY);
        WaitUtil.waitForElementToLoad(driver, rowElements);

        List<String> headers = getHeaderStringList();

        List<WebElement> rows = driver.findElements(By.xpath(ROW));
        Map<String, String> rowMap = new HashMap<>();
        boolean found = false;

        for (int i = 1; i <= rows.size(); i++) {
            List<WebElement> cells = driver.findElements(By.xpath(CELL_PATH + i + "]/td"));

            for (int j = 0; j < headers.size(); j++) {
                rowMap.put(headers.get(j), cells.get(j).getText().trim());
            }
            logger.info("Row is: "+ i + "Data is: " + rowMap);

            if (rowMap.get("Item").equals(itemName) && rowMap.get("Price").equals("$" + unitPrice)) {
                found = true;
                break;
            }
        }
        return found;
    }

    private List<String> getHeaderStringList() {
        List<WebElement> headerElements = driver.findElements(By.xpath("//table[@class='table table-striped cart-items']/thead/tr/th"));
        List<String> headers = new ArrayList<>();
        for (WebElement header : headerElements) {
            headers.add(header.getText().trim());  // Store headers in a List
        }
        return headers;
    }

    public double getSubtotal(String itemName) {
        By rowElements = By.xpath(TBODY);
        WaitUtil.waitForElementToLoad(driver, rowElements);


        List<String> headers = getHeaderStringList();

        List<WebElement> rows = driver.findElements(By.xpath(ROW));
        Map<String, String> rowMap = new HashMap<>();
        String subTotalFromTable = "";

        for (int i = 1; i <= rows.size(); i++) {
            List<WebElement> cells = driver.findElements(By.xpath(CELL_PATH + i + "]/td"));

            for (int j = 0; j < headers.size(); j++) {
                rowMap.put(headers.get(j), cells.get(j).getText().trim());
            }
            logger.info("Row is: "+ i + "Data is: " + rowMap);

            if (rowMap.get("Item").equals(itemName)) {
                subTotalFromTable = rowMap.get("Subtotal");
                break;
            }
        }
        String subTotal = subTotalFromTable.replace("$", "");
        return Double.parseDouble(subTotal);

    }

    private double getPrice(String itemName) {
        By rowElements = By.xpath(TBODY);
        WaitUtil.waitForElementToLoad(driver, rowElements);
        List<String> headers = getHeaderStringList();

        List<WebElement> rows = driver.findElements(By.xpath(ROW));
        Map<String, String> rowMap = new HashMap<>();
        String price = "";
        String pricePerItem = "";
        for (int i = 1; i <= rows.size(); i++) {
            List<WebElement> cells = driver.findElements(By.xpath(CELL_PATH + i + "]/td"));

            for (int j = 0; j < headers.size(); j++) {
                rowMap.put(headers.get(j), cells.get(j).getText().trim());
            }
            logger.info("Row is: "+ i + "Data is: " + rowMap);

            if (rowMap.get("Item").equals(itemName)) {
                price = rowMap.get("Price");
                break;
            }

        }

        pricePerItem = price.replace("$", "");
        return Double.parseDouble(pricePerItem);

    }

    public double getTotal() {
        String totalValue = driver.findElement(total).getText();
        String value = totalValue.replace("Total: ", "");
        return Double.parseDouble(value);
    }

}
