package com.projectname.testutils.retryAnalyser;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Retry rule for testclasses which are failed because of wait timed out No of retries can be specified in the TestBaseClass @Rule
 * annotated place
 * 
 * @author AspireQA
 * 
 */
public class RetryRule implements IRetryAnalyzer {
	private int retryCount;
	private int maxRetry;

	public RetryRule() {
		this.retryCount = 0;
		this.maxRetry=1;
	}
	
	public boolean retry(ITestResult result) {
		String e= result.getThrowable().toString();
		if(e.contains("AssertionError")&&maxRetry>retryCount) {
				retryCount++;
				return true;
		}
		return false;
	}

}
