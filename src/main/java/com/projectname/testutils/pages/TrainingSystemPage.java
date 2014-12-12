package com.projectname.testutils.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.projectname.testutils.pages.IntranetHomePage;
import com.projectname.testutils.seleniumutils.SeleniumWebDriver;


public class TrainingSystemPage extends SeleniumWebDriver{
	
	protected By readyLocator = By.linkText("For Information Security Management System (ISMS)");
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
		PageFactory.initElements(driver, this);
		isElementPresent(readyLocator);
	}

	public boolean verifyTrainingSystemPage(){
		boolean returnValue=true;
		
		if(!verifyElementISMS()){
			returnValue=false;
		}
		if(!verifyElementOrientationProg()){
			returnValue=false;
		}
		if(!verifyImpactProg()){
			returnValue=true;
		}
		if(!verifyWisdomCurve()){
			returnValue=false;
		}
		if(!verifyDevTeams()){
			returnValue=false;
		}
		if(!verifyTestingTeams()){
			returnValue=true;
		}
		return returnValue;
		
	}
	
	public boolean verifyImpactProg() {
		if (isElementPresent(lnkImpactProg)) {
			return true;
		} else
			return false;

	}
	public boolean verifyWisdomCurve() {
		if (isElementPresent(lnkWisdomCurve)) {
			return true;
		} else
			return false;

	}
	public boolean verifyDevTeams() {
		if (isElementPresent(lnkDevTeams)) {
			return true;
		} else
			return false;

	}
	public boolean verifyTestingTeams() {
		if (isElementPresent(lnkTestingTeams)) {
			return true;
		} else
			return false;

	}
	public boolean verifyElementISMS() {
		if (isElementPresent(lnkISMS)) {
			return true;
		} else
			return false;

	}
	public boolean verifyElementOrientationProg() {
		if (isElementPresent(lnkOrientationProg)) {
			return true;
		} else
			return false;

	}

}
