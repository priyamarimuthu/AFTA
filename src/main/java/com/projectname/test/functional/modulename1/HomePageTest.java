package com.projectname.test.functional.modulename1;

import java.util.ArrayList;

import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.projectname.functional.annotations.MapToTestLink;
import com.projectname.testutils.baseclass.TestBaseClass;
import com.projectname.testutils.pages.LoginPage;
import com.projectname.testutils.pages.IntranetHomePage;
import com.projectname.testutils.pages.SharedPage;
import com.projectname.testutils.retryAnalyser.RetryRule;
import com.projectname.testutils.testdatareader.ExcelReader;

@Listeners(com.projectname.testutils.baseclass.CustomizedReporter.class)

public class HomePageTest  extends TestBaseClass{
		
	IntranetHomePage homePage;
	/*************************************************************************************************** 
	 * @purpose To verify home page elements
 	 * @action Verify the links present on the home page
   	 * @author AspireQA
   	 * @since October 30, 2014
   	 ***************************************************************************************************/

	@Test(retryAnalyzer = RetryRule.class,groups = {"Regression"})
	@MapToTestLink(testCaseID = "TestCase_2")
	public void homePageTest(){
	
		ArrayList<HashedMap> testData = ExcelReader.getTestDataByTestCaseId(
				"TC_EBS_001", LoginTest.class.getSimpleName());
		log.info(testData.get(0).get("UserName").toString() + " - ");
	
		// ------------------------------------------------------------------//
		// Step-1: Login to the application
		// ------------------------------------------------------------------//
		logTitleMessage("Login to application");
		loginPage = new LoginPage();
		homePage=loginPage.login(testData.get(0).get("UserName").toString(), testData.get(0).get("Password").toString());
		logTitleMessage("Login Successful");
						
		// ------------------------------------------------------------------//
		// Step-2:Verify Home page element //
		// ------------------------------------------------------------------//
		logTitleMessage("Verify presense of home page elements");
		assertTrue(homePage.verifyelement(),"Verification failed",driver);
		logTitleMessage("Verified home page elements");
	}
}
