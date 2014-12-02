package com.projectname.testutils.pages;

import java.util.Set;

import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.projectname.testutils.seleniumutils.SeleniumWebDriver;
public class IntranetHomePage extends SeleniumWebDriver{
	
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
	
	/***
	 * Call to super constructor
	 */
	public IntranetHomePage(){
		PageFactory.initElements(SeleniumWebDriver.driver, this);	
		isElementPresent(readyLocator);
	}
	

	public boolean verifyelement(){
		boolean returnValue=true;
		if(!isElementPresent(lnkIDM)){
			returnValue=true;
		}
		if(!isElementPresent(lnkLMS)){
			returnValue=false;
		}
		if(!isElementPresent(lnkSeventhSense)){
			returnValue=false;
		}
		return returnValue;
	}
	
	public TrainingSystemPage goToTrainingSystemPage(){
		String parentHandle = driver.getWindowHandle();
		click(lnkTrainingSystem);
		Set<String> winHandle=driver.getWindowHandles();
		driver.switchTo().window(winHandle.iterator().toString()); 
		waitForPageToLoad();
		return new TrainingSystemPage();
	}
}
