package com.pageobjects;

import com.base.BasePage;
import com.utils.WaitUtil;
import org.openqa.selenium.By;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;




public class ContactPage extends BasePage {

    private final WebDriver driver;
    private final By submitButton = By.xpath("//a[normalize-space()='Submit']");
    private final By errorForeNameIsRequired = By.xpath("//span[@id='forename-err']");
    private final By errorEamilIsRequired = By.xpath("//span[@id='email-err']");
    private final By errorMessageIsRequired = By.xpath("//span[@id='message-err']");
    private final By foreNameInputField = By.xpath("//input[@id='forename']");
    private final By emailInputField = By.xpath("//input[@id='email']");
    private final By messageInputField = By.xpath("//textarea[@id='message']");
    private final By successMessage = By.xpath("//div[@class='alert alert-success']");
    public ContactPage(WebDriver driver) {
        this.driver = driver;
        WaitUtil.waitForElementToLoad(driver, submitButton);
    }
    public String getTitle() {
        return driver.getTitle();
    }

    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    public boolean validateForenameRequired() {
            try {
                return driver.findElement(errorForeNameIsRequired).isDisplayed();
            } catch (NoSuchElementException e) {
                return false;
            }
    }

    public boolean validateEmailRequired() {
        try {
            return  driver.findElement(errorEamilIsRequired).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean validateMessageRequired() {
        try {
            return driver.findElement(errorMessageIsRequired).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public void typeInForenameInputField(String forename) {
        driver.findElement(foreNameInputField).sendKeys(forename);
    }

    public void typeInEmailInputField(String email) {
        driver.findElement(emailInputField).sendKeys(email);
    }
    public void typeInMessageInputField(String message) {
        driver.findElement(messageInputField).sendKeys(message);
    }
    public void waitForSuccessMessage() {
        WaitUtil.waitForElementToLoad(driver, successMessage);
    }







}
