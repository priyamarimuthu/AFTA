package com.projectname.testutils.support;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Augmenter;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

public class Log{

	public static boolean printconsoleoutput;
	protected PrintWriter writer;
	protected final Logger logger = Logger.getLogger(getClass().getSimpleName());
	private final String deliminator = "####";

	public static void cleanScreenShotFolder(ITestContext context) throws IOException {

		printconsoleoutput = Boolean.valueOf(context.getCurrentXmlTest().getParameter("printconsoleoutput"));
		File screenShotFolder = new File(Reporter.getCurrentTestResult().getTestContext().getOutputDirectory());
		screenShotFolder = new File(screenShotFolder.getParent() + File.separator + "ScreenShot");

		if (!screenShotFolder.exists()) {
			screenShotFolder.mkdir();
			return;
		}

		File[] screenShots = screenShotFolder.listFiles();

		for (File screenShot : screenShots)
			screenShot.delete();

		screenShotFolder = new File(screenShotFolder.getParent() + File.separator + "Pass_ScreenShot");

		if (!screenShotFolder.exists()) {
			screenShotFolder.mkdir();
			return;
		}

		screenShots = screenShotFolder.listFiles();

		for (File screenShot : screenShots)
			screenShot.delete();

	}

	public void addTestRunMachineInfo(WebDriver driver) {

		Object params[] = Reporter.getCurrentTestResult().getParameters();
		String testMachine = "";
		String hub = "localhost";

		try {
			hub = (Reporter.getCurrentTestResult().getHost() == null) ? Inet4Address.getLocalHost().getHostName() : Reporter.getCurrentTestResult().getHost();
		}
		catch (UnknownHostException e) {
		}

		try {
			testMachine = "(Hub: " + hub + ", Node:)";
		}
		catch (Exception e) {
		}

		params[0] = testMachine;
		Reporter.getCurrentTestResult().setParameters(params);

	}

	public void testCaseInfo(String description) {

		if (Reporter.getOutput(Reporter.getCurrentTestResult()).toString().contains("<div class=\"test-title\">")) {
			Reporter.log("<div class=\"test-title\"> <strong><font color = \"blue\">" + description + "</font> </strong> </div><div><strong>Steps:</strong></div>");

		}
		else {
			Reporter.log("<div class=\"test-title\"> <strong><font color = \"blue\">" + description + "</font> </strong> </div><div><strong>Steps:</strong></div><!-- Report -->");

		}

		message("");
		System.out.println(description);
	}
	
	

	public void message(String description) {
		try {
			logger.info(description);
			logTitleMessage(description);
			Reporter.log("<div class=\"test-message\"><p><strong><font color = \"black\">" + new String(description.getBytes(), "UTF-8") + "</strong></p></div>");
		}
		catch (UnsupportedEncodingException e) {
		}

		if (printconsoleoutput)
			System.out.println(description);
	}
	//Report Part
	protected final String empty = "";
	
	protected final String dot = ".";	
	
	protected String status = null;
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
	protected String getCustomAttributeValue(String operation,String elementLocator1, String optional,String status, String screenShot, String callingMethodAndLineNumber){
		
		return operation + deliminator + elementLocator1 + deliminator + optional + deliminator + status + deliminator + screenShot + deliminator + callingMethodAndLineNumber;
		
	}
	
	protected boolean logTitleMessage(String message1){
		
		logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(message1,empty, empty, "title", empty, getCallingMethodAndLineNumber()));
		return true;
	}

	
	/**
	 * used for get the calling method name with line number
	 * @return
	 */
	protected String getCallingMethodAndLineNumber(){
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		
		String callingMethodWithLineNumber = stackTraceElements[3].getClassName() + dot + stackTraceElements[3].getMethodName() + dot + stackTraceElements[3].getLineNumber() ;
		
		return callingMethodWithLineNumber;
	}
	protected ITestResult logCustomMessage() {
		return Reporter.getCurrentTestResult();
	}
	/**
	 * This method returns the current date and time in format HH-mm-ss.SSS
	 * 
	 * @return time - in the above mentioned format
	 */
	protected static String getCurrentDateAndTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		Date date = new Date();
		String time = sdf.format(date);
		return time;
	}

	public void message(String description, WebDriver driver) {

		String inputFile = "";

		try {
			File screenShotFolder = new File(Reporter.getCurrentTestResult().getTestContext().getOutputDirectory());
			String strBasePath = screenShotFolder.getParent() + File.separator + "ScreenShot" + File.separator;
			inputFile = Reporter.getCurrentTestResult().getName() + "_" + (new Date()).getTime() + ".png";
			WebDriver augmented = new Augmenter().augment(driver);
			File screenshot = ((TakesScreenshot) augmented).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(strBasePath + inputFile));
		}
		catch (IOException | WebDriverException e1) {
			message("Screen shot capture failed.");
			e1.printStackTrace();
		}

		try {
			String screenShotLink = "<a href=\"." + File.separator + "ScreenShot" + File.separator + inputFile + "\" target=\"_blank\" >[ScreenShot]</a>";
			Reporter.log("<div class=\"test-message\">" + new String(description.getBytes(), "UTF-8") + screenShotLink + "</div>");

		}
		catch (UnsupportedEncodingException e) {
		}

		if (printconsoleoutput)
			System.out.println(description);
	}

	public static void event(String description) {

		try {
			Reporter.log("<div class=\"test-event\"> <i> " + new String(description.getBytes(), "UTF-8") + " <p> <small>" + "[Time: " + (new Date(System.currentTimeMillis())).toString() + "]"
					+ "</small> </p> </i> </div>");
		}
		catch (UnsupportedEncodingException e) {
		}

		if (printconsoleoutput)
			System.out.println(description + "[Time: " + (new Date(System.currentTimeMillis())).toString() + "]");
	}

	public static void event(String description, long duration) {

		Reporter.log("<div class=\"test-event\"> <i> " + description + " <p> <small>" + "[Duration: " + duration + "] " + "[Time: " + (new Date(System.currentTimeMillis())).toString() + "] ["
				+ Thread.currentThread().getStackTrace()[2].toString() + "]" + "</small> </p> </i> </div>");

		if (printconsoleoutput)
			System.out.println("\t--" + description + "[Duration: " + duration + "] [" + "Time: " + (new Date(System.currentTimeMillis())).toString() + "] ["
					+ Thread.currentThread().getStackTrace()[2].toString() + "]");
	}

	public void pass(String description) {

		Reporter.log("<div class=\"test-result\"><br><font color=\"green\"><strong> " + description + " </strong></font><br> </div>");

		if (printconsoleoutput)
			System.out.println("Pass: " + description);
	}

	public void pass(String description, WebDriver driver) {

		String inputFile = "";

		try {
			File screenShotFolder = new File(Reporter.getCurrentTestResult().getTestContext().getOutputDirectory());
			String strBasePath = screenShotFolder.getParent() + File.separator + "ScreenShot" + File.separator;
			inputFile = Reporter.getCurrentTestResult().getName() + "_" + (new Date()).getTime() + ".png";
			WebDriver augmented = new Augmenter().augment(driver);
			File screenshot = ((TakesScreenshot) augmented).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(strBasePath + inputFile));
		}
		catch (IOException | WebDriverException e1) {
			message("Screen shot capture failed.");
			e1.printStackTrace();
		}

		String screenShotLink = "<a href=\"." + File.separator + "ScreenShot" + File.separator + inputFile + "\" target=\"_blank\" >[ScreenShot]</a>";
		Reporter.log("<div class=\"test-result\"><br><font color=\"green\"><strong> " + description + " </strong></font>" + screenShotLink + "<br> </div>");

		if (printconsoleoutput)
			System.out.println("Pass: " + description);
	}

	public void fail(String description, WebDriver driver) {

		String inputFile = "";

		try {
			File screenShotFolder = new File(Reporter.getCurrentTestResult().getTestContext().getOutputDirectory());
			String strBasePath = screenShotFolder.getParent() + File.separator + "ScreenShot" + File.separator;
			inputFile = Reporter.getCurrentTestResult().getName() + "_" + (new Date()).getTime() + ".png";
			WebDriver augmented = new Augmenter().augment(driver);
			File screenshot = ((TakesScreenshot) augmented).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(strBasePath + inputFile));
		}
		catch (IOException | WebDriverException e1) {
			message("Screen shot capture failed.");
			e1.printStackTrace();
		}

		String screenShotLink = "<a href=\"." + File.separator + "ScreenShot" + File.separator + inputFile + "\" target=\"_blank\" >[ScreenShot]</a>";
		Reporter.log("<div class=\"test-result\"> <font color=\"red\"><strong>" + description + " </strong> </font>" + screenShotLink + "</div>");

		if (printconsoleoutput)
			System.out.println("Fail: " + description);

		Assert.fail(description);

	}

	public void captureScreenShot(WebDriver driver) {

		String inputFile = "";

		try {
			File screenShotFolder = new File(Reporter.getCurrentTestResult().getTestContext().getOutputDirectory());
			String strBasePath = screenShotFolder.getParent() + File.separator + "ScreenShot" + File.separator;
			inputFile = Reporter.getCurrentTestResult().getName() + "_" + (new Date()).getTime() + ".png";
			WebDriver augmented = new Augmenter().augment(driver);
			File screenshot = ((TakesScreenshot) augmented).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(strBasePath + inputFile));
		}
		catch (IOException | WebDriverException e1) {
			message("Screen shot capture failed.");
			e1.printStackTrace();
		}

	}

	public static void message(WebDriver driver, String description, String screenshotfolderpath, String screenshotfileName) throws IOException {

		String inputFile = screenshotfileName + ".png";
		WebDriver augmented = new Augmenter().augment(driver);
		File screenshot = ((TakesScreenshot) augmented).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(screenshotfolderpath + File.separator + inputFile));
		String screenShotLink = "<a href= \"" + screenshotfolderpath + File.separator + inputFile + "\" target=\"_blank\" >[ScreenShot]</a>";
		Reporter.log("<div class=\"test-message\">" + new String(description.getBytes(), "UTF-8") + screenShotLink + "</div>");

	}

	public void failsoft(String description, WebDriver driver) {

		String inputFile = "";

		try {
			File screenShotFolder = new File(Reporter.getCurrentTestResult().getTestContext().getOutputDirectory());
			String strBasePath = screenShotFolder.getParent() + File.separator + "ScreenShot" + File.separator;
			inputFile = Reporter.getCurrentTestResult().getName() + "_" + (new Date()).getTime() + ".png";
			WebDriver augmented = new Augmenter().augment(driver);
			File screenshot = ((TakesScreenshot) augmented).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(strBasePath + inputFile));
		}
		catch (IOException | WebDriverException e1) {
			message("Screen shot capture failed.");
			e1.printStackTrace();
		}

		String screenShotLink = "<a href=\"." + File.separator + "ScreenShot" + File.separator + inputFile + "\" target=\"_blank\" >[ScreenShot]</a>";
		Reporter.log("<div class=\"test-result\"> <small><font color=\"red\"><strong>" + description + " </strong> </font> </small>" + screenShotLink + "</div>");

		if (printconsoleoutput)
			System.out.println("Fail: " + description);

	}

	public void exception(Exception e, WebDriver driver) throws Exception {

		String inputFile = "";

		File screenShotFolder = new File(Reporter.getCurrentTestResult().getTestContext().getOutputDirectory());
		String strBasePath = screenShotFolder.getParent() + File.separator + "ScreenShot" + File.separator;
		
		try {
			
			inputFile = Reporter.getCurrentTestResult().getName() + "_" + (new Date()).getTime() + ".png";
			//WebDriver augmented = new Augmenter().augment(driver);
			File screenshot =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(strBasePath + inputFile));
		}
		catch (IOException | WebDriverException e1) {
			message("Screen shot capture failed.");
			e1.printStackTrace();
		}

		String screenShotLink = "<a href=\"" + strBasePath + inputFile + "\" target=\"_blank\" ><img src=\'" + strBasePath  + inputFile +"\' height=\"100\" width=\"100\"></a>";
		if (e instanceof SkipException) {

			Reporter.log("<div class=\"test-result\"> <font color=\"orange\"> " + " </strong> </font><p>" + screenShotLink + "</p></div>");
		}
		else {

			Reporter.log("<div class=\"test-result\"> <font color=\"orange\"> " + " </strong> </font><p>" + screenShotLink + "</p></div>");

		}

		if (e instanceof org.testng.internal.thread.ThreadTimeoutException)
			//WebDriverUtils.markUnResuable(driver);

		throw e;

	}
	
	public void exception(Exception e, WebDriver driver,String time,String operation,By elementName,String status,String callingMethodWithLineNumber) throws Exception {

		String inputFile = "";

		File screenShotFolder = new File(Reporter.getCurrentTestResult().getTestContext().getOutputDirectory());
		String strBasePath = screenShotFolder.getParent() + File.separator + "ScreenShot" + File.separator;
		
	//	Log.seleneseTraceMessage(time, operation, elementName, status, callingMethodWithLineNumber);
		
		try {
			
			inputFile = Reporter.getCurrentTestResult().getName() + "_" + (new Date()).getTime() + ".png";
			//WebDriver augmented = new Augmenter().augment(driver);
			File screenshot =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(strBasePath + inputFile));
		}
		catch (IOException | WebDriverException e1) {
			message("Screen shot capture failed.");
			e1.printStackTrace();
		}

		String screenShotLink = "<a href=\"" + strBasePath + inputFile + "\" target=\"_blank\" ><img src=\'" + strBasePath+ inputFile+ "\' height=\"100\" width=\"100\"></a>";
		if (e instanceof SkipException) {

		Reporter.log("<table class=\"inner\"><tr> <td class=\"inner\" height=\"5\"><strong><font color = \"red\">"+operation+"</td>" +
						"<td class=\"inner\" height=\"5\"><strong><font color = \"red\">"+elementName+"</td>" +
						"<td class=\"inner\" height=\"5\"><strong><font color = \"red\">"+status+"</td></tr>" +
								"</table><div class=\"test-result\"> <font color=\"orange\"> " + " </strong> </font><p>" + screenShotLink + "</p></div>");
		}
		else {

			Reporter.log("<table class=\"inner\"><tr> <td class=\"inner\" class=\"inner\" height=\"5\"><strong><font color = \"red\">"+operation+"</td>" +
							"<td class=\"inner\" height=\"5\"><strong><font color = \"red\">"+elementName+"</td>" +
							"<td class=\"inner\" height=\"5\"><strong><font color = \"red\">"+status+"</td></tr>" +
									"</table><div class=\"test-result\"> <font color=\"orange\"> " + " </strong> </font><p>" + screenShotLink + "</p></div>");

		}

		if (e instanceof org.testng.internal.thread.ThreadTimeoutException)
			//WebDriverUtils.markUnResuable(driver);

		throw e;

	}

	public static void exception(Exception e) throws Exception {

		if (e instanceof SkipException) {

			Reporter.log("<div class=\"test-result\"> <font color=\"orange\"> " + e.getMessage() + " </strong> </font> </div>");

		}
		else {

			Reporter.log("<div class=\"exceptions\"> <font color=\"red\"><I>" + e.getMessage() + " </I> </font> </div>");

		}

		throw e;

	}
	
	public void seleneseTraceMessage(String time,String operation,By elementName,String status,String callingMethodWithLineNumber) {

		if(status.equals("Pass")){
		if (Reporter.getOutput(Reporter.getCurrentTestResult()).toString().contains("<div class=\"test-title\">")) {
			Reporter.log("<table class=\"inner\"><tr> <td class=\"inner\" height=\"5\"><strong><font color = \"green\">"+operation+"</td>" +
							"<td class=\"inner\" height=\"5\"><strong><font color = \"green\">"+elementName+"</td>" +
							"<td class=\"inner\" height=\"5\"><strong><font color = \"green\">"+status+"</td></tr></table>");
		}
		else {
			Reporter.log("<table class=\"inner\"><tr> <td class=\"inner\" height=\"5\"><strong><font color = \"green\">"+operation+"</td>" +
							"<td class=\"inner\" height=\"5\"><strong><font color = \"green\">"+elementName+"</td>" +
							"<td class=\"inner\" height=\"5\"><strong><font color = \"green\">"+status+"</td></tr></table>");

		}

		message("");
		System.out.println("");
		}
		else{
			if (Reporter.getOutput(Reporter.getCurrentTestResult()).toString().contains("<div class=\"test-title\">")) {
				Reporter.log("<table class=\"inner\"><tr> <td class=\"inner\" height=\"5\"><strong><font color = \"red\">"+operation+"</td>" +
								"<td class=\"inner\" height=\"5\"><strong><font color = \"red\">"+elementName+"</td>" +
								"<td class=\"inner\" height=\"5\"><strong><font color = \"red\">"+status+"</td></tr></table>");
				 
			}
			else {
				Reporter.log("<table class=\"inner\"><tr> <td class=\"inner\" height=\"5\"><strong><font color = \"red\">"+operation+"</td><" +
								"td class=\"inner\" height=\"5\"><strong><font color = \"red\">"+elementName+"</td>" +
								"<td class=\"inner\" height=\"5\"><strong><font color = \"red\">"+status+"</td></tr></table>");

			}

			message("");
			System.out.println("");
		}
	}
}
