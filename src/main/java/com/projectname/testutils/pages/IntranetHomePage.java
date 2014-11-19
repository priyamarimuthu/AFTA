package com.projectname.testutils.pages;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.projectname.testutils.genericutility.ExceptionHandler;
import com.projectname.testutils.pages.IntranetHomePage;
import com.projectname.testutils.seleniumutils.SeleniumWebDriver;
public class IntranetHomePage extends LoadableComponent<IntranetHomePage>{
	
	protected By lnkIDM = By.linkText("123");
	protected By readyLocator = By.linkText("IDM/PMS");
	protected By lnkLearnStation = By.linkText("Learn Station");
	protected By lnkLMS = By.linkText("LMS");
	protected By lnkHelpDesk = By.linkText("Help Desk");
	protected By lnkTas = By.linkText("TAS");
	protected By lnkSeventhSense = By.linkText("Seventh Sense-Old");
	protected By lnkLibrary = By.linkText("Library");
	protected By lnkPayroll = By.linkText("Payroll");
	protected By lnkGain = By.linkText("iGain");
	protected By lnkTrainingSystem = By.linkText("Training System");
	protected By txtSearchtextbox = By.id("searchtextbox");
	protected By btnSearch = By.id("searchbtn");
	/***
	 * Call to super constructor
	 */
	public IntranetHomePage(){
		PageFactory.initElements(SeleniumWebDriver.driver, this);	
	}
	

	/***
	 * Search by any name to the Home screen
	 * @throws ExceptionHandler
	 * @throws IOException 
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public boolean searchbyanyname(HashedMap dashboardLibObj){
		SeleniumWebDriver.sendKeys(txtSearchtextbox,dashboardLibObj.get("UserName").toString());
		SeleniumWebDriver.click(btnSearch);
		SeleniumWebDriver.waitForPageToLoad();
		return true;
	}
	
	public boolean verifyelement(){
		boolean returnValue=true;
		if(!SeleniumWebDriver.isElementPresent(lnkIDM)){
			returnValue=true;
		}
		if(!SeleniumWebDriver.isElementPresent(lnkLMS)){
			returnValue=false;
		}
		if(!SeleniumWebDriver.isElementPresent(lnkSeventhSense)){
			returnValue=false;
		}
		return returnValue;
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
