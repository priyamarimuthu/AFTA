package com.projectname.test.functional.modulename1;


import java.util.ArrayList;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.projectname.functional.annotations.MapToTestLink;
import com.projectname.testutils.baseclass.TestBaseClass;
import com.projectname.testutils.pages.LoginPage;
import com.projectname.testutils.pages.SearchPage;
import com.projectname.testutils.pages.SharedPage;
import com.projectname.testutils.retryAnalyser.RetryRule;
import com.projectname.testutils.testdatareader.ExcelReader;

@Listeners(com.projectname.testutils.baseclass.CustomizedReporter.class)

public class SearchByName extends TestBaseClass{
	SearchPage searchPage;

	

	/*************************************************************************************************** 
	 * @purpose To verify search function
 	 * @action Search an aspirian by name
   	 * @author AspireQA
   	 * @since October 30, 2014
   	 ***************************************************************************************************/
	
	@Test(retryAnalyzer = RetryRule.class,groups = {"Regression"})
	@MapToTestLink(testCaseID = "TestCase_3")
	public void searchbyname(){

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
		loginPage.login(loginTestData.get(0).get("UserName").toString(), loginTestData.get(0).get("Password").toString());
		logTitleMessage("Login Successful");
		
		// ------------------------------------------------------------------//
		// Step-2: Get the test data to search//
		// ------------------------------------------------------------------//
		ArrayList<HashedMap> testData = ExcelReader.getTestDataByTestCaseId(
				"TC_CT_001", SearchByName.class.getSimpleName());
		log.info(testData.get(0).get("TC_ID").toString() + " - ");
		
		// ------------------------------------------------------------------//
		// Step-2: Load Home page elements //
		// ------------------------------------------------------------------//
		SharedPage sharedPage = new SharedPage();
		log.info("Successfully loaded Home Page elements");
		
		// ------------------------------------------------------------------//
		// Step-3:Search by name //
		// ------------------------------------------------------------------//
		for(int i=0;i<testData.size();i++){
			log.info("Searching for: - "+testData.get(i).get("UserName"));
			searchPage=sharedPage.searchbyanyname(testData.get(i).get("UserName").toString());
			Assert.assertTrue("Could not find the Name: "+testData.get(i).get("UserName"),searchPage.verifySearchPage());
			log.info("Successfully got: - "+testData.get(i).get("UserName"));
		}
				
	}
}
