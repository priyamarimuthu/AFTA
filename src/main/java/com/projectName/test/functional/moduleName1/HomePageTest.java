package com.projectName.test.functional.moduleName1;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.projectName.testutils.baseclass.TestBaseClass;
import com.projectName.testutils.pages.genericPages.HomePage;
import com.projectName.testutils.pages.projectNamePages.HomeScreen;
import com.projectName.testutils.retryAnalyser.RetryRule;

@Listeners(com.projectName.testutils.baseclass.CustomizedReporter.class)

public class HomePageTest  extends TestBaseClass{

	@Test(retryAnalyzer = RetryRule.class)
	public void homePageTest(){
		// ------------------------------------------------------------------//
		// Step-1: Log in to the application //
		// ------------------------------------------------------------------//
		logTitleMessage("Login to application");
		homePage = loginUser1();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logTitleMessage("Login Successful");
		
		// ------------------------------------------------------------------//
		// Step-2:Load Home Page Variable //
		// ------------------------------------------------------------------//
		logTitleMessage("Load home page elements");
		homePage = PageFactory.initElements(driver, HomePage.class);	
		HomeScreen homeobject = homePage.navigateToHomePage();
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logTitleMessage("Successfully loaded Home Page elements");
		
		
		// ------------------------------------------------------------------//
		// Step-3:Verify Home page element //
		// ------------------------------------------------------------------//
		logTitleMessage("Verify presense of home page elements");
		Assert.assertTrue(homeobject.verifyelement(),"Verification failed");
		logTitleMessage("Verified home page elements");

	}
	
}
