package com.projectname.testutils.pages;

import org.openqa.selenium.By;
import com.projectname.testutils.seleniumutils.SeleniumWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

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
		if(!isElementPresent(lnkHome)){
			return false;
		}
		if(!isElementPresent(lnkAnnouncement)){
			return false;
		}
		if(!isElementPresent(lnkCelebrations)){
			return false;
		}
		if(!isElementPresent(lnkClassifields)){
			return false;
		}
		return true;
	}
}
