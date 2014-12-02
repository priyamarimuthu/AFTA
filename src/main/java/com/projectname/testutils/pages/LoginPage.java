package com.projectname.testutils.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.projectname.testutils.genericutility.Config;
import com.projectname.testutils.pages.IntranetHomePage;
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
	 */
	public LoginPage(){
		PageFactory.initElements(SeleniumWebDriver.driver, this);	
		isElementPresent(readyLocator);
	}

	public IntranetHomePage login(String UserName, String Password){
		click(btnLogin);
		waitForPageToLoad();
		sendKeys(txtUserName,Config.userName);
		sendKeys(txtPassword, Config.password);
	
		click(btnSubmit);
		waitForPageToLoad();
		waitForPageToLoad();
		isTextPresent("LMS");
		waitForPageToLoad();
		return new IntranetHomePage();
	}

}
