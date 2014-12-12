package com.projectname.testutils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.projectname.testutils.seleniumutils.SeleniumWebDriver;

public class IntranetHomePage extends SeleniumWebDriver {

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
	public IntranetHomePage() {
		PageFactory.initElements(SeleniumWebDriver.driver, this);
		isElementPresent(readyLocator);
	}
	
	/*************************************************************************************************** 
	 * @purpose To verify elements present on Intranet Home Page
 	 * @action Verify the links present on the Intranet Home Page
   	 * @author AspireQA
   	 * @since October 30, 2014
   	 ***************************************************************************************************/
	public boolean verifyelement() {
		boolean returnValue = true;
		if (!verifyElementIDM()) {
			returnValue = false;
		}
		if (!verifyElementMS()) {
			returnValue = false;
		}
		if (!verifyElementSeventhSense()) {
			returnValue = false;
		}
		return returnValue;
	}
	
	/*************************************************************************************************** 
	 * @purpose To click Training Systems Link
 	 * @action click Training Systems Link
   	 * @author AspireQA
   	 * @since October 30, 2014
   	 ***************************************************************************************************/

	public TrainingSystemPage clickTrainingSystemLink() {
		String winHandleBefore = driver.getWindowHandle();
		click(lnkTrainingSystem);
		waitForPageToLoad();
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		//driver.close();
		String winHandleAfter = driver.getWindowHandle();
		
		return new TrainingSystemPage();
	}
	
	/*************************************************************************************************** 
	 * @purpose To click IDM Link
 	 * @action click IDM Link
   	 * @author AspireQA
   	 * @since October 30, 2014
   	 ***************************************************************************************************/

	public IDMPage clickIDMLink() {
		String winHandleBefore = driver.getWindowHandle();
		click(readyLocator);
		waitForPageToLoad();
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		//driver.close();
		String winHandleAfter = driver.getWindowHandle();
		
		return new IDMPage();
	}

	
	/***
	 * Verify if IDM Link is present
	 */
	public boolean verifyElementIDM() {
		if (isElementPresent(lnkIDM)) {
			return true;
		} else
			return false;

	}
	/***
	 * Verify if LMS Link is present
	 */
	public boolean verifyElementMS() {
		if (isElementPresent(lnkLMS)) {
			return true;
		} else
			return false;

	}
	/***
	 * Verify if Seventh Sense Link is present
	 */
	public boolean verifyElementSeventhSense() {
		if (isElementPresent(lnkSeventhSense)) {
			return true;
		} else
			return false;

	}
}
