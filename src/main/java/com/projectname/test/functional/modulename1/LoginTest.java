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

public class LoginTest extends TestBaseClass {
	IntranetHomePage intranetHomePage=null;
	
	
	/*************************************************************************************************** 
	 * @purpose To verify login
 	 * @action Enter the credentials and click on submit button
   	 * @author AspireQA
	 * @throws Exception 
   	 * @since October 30, 2014
   	 ***************************************************************************************************/
	
	@Test(retryAnalyzer = RetryRule.class,groups = {"Regression"})
	@MapToTestLink(testCaseID = "TestCase_1")
	public void loginTest() throws Exception{
		log.testCaseInfo("Login Test" + "<small>[" + "To verify login".split("<>")[0] + "]</small>");
		ArrayList<HashedMap> testData = ExcelReader.getTestDataByTestCaseId(
				"TC_EBS_001", LoginTest.class.getSimpleName());
		log.message(testData.get(0).get("UserName").toString() + " - ");
	
	// ------------------------------------------------------------------//
	// Step-1: Login to the application
	// ------------------------------------------------------------------//
		log.message("Login to application");
		loginPage = new LoginPage();
		loginPage.login(testData.get(0).get("UserName").toString(), testData.get(0).get("Password").toString());
		log.message("Login Successful");
	}
}