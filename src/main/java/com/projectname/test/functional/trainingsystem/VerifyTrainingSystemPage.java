package com.projectname.test.functional.trainingsystem;


import java.util.ArrayList;

import org.apache.commons.collections.map.HashedMap;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.projectname.functional.annotations.MapToTestLink;
import com.projectname.test.functional.login.LoginTest;
import com.projectname.testutils.baseclass.TestBaseClass;
import com.projectname.testutils.pages.IntranetHomePage;
import com.projectname.testutils.pages.LoginPage;
import com.projectname.testutils.pages.TrainingSystemPage;
import com.projectname.testutils.retryAnalyser.RetryRule;
import com.projectname.testutils.testdatareader.ExcelReader;

@Listeners(com.projectname.testutils.baseclass.CustomizedReporter.class)

public class VerifyTrainingSystemPage extends TestBaseClass{
	private IntranetHomePage homePage;
	private TrainingSystemPage trainingSystemPage;

	

	/*************************************************************************************************** 
	 * @purpose To verify Training System Page
 	 * @action Verify the links present on the Training System page
   	 * @author AspireQA
   	 * @since October 30, 2014
   	 ***************************************************************************************************/
	
	@Test(retryAnalyzer = RetryRule.class,groups = {"Regression"})
	@MapToTestLink(testCaseID = "TestCase_4")
	public void verifyTrainingSystem(){

		// ------------------------------------------------------------------//
		// Step-1: Load the application //
		// ------------------------------------------------------------------//
	
		ArrayList<HashedMap> loginTestData = ExcelReader.getTestDataByTestCaseId(
				"TC_EBS_001", LoginTest.class.getSimpleName());
		log.info(loginTestData.get(0).get("UserName").toString() + " - ");
	
		// ------------------------------------------------------------------//
		// Step-2: Login to the application
		// ------------------------------------------------------------------//
		logTitleMessage("Login to application");
		loginPage = new LoginPage();
		homePage=loginPage.login(loginTestData.get(0).get("UserName").toString(), loginTestData.get(0).get("Password").toString());
		logTitleMessage("Login Successful");	
			
		// ------------------------------------------------------------------//
		// Step-3: Go to Training system Page //
		// ------------------------------------------------------------------//
		trainingSystemPage=homePage.clickTrainingSystemLink();
		assertTrue(trainingSystemPage.verifyTrainingSystemPage(),"Error in verifying training system page",driver);
		
	}
}
