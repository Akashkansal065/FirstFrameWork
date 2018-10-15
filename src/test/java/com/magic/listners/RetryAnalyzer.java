package com.magic.listners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.magic.base.AllDrive;
import com.magic.utilities.ExtentManager;
import com.magic.utilities.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;


public class RetryAnalyzer implements IRetryAnalyzer {

	public int retryCount = 0;
	public int maxRetryCount = 0;

	// Below method returns 'true' if the test method has to be retried else 'false' 
	//and it takes the 'Result' as parameter of the test method that just ran
	public boolean isRetryAvailable() {
		return (retryCount < maxRetryCount);
	}
	public boolean isRetryAvailableAssert() {
		return (retryCount < maxRetryCount);
	}
	public synchronized boolean retry(ITestResult result) {
		if (isRetryAvailable()) {
			result.setAttribute("RETRY", retryCount);
			if(ExtentManager.extent !=null && AllDrive.driverSession.get() !=null)
			{
				ExtentTestManager.getTest().log(LogStatus.SKIP,"Retrying test " + result.getName() + " with status "
						+ getResultStatusName(result.getStatus()) + " for the " + (retryCount+1) + " time(s).");
			}
			System.out.println("Retrying test " + result.getName() + " with status "
					+ getResultStatusName(result.getStatus()) + " for the " + (retryCount+1) + " time(s).");

			retryCount++;

			return true;
		}
		return false;
	}

	public String getResultStatusName(int status) {
		String resultName = null;
		if(status==1)
			resultName = "SUCCESS";
		if(status==2)
			resultName = "FAILURE";
		if(status==3)
			resultName = "SKIP";
		return resultName;
	}
}