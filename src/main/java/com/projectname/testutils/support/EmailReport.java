package com.projectname.testutils.support;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.Utils;
import org.testng.reporters.EmailableReporter2;
import org.testng.xml.XmlSuite;

import com.projectname.testutils.retryAnalyser.TestUtil;

public class EmailReport extends EmailableReporter2 implements ITestListener{

	PrintWriter bet365Out;
	String fileName;
	static String unescapePattern = "\\<div\\sclass=\"messages\">(.*)\\<\\/div\\>";
	static String startTestTitle = "<div class=\"test-title\"> <strong><font color = \"blue\">";
	static String endTestTitle = "</font> </strong> </div><div><strong>Steps:</strong></div><!-- Report -->";

	@Override
	public void generateReport(List <XmlSuite> xml, List <ISuite> suites, String outdir) {

		super.generateReport(xml, suites, outdir);
		File eScripts = new File("jsscripts.txt");
		File eCSS = new File("ReportCSS.txt");

		try {
			File eReport = new File(outdir + File.separator + "TestAutomationResults.html");
			File eReport1 = new File(outdir + File.separator + "emailable-report.html");

			FileUtils.copyFile(eReport, eReport1);
			String eContent = FileUtils.readFileToString(eReport, "UTF-8");

			Pattern p = Pattern.compile(unescapePattern, Pattern.DOTALL);
			Matcher matcher = p.matcher(eContent);
			int matchCount = 0;

			while (matcher.find()) {
				matchCount++;
			}

			matcher = p.matcher(eContent);

			for (int i = 0; i < matchCount; i++) {
				matcher.find();
				String unEscapePart = matcher.group(1);
				unEscapePart = unEscapePart.replace("&lt;", "<"); // removing the HTML escaping in the email report
				unEscapePart = unEscapePart.replace("&gt;", ">"); // removing the HTML escaping in the email report
				unEscapePart = unEscapePart.replace("&quot;", "\"");
				unEscapePart = unEscapePart.replace("&apos;", "'");
				unEscapePart = unEscapePart.replace("&amp;", "&");
				eContent = eContent.replace(matcher.group(1), unEscapePart);
			}

			long minStartTime = 0;
			long maxEndTime = 0;
			long temp = 0;

			// Adding Test method - description to Summary Table (i.e)Test case title
			for (SuiteResult suiteResult : super.suiteResults) {

				for (TestResult testResult : suiteResult.getTestResults()) {

					for (ClassResult classResult : testResult.getFailedTestResults()) {

						for (MethodResult methodResult : classResult.getMethodResults()) {

							for (ITestResult tResult : methodResult.getResults()) {

								temp = tResult.getStartMillis();

								String exceptionReplacement = tResult.getThrowable().getMessage(); // Replace stake trace with original unescape them

								if (!(tResult.getThrowable() instanceof java.lang.AssertionError) && exceptionReplacement != null && !exceptionReplacement.isEmpty()) {

									if (exceptionReplacement.indexOf("(Session") > 0)
										exceptionReplacement = exceptionReplacement.substring(0, exceptionReplacement.indexOf("(Session") - 1).trim();

									String exceptionToReplace = exceptionReplacement;
									exceptionReplacement = exceptionReplacement.replace("&", "&amp;");
									exceptionReplacement = exceptionReplacement.replace("<", "&lt;");
									exceptionReplacement = exceptionReplacement.replace(">", "&gt;");
									exceptionReplacement = exceptionReplacement.replace("\"", "&quot;");
									exceptionReplacement = exceptionReplacement.replace("'", "&apos;");
									eContent = eContent.replace(exceptionToReplace, exceptionReplacement);
								}

								if (minStartTime == 0 || temp < minStartTime)
									minStartTime = temp;

								temp = tResult.getEndMillis();

								if (maxEndTime == 0 || temp > maxEndTime)
									maxEndTime = temp;

								if (!tResult.getMethod().isTest())
									continue;

								String methodDescription = getTestTitle(Reporter.getOutput(tResult).toString());
								String methodName = tResult.getMethod().getMethodName();

								if (methodDescription.isEmpty())
									methodDescription = tResult.getMethod().getDescription();

								String toReplace = "<a href=\"#m([0-9]{1,4})\">" + methodName + "</a>";
								String toReplaceBy = "<a href=\"#m$1\">" + methodName + ": " + methodDescription + "</a>";
								eContent = eContent.replaceFirst(toReplace, toReplaceBy);

							}

						}

					}

					for (ClassResult classResult : testResult.getSkippedTestResults()) {

						for (MethodResult methodResult : classResult.getMethodResults()) {

							for (ITestResult tResult : methodResult.getResults()) {

								temp = tResult.getStartMillis();

								if (minStartTime == 0 || temp < minStartTime)
									minStartTime = temp;

								temp = tResult.getEndMillis();

								if (maxEndTime == 0 || temp > maxEndTime)
									maxEndTime = temp;

								if (!tResult.getMethod().isTest())
									continue;

								String methodName = tResult.getMethod().getMethodName();
								String methodDescription = getTestTitle(Reporter.getOutput(tResult).toString());

								if (methodDescription.isEmpty())
									methodDescription = tResult.getMethod().getDescription();

								String toReplace = "<a href=\"#m([0-9]{1,4})\">" + methodName + "</a>";
								String toReplaceBy = "<a href=\"#m$1\">" + methodName + ": " + methodDescription + "</a>";
								eContent = eContent.replaceFirst(toReplace, toReplaceBy);
							}

						}

					}

					for (ClassResult classResult : testResult.getPassedTestResults()) {

						for (MethodResult methodResult : classResult.getMethodResults()) {

							for (ITestResult tResult : methodResult.getResults()) {

								temp = tResult.getStartMillis();

								if (minStartTime == 0 || temp < minStartTime)
									minStartTime = temp;

								temp = tResult.getEndMillis();

								if (maxEndTime == 0 || temp > maxEndTime)
									maxEndTime = temp;

								if (!tResult.getMethod().isTest())
									continue;

								String methodName = tResult.getMethod().getMethodName();
								String methodDescription = getTestTitle(Reporter.getOutput(tResult).toString());

								if (methodDescription.isEmpty())
									methodDescription = tResult.getMethod().getDescription();

								String toReplace = "<a href=\"#m([0-9]{1,4})\">" + methodName + "</a>";
								String toReplaceBy = "<a href=\"#m$1\">" + methodName + ": " + methodDescription + "</a>";
								eContent = eContent.replaceFirst(toReplace, toReplaceBy);
							}

						}

					}

				}

			}

			eContent = eContent.replace("</head>", "\r</head>\r");
			eContent = eContent.replace("<table", "\r\t<table");
			eContent = eContent.replace("</table>", "\r\t</table>\r");
			eContent = eContent.replaceFirst("<table>", "<table id='suitesummary' title=\"Filters results based on cell clicked/Shows all result on double-click\">");
			eContent = eContent.replaceFirst("<table>", "<table id='summary'>");

			eContent = eContent.replace("<thead>", "\r\t<thead>\r");
			eContent = eContent.replace("</thead>", "\r\t</thead>\r");
			eContent = eContent.replace("<tbody>", "\r\t<tbody>\r");
			eContent = eContent.replace("</tbody>", "\r\t</tbody>\r");

			eContent = eContent.replace("<h2", "\r\t\t<h2");
			eContent = eContent.replace("<tr", "\r\t\t<tr");
			eContent = eContent.replace("</tr>", "\r\t\t</tr>\r");
			eContent = eContent.replace("<td>", "\r\t\t\t<td>");
			eContent = eContent.replace("</td>", "\r\t\t\t</td>\r");
			eContent = eContent.replace("<th", "\r\t\t\t<th");
			eContent = eContent.replace("</th>", "\r\t\t\t</th>");
			eContent = eContent.replace("<br/>", "");
			//eContent = eContent.replaceAll("<style(.*)</style>", "\r" + FileUtils.readFileToString(eCSS) + "\r");
			//eContent = eContent.replace("<head>", "<head>" + "\r" + FileUtils.readFileToString(eScripts) + "\r");
			eContent = eContent.replace("<head>", "<head>" + "\r<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\" />\r");

			eContent = eContent.replaceFirst("<table id='suitesummary' title=\"Filters results based on cell clicked/Shows all result on double-click\">",
					"<table id='suitesummary' title=\"Filters results based on cell clicked/Shows all result on double-click\" duration=\"" + (maxEndTime - minStartTime) + "\">");
			
			FileUtils.writeStringToFile(eReport, eContent);
		
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		try {

			FileReader fr = null;
			fr = new FileReader(outdir + File.separator + "TestAutomationResults.html");
			BufferedReader br = new BufferedReader(fr);
			StringBuilder content = new StringBuilder(10000);
			String s;
			int tableCount = 0;

			String hub = "localhost";

			try {
				hub = (suites.get(0).getHost() == null) ? Inet4Address.getLocalHost().getHostName() : suites.get(0).getHost();
			}
			catch (UnknownHostException e) {
			}

			while ((s = br.readLine()) != null) {

				content.append(s + "\n");
				if (s.trim().contains("</table>"))
					tableCount++;

				if (s.startsWith("<body")) {

					content.append("<p> Hi, </p>" + "\n" + "<p> Test automation scripts execution completed. Please summary of the results below. </p>" + "\n"
							+ "<p> Note: Attached detailed results & screen shots for reference. </p>" + "\n" + "<p> <u><h3> Test Run Details: </h3> </u>"
							+ "\r<table  bordercolor=\"#FFFFF\"> </u></h3> </p>\r" +

							"\r<pre style=\"font-size: 1.2em;\">\r" + "   <b>Test Name</b> : " + System.getProperty("testname") + "\r" + "   <b>Suite Name</b>: " + System.getProperty("suiteFile")
							+ "\r" + "   <b>Run Date</b>  : " + (new Date()).toString() + "\r" + "   <b>Test Name</b> : " + System.getProperty("name") + "\r" + "   <b>Run By</b>    : " + hub + "\r"
							+ "</pre>" + "<br><br>\n");
				}

				if (tableCount == 1) {
					content.append("<p><br></p><p> Thanks </p>\n</body>\n</html>");
					break;
				}

			}

			String emailContent = content.toString();
			File emailMsg = new File("." + "\\src\\test\\java\\AutomationTestResultsEmail.html".replace("\\", File.separator));
			FileUtils.writeStringToFile(emailMsg, emailContent);

			br.close();
			fr.close();

			// adding files/folders to be added on zip folder
			List <String> fileName = new ArrayList <String>();
			fileName.add(outdir + File.separator + "TestAutomationResults.html");
			fileName.add(outdir + File.separator + "ScreenShot");

			String ouputFile = outdir + File.separator + "AutomationTestSummaryReport.zip";
			FolderZiper folderZiper = new FolderZiper();
			folderZiper.zipFolder(fileName, ouputFile);

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getTestTitle(String content) {

		Pattern p = Pattern.compile(startTestTitle + "(.*)" + endTestTitle, Pattern.DOTALL);
		Matcher matcher = p.matcher(content);

		try {
			if (matcher.find())
				return matcher.group(1);
			else
				return "";
		}
		catch (IllegalStateException e) {
			return "";
		}

	}
	 protected void writeStylesheet() {
	        writer.print("<style type=\"text/css\">");
	        
	         
	        writer.print("table {margin-bottom:10px;border-collapse:collapse;empty-cells:show;width:80%}");
	        writer.print("table.inner {width:100%}");
	        writer.print("td.inner {width:32%}");
	        writer.print("th,td {border:1px solid #009;padding:.25em .5em}");
	        writer.print("th {vertical-align:bottom}");
	        writer.print("td {vertical-align:top}");
	        writer.print("table a {font-weight:bold}");
	        writer.print(".stripe td {background-color: #E6EBF9}");
	        writer.print(".num {text-align:right}");
	        writer.print(".passedodd td {background-color: #0A0}");
	        writer.print(".passedeven td {background-color: #0A0}");
	        writer.print(".skippedodd td {background-color: #DDD}");
	        writer.print(".skippedeven td {background-color: #CCC}");
	        writer.print(".failedodd td,.attn {background-color: #D00}");
	        writer.print(".failedeven td,.stripe .attn {background-color: #D00}");
	        writer.print(".stacktrace {white-space:pre;font-family:monospace}");
	        writer.print(".totop {font-size:85%;text-align:center;border-bottom:2px solid #000}");
	        writer.print("</style>");
	    }


	@Override
	protected PrintWriter createWriter(String outdir) throws IOException {
		new File(outdir).mkdirs();
		bet365Out = new PrintWriter(new BufferedWriter(new FileWriter(new File(outdir, "TestAutomationResults.html"))));
		return bet365Out;
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}


	public void onFinish(ITestContext testContext) {
		// List of test results which we will delete later

		List<ITestResult> testsToBeRemoved = new ArrayList<ITestResult>();

		// collect all id's from passed test

		Set <Integer> passedTestIds = new HashSet<Integer>();

		for (ITestResult passedTest : testContext.getPassedTests().getAllResults()) {

		passedTestIds.add(TestUtil.getId(passedTest));

		}

		Set <Integer> failedTestIds = new HashSet<Integer>();

		for (ITestResult failedTest : testContext.getFailedTests().getAllResults()) {

		// id = class + method + dataprovider

		int failedTestId = TestUtil.getId(failedTest);

		// if we saw this test as a failed test before we mark as to be deleted

		// or delete this failed test if there is at least one passed version

		if (failedTestIds.contains(failedTestId) || passedTestIds.contains(failedTestId)) {

		testsToBeRemoved.add(failedTest);

		} else {

		failedTestIds.add(failedTestId);

		}

		}

		// finally delete all tests that are marked

		for (Iterator<ITestResult> iterator = testContext.getFailedTests().getAllResults().iterator(); iterator.hasNext(); ) {

		ITestResult testResult = iterator.next();

		if (testsToBeRemoved.contains(testResult)) {

		iterator.remove();

		}

		}


		
	}

}
