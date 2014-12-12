package com.projectname.testutils.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.projectname.testutils.seleniumutils.SeleniumWebDriver;


public class LoginPage extends SeleniumWebDriver{
	
	private By readyLocator = By.linkText("Log In");
	private By txtUserName = By.id("edit-name");
	private By txtPassword = By.id("edit-pass");

	private By btnSubmit = By.id("edit-submit--2");
	private By btnLogin = By.linkText("Log In");
	
	/***
	 * Call to super constructor
	 */
	public LoginPage(){
		PageFactory.initElements(SeleniumWebDriver.driver, this);	
		isElementPresent(readyLocator);
	}

	/**
	 * login to the application
	 * @param String, String
	 * @return IntranetHomePage
	 */
	public IntranetHomePage login(String userName, String password){
		click(btnLogin);
		waitForPageToLoad();
		sendKeys(txtUserName,userName);
		sendKeys(txtPassword, password);
	
		click(btnSubmit);
		waitForPageToLoad();
		return new IntranetHomePage();
	}

}
