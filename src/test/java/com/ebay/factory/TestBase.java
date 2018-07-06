package com.ebay.factory;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public abstract class TestBase {

	Logger logger = Logger.getLogger(TestBase.class);

	public AppiumDriver<MobileElement> driver;;

	@BeforeMethod(alwaysRun=true)
	public void launchBrowser(Method method) throws MalformedURLException{
		driver = WebdriverManager.getAPMDriver();
		logger.info("Started test method: " +method.getName());
	}

	@AfterMethod(dependsOnMethods="takeScreenshot", alwaysRun=true)
	public void quitBrowser(){
		driver.quit();

	}

	@AfterMethod
	public void takeScreenshot(ITestResult result){
		String testName = result.getName();
		logger.info("End of test method :" +testName);

		TakesScreenshot screenshot = ((TakesScreenshot)driver);
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

		String time = new SimpleDateFormat("ddMMMyyy_hhmmaaa").format(Calendar.getInstance().getTime());
		try {
			FileUtils.copyFile(srcFile, new File("C:\\screenshots\\"+result.getInstanceName()+"_"+result.getName()+time+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
