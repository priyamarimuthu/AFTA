package com.projectname.testutils.seleniumutils;


import static org.testng.internal.EclipseInterface.ASSERT_LEFT;
import static org.testng.internal.EclipseInterface.ASSERT_MIDDLE;
import static org.testng.internal.EclipseInterface.ASSERT_RIGHT;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.bcel.verifier.exc.LoadingException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.Assert;

import com.projectname.testutils.genericutility.Config;
import com.projectname.testutils.genericutility.ExceptionHandler;
import com.thoughtworks.selenium.Selenium;

public class SeleniumWebDriver {

	/**
	 * Creating the web driver object to be used
	*/
	protected static WebDriver driver;
	WebDriverWait wait;
	private String returnString="";
	private Boolean result = true;
		
	//Time to wait for page to load
	private int secondsToWait = 20;
	
	public SeleniumWebDriver() {
		//SeleniumWebDriver.driver = driver;
	}
	public SeleniumWebDriver(WebDriver driver) {
		SeleniumWebDriver.driver = driver;
	}

	/**
	 * Verify the presence of a text in the page.
	 * @param driver
	 * @param text
	 * @return true/false
	 * @throws IOException 
	 */
	public boolean isTextPresent(String text){
		try {
			result= driver.getPageSource().contains(text);
			status = "Pass";
		}catch (Exception e) {
			status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), text.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), text.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
		return result;
	}

	/**
	 * Verify the presence of a element in the page. 
	 * @param By
	 * @param text
	 * @return true/false
	 * @throws IOException 
	 */
	public boolean isElementPresent(By element){
		boolean exists = true;
		try {
			exists = driver.findElement(element).isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), element.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), element.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
		return exists;
	}

	/**
	 * Wait for page to load
	 */
	public void causeMinorTimeDelay() {
		driver.manage().timeouts()
				.implicitlyWait(Config.DELAY_TIME, TimeUnit.SECONDS);
	}

	/**
	 * Wait for page to load
	 */
	public void causeTimeDelay() {
		try {
			int counter = 0;
			Thread.sleep(2000);
			while (true) {
				String ajaxIsComplete = ((JavascriptExecutor) driver)
						.executeScript("return Ajax.activeRequestCount")
						.toString();
				if (Integer.parseInt(ajaxIsComplete) == 0){
					break;
				}
				if (counter == 100){
					break;
				}
				Thread.sleep(100);
			}
		} catch (Exception e) {

		}

	}

	/**
	 * To Capture Screenshot
	 * @param driver
	 * @return
	 */
	public static File takeScreenshot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}

	public boolean waitForElement(final By ajaxElementName, int timeOutValue){
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, timeOutValue);
		try {
			ExpectedCondition<Boolean> e = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return driver.findElement(ajaxElementName).isDisplayed();
				}
			};
			wait.until(e);
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			result = false;
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), ajaxElementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), ajaxElementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
		return result;

	}
	
	/**
	 * To wait for element to load
	 * @param driver
	 * @return
	 */
	
	public void waitForElementToLoad(Selenium selenium, String elementId)
			throws InterruptedException {
		int i = 0;
		while (!selenium.isElementPresent(elementId)) {
			i++;
			Thread.sleep(3000);
			if (i == 9) {
				// Assert.fail("Time out :-CounldNotFind the Element With ID  : "+elementId
				// );
				break;
			}
		}
	}
	
	/**
	 * To type text on the field
	 * @param driver
	 * @return
	 */

	public boolean sendKeys(By elementLocator, String value) {
		try {
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
			driver.findElement(elementLocator).clear();
			driver.findElement(elementLocator).sendKeys(value);
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			result = false;
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), elementLocator.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), elementLocator.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
		return result;
	}

	/**
	 * To click an element on the screen
	 * @param driver
	 * @return
	 */
	
	public boolean click(final By ajaxElementName){
		try {
			waitForElement(ajaxElementName, Config.AVG_WAIT_TIME_FOR_ELEMENT);
			if (driver.findElement(ajaxElementName).isDisplayed()
					&& driver.findElement(ajaxElementName).isEnabled()) {
				driver.findElement(ajaxElementName).click();
				status = "Pass";
			} 
			
		} catch (Exception e) {
			status = "Fail";
			result = false;
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), ajaxElementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), ajaxElementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
		return result;
	}

	/**
	 * isChecked function to verify if the AJAX based Checkbox is checked
	 * @param selenium
	 * @param ajaxCheckboxName (Name of the ajax Checkbox)
	 * @throws IOException 
	 * @throws MyException
	 * @since March 04, 2013
	 */
	
	public boolean isChecked(final By ajaxCheckboxName){
		try{
			if (waitForElement(ajaxCheckboxName,
					Config.AVG_WAIT_TIME_FOR_ELEMENT)) {
				driver.findElement(ajaxCheckboxName).isSelected();
				boolean checkBoxStatus = driver.findElement(ajaxCheckboxName)
						.isSelected();
				if (checkBoxStatus) {
					status = "Pass";
					result = true;
				} else {
					status = "Fail";
					result = false;
				}
			} else {
				status = "Fail";
				result = false;
			}
		}
		catch (Exception e) {
			status = "fail";
			result = false;
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), ajaxCheckboxName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), ajaxCheckboxName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
		return result;
	}


	/**
	 * To wait for page to load
	 * @param driver
	 * @return
	 */
	
	public void waitForPageToLoad() {
		try {
			int counter = 0;
			
			while (true) {
				String readyState = ((JavascriptExecutor) driver)
						.executeScript("return document.readyState")
						.toString();

				//verify jquery loaded 
				int jQueryComplete =Integer.parseInt(((JavascriptExecutor) driver)
						.executeScript("return jQuery.active")
						.toString());
				
				if ((readyState.equals("complete")) && (jQueryComplete == 0))
					break;
				if (counter == secondsToWait){
					throw new LoadingException("PageNotLoadedException"); 
				}
				Thread.sleep(1000);
				counter++;
			}
			status = "Pass";
		}catch (Exception e) {
			status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), empty, empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		if(Config.requireToWrite)
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), empty, empty, status, empty, getCallingMethodAndLineNumber()));		
	}

	

	/**
	 * To mouse over on an element
	 * @param driver
	 * @return
	 */
	
	public void mouseOver(WebElement element){
		try {
			String code = "var fireOnThis = arguments[0];"
					+ "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initEvent( 'mouseover', true, true );"
					+ "fireOnThis.dispatchEvent(evObj);";
			((JavascriptExecutor) driver).executeScript(code, element);
			status = "Pass";
		}catch (Exception e) {
			status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), element.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), element.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
	}

	/**
	 * To select an option from the list
	 * @param driver
	 * @return
	 */
	public boolean select(By listName, String valueForSelection){
		valueForSelection = valueForSelection != null ? valueForSelection
				.trim() : "";
		try {
			waitForElement(listName, Config.AVG_WAIT_TIME_FOR_ELEMENT);
			if (driver.findElement(listName).isDisplayed()) {
				Select elSelect = new Select(driver.findElement(listName));
				elSelect.selectByVisibleText(valueForSelection);
				status = "Pass";
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			status = "Fail";
			result = false;
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), listName.toString(), valueForSelection, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), listName.toString(), valueForSelection, status, empty, getCallingMethodAndLineNumber()));
		}
		return result;
	}

	/**
	 * To get the text of a field
	 * @param driver
	 * @return
	 */
	
	public String getText(By elementName, int wait) throws ExceptionHandler, IOException {

		try {
			if (waitForElement(elementName, wait)) {
				returnString=driver.findElement(elementName).getText();
				status = "Pass";
			}
		}  catch (Exception e) {
			status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), elementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), elementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
		return returnString;
	}

	/**
	 * To get the value of a field
	 * @param driver
	 * @return
	 */
	public String getValue(By elementName){

		try {
			if (waitForElement(elementName, Config.AVG_WAIT_TIME_FOR_ELEMENT)) {
				returnString=driver.findElement(elementName).getAttribute("value");
				status = "Pass";
			} 
		} catch (Exception e) {
			status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), elementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), elementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
		return returnString;
	}

	// Customized Assert block starts
	
	/**
	 * Asserts that a condition is true. If it isn't,
	 * an AssertionError, with the given message, is thrown.
	 * @param condition the condition to evaluate
	 * @param message the assertion error message
	 */
		 public void assertTrue(boolean condition, String message, WebDriver driver) {
		    if(!condition) {
  	
			    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				String workingdirectory = System.getProperty("user.dir");
				File scrFile1 = new File(workingdirectory +"/custom-test-report/Failure_Screenshot/AssertFailure.jpg");
					
				try {
					FileUtils.copyFile(scrFile, scrFile1);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
					
				//log.info("Customized Assert true block executed...Temprory function, Need to enhance if You wish scrrenshot in report. Failure screenshot in 'custom-test-report/Failure_Screenshot/AssertFailure.jpg");
					
				failNotEquals( Boolean.valueOf(condition), Boolean.TRUE, message);
			    }
		}
			  
			  
		 static private void failNotEquals(Object actual , Object expected, String message ) {
			  Assert.fail(format(actual, expected, message));
		  }

		 static String format(Object actual, Object expected, String message) {
		    String formatted = "";
			    if (null != message) {
			      formatted = message + " ";
			    }
			    return formatted + ASSERT_LEFT + expected + ASSERT_MIDDLE + actual + ASSERT_RIGHT;
			  }
			  
	// Customized Assert block Ends
			  
			  
	// Customized Verify block starts
			
	  /**
	   * Asserts that a condition is true. If it isn't,
	   * an AssertionError, with the given message, is thrown.
	   * @param condition the condition to evaluate
	   * @param message the assertion error message
	   */
		 public void verifyTrue(boolean condition, String message, WebDriver driver) {
		    if(!condition) {
			    	
		    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				String workingdirectory = System.getProperty("user.dir");
				File scrFile1 = new File(workingdirectory +"/custom-test-report/Failure_Screenshot/AssertFailure.jpg");
			try {
				FileUtils.copyFile(scrFile, scrFile1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
					
			//log.info("Customized Verify True block executed...Temprory function, Need to enhance if You wish scrrenshot in report. Failure screenshot in 'custom-test-report/Failure_Screenshot/AssertFailure.jpg");
					
			 }
		 }
			  
	// Customized Verify block Ends
	
	//Report Part
	private final String deliminator = "####";
	private final String empty = "";
	
	private final String dot = ".";	
	
	private String status = null;
	

	private ITestResult logCustomMessage() {
		return Reporter.getCurrentTestResult();
	}
	
	/**
	 * used for get the calling method name with line number
	 * @return
	 */
	private String getCallingMethodAndLineNumber(){
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		
		String callingMethodWithLineNumber = stackTraceElements[3].getClassName() + dot + stackTraceElements[3].getMethodName() + dot + stackTraceElements[3].getLineNumber() ;
		
		return callingMethodWithLineNumber;
	}
	
	
	/**
	 * This method returns the current date and time in format HH-mm-ss.SSS
	 * 
	 * @return time - in the above mentioned format
	 */
	private static String getCurrentDateAndTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		Date date = new Date();
		String time = sdf.format(date);
		return time;
	}
	
	/**
	 * used to get the custom attribute value
	 * @param operation
	 * @param elementLocator1
	 * @param optional
	 * @param status
	 * @param screenShot
	 * @param callingMethodAndLineNumber
	 * @return
	 */
	private String getCustomAttributeValue(String operation,String elementLocator1, String optional,String status, String screenShot, String callingMethodAndLineNumber){
		
		return operation + deliminator + elementLocator1 + deliminator + optional + deliminator + status + deliminator + screenShot + deliminator + callingMethodAndLineNumber;
		
	}
	
	//End of code for reporting
	
	
	/**
	 * used for get the current method name
	 * @return
	 */
	private String getCurrentMethodName(){
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		
		String currentMethodName = stackTraceElements[2].getMethodName();
		
		return currentMethodName;
	}
		
}
