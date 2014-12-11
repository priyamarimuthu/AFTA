package com.projectname.testutils.support;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Augmenter;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.SkipException;

public class Log1 {

	public static boolean printconsoleoutput;

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

	public static void addTestRunMachineInfo(WebDriver driver) {

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

		params[0] = testMachine + ", UserAgent: " + params[0].toString().trim();
		Reporter.getCurrentTestResult().setParameters(params);

	}

	public static void testCaseInfo(String description) {

		if (Reporter.getOutput(Reporter.getCurrentTestResult()).toString().contains("<div class=\"test-title\">")) {
			Reporter.log("<div class=\"test-title\"> <strong><font color = \"blue\">" + description + "</font> </strong> </div><div><strong>Steps:</strong></div>");

		}
		else {
			Reporter.log("<div class=\"test-title\"> <strong><font color = \"blue\">" + description + "</font> </strong> </div><div><strong>Steps:</strong></div>");

		}

		message("");
		System.out.println(description);
	}
	
	public static void seleneseTraceMessage(String time,String operation,By elementName,String status,String callingMethodWithLineNumber) {

		if(status.equals("Pass")){
		if (Reporter.getOutput(Reporter.getCurrentTestResult()).toString().contains("<div class=\"test-title\">")) {
			Reporter.log("<table style><col width=\"150\"> <col width=\"180\"> <col width=\"100\"><tr> <td height=\"8\"><strong><font color = \"green\">"+operation+"</td><td height=\"8\"><strong><font color = \"green\">"+elementName+"</td><td height=\"8\"><strong><font color = \"green\">"+status+"</td></tr>");
		}
		else {
			Reporter.log("<table style><col width=\"150\"> <col width=\"180\"> <col width=\"100\"><tr> <td height=\"8\"><strong><font color = \"green\">"+operation+"</td><td height=\"8\"><strong><font color = \"green\">"+elementName+"</td>"+"<td height=\"8\"><strong><font color = \"green\">"+status+"</td></tr>");

		}

		message("");
		System.out.println("");
		}
		else{
			if (Reporter.getOutput(Reporter.getCurrentTestResult()).toString().contains("<div class=\"test-title\">")) {
				Reporter.log("<table style><col width=\"150\"> <col width=\"180\"> <col width=\"100\"><tr> <td height=\"8\"><strong><font color = \"red\">"+operation+"</td><td height=\"8\"><strong><font color = \"red\">"+elementName+"</td><td height=\"8\"><strong><font color = \"red\">"+status+"</td></tr>");
				 
			}
			else {
				Reporter.log("<table style><col width=\"150\"> <col width=\"180\"> <col width=\"100\"><tr> <td height=\"8\"><strong><font color = \"red\">"+operation+"</td><td height=\"8\"><strong><font color = \"red\">"+elementName+"</td><td height=\"8\"><strong><font color = \"red\">"+status+"</td></tr>");

			}

			message("");
			System.out.println("");
		}
	}

	public static void message(String description) {
		try {

			Reporter.log("<div class=\"test-message\"><strong><font color = \"black\"><p>"+ new String(description.getBytes(), "UTF-8") + "</strong> </p></div>");

		}
		catch (UnsupportedEncodingException e) {
		}

		if (printconsoleoutput)
			System.out.println(description);
	}
	
	

	public static void message(String description, WebDriver driver) {

		String inputFile = "";

		try {
			File screenShotFolder = new File(Reporter.getCurrentTestResult().getTestContext().getOutputDirectory());
			String strBasePath = screenShotFolder.getParent() + File.separator + "ScreenShot" + File.separator;
			inputFile = Reporter.getCurrentTestResult().getName() + "_" + (new Date()).getTime() + ".png";
			//WebDriver augmented = new Augmenter().augment(driver);
			
			File screenshot =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
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

	public static void pass(String description) {

		Reporter.log("<div class=\"test-result\"><br><font color=\"green\"><strong> " + description + " </strong></font><br> </div>");

		if (printconsoleoutput)
			System.out.println("Pass: " + description);
	}

	public static void pass(String description, WebDriver driver) {

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

	public static void fail(String description, WebDriver driver) {

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

	public static void captureScreenShot(WebDriver driver) {

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

	public static void failsoft(String description, WebDriver driver) {

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

	public static void exception(Exception e, WebDriver driver) throws Exception {

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

			Reporter.log("<div class=\"exceptions\"> <font color=\"red\"><I>"+ " </I> </font><p>" + screenShotLink + "</p></div>");

		}

		if (e instanceof org.testng.internal.thread.ThreadTimeoutException)
			/*WebDriverUtils.markUnResuable(driver);*/

		throw e;

		
		Assert.fail(e.getMessage());
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

}
