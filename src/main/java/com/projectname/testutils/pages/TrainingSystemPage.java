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
	 */
	public TrainingSystemPage(){
		PageFactory.initElements(SeleniumWebDriver.driver, this);
		isElementPresent(readyLocator);
	}

	public boolean verifyTrainingSystemPage(){
		boolean returnValue=true;
		if(!SeleniumWebDriver.isElementPresent(lnktrainingHours)){
			returnValue=true;
		}
		if(!SeleniumWebDriver.isElementPresent(lnktrainingHoursReport)){
			returnValue=false;
		}
		if(!SeleniumWebDriver.isElementPresent(lnkHome)){
			returnValue=false;
		}
		if(!SeleniumWebDriver.isElementPresent(lnkProfileSettings)){
			returnValue=true;
		}
		if(!SeleniumWebDriver.isElementPresent(lnkISMS)){
			returnValue=false;
		}
		if(!SeleniumWebDriver.isElementPresent(lnkOrientationProg)){
			returnValue=false;
		}
		return returnValue;
		
	}

}
