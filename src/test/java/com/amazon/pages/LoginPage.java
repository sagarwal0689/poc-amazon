package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**Web Elements*/
    By emailId = By.id("ap_email");
    By continueButton = By.id("continue");
    By password = By.id("ap_password");
    By signInButton = By.id("auth-signin-button");
    By errorMessageUsernameXpath = By.xpath("//*[@id=\"loginForm\"]/div[1]/div/div");
    By errorMessagePasswordXpath = By.xpath("//*[@id=\"loginForm\"]/div[2]/div/div");

    /**Page Methods*/
    public boolean loginToAmazon(String uName, String pwd) {

       enterEmailId(uName);
       clickContinue();
       enterPassword(pwd);
       clickSignInButton();
        return true;
    }

    public LoginPage verifyLoginUserName(String expectedText) {
        waitForVisibility(errorMessageUsernameXpath);
        Assert.assertEquals(readText(errorMessageUsernameXpath), expectedText);
        return this;
    }

    public LoginPage verifyLoginPassword(String expectedText) {
        waitForVisibility(errorMessagePasswordXpath);
        Assert.assertEquals(readText(errorMessagePasswordXpath), expectedText);
        return this;
    }

    public void enterEmailId(String uName){
        writeText(emailId, uName);
    }
    public void enterPassword(String pwd){
        writeText(password, pwd);
    }
    public void clickContinue(){
        click(continueButton);
    }

    public void clickSignInButton(){
        click(signInButton);
    }
}
