package com.ebay.factory;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class WebdriverManager {
		
	public static AppiumDriver<MobileElement> getAPMDriver() throws MalformedURLException {
		
		AppiumDriver<MobileElement> driver = null;
		
		// Get these values from properties file(Config.properties)
		String mobileDeviceName = PropertyManager.getProperty("mobileDeviceName");
		String deviceName = PropertyManager.getProperty("deviceName");
		String platformVersion = PropertyManager.getProperty("platformVersion");
		String platformName = PropertyManager.getProperty("platformName");
		String appAPKFilePath = PropertyManager.getProperty("appPackage");
		String appActivity = PropertyManager.getProperty("appActivity");

		if (mobileDeviceName.equals("android")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			// Specify the device name (any name), platform version ,platform name, app package etc
			capabilities.setCapability("deviceName", deviceName);
			capabilities.setCapability("platformVersion", platformVersion);
			capabilities.setCapability("platformName", platformName);
			capabilities.setCapability("appPackage", appAPKFilePath);
			capabilities.setCapability("appActivity", appActivity);

			// Start android driver (default it will be 4723)
			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
;
		} else {
			// IOSdriver properties
		}
		return driver;

	}

}
