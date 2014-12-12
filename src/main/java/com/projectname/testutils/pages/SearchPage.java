package com.projectname.testutils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.projectname.testutils.seleniumutils.SeleniumWebDriver;

public class SearchPage extends SeleniumWebDriver{

	protected By lnkHome = By.linkText("Home");
	protected By readyLocator= By.linkText("Home");
	protected By lnkAnnouncement = By.linkText("Announcements");
	protected By lnkCelebrations = By.linkText("Celebrations");
	protected By lnkClassifields = By.linkText("Classifieds");
	
	
	
	/***
	 * Call to super constructor
	 */
	public SearchPage() {
		PageFactory.initElements(SeleniumWebDriver.driver, this);
		isElementPresent(readyLocator);
	}
	
	public boolean verifySearchPage(){
		if(!verifyElementHome()){
			return false;
		}
		if(!verifyElementAnnouncement()){
			return false;
		}
		if(!verifyElementCelebrations()){
			return false;
		}
		if(!verifyElementCelebrations()){
			return false;
		}
		return true;
	}
	
	public boolean verifyElementHome() {
		if (isElementPresent(lnkHome)) {
			return true;
		} else
			return false;

	}
	public boolean verifyElementAnnouncement() {
		if (isElementPresent(lnkAnnouncement)) {
			return true;
		} else
			return false;

	}
	public boolean verifyElementCelebrations() {
		if (isElementPresent(lnkCelebrations)) {
			return true;
		} else
			return false;

	}
	public boolean verifyElementClassifields() {
		if (isElementPresent(lnkClassifields)) {
			return true;
		} else
			return false;

	}
}
