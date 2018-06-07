package com.magic.listners;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.TestResult;

import com.magic.base.AllDrive;
import com.magic.utilities.ExtentManager;
import com.magic.utilities.ExtentTestManager;
import com.magic.utilities.ScreenShot;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListner implements ITestListener,IInvokedMethodListener{

	public Date date =new Date();
	@Override
	public void onTestStart(ITestResult testResult) {
		System.out.println("On test Start Method Called");
		Reporter.log("Test start onTestStart:- "+testResult.getMethod().getMethodName());
		ExtentTestManager.startTest(testResult.getTestClass().getName()+"."+testResult.getName().toUpperCase(),testResult.getMethod().getDescription());
		ExtentTestManager.getTest().assignCategory(testResult.getTestClass().getName());
		ExtentTestManager.getTest().log(LogStatus.INFO,"onTestStart" ,testResult.getName());
	}

	@Override
	public void onTestSuccess(ITestResult testResult) {
		AllDrive.cleanUp();
		Reporter.log("Test Success:- "+testResult.getMethod().getMethodName());
		ExtentTestManager.getTest().log(LogStatus.PASS, testResult.getName().toUpperCase()+"Success");
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onTestFailure(ITestResult testResult) {
		System.out.println("Test Failed on TEST FAILURE");
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		String shotName = null;
		try {
			System.out.println("Try to capture Screen Shot");
			shotName = ScreenShot.ShotCaptured(AllDrive.getWebDriver(), System.getProperty("user.dir")+"/Reports/extent_reports/"+ 
					(date.getMonth()+1) +"/"+date.getDate()+"/"+testResult.getMethod().getMethodName()+date.getTime());
		}
		catch (IOException e) {e.printStackTrace();}

		if (testResult.getMethod().getRetryAnalyzer() != null) {
			RetryAnalyzer retryAnalyzer = (RetryAnalyzer)testResult.getMethod().getRetryAnalyzer();

			if(retryAnalyzer.isRetryAvailable()) {
				testResult.setStatus(ITestResult.SKIP);
				ExtentTestManager.getTest().log(LogStatus.SKIP,ExtentTestManager.getTest().addScreenCapture(shotName),testResult.getThrowable());
			} else {
				testResult.setStatus(ITestResult.FAILURE);
				ExtentTestManager.getTest().log(LogStatus.FAIL, ExtentTestManager.getTest().addScreenCapture(shotName),testResult.getThrowable());
			}
			//Reporter.setCurrentTestResult(testResult);
		}
		//ExtentTestManager.getTest().log(LogStatus.FAIL, ExtentTestManager.getTest().addScreenCapture(shotName),testResult.getThrowable());
		Reporter.log("Test complete");
		Reporter.log("<a target=\"blank\" href="+shotName+">Click</a>");
		AllDrive.cleanUp();
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	@Override
	public void onTestSkipped(ITestResult testResult) {
		System.out.println("Test Skipped:- "+testResult.getMethod().getMethodName());
		ExtentTestManager.getTest().log(LogStatus.SKIP,"Test Gets Skipped on onTestSkipped");
		AllDrive.cleanUp();
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	@Override
	public void onFinish(ITestContext testResult) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext testResult) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult testResult) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

		Reporter.setCurrentTestResult(testResult);
		System.out.println("After Invocation about to End Following method:- "+method.getTestMethod().getMethodName());

		// Handle Soft CustomAssertion
		if (testResult.getMethod().isTest()) 
		{
			if(!CustomAssert.map.isEmpty())
			{
				if(CustomAssert.map.get(Thread.currentThread().getId()) == false)
				{
					System.out.println("Making the test Failed");
					//Change Pass to failure and throw custom exception error
					if (testResult.getStatus() == ITestResult.SUCCESS) {
						ITestContext tc = Reporter.getCurrentTestResult().getTestContext();
						tc.getPassedTests().addResult(testResult, Reporter.getCurrentTestResult().getMethod());
						tc.getPassedTests().getAllMethods().remove(Reporter.getCurrentTestResult().getMethod());
						Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
						List<Throwable> ls=CustomAssert.verificationFailuresMap.get(Thread.currentThread().getId());
						int size = ls.size();
						
						if(size == 1)
						{
							ExtentTestManager.getTest().log(LogStatus.FAIL,"Assert Fail",ls.get(0));
							Reporter.getCurrentTestResult().setThrowable(ls.get(0));
						}
						if(size > 1)
						{
							for(int i=0;i<size;i++)
							{
								ExtentTestManager.getTest().log(LogStatus.FAIL,"Assert Fail :- "+i,ls.get(i));
								Reporter.getCurrentTestResult().setThrowable(ls.get(i));
							}
						}

						tc.getFailedTests().addResult(testResult, Reporter.getCurrentTestResult().getMethod());
						//tc.getSkippedTests().addResult(testResult, Reporter.getCurrentTestResult().getMethod());
					}

					testResult.setStatus(TestResult.FAILURE);
					CustomAssert.map.clear();
					CustomAssert.verificationFailuresMap.clear();
				}
			}
		}
	}
}