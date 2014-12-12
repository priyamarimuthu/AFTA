package com.projectname.testutils.pages;
import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import com.projectname.testutils.seleniumutils.SeleniumWebDriver;


public class IDMPage extends SeleniumWebDriver{
	
	protected By readyLocator = By.id("imgSearch");
	protected By btnSearch = By.id("imgSearch");
	
	
	
	
	/***
	 * Call to super constructor
	 */
	public IDMPage(){
		PageFactory.initElements(driver, this);
		isElementPresent(readyLocator);
	}

	/*************************************************************************************************** 
	 * @purpose To verify IDM Page
 	 * @action Verify the user details on IDM page
   	 * @author AspireQA
   	 * @since October 30, 2014
   	 ***************************************************************************************************/
	public boolean verifyIDMPage(HashedMap IDMData){
		boolean returnValue=true;
		
		if(!isTextPresent(IDMData.get("EN").toString())){
			returnValue=false;
		}
		if(!isTextPresent(IDMData.get("DOJ").toString())){
			returnValue=false;
		}
		if(!isTextPresent(IDMData.get("EmployeeType").toString())){
			returnValue=true;
		}
		if(!isTextPresent(IDMData.get("PrimaryManager").toString())){
			returnValue=false;
		}
		return returnValue;
	}
	
	
}
