package com.ebay.factory;

import static org.testng.Assert.assertNotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;


public abstract class BasePage implements WebdriverWaitTime {
	static Logger logger = Logger.getLogger(BasePage.class);

	public AppiumDriver<MobileElement> driver;
	public  String  currentWindow;
	public  String currWindow;

	public BasePage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

		waitForPageToLoad();
		verifyPageElements();
	}

	public abstract void waitForPageToLoad();

	public abstract void verifyPageElements();

	/**
	 * Verify and click on element
	 * 
	 * @param
	 */

	public void verifyAndClick(WebElement element) {
		logger.info("Verify and Click on element: " + element);
		Assert.assertNotNull(element);
		Assert.assertTrue(element.isEnabled());
		element.click();
	}


	/**
	 * check if element is present or not . If present returns true otherwise
	 * false
	 * 
	 * @return
	 */
	public boolean isElementVisible(WebElement element) {
		assertNotNull(element);
		return element.isDisplayed();
	}
	
	/**
	 * Swipe ToLeft
	 */
	public void swipeLeft() {
		Dimension size = driver.manage().window().getSize();
		int startx = (int) (size.width * 0.8);
		int endx = (int) (size.width * 0.20);
		int starty = size.height / 2;
		driver.swipe(startx, starty, endx, starty, 1000);
	}

	/**
	 * Swipe to Right
	 * @param by
	 * @throws Exception
	 */
	public void swipeRight(By by) throws Exception {
		MobileElement element = (MobileElement) driver.findElement(by);
		Point center = element.getCenter();
		try {
			element.swipe(SwipeElementDirection.RIGHT, 1000);
			Thread.sleep(500);
		} catch (InterruptedException e) {
			logger.error("Not able to Swipe", e);
		}

		driver.tap(1, center.x, center.y, 200);

	}

	
	public void fluentwait( final By locator){
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>( driver )
				.withTimeout(90, TimeUnit.SECONDS)
				.pollingEvery(250, TimeUnit.MILLISECONDS)
				.ignoring( NoSuchElementException.class, StaleElementReferenceException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
	}
	/**
	 * To Type into text box . takes two parameter
	 * 
	 * @param email
	 *            :- Value to be typed
	 */

	public void type(WebElement element, String value) {
		assertNotNull(element);
		element.sendKeys(value + "\t");
	}
	
	public void clearThenType(WebElement element, String value) {
		assertNotNull(element);
		element.clear();
		element.sendKeys(value + "\t");
	}

	public void selectOption(List<WebElement> elementList, String itemToSelect) {
		logger.info("Select item : " + itemToSelect + ": " + elementList);
		for (WebElement item : elementList) {
			if (getText(item).contains(itemToSelect)) {
				verifyAndClick(item);
				break;
			}
		}
	}

	/**
	 * Wait for a element Present
	 * 
	 * @param elmLocator
	 *            Locator of the element
	 * @param timeInSecs
	 *            time in seconds
	 */
	public MobileElement waitForElementPresent(By elmLocator, long timeInSecs) {

		WebDriverWait wait = new WebDriverWait(driver, timeInSecs);
		wait.until(ExpectedConditions.presenceOfElementLocated(elmLocator));
		return driver.findElement(elmLocator);
	}

	/**
	 * Verify Page Title
	 */
	public void verifyPageTitle(String pageTitle) {
		String pTitle = driver.getTitle();
		Assert.assertTrue(pTitle.toLowerCase()
				.contains(pageTitle.toLowerCase()));
	}

	/**
	 * Get Text from HTML
	 */
	public String getText(WebElement element) {
		Assert.assertNotNull(element);
		return element.getText().replaceAll("[^\\x00-\\x7f]", "");
	}

	
	/**
	 * Opens given url
	 * 
	 * @param driver
	 *            : driver object
	 * @param url
	 *            : URL you wish to navigate to
	 */
	public void openUrl(WebDriver driver, String url) {
		logger.info("Navigating to url: " + url);
		driver.get(url);;

	}

	/**
	 * Clear the values of the Element
	 */

	public void clearField(WebElement element) {
		element.clear();
	}

	/**
	 * Mouse Hover on an Element
	 */
	public void hoverOver(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	/**
	 * Hover over an element and click
	 * 
	 * @param webElement
	 *            : Object of located element
	 */
	public void hoverClick(WebElement webElement) {
		Actions action = new Actions(driver);
		action.moveToElement(webElement).click().build().perform();
	}

	/**
	 * This getCurrentUrl() function is used to get current url of the web page.
	 * 
	 * @return
	 */
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * This navigateToBack() function is used to navigate to back web page.
	 */
	public void navigateToBack() {
		driver.navigate().back();
	}

	public void navigateToForward() {
		driver.navigate().forward();
	}

	/**
	 * This function is used to clear all cookies.
	 */
	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	public void reloadPage(){
		driver.navigate().refresh();
	}
	
	public void clickOperation(By by) {
		driver.findElement(by).click();
	}
	
	public void javaScriptClick(WebElement element){
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("$('#continue').click();");
	}

	public void verifyPageSourceContains(String expectedText){
		Assert.assertTrue(driver.getPageSource().contains(expectedText));
	}
   
    public void scrollIntoView(WebElement element) {
        final String scrollElementIntoMiddle =
                "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                        + "var elementTop = arguments[0].getBoundingClientRect().top;"
                        + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
    }

}



