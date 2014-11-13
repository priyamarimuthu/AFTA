package com.projectname.testutils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.projectname.testutils.seleniumutils.SeleniumWebDriver;

public class SearchPage extends SeleniumWebDriver{

	protected By lnkHome = By.xpath("//a[contains(text(),'Home')]/parent::li");
	protected By readyLocator= By.xpath("//a[contains(text(),'Home')]/parent::li");
	protected By lnkAnnouncement = By.xpath("//a[contains(text(),'Announcement')]/parent::li");
	protected By lnkCelebrations = By.xpath("//a[contains(text(),'Celebrations')]/parent::li");
	protected By lnkClassifields = By.xpath("//a[contains(text(),'Classifieds')]/parent::li");
	
	
	
	/***
	 * Call to super constructor
	 */
	public SearchPage(WebDriver driver) {
		super(driver);
		waitForElement(readyLocator, 2000);
	}
	
	public boolean searchPage(){
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
