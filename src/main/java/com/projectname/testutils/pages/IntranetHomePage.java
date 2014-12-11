package com.projectname.testutils.pages;

import java.util.Set;

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
	 * @throws Exception 
	 */
	public IntranetHomePage() throws Exception {
		PageFactory.initElements(SeleniumWebDriver.driver, this);
		isElementPresent(readyLocator);
	}

	public boolean verifyelement() throws Exception {
		boolean returnValue = true;
		if (!verifyElementIDM()) {
			returnValue = true;
		}
		if (!verifyElementMS()) {
			returnValue = false;
		}
		if (!verifyElementSeventhSense()) {
			returnValue = false;
		}
		return returnValue;
	}

	public TrainingSystemPage clickTrainingSystemLink() throws Exception {
		click(lnkTrainingSystem);
		Set<String> winHandle = driver.getWindowHandles();
		driver.switchTo().window(winHandle.iterator().toString());
		waitForPageToLoad();
		return new TrainingSystemPage();
	}

	public boolean verifyElementIDM() throws Exception {
		if (!isElementPresent(lnkIDM)) {
			return true;
		} else
			return false;

	}

	public boolean verifyElementMS() throws Exception {
		if (!isElementPresent(lnkLMS)) {
			return true;
		} else
			return false;

	}

	public boolean verifyElementSeventhSense() throws Exception {
		if (!isElementPresent(lnkSeventhSense)) {
			return true;
		} else
			return false;

	}
}
