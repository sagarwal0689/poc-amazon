package com.amazon.tests;

import com.amazon.pages.BasePage;
import com.amazon.pages.BrowserType;
import com.amazon.pages.HomePage;
import com.amazon.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 *  Class to contain basic test funtions
 * @author    Sonal Agarwal
 */

public class BaseTest {
    public WebDriver driver;
    public HomePage homePage;
    public LoginPage loginPage;
    public Map<String,String> testData;
    public Map<String,String> config;
    public WebDriver getDriver() {
        return driver;
    }
    public String path;
    @BeforeClass
    public WebDriver classLevelSetup() {
        try {

            path=System.getProperty("user.dir");
            config = BasePage.convertPropertiesToHashMap(path+File.separator+"config.properties");
            driver=BasePage.setWebDriver(config.get("browser"));
            return driver;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return driver;
    }

    @BeforeMethod
    public void methodLevelSetup()  {
        path=System.getProperty("user.dir");
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);

        testData=BasePage.convertPropertiesToHashMap(path+File.separator+"TestData\\testData.properties");
        homePage.goToAmazon(config.get("URL"));
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

}
