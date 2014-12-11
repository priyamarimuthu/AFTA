package com.projectname.testutils.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.projectname.testutils.seleniumutils.SeleniumWebDriver;


public class LoginPage extends SeleniumWebDriver{
	
	protected By readyLocator = By.linkText("Log In");
	protected By txtUserName = By.id("edit-name");
	protected By txtPassword = By.id("edit-pass");

	protected By btnLogout = By.linkText("Logout");
	protected By btnSubmit = By.id("edit-submit--2");
	protected By btnLogin = By.linkText("Log In");
	
	/***
	 * Call to super constructor
	 * @throws Exception 
	 */
	public LoginPage() throws Exception{
		PageFactory.initElements(SeleniumWebDriver.driver, this);	
		isElementPresent(readyLocator);
	}

	public IntranetHomePage login(String UserName, String Password) throws Exception{
		click(btnLogin);
		waitForPageToLoad();
		sendKeys(txtUserName,UserName);
		sendKeys(txtPassword, Password);
	
		click(btnSubmit);
		waitForPageToLoad();
		return new IntranetHomePage();
	}

}
