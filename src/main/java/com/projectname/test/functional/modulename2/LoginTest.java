package com.projectname.test.functional.modulename2;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.projectname.functional.annotations.MapToTestLink;
import com.projectname.testutils.pages.SharedPage;
import com.projectname.testutils.retryAnalyser.RetryRule;

@Listeners(com.projectname.testutils.baseclass.CustomizedReporter.class)

public class LoginTest extends SharedPage {
	
	public LoginTest(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/*************************************************************************************************** 
	 * @purpose To verify login
 	 * @action Enter the credentials and click on submit button
   	 * @author AspireQA
   	 * @since October 30, 2014
   	 ***************************************************************************************************/
	
	@Test(retryAnalyzer = RetryRule.class,groups = {"Regression"})
	@MapToTestLink(testCaseID = "TestCase_1")
	public void loginTest(){
	
	// ------------------------------------------------------------------//
	// Step-1: Login to the application
	// ------------------------------------------------------------------//
		logTitleMessage("Login to application");
		homePage = loginUser();
		logTitleMessage("Login Successful");
	}
}