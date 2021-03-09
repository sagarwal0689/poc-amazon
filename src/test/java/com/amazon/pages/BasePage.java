package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Base class to contain common utilities.
 * @author    Sonal Agarwal
 */

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;

    //Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TimeUnit.SECONDS.toSeconds(10));
    }

    //Click Method
    public void click(By elementLocator) {
        waitForVisibility(elementLocator);
        getElement(elementLocator).click();
    }

    //Write Text
    public void writeText(By elementLocator, String text) {
        waitForVisibility(elementLocator);
        getElement(elementLocator).sendKeys(text);
    }

    //Read Text
    public String readText(By elementLocator) {
        waitForVisibility(elementLocator);
        return getElement(elementLocator).getText();
    }

    //Wait for element to be located
    public void waitForVisibility(By by){

        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    // maximize window

    public void maximizeWindow(){
        driver.manage().window().maximize();
    }

    public void mouseOver(By elementLocator){
        WebElement element= getElement(elementLocator);
        Actions actions= new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public WebElement getElement(By elementLocator){
        return driver.findElement(elementLocator);
    }

    public boolean verifyTextIsEqual(By elementLocator, String text){
        if(readText(elementLocator).equals(text))
            return  true;
        else
            return false;
    }

    public boolean isElementDisplayed(By elementLocator){
        return getElement(elementLocator).isDisplayed();
    }

}
