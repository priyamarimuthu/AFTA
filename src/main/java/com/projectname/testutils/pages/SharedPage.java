package com.projectname.testutils.pages;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.projectname.testutils.baseclass.TestBaseClass;
import com.projectname.testutils.genericutility.Config;
import com.projectname.testutils.genericutility.ExceptionHandler;


public class SharedPage extends TestBaseClass {

	protected By txtUserName = By.id("edit-name1");
	protected By readyLocator = By.id("edit-name1");
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
	public SharedPage(WebDriver driver) {
		super();
		waitForElement(readyLocator, 2000);
	}

	/***
	 * Login to the application
	 * @throws ExceptionHandler
	 * @throws IOException 
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	
	public HomePage login(String UserName, String Password){
			click(btnLogin);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			sendKeys(txtUserName,Config.userName);
			sendKeys(txtPassword, Config.password);
	
			click(btnSubmit);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.getMessage();
			}
			waitForPageToLoad();
			isTextPresent("LMS");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.getMessage();
			}
		return new HomePage(driver);
	}
	
	public void logOut(){
		click(lnkUserName);
		waitForPageToLoad();
		if (waitForElement(btnLogout, 5)) {
			click(btnLogout);
			waitForPageToLoad();
		}
	}

}
