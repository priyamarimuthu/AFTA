package com.projectname.testutils.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.projectname.testutils.pages.IntranetHomePage;
import com.projectname.testutils.seleniumutils.SeleniumWebDriver;


public class TrainingSystemPage extends SeleniumWebDriver{
	
	protected By readyLocator = By.linkText("View Training Hours");
	protected By lnktrainingHours = By.linkText("View Training Hours");
	protected By lnktrainingHoursReport = By.linkText("Training Hours Report - Excel");
	protected By lnkHome = By.linkText("My home");
	protected By lnkProfileSettings = By.cssSelector("css=#usersettings > span");
	protected By lnkISMS = By.linkText("For Information Security Management System (ISMS)");
	protected By lnkOrientationProg = By.linkText("Orientation Program");
	protected By lnkImpactProg = By.linkText("Impact Training Program");
	protected By lnkWisdomCurve = By.linkText("Wisdom Curve");
	protected By lnkDevTeams = By.linkText("For Development Teams");
	protected By lnkTestingTeams = By.linkText("For Testing Teams");
	protected By lnkSupportTeams = By.linkText("For Support Teams");
	protected By lnkShortTraining = By.linkText("Short Training");
	protected By lnkSoftSkills = By.linkText("Soft Skills");
	protected By lnkGK = By.linkText("GK");
	
	
	
	/***
	 * Call to super constructor
	 * @throws Exception 
	 */
	public TrainingSystemPage() throws Exception{
		PageFactory.initElements(SeleniumWebDriver.driver, this);
		isElementPresent(readyLocator);
	}

	public boolean verifyTrainingSystemPage() throws Exception{
		boolean returnValue=true;
		if(!verifyTrainingHours()){
			returnValue=true;
		}
		if(!verifyTrainingHoursReport()){
			returnValue=false;
		}
		if(!verifyElementHome()){
			returnValue=false;
		}
		if(!verifyElementProfileSettings()){
			returnValue=true;
		}
		if(!verifyElementISMS()){
			returnValue=false;
		}
		if(!verifyElementOrientationProg()){
			returnValue=false;
		}
		return returnValue;
		
	}
	
	public boolean verifyTrainingHours() throws Exception {
		if (!isElementPresent(lnktrainingHours)) {
			return true;
		} else
			return false;

	}
	public boolean verifyTrainingHoursReport() throws Exception {
		if (!isElementPresent(lnktrainingHoursReport)) {
			return true;
		} else
			return false;

	}
	public boolean verifyElementHome() throws Exception {
		if (!isElementPresent(lnkHome)) {
			return true;
		} else
			return false;

	}
	public boolean verifyElementProfileSettings() throws Exception {
		if (!isElementPresent(lnkProfileSettings)) {
			return true;
		} else
			return false;

	}
	public boolean verifyElementISMS() throws Exception {
		if (!isElementPresent(lnkISMS)) {
			return true;
		} else
			return false;

	}
	public boolean verifyElementOrientationProg() throws Exception {
		if (!isElementPresent(lnkOrientationProg)) {
			return true;
		} else
			return false;

	}

}
