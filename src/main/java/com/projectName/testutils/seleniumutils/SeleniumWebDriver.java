package com.projectName.testutils.seleniumutils;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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

import com.projectName.testutils.genericutility.Constants;
import com.projectName.testutils.genericutility.ExceptionHandler;
import com.thoughtworks.selenium.Selenium;

public class SeleniumWebDriver {

	/**
	 * Creating the web driver object to be used
	 */
	protected static WebDriver driver;
	WebDriverWait wait;
	private String returnString="";
	private Boolean result = true;
	
	//used for weather click, type etc.. (low level log) need to be in report or not  
	private Boolean requireToWrite = true; 
	
	public SeleniumWebDriver(WebDriver driver) {
		SeleniumWebDriver.driver = driver;
	}

	/**
	 * Veriy the presence of a text in the page.
	 * 
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
			new ExceptionHandler(e, driver, getCustomAttributeValue("Is Text Present", text.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		if(requireToWrite)
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue("Is Text Present", text.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		
		return result;
	}

	/**
	 * Veriy the presence of a element in the page.
	 * 
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
			new ExceptionHandler(e, driver, getCustomAttributeValue("isElement Present", element.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(requireToWrite)
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue("isElementPresent", element.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		
		return exists;
	}

	/**
	 * Wait for page to load
	 */
	public void causeMinorTimeDelay() {
		driver.manage().timeouts()
				.implicitlyWait(Constants.DELAY_TIME, TimeUnit.SECONDS);
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
				if (Integer.parseInt(ajaxIsComplete) == 0)
					break;
				if (counter == 100)
					break;
				Thread.sleep(100);
			}
		} catch (Exception e) {

		}

	}

	/**
	 * The screen shot is captured
	 * 
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
			new ExceptionHandler(e, driver, getCustomAttributeValue("WaitForElement", ajaxElementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(requireToWrite)
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue("WaitForElement", ajaxElementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		
		return result;

	}

	// Wait for Element to Load
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

	public boolean sendKeys(By elementLocator, String value) {
		try {
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
			driver.findElement(elementLocator).clear();
			driver.findElement(elementLocator).sendKeys(value);
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			result = false;
			new ExceptionHandler(e, driver, getCustomAttributeValue("SendKeys", elementLocator.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(requireToWrite)
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue("SendKeys", elementLocator.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		
		return result;
	}

	public boolean click(final By ajaxElementName){
		try {
			waitForElement(ajaxElementName, Constants.AVG_WAIT_TIME_FOR_ELEMENT);
			if (driver.findElement(ajaxElementName).isDisplayed()
					&& driver.findElement(ajaxElementName).isEnabled()) {
				driver.findElement(ajaxElementName).click();
				status = "Pass";
			} 
			
		} catch (Exception e) {
			status = "Fail";
			result = false;
			new ExceptionHandler(e, driver, getCustomAttributeValue("Click", ajaxElementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(requireToWrite)
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue("Click", ajaxElementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		
		return result;
	}

	/**
	 * isChecked function to verify if the AJAX based Checkbox is checked
	 * 
	 * @param selenium
	 * @param ajaxCheckboxName
	 *            (Name of the ajax Checkbox)
	 * @throws IOException 
	 * @throws MyException
	 * 
	 * @since March 04, 2013
	 */
	public boolean isChecked(final By ajaxCheckboxName){
		try{
			if (waitForElement(ajaxCheckboxName,
					Constants.AVG_WAIT_TIME_FOR_ELEMENT)) {
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
			new ExceptionHandler(e, driver, getCustomAttributeValue("Is Checked", ajaxCheckboxName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(requireToWrite)
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue("Is Checked", ajaxCheckboxName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		
		return result;
	}


	public void waitForPageToLoad() {
		String element="";
		try {
			int counter = 0;
			
			Thread.sleep(1000);
			while (true) {
				String ajaxIsComplete = ((JavascriptExecutor) driver)
						.executeScript("return Ajax.activeRequestCount")
						.toString();
				if (Integer.parseInt(ajaxIsComplete) == 0)
					break;
				if (counter == 20)
					break;
				Thread.sleep(100);
				counter++;
			}
			status = "Pass";
		}catch (Exception e) {
			status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue("Wait for page to load", element.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		if(requireToWrite)
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue("Wait for page to load", element.toString(), empty, status, empty, getCallingMethodAndLineNumber()));		
	}
	

	// Mouse over Method
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
			new ExceptionHandler(e, driver, getCustomAttributeValue("Mouse Over", element.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(requireToWrite)
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue("Mouse Over", element.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		
	}

	public boolean select(By listName, String valueForSelection){
		valueForSelection = valueForSelection != null ? valueForSelection
				.trim() : "";
		try {
			waitForElement(listName, Constants.AVG_WAIT_TIME_FOR_ELEMENT);
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
			new ExceptionHandler(e, driver, getCustomAttributeValue("Selct", listName.toString(), valueForSelection, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(requireToWrite)
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue("Select", listName.toString(), valueForSelection, status, empty, getCallingMethodAndLineNumber()));
		
		return result;
	}

	public String getText(By elementName, int wait) throws ExceptionHandler, IOException {

		try {
			if (waitForElement(elementName, wait)) {
				returnString=driver.findElement(elementName).getText();
				status = "Pass";
			}
		}  catch (Exception e) {
			status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue("Get Text", elementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(requireToWrite)
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue("Get Text", elementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		
		return returnString;
	}

	public String getValue(By elementName){

		try {
			if (waitForElement(elementName, Constants.AVG_WAIT_TIME_FOR_ELEMENT)) {
				returnString=driver.findElement(elementName).getAttribute("value");
				status = "Pass";
			} 
		} catch (Exception e) {
			status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue("Get Value", elementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(requireToWrite)
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue("Get Value", elementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		
		return returnString;
	}

	
	
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
		
}
