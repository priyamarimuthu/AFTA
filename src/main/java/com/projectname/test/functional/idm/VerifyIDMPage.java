package com.projectname.test.functional.idm;


import java.util.ArrayList;

import org.apache.commons.collections.map.HashedMap;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.projectname.functional.annotations.MapToTestLink;
import com.projectname.test.functional.login.LoginTest;
import com.projectname.testutils.baseclass.TestBaseClass;
import com.projectname.testutils.pages.IDMPage;
import com.projectname.testutils.pages.IntranetHomePage;
import com.projectname.testutils.pages.LoginPage;
import com.projectname.testutils.pages.TrainingSystemPage;
import com.projectname.testutils.retryAnalyser.RetryRule;
import com.projectname.testutils.testdatareader.ExcelReader;

@Listeners(com.projectname.testutils.baseclass.CustomizedReporter.class)

public class VerifyIDMPage extends TestBaseClass{
	IntranetHomePage homePage;
	IDMPage iDMPage;

	

	/*************************************************************************************************** 
	 * @purpose To verify IDM Page
 	 * @action Go to IDM page and verify user details
   	 * @author AspireQA
   	 * @since October 30, 2014
   	 ***************************************************************************************************/
	
	@Test(retryAnalyzer = RetryRule.class,groups = {"Regression"})
	@MapToTestLink(testCaseID = "TestCase_5")
	public void verifyIDMPage(){

		// ------------------------------------------------------------------//
		// Step-1: Load the application //
		// ------------------------------------------------------------------//
	
		ArrayList<HashedMap> loginTestData = ExcelReader.getTestDataByTestCaseId(
				"TC_EBS_001", LoginTest.class.getSimpleName());
		log.info(loginTestData.get(0).get("UserName").toString() + " - ");
	
		// ------------------------------------------------------------------//
		// Step-1: Login to the application
		// ------------------------------------------------------------------//
		logTitleMessage("Login to application");
		loginPage = new LoginPage();
		homePage=loginPage.login(loginTestData.get(0).get("UserName").toString(), loginTestData.get(0).get("Password").toString());
		logTitleMessage("Login Successful");	
			
		// ------------------------------------------------------------------//
		// Step-2: Go to IDM system Page //
		// ------------------------------------------------------------------//
		iDMPage=homePage.clickIDMLink();
		// ------------------------------------------------------------------//
		// Step-2: Fetch Test Data for IDM Page //
		// ------------------------------------------------------------------//
		ArrayList<HashedMap> IDMData = ExcelReader.getTestDataByTestCaseId(
				"TC_IDM_001", VerifyIDMPage.class.getSimpleName());
		assertTrue(iDMPage.verifyIDMPage(IDMData.get(0)),"Error in verifying training system page",driver);
		
	}
}
