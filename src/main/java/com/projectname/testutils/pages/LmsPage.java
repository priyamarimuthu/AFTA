package com.projectname.testutils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LmsPage {

	protected By txtReleaseNote = By.xpath("//td[@id='ctl00_IDMMenun2']/table/tbody/tr/td");
	protected By ajxMyLeave = By.xpath("//td[@id='ctl00_IDMMenun1']/table/tbody/tr/td");
	
	public LmsPage(WebDriver driver) {
		
	}
}
