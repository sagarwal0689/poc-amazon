package com.amazon.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Base class to contain common utilities.
 * @author    Sonal Agarwal
 */

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    public Properties properties;
    //public HashMap<String,String> testData;
    public HashMap<String,String> objRepo;
    String path;
    //Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TimeUnit.SECONDS.toSeconds(10));
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        path=System.getProperty("user.dir");
        objRepo=new HashMap<String, String>();
      /*  testData=new HashMap<String, String>();

        if(testData.isEmpty()) {
            testData = BasePage.convertPropertiesToHashMap(path + File.separator + "TestData\\testData.properties");
        }
        else {
            System.out.println("Data already present");
        }*/

        if(objRepo.isEmpty())
            objRepo=BasePage.convertPropertiesToHashMap(path+ File.separator+"ObjectRepo\\objectRepo.properties");
        else
            System.out.println("Object Repo already loaded");
    }

    public static WebDriver setWebDriver(String browser) throws Exception {
        WebDriver driver = null;
        BrowserType browserType = null;
        if (browser.equalsIgnoreCase("chrome")) {
            browserType = BrowserType.CHROME;
            driver=setWebDriver(browserType);
            return driver;
            
        } else if (browser.equalsIgnoreCase("firefox")) {
            browserType = BrowserType.FIREFOX;
            driver= setWebDriver(browserType);
            return driver;
        }
        return driver;
    }
    public static WebDriver setWebDriver(BrowserType browser) throws Exception{
        WebDriver driver;
        DesiredCapabilities desiredCapabilities=null;
        String path = System.getProperty("user.dir");
        String downloadFolderPath= path+File.separator+"executables";

            switch(browser) {
                case FIREFOX:
                    System.setProperty("webdriver.gecko.driver", path+ File.separator+"executables\\geckodriver.exe");
                    desiredCapabilities = new DesiredCapabilities();

                    FirefoxProfile profile = new FirefoxProfile();
                    profile.setPreference("browser.download.folderList", 2);
                    profile.setPreference("browser.download.dir", downloadFolderPath);
                    desiredCapabilities.setCapability(FirefoxDriver.PROFILE, profile);
                    driver=new FirefoxDriver(desiredCapabilities);
                    return driver;

                case CHROME:
                    File chromeDriverPath;
                    System.setProperty("webdriver.chrome.driver", path+ File.separator+"executables\\chromedriver.exe");
                    desiredCapabilities = new DesiredCapabilities();
                    HashMap<String, Object> chromePrefs = new HashMap<String,Object>();
                    chromePrefs.put("profile.default_content_settings.popups", 0);
                    chromePrefs.put("download.default_directory", downloadFolderPath);
                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("prefs", chromePrefs);
                    options.addArguments("--disable-extensions");
                    options.addArguments("--disable-notifications");
                    desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
                    desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                    desiredCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
                    driver=new ChromeDriver(desiredCapabilities);
                    return driver;

                default:
                    throw new Exception("Browser not implemented");

            }
        }


     public static HashMap<String,String> convertPropertiesToHashMap (String filePath)  {
         HashMap<String,String> data = new HashMap<String, String>();
        Properties properties=new Properties();
         try {
             properties.load(new FileReader(filePath));
         } catch (IOException e) {
             e.printStackTrace();
         }
         Set<String> keys=properties.stringPropertyNames();
        for (String key:keys){
            data.put(key,properties.getProperty(key));

        }
       return data;
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
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }catch(Exception e){
            System.out.println("Element not visible in stipulated time frame");
            e.printStackTrace();
        }
    }

    // maximize window

    public void maximizeWindow(){
        driver.manage().window().maximize();
    }

    public void mouseOver(By elementLocator){
        try {
            WebElement element= getElement(elementLocator);
            Actions actions= new Actions(driver);
            actions.moveToElement(element).perform();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public WebElement getElement(By elementLocator){

        WebElement element= find(elementLocator);
        if(element==null){
            throw new NoSuchElementException(elementLocator + " Not found");
        }else
            return element;
    }

    public WebElement find(By elementLocator ){
        try{
            return driver.findElement(elementLocator);
        }catch(NoSuchElementException e){
            e.printStackTrace();;
        }catch(Exception e) {
            e.printStackTrace();

        }
        return  null;
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

    public boolean isElementEnabled(By elementLocator){

        return getElement(elementLocator).isEnabled();
    }

    public String getTitleOfPage(){
        return driver.getTitle();
    }

}
