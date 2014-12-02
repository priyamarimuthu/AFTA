package com.projectname.testutils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.projectname.testutils.seleniumutils.SeleniumWebDriver;

public class LmsPage extends SeleniumWebDriver{

	protected By readyLocator = By.xpath("//td[@id='ctl00_IDMMenun2']/table/tbody/tr/td");
	protected By txtReleaseNote = By.xpath("//td[@id='ctl00_IDMMenun2']/table/tbody/tr/td");
	protected By ajxMyLeave = By.xpath("//td[@id='ctl00_IDMMenun1']/table/tbody/tr/td");
	
	public LmsPage() {
		PageFactory.initElements(SeleniumWebDriver.driver, this);
		isElementPresent(readyLocator);
		
	}

}
