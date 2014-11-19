package com.projectname.testutils.pages;

import org.openqa.selenium.By;
import com.projectname.testutils.seleniumutils.SeleniumWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.projectname.testutils.seleniumutils.SeleniumWebDriver;

public class SearchPage extends LoadableComponent<SearchPage>{

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
	}
	
	public boolean searchPage(){
		if(!SeleniumWebDriver.isElementPresent(lnkHome)){
			return false;
		}
		if(!SeleniumWebDriver.isElementPresent(lnkAnnouncement)){
			return false;
		}
		if(!SeleniumWebDriver.isElementPresent(lnkCelebrations)){
			return false;
		}
		if(!SeleniumWebDriver.isElementPresent(lnkClassifields)){
			return false;
		}
		return true;
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
