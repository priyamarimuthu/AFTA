package com.projectname.test.functional.trainingsystem;


import java.util.ArrayList;

import org.apache.commons.collections.map.HashedMap;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.projectname.functional.annotations.MapToTestLink;
import com.projectname.test.functional.modulename1.LoginTest;
import com.projectname.testutils.baseclass.TestBaseClass;
import com.projectname.testutils.pages.IntranetHomePage;
import com.projectname.testutils.pages.LoginPage;
import com.projectname.testutils.pages.TrainingSystemPage;
import com.projectname.testutils.retryAnalyser.RetryRule;
import com.projectname.testutils.testdatareader.ExcelReader;

//@Listeners(com.projectname.testutils.baseclass.CustomizedReporter.class)
@Listeners(com.projectname.testutils.support.EmailReport.class)

public class verifyTrainingSystemPage extends TestBaseClass{
	IntranetHomePage homePage;
	TrainingSystemPage trainingSystemPage;

	

	/*************************************************************************************************** 
	 * @purpose To verify Training System Page
 	 * @action Search an aspirian by name
   	 * @author AspireQA
	 * @throws Exception 
   	 * @since October 30, 2014
   	 ***************************************************************************************************/
	
	@Test(retryAnalyzer = RetryRule.class,groups = {"Regression"})
	@MapToTestLink(testCaseID = "TestCase_3")
	public void verifyTrainingSystem() throws Exception{
		log.testCaseInfo("Verify Training System" + "<small>[" + "To verify trainig system page".split("<>")[0] + "]</small>");
		// ------------------------------------------------------------------//
		// Step-1: Load the application //
		// ------------------------------------------------------------------//
	
		ArrayList<HashedMap> loginTestData = ExcelReader.getTestDataByTestCaseId(
				"TC_EBS_001", LoginTest.class.getSimpleName());
		log.message(loginTestData.get(0).get("UserName").toString() + " - ");
	
		// ------------------------------------------------------------------//
		// Step-1: Login to the application
		// ------------------------------------------------------------------//
		log.message("Login to application");
		loginPage = new LoginPage();
		homePage=loginPage.login(loginTestData.get(0).get("UserName").toString(), loginTestData.get(0).get("Password").toString());
		log.message("Login Successful");	
			
		// ------------------------------------------------------------------//
		// Step-2: Go to Training system Page //
		// ------------------------------------------------------------------//
		trainingSystemPage=homePage.clickTrainingSystemLink();
		assertTrue(trainingSystemPage.verifyTrainingSystemPage(),"Error in verifying training system page",driver);
		
	}
}
