package com.projectname.testutils.pages.genericPages;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.projectname.testutils.genericutility.ExceptionHandler;
import com.projectname.testutils.seleniumutils.SeleniumWebDriver;

public class LoginPage extends SeleniumWebDriver {

	protected By txtUserName = By.id("edit-name");
	protected By txtPassword = By.id("edit-pass");

	protected By logoutButton = By.linkText("Logout");
	protected By submitButton = By.id("edit-submit--2");
	protected By loginButton = By.linkText("Log In");
	protected By lnkChangePwd = By.linkText("change password");
	protected By imgLoginPopup = By.id("modal-content");
	
	protected By lnkUserName = By.cssSelector("css=span.user_name");
	/***
	 * Call to super constructor
	 */
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	/***
	 * Login to the application
	 * 
	 * @throws ExceptionHandler
	 * @throws IOException 
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	//public Screen1 login(HashedMap testData, WebDriver driver)
	public HomePage login(String UserName, String Password){
			Assert.assertTrue(click(loginButton), "Could not click on login  button");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			Assert.assertTrue(sendKeys(txtUserName,UserName ),
					"Could not enter user name");
			Assert.assertTrue(sendKeys(txtPassword, Password),
					"Could not enter password");
	
			Assert.assertTrue(click(submitButton), "Could not click on submit button");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.getMessage();
			}
			//return new Screen1(driver);
			Assert.assertTrue(!isTextPresent("Log in"),"Could not Login to the application" );
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.getMessage();
			}
		return new HomePage(driver);
	}
	
	public void logOut(){
		Assert.assertTrue(click(lnkUserName), "Could not click on user name  button");
		waitForPageToLoad();
		if (waitForElement(logoutButton, 5)) {
			click(logoutButton);
			waitForPageToLoad();
		}
	}

}
