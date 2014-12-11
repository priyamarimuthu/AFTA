package com.projectname.test.functional.modulename1;

import java.util.ArrayList;

import org.apache.commons.collections.map.HashedMap;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.projectname.functional.annotations.MapToTestLink;
import com.projectname.testutils.baseclass.TestBaseClass;
import com.projectname.testutils.pages.IntranetHomePage;
import com.projectname.testutils.pages.LoginPage;
import com.projectname.testutils.retryAnalyser.RetryRule;
import com.projectname.testutils.testdatareader.ExcelReader;

//@Listeners(com.projectname.testutils.baseclass.CustomizedReporter.class)
@Listeners(com.projectname.testutils.support.EmailReport.class)

public class HomePageTest  extends TestBaseClass{
	IntranetHomePage homePage;
	/*************************************************************************************************** 
	 * @purpose To verify home page elements
 	 * @action Verify the links present on the home page
   	 * @author AspireQA
	 * @throws Exception 
   	 * @since October 30, 2014
   	 ***************************************************************************************************/

	
	@Test(retryAnalyzer = RetryRule.class,groups = {"Regression"})
	@MapToTestLink(testCaseID = "TestCase_2")
	public void homePageTest() throws Exception{
		log.testCaseInfo("Home Page Test" + "<small>[" + "To verify home page elements".split("<>")[0] + "]</small>");
		ArrayList<HashedMap> testData = ExcelReader.getTestDataByTestCaseId(
				"TC_EBS_001", LoginTest.class.getSimpleName());
		log.message(testData.get(0).get("UserName").toString() + " - ");
		
		// ------------------------------------------------------------------//
		// Step-1: Login to the application
		// ------------------------------------------------------------------//
		log.message("Login to application");
		loginPage = new LoginPage();
		homePage=loginPage.login(testData.get(0).get("UserName").toString(), testData.get(0).get("Password").toString());
		log.message("Login Successful");
						
		// ------------------------------------------------------------------//
		// Step-2:Verify Home page element //
		// ------------------------------------------------------------------//
		log.message("Verify presense of home page elements");
		assertTrue(homePage.verifyelement(),"Verification failed",driver);
		log.message("Verified home page elements");
	}
}
