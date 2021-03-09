package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

    /**Constructor*/
    public HomePage(WebDriver driver) {
        super(driver);
    }

    By signInButton = By.id("nav-link-accountList");
    By signOut = By.id("nav-item-signout");
    By searchTextBox = By.id("twotabsearchtextbox");
    By searchResultMessage = By.xpath("//div[contains(@class,'a-spacing-small a-spacing-top-small')]");




    /*
     *Page Action Methods
     */
    public HomePage goToAmazon(String url) {
        driver.get(url);
         maximizeWindow();
        return this;
    }

    public LoginPage navigateToLoginPage() {
        click(signInButton);
        return new LoginPage(driver);
    }

    public void clickSignOut(){
        click(signOut);
    }
    public  void signOut(){
        mouseOver(signInButton);
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
