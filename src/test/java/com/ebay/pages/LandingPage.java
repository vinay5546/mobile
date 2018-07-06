package com.ebay.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.ebay.factory.BasePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LandingPage extends BasePage {
	@FindBy(xpath="") WebElement ebayLandingPageHeader;
	private By menuIemsDivBy = By.cssSelector(".lh-layerhandler-menu #nav li.lh-layerhandler"); 
	private String subLinkCSS="div.lh-layerhandler-menu li a";
	
	public LandingPage(AppiumDriver<MobileElement> driver) {
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
	 * Select menu Item
	 * @param menuItem
	 * @param subItem
	 */
	public void selectMenuOptions(String menuItem, String subItem){
	    	List<MobileElement> itemsList = driver.findElements(menuIemsDivBy);
	    	for (MobileElement item : itemsList) {
	    		System.out.println(item.getText());
	    		if(item.getText().equals(menuItem)){
	    			hoverOver(item);
	    			List<MobileElement> links = item.findElements(By.cssSelector(subLinkCSS));

	    			for (MobileElement link : links) {
	    				System.out.println(link.getText());
	    				if(link.getText().contains(subItem)){
	    					link.click();
	    					break;
	    				}
	    			}
	    			break;
	    		}
	    	}
	    }
	
	/**
	 * Verify Landing Page Header
	 */
	public void verifyHeader() {
		Assert.assertTrue(ebayLandingPageHeader.getText().equals("Ebay Landing Page"), "Coorect Landing Page should be displayed.");
	}
	
	/**
	 * Click Item
	 * @param itemName
	 */
    public void clickItem(String itemName){
    	clickOperation(By.linkText(itemName));
    }

}
