package com.projectname.test.functional.modulename1;


import org.apache.commons.collections.map.HashedMap;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.projectname.testutils.baseclass.TestBaseClass;
import com.projectname.testutils.pages.genericPages.HomePage;
import com.projectname.testutils.pages.genericPages.SearchPage;
import com.projectname.testutils.pages.projectnamepages.HomeScreen;
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
	
	@Test(retryAnalyzer = RetryRule.class)
	public void searchbyname(){

		// ------------------------------------------------------------------//
		// Step-1: Load the application //
		// ------------------------------------------------------------------//
	
		homePage = loginUser1();
		log.info("Successfully navigated to Preferences Page.");
		
		// ------------------------------------------------------------------//
		// Step-2: Get the test data //
		// ------------------------------------------------------------------//
		HashedMap testData = ExcelReader.getTestDataByTestCaseId(
				"TC_CT_001", SearchByAnyName.class.getSimpleName());
		log.info(testData.get("TC_ID").toString() + " - ");
		
		// ------------------------------------------------------------------//
		// Step-2: Load Home page elements //
		// ------------------------------------------------------------------//
		homePage = PageFactory.initElements(driver, HomePage.class);	
		HomeScreen homeobject = homePage.navigateToHomePage();
		
		SearchPage searchobject = homePage.navigateToSearchPage();
		log.info("Successfully loaded Home Page elements");
		
		// ------------------------------------------------------------------//
		// Step-3:Search by name //
		// ------------------------------------------------------------------//
		Assert.assertTrue(homeobject.searchbyanyname(testData));
		
		// ------------------------------------------------------------------//
		// Step-4:Verify Search Screen page //
		// ------------------------------------------------------------------//
		Assert.assertTrue(searchobject.searchPage());
			
	}
}
