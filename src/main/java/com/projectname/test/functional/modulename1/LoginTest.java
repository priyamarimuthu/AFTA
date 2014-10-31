package com.projectname.test.functional.modulename1;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.projectname.testutils.baseclass.TestBaseClass;
import com.projectname.testutils.retryAnalyser.RetryRule;

@Listeners(com.projectname.testutils.baseclass.CustomizedReporter.class)

public class LoginTest extends TestBaseClass {
	
	/*************************************************************************************************** 
	 * @purpose To verify login
 	 * @action Enter the credentials and click on submit button
   	 * @author AspireQA
   	 * @since October 30, 2014
   	 ***************************************************************************************************/
	
	@Test(retryAnalyzer = RetryRule.class)
	public void loginTest(){
	
	// ------------------------------------------------------------------//
	// Step-1: Login to the application
	// ------------------------------------------------------------------//
		/*logTitleMessage("Login to application");
		homePage = loginUser1();
		logTitleMessage("Login Successful");*/
	}
}