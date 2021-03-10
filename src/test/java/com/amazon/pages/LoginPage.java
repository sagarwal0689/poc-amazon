package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.File;
import java.util.Map;

public class LoginPage extends BasePage{
  //  public Map<String,String> testData;
    String path;
    public LoginPage(WebDriver driver) {
        super(driver);
      /*  path=System.getProperty("user.dir");
        testData=BasePage.convertPropertiesToHashMap(path+ File.separator+"TestData\\testData.properties");*/
    }

    /**Web Elements*/

/*    By emailId = By.id("ap_email");
    By continueButton = By.id("continue");
    By password = By.id("ap_password");
    By signInButton = By.id("auth-signin-button");*/

    By emailId = By.id(objRepo.get("emailId"));
    By continueButton = By.id(objRepo.get("continueButton"));
    By password = By.id(objRepo.get("password"));
    By signInButton = By.id(objRepo.get("signInButton"));
    By helloSignInLink = By.id(objRepo.get("helloSignInLink"));


    /**Page Methods*/
    public void loginToAmazon(String uName, String pwd, String text) {

       enterEmailId(uName);
       clickContinue();
       enterPassword(pwd);
       clickSignInButton();
      Assert.assertNotEquals(readText(helloSignInLink),text);

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
