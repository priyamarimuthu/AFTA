package com.projectname.testutils.genericutility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ExceptionHandler extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customMessage = "";
	private WebDriver driver = null;
	private String message[];
	
    public ExceptionHandler(Exception e, WebDriver driver, String customMessage){
    	this.customMessage = customMessage;
    	this.driver        = driver;
    	
    	message = customMessage.split("####");
		
    	if(e.getCause().toString().contains("ArrayIndexOutOfBoundsException")){
			takeScreenshot(e);
		}
		else if(e.getCause().toString().contains("RuntimeException")){
			takeScreenshot(e);
		}
		else if(e.getCause().toString().contains("IOException")){
			takeScreenshot(e);
		}
		else if(e.getCause().toString().contains("NoSuchElementException")){
			takeScreenshot(e);
		}
		else if(e.getCause().toString().contains("InterruptedException")){
			takeScreenshot(e);
		}
		else if(e.getMessage().toString().contains("PageNotLoadedException")){
			takeScreenshot(e);
		}
		else if(e.getCause().toString().contains("Exception")){
			takeScreenshot(e);
		}
		e.getMessage();
    }
    
    private void takeScreenshot(Exception e){
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String workingdirectory = System.getProperty("user.dir");
		
		File scrFile1 = new File(workingdirectory +"/custom-test-report/Failure_Screenshot/"+message[5]+".jpg");
		
		try {
			FileUtils.copyFile(scrFile, scrFile1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		logCustomMessage().setAttribute(getCurrentDateAndTime(), customMessage);
		Assert.fail(e.getMessage());
		
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
	
	private ITestResult logCustomMessage() {
		return Reporter.getCurrentTestResult();
	}
	
}

