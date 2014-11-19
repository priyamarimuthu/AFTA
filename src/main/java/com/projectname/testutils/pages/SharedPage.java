package com.projectname.testutils.pages;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.By;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;


import com.projectname.testutils.genericutility.Config;
import com.projectname.testutils.genericutility.ExceptionHandler;
import com.projectname.testutils.seleniumutils.SeleniumWebDriver;


public class SharedPage extends LoadableComponent<SharedPage>{

	protected By txtUserName = By.id("edit-name");
	protected By readyLocator = By.id("searchtextbox");
	protected By txtPassword = By.id("edit-pass");

	protected By btnLogout = By.linkText("Logout");
	protected By btnSubmit = By.id("edit-submit--2");
	protected By btnLogin = By.linkText("Log In");
	protected By lnkChangePwd = By.linkText("change password");
	protected By imgLoginPopup = By.id("modal-content");
	
	protected By lnkUserName = By.cssSelector("css=span.user_name");
	/***
	 * Call to super constructor
	 */
	public SharedPage(){
		PageFactory.initElements(SeleniumWebDriver.driver, this);
	}

	/***
	 * Login to the application
	 * @throws ExceptionHandler
	 * @throws IOException 
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	
	public HomePage login(String UserName, String Password){
		SeleniumWebDriver.click(btnLogin);
		SeleniumWebDriver.waitForPageToLoad();
		SeleniumWebDriver.sendKeys(txtUserName,Config.userName);
		SeleniumWebDriver.sendKeys(txtPassword, Config.password);
	
		SeleniumWebDriver.click(btnSubmit);
		SeleniumWebDriver.waitForPageToLoad();
		SeleniumWebDriver.waitForPageToLoad();
		SeleniumWebDriver.isTextPresent("LMS");
		SeleniumWebDriver.waitForPageToLoad();
		return new HomePage().get();
	}
	
	public void logOut(){
		SeleniumWebDriver.click(lnkUserName);
		SeleniumWebDriver.waitForPageToLoad();
		if (SeleniumWebDriver.waitForElement(btnLogout, 5)) {
			SeleniumWebDriver.click(btnLogout);
			SeleniumWebDriver.waitForPageToLoad();
		}
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
