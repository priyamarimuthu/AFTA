package com.projectname.test.functional.modulename1;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.projectname.testutils.baseclass.TestBaseClass;
import com.projectname.testutils.retryAnalyser.RetryRule;

@Listeners(com.projectname.testutils.baseclass.CustomizedReporter.class)

public class LoginTest extends TestBaseClass {
	/**
	 * Test to verify login
	 * @throws IOException 
	 */
 @Test(retryAnalyzer = RetryRule.class)
 public void loginTest(){
	// ------------------------------------------------------------------//
	// Step-2: Load the application //
	// ------------------------------------------------------------------//
	homePage = loginUser1();
	log.info("Successfully navigated to Preferences Page.");
	}
}