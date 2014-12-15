package com.projectname.testutils.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.projectname.testutils.seleniumutils.SeleniumWebDriver;


public class TrainingSystemPage extends SeleniumWebDriver{
	
	private By readyLocator = By.linkText("For Information Security Management System (ISMS)");
	private By lnkISMS = By.linkText("For Information Security Management System (ISMS)");
	private By lnkOrientationProg = By.linkText("Orientation Program");
	private By lnkImpactProg = By.linkText("Impact Training Program");
	private By lnkWisdomCurve = By.linkText("Wisdom Curve");
	private By lnkDevTeams = By.linkText("For Development Teams");
	private By lnkTestingTeams = By.linkText("For Testing Teams");
	
	/***
	 * Call to super constructor
	 */
	public TrainingSystemPage(){
		PageFactory.initElements(driver, this);
		logTitleMessage("Instantiating page, waiting for element: "+readyLocator+ " to be present");
		waitForElement(readyLocator, READYLOCATORWAITTIME);
		logTitleMessage("Instantiated "+readyLocator +" , ready for use");
	}

	/**
	 * verify Training System Page
	 * @param 
	 * @return boolean
	 */
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
	
	/***
	 * Verify if IDM Link is present
	 * @return boolean
	 */
	public boolean verifyImpactProg() {
		if (isElementPresent(lnkImpactProg)) {
			return true;
		} else
			return false;

	}
	
	/***
	 * Verify if Wisdom Curve Link is present
	 * @return boolean
	 */
	public boolean verifyWisdomCurve() {
		if (isElementPresent(lnkWisdomCurve)) {
			return true;
		} else
			return false;

	}
	
	/***
	 * Verify if Dev Teams Link is present
	 * @return boolean
	 */
	public boolean verifyDevTeams() {
		if (isElementPresent(lnkDevTeams)) {
			return true;
		} else{
			return false;
		}
	}
	
	/***
	 * Verify if Testing Teams Link is present
	 * @return boolean
	 */
	public boolean verifyTestingTeams() {
		if (isElementPresent(lnkTestingTeams)) {
			return true;
		} else{
			return false;
		}
	}
	
	/***
	 * Verify if ISMS Link is present
	 * @return boolean
	 */
	public boolean verifyElementISMS() {
		if (isElementPresent(lnkISMS)) {
			return true;
		} else
			return false;

	}
	
	/***
	 * Verify if Orientation Program Link is present
	 * @return boolean
	 */
	public boolean verifyElementOrientationProg() {
		if (isElementPresent(lnkOrientationProg)) {
			return true;
		} else{
			return false;
		}
	}

}
