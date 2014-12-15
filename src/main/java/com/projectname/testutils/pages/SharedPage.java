package com.projectname.testutils.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import com.projectname.testutils.seleniumutils.SeleniumWebDriver;


public class SharedPage extends SeleniumWebDriver{

	
	private By readyLocator = By.id("searchtextbox");


	private By btnLogout = By.linkText("Logout");

	private By lnkUserName = By.cssSelector("css=span.user_name");
	private By txtSearchtextbox = By.id("searchtextbox");
	private By btnSearch = By.id("searchbtn");
	/***
	 * Call to super constructor
	 */
	public SharedPage(){
		PageFactory.initElements(SeleniumWebDriver.driver, this);
		logTitleMessage("Verifying Ready Locator for Shared Page: "+readyLocator);
		isElementPresent(readyLocator);
		logTitleMessage("Ready Locator got Successful");
	}

	/**
	 * Log out of the Application
	 * @param 
	 * @return void
	 */
	public void logOut(){
		click(lnkUserName);
		waitForPageToLoad();
		if (waitForElement(btnLogout, 5)) {
			click(btnLogout);
			waitForPageToLoad();
		}
	}
	
	/***
	 * Search by any name to the Home screen
	 * @return SearchPage
	 */
	
	public SearchPage searchbyanyname(String userName){
		sendKeys(txtSearchtextbox,userName);
		click(btnSearch);
		waitForPageToLoad();
		return new SearchPage();
	}

}
