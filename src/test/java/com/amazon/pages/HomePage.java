package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.File;
import java.util.Map;

/**
 *  Class to contain homepage locators and action methods
 * @author    Sonal Agarwal
 */
public class HomePage extends BasePage{

    String path;
    /**Constructor*/
    public HomePage(WebDriver driver) {
        super(driver);


    }


    By signIn = By.id(objRepo.get("signIn"));
    By signOut = By.id(objRepo.get("signOut"));
    By searchTextBox = By.id(objRepo.get("searchTextBox"));
    By searchResultMessage = By.xpath(objRepo.get("searchResultMessage"));




    /*
     *Page Action Methods
     */
    public HomePage goToAmazon(String url) {
        driver.get(url);
         maximizeWindow();
        return this;
    }

    public boolean navigateToLoginPage(String text) {
        boolean flag=false;
        if(isElementDisplayed(signIn)) {
            click(signIn);
            Assert.assertEquals(getTitleOfPage(),text);
            flag=true;

        }
        return flag;
        //return new LoginPage(driver);
    }

    public void clickSignOut(){
        click(signOut);
    }
    public  void signOut(){
        waitForVisibility(signIn);
        mouseOver(signIn);
        clickSignOut();
    }
     public void searchItem(String item){
        writeText(searchTextBox,item);

     }
     public boolean verifySearchResult(){
        boolean flag=false;
        if(isElementDisplayed(searchResultMessage))
        {
            flag=true;
            return  flag;
        }
         return flag;
     }

}
