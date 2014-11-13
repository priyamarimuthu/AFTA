package com.projectname.test.functional.modulename1;


import java.util.ArrayList;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.projectname.functional.annotations.MapToTestLink;
import com.projectname.testutils.pages.HomePage;
import com.projectname.testutils.pages.SearchPage;
import com.projectname.testutils.pages.IntranetHomePage;
import com.projectname.testutils.pages.SharedPage;
import com.projectname.testutils.retryAnalyser.RetryRule;
import com.projectname.testutils.testdatareader.ExcelReader;

@Listeners(com.projectname.testutils.baseclass.CustomizedReporter.class)

public class SearchByAnyName extends SharedPage{

	public SearchByAnyName(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

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
		homePage = PageFactory.initElements(driver, HomePage.class);	
		IntranetHomePage homeobject = homePage.navigateToHomePage();
		
		SearchPage searchobject = homePage.navigateToSearchPage();
		log.info("Successfully loaded Home Page elements");
		
		// ------------------------------------------------------------------//
		// Step-3:Search by name //
		// ------------------------------------------------------------------//
		for(int i=0;i<testData.size();i++){
			Assert.assertTrue(homeobject.searchbyanyname(testData.get(i)));
		}
		
		
		// ------------------------------------------------------------------//
		// Step-4:Verify Search Screen page //
		// ------------------------------------------------------------------//
		Assert.assertTrue(searchobject.searchPage());
			
	}
}
