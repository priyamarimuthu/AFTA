package com.projectname.test.functional.modulename2;


import java.util.ArrayList;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Assert;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.projectname.functional.annotations.MapToTestLink;
import com.projectname.testutils.baseclass.TestBaseClass;

import com.projectname.testutils.pages.SearchPage;
import com.projectname.testutils.pages.IntranetHomePage;

import com.projectname.testutils.retryAnalyser.RetryRule;
import com.projectname.testutils.testdatareader.ExcelReader;

@Listeners(com.projectname.testutils.baseclass.CustomizedReporter.class)

public class SearchByAnyName extends TestBaseClass{

	

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
	
		homePage = loginUser();
		log.info("Successfully navigated to Preferences Page.");
		
		// ------------------------------------------------------------------//
		// Step-2: Get the test data //
		// ------------------------------------------------------------------//
		ArrayList<HashedMap> testData = ExcelReader.getTestDataByTestCaseId(
				"TC_CT_001", SearchByAnyName.class.getSimpleName());
		log.info(testData.get(0).get("TC_ID").toString() + " - ");
		
		// ------------------------------------------------------------------//
		// Step-2: Load Home page elements //
		// ------------------------------------------------------------------//
		IntranetHomePage homeobject = homePage.navigateToHomePage();
		log.info("Successfully loaded Home Page elements");
		
		// ------------------------------------------------------------------//
		// Step-3:Search by name //
		// ------------------------------------------------------------------//
		for(int i=0;i<testData.size();i++){
			log.info("Searching for: - "+testData.get(i).get("UserName"));
			Assert.assertTrue("Could not find the Name: "+testData.get(i).get("UserName"),homeobject.searchbyanyname(testData.get(i)));
			log.info("Successfully got: - "+testData.get(i).get("UserName"));
		}
		
		
		// ------------------------------------------------------------------//
		// Step-4:Verify Search Screen page //
		// ------------------------------------------------------------------//
		SearchPage searchobject = homePage.navigateToSearchPage();
		Assert.assertTrue(searchobject.searchPage());
			
	}
}
