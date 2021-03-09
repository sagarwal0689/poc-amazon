package com.amazon.tests;

import com.amazon.pages.HomePage;
import com.amazon.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


import java.io.File;

public class BaseTest {
    public WebDriver driver;
    public HomePage homePage;
    public LoginPage loginPage;
    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void classLevelSetup() {
        String path= System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", path+ File.separator+"executables\\chromedriver.exe");
        System.out.println(path+ File.separator+"executables");
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void methodLevelSetup() {

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        homePage.goToAmazon("https://www.amazon.co.in");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

}
