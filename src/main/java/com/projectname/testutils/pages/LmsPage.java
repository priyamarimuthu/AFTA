package com.projectname.testutils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.projectname.testutils.seleniumutils.SeleniumWebDriver;

public class LmsPage extends SeleniumWebDriver{

	private By readyLocator = By.xpath("//td[@id='ctl00_IDMMenun2']/table/tbody/tr/td");
	
	public LmsPage() {
		PageFactory.initElements(SeleniumWebDriver.driver, this);
		isElementPresent(readyLocator);
		
	}

}
