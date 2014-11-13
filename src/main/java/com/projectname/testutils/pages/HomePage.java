package com.projectname.testutils.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.projectname.testutils.pages.IntranetHomePage;
import com.projectname.testutils.seleniumutils.SeleniumWebDriver;


public class HomePage extends SeleniumWebDriver {
	
	protected By readyLocator = By.linkText("LMS");
	
	/***
	 * Call to super constructor
	 */
	public HomePage(WebDriver driver) {
		super(driver);
		waitForElement(readyLocator, 2000);
	}

	public String getLoggedInUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void logOut() {
		// TODO Auto-generated method stub

	}

	

	public SharedPage navigateToLoginPage(){
		SharedPage createRequestPage = PageFactory.initElements(driver,
				SharedPage.class);
		return createRequestPage;

	}

	public IntranetHomePage navigateToHomePage(){
		IntranetHomePage createHomePage = PageFactory.initElements(driver,
				IntranetHomePage.class);
		return createHomePage;
	}
	
	public SearchPage navigateToSearchPage(){
		SearchPage searchPage = PageFactory.initElements(driver,
				SearchPage.class);
		return searchPage;
	}

}
