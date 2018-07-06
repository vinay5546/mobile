package com.ebay.pages;

import org.openqa.selenium.By;

import com.ebay.factory.BasePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class HomePage extends BasePage {
	private By loginBy = By.cssSelector("#header-profile a.lh-layerhandler-toggle");
	private By unameId = By.id("uname");
	private By pwdId = By.id("pwd");
	
	public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

	@Override
	public void waitForPageToLoad() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verifyPageElements() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Click Login 
	 */
    public void clickLogin(){
    	MobileElement loginBtn=waitForElementPresent(loginBy,5);
    	loginBtn.click();
    }
    
    /**
     * Login Into Ebay App
     * @param name
     * @param pwd
     * @return
     */
    public LandingPage loginIntoApplication(String name,String pwd){
    	MobileElement uname=waitForElementPresent(unameId,5);
    	uname.sendKeys(name);
    	MobileElement pwdU=waitForElementPresent(pwdId,5);
    	pwdU.sendKeys(pwd);
    	clickLogin();
    	return new LandingPage(driver);
    }
   
    public void clickItem(String itemName){
    	clickOperation(By.linkText(itemName));
    }

}
