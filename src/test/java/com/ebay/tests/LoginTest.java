package com.ebay.tests;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.ebay.factory.ExcelReader;
import com.ebay.factory.TestBase;
import com.ebay.pages.HomePage;
import com.ebay.pages.LandingPage;

public class LoginTest extends TestBase {

	Logger logger = Logger.getLogger(LoginTest.class);	
	
	@Test
	public void verifuySuccessfullLoginScenario() throws IOException{
		
		logger.info("Test Method started: verifuySuccessfullLoginScenario()");
		
		//Test data setup.
	    ExcelReader objExcelFile = new ExcelReader();

	    //Prepare the path of excel file
	    String filePath = System.getProperty("user.dir")+"\\src\\test\\resources";

	    //Call read file method of the class to read data
	    String userName = objExcelFile.readExcel(filePath,"LoginData.xlsx","dataSheet","name");
	    String password = objExcelFile.readExcel(filePath,"LoginData.xlsx","dataSheet","pwd");

		// Home Page Object
		HomePage homePage = new HomePage(driver);
		
		// Login to application
		LandingPage landingPage = homePage.loginIntoApplication(userName,password);
		logger.info("User navigated to Ebay Landing Page");
		
		// Verify Landing Page
		landingPage.verifyHeader();
		logger.info("Ebay Landing Page header verified successfully.");
		
		// Select Menu
		landingPage.selectMenuOptions("Electronics", "Mobile");
		
	}
	
	
	
	}
