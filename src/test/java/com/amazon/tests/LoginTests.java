package com.amazon.tests;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
//import utils.ExtentReports.ExtentTestManager;

import java.lang.reflect.Method;

public class LoginTests extends BaseTest {


    @Test( description = "Login to Amazon site with valid credentials")
    public void loginToAmazon(Method method) {
        //ExtentReports Description
       // ExtentTestManager.startTest(method.getName(), "Navigating to login page");

        //homePage.goToAmazon();
        homePage.navigateToLoginPage();
        Assert.assertTrue(loginPage.loginToAmazon("sonal.fd@gmail.com","sonalagarwal06"));
        homePage.signOut();

    }

    @Test( description = "Login to Amazon site with valid credentials")
    public void searchItem(Method method) {
        //ExtentReports Description
        // ExtentTestManager.startTest(method.getName(), "Navigating to login page");

        //homePage.goToAmazon();
        homePage.navigateToLoginPage();
        Assert.assertTrue(loginPage.loginToAmazon("sonal.fd@gmail.com","sonalagarwal06"));
        homePage.searchItem("yyyyyyyyyyyyyyyyyyyyy" + Keys.ENTER);
        Assert.assertTrue(homePage.verifySearchResult());
        homePage.signOut();

    }

}