package com.projectname.testutils.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.projectname.testutils.pages.IntranetHomePage;
import com.projectname.testutils.seleniumutils.SeleniumWebDriver;


public class HomePage extends LoadableComponent<HomePage>{
	
	protected By readyLocator = By.linkText("IDM/PMS");
	
	/***
	 * Call to super constructor
	 */
	public HomePage(){
		PageFactory.initElements(SeleniumWebDriver.driver, this);	
	}

	public String getLoggedInUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void logOut() {
		// TODO Auto-generated method stub

	}

	

	public SharedPage navigateToLoginPage(){
		SharedPage createRequestPage = new SharedPage().get();
		return createRequestPage;

	}

	public IntranetHomePage navigateToHomePage(){
		IntranetHomePage createHomePage = new IntranetHomePage().get();
		return createHomePage;
	}
	
	public SearchPage navigateToSearchPage(){
		SearchPage searchPage = new	SearchPage().get();
		return searchPage;
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		SeleniumWebDriver.isElementPresent(readyLocator);
	}

}
