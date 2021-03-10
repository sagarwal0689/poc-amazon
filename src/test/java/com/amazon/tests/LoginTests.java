package com.amazon.tests;

import com.amazon.utils.ExtentReports.ExtentTestManager;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
//import utils.ExtentReports.ExtentTestManager;

import java.lang.reflect.Method;

public class LoginTests extends BaseTest {



    @Test( description = "Login to Amazon site with valid credentials")
    public void loginToAmazon(Method method) {
        //ExtentReports Description
         ExtentTestManager.startTest(method.getName(), "Testing the login functionality");
         //homePage.goToAmazon();
        homePage.navigateToLoginPage(testData.get("title"));
        loginPage.loginToAmazon(config.get("email"),config.get("password"), testData.get("userDisplayed"));
        homePage.signOut();

    }

    @Test( description = "Login to Amazon site with valid credentials")
    public void searchItem(Method method) {
        //ExtentReports Description
        ExtentTestManager.startTest(method.getName(), "Testing the search functionality");

        //homePage.goToAmazon();
        homePage.navigateToLoginPage(testData.get("title"));
        loginPage.loginToAmazon(config.get("email"),config.get("password"), testData.get("userDisplayed"));
        homePage.searchItem(testData.get("itemToSearch") + Keys.ENTER);
        homePage.verifySearchResult();
        homePage.signOut();

    }

}