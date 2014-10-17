package com.projectname.testutils.pages.projectnamepages;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.projectname.testutils.genericutility.ExceptionHandler;
import com.projectname.testutils.pages.projectnamepages.HomeScreen;
import com.projectname.testutils.seleniumutils.SeleniumWebDriver;
public class HomeScreen extends SeleniumWebDriver{
	
	protected By lnkIDM = By.linkText("123");
	protected By lnkLearnStation = By.linkText("Learn Station");
	protected By lnkLMS = By.linkText("LMS");
	protected By lnkHelpDesk = By.linkText("Help Desk");
	protected By lnkTas = By.linkText("TAS");
	protected By lnkSeventhSense = By.linkText("Seventh Sense");
	protected By lnkLibrary = By.linkText("Library");
	protected By lnkPayroll = By.linkText("Payroll");
	protected By lnkGain = By.linkText("iGain");
	protected By lnkTrainingSystem = By.linkText("Training System");
	protected By txtSearchtextbox = By.id("searchtextbox");
	protected By btnSearch = By.id("searchbtn");
	/***
	 * Call to super constructor
	 */
	public HomeScreen(WebDriver driver) {
		super(driver);
	}
	

	/***
	 * Search by any name to the Home screen
	 * @throws ExceptionHandler
	 * @throws IOException 
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public boolean searchbyanyname(HashedMap dashboardLibObj){
		Assert.assertTrue(sendKeys(txtSearchtextbox,dashboardLibObj.get("UserName").toString()),"Could not enter user name");
		Assert.assertTrue(click(btnSearch),"could not press the enter button");
		return true;
	}
	
	public boolean verifyelement(){
		boolean returnValue=true;
		if(!isElementPresent(lnkIDM)){
			returnValue=false;
		}
		if(!isElementPresent(lnkLMS)){
			returnValue=false;
		}
		if(!isElementPresent(lnkSeventhSense)){
			returnValue=false;
		}
		return returnValue;
	}
}
