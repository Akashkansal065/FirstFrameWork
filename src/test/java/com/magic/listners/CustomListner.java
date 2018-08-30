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
import com.magic.seleniumUtils.SeleniumContext;
import com.magic.utilities.ExtentManager;
import com.magic.utilities.ExtentTestManager;
import com.magic.utilities.ScreenShot;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListner implements ITestListener,IInvokedMethodListener{

	public Date date =new Date();
	@Override
	public void onTestStart(ITestResult testResult) {
		System.out.println("On test Start Method Called");
		SeleniumContext.context = testResult.getTestContext();
		/*ExtentTestManager.startTest(testResult.getTestClass().getName()+"."+testResult.getName().toUpperCase(),testResult.getMethod().getDescription());
		ExtentTestManager.getTest().assignCategory(testResult.getTestClass().getName());
		ExtentTestManager.getTest().log(LogStatus.INFO,"onTestStart" ,testResult.getName());*/
	}

	@Override
	public void onTestSuccess(ITestResult testResult) {

		if(ExtentManager.extent !=null && AllDrive.driverSession.get() !=null)
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, testResult.getName().toUpperCase()+"Success");
			ExtentTestManager.endTest();
			ExtentManager.getInstance().flush();
		}
		AllDrive.cleanUp();
	}


	@Override
	public void onTestFailure(ITestResult testResult) {
		System.out.println("Test Failed on TEST FAILURE");
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		/*if(AllDrive.driverSession.get() !=null)
		{
			String shotName = null;
			try {
				System.out.println("Try to capture Screen Shot");
				shotName = ScreenShot.ShotCaptured(AllDrive.getWebDriver(), System.getProperty("user.dir")+"/Reports/extent_reports/"+ 
						(date.getMonth()+1) +"/"+date.getDate()+"/"+testResult.getMethod().getMethodName()+date.getTime());
			}catch (IOException e) {e.printStackTrace();}
			//ExtentTestManager.getTest().log(LogStatus.FAIL, ExtentTestManager.getTest().addScreenCapture(shotName),testResult.getThrowable());
		}*/

		if (testResult.getMethod().getRetryAnalyzer() != null) {
			RetryAnalyzer retryAnalyzer = (RetryAnalyzer)testResult.getMethod().getRetryAnalyzer();

			if(retryAnalyzer.isRetryAvailable()) {
				testResult.setStatus(ITestResult.SKIP);
				//ExtentTestManager.getTest().log(LogStatus.SKIP,ExtentTestManager.getTest().addScreenCapture(shotName),testResult.getThrowable());
			} else {
				testResult.setStatus(ITestResult.FAILURE);
				//ExtentTestManager.getTest().log(LogStatus.FAIL, ExtentTestManager.getTest().addScreenCapture(shotName),testResult.getThrowable());
			}
			//Reporter.setCurrentTestResult(testResult);
		}
		//Reporter.log("Test complete");
		//Reporter.log("<a target=\"blank\" href="+shotName+">Click</a>");

		if(ExtentManager.extent !=null && AllDrive.driverSession.get() !=null){
			ExtentTestManager.endTest();
			ExtentManager.getInstance().flush();
		}
		AllDrive.cleanUp();
	}

	@Override
	public void onTestSkipped(ITestResult testResult) {

		Reporter.getCurrentTestResult().setThrowable(testResult.getThrowable());
		System.out.println("Test Skipped:- "+testResult.getMethod().getMethodName());
		if(AllDrive.driverSession.get() !=null)
		{
			String shotName = null;
			try {
				System.out.println("Try to capture Screen Shot onTestSkipped");
				shotName = ScreenShot.ShotCaptured(AllDrive.getWebDriver(), System.getProperty("user.dir")+"/Reports/extent_reports/"+ 
						(date.getMonth()+1) +"/"+date.getDate()+"/"+testResult.getMethod().getMethodName()+date.getTime());
				ExtentTestManager.getTest().log(LogStatus.SKIP,ExtentTestManager.getTest().addScreenCapture(shotName),testResult.getThrowable());
			}catch (IOException e) {e.printStackTrace();}
		}

		if(ExtentManager.extent !=null && AllDrive.driverSession.get() !=null){
			ExtentTestManager.getTest().log(LogStatus.SKIP,"Test Gets Skipped on ''onTestSkipped'' ");
			ExtentTestManager.endTest();
			ExtentManager.getInstance().flush();
		}
		AllDrive.cleanUp();
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

		System.out.println("After Invocation about to End Following method:- "+method.getTestMethod().getMethodName() + "   " + testResult);

		// Handle Soft CustomAssertion
		if (testResult.getMethod().isTest()) 
		{
			if(!CustomAssert.map.isEmpty())
			{
				if(CustomAssert.map.get(Thread.currentThread().getId()) != null && CustomAssert.map.get(Thread.currentThread().getId()) == false)
				{
					System.out.println("CustomAssert.map is not empty and current thread"+CustomAssert.map.size());

					if (testResult.getMethod().getRetryAnalyzer() != null) {

						System.out.println("Retry Present");
						System.out.println("Current test result status:- "+testResult.getStatus());

						RetryAnalyzer retryAnalyzer = (RetryAnalyzer) testResult.getMethod().getRetryAnalyzer();

						/*When test gets the Elemnet not found exception or any other except assertException*/
						if(testResult.getStatus()==ITestResult.FAILURE)
						{
							ITestContext tc = Reporter.getCurrentTestResult().getTestContext();
							tc.getPassedTests().addResult(testResult, Reporter.getCurrentTestResult().getMethod());
							tc.getPassedTests().getAllMethods().remove(Reporter.getCurrentTestResult().getMethod());

							System.out.println("Addiding logs in failed test");

							//Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
							List<Throwable> ls=CustomAssert.verificationFailuresMap.get(Thread.currentThread().getId());
							int size = ls.size();

							if(size == 1)
							{
								if(ExtentManager.extent !=null)
								{
									ExtentTestManager.getTest().log(LogStatus.WARNING,"Assert Fail",ls.get(0));
								}
								Reporter.getCurrentTestResult().setThrowable(ls.get(0));
							}
							if(size > 1)
							{
								for(int i=0;i<size;i++)
								{
									if(ExtentManager.extent !=null)
									{
										ExtentTestManager.getTest().log(LogStatus.WARNING,"Assert Fail :- "+i,ls.get(i));
									}
									Reporter.getCurrentTestResult().setThrowable(ls.get(i));
								}
							}

							//tc.getFailedTests().addResult(testResult, Reporter.getCurrentTestResult().getMethod());
							//tc.getSkippedTests().addResult(testResult, Reporter.getCurrentTestResult().getMethod());
							CustomAssert.map.clear();
							CustomAssert.verificationFailuresMap.clear();
							//testResult.setStatus(TestResult.FAILURE);
						}
						/*When test have multiple or single Custom assert failure
						 * 
						 * In that case the result of the test is by default pass
						 * but now we have to fail the test explicitly
						 * and add the failure in the report
						 * 
						 * */
						if(testResult.getStatus()==ITestResult.SUCCESS)
						{
							//Change Pass to failure and throw custom exception error
							ITestContext tc = Reporter.getCurrentTestResult().getTestContext();
							tc.getPassedTests().addResult(testResult, Reporter.getCurrentTestResult().getMethod());
							tc.getPassedTests().getAllMethods().remove(Reporter.getCurrentTestResult().getMethod());

							if(retryAnalyzer.isRetryAvailable())
							{
								System.out.println("Making the test Skip as retry available");
								Reporter.getCurrentTestResult().setStatus(ITestResult.SKIP);
								List<Throwable> ls = CustomAssert.verificationFailuresMap.get(Thread.currentThread().getId());
								int size = ls.size();
								if(size==1)
								{
									Reporter.getCurrentTestResult().setThrowable(ls.get(0));
									if(ExtentManager.extent !=null)
									{
										ExtentTestManager.getTest().log(LogStatus.WARNING,"Assert Fail", ls.get(0));
									}
								}
								if(size > 1)
								{
									for(int i=0;i<ls.size();i++)
									{
										Reporter.getCurrentTestResult().setThrowable(ls.get(i));
										if(ExtentManager.extent !=null)
										{
											ExtentTestManager.getTest().log(LogStatus.WARNING,"Assert Fail multiple", ls.get(i));
										}
									}
								}
							}
							else
							{
								System.out.println("Making the test Fail as retry not available");
								Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
								List<Throwable> ls=CustomAssert.verificationFailuresMap.get(Thread.currentThread().getId());
								int size = ls.size();

								if(size == 1)
								{
									if(ExtentManager.extent !=null)
									{
										ExtentTestManager.getTest().log(LogStatus.FAIL,"Assert Fail",ls.get(0));
									}
									Reporter.getCurrentTestResult().setThrowable(ls.get(0));
								}
								if(size > 1)
								{
									for(int i=0;i<size;i++)
									{
										if(ExtentManager.extent !=null)
										{
											ExtentTestManager.getTest().log(LogStatus.FAIL,"Assert Fail :- "+i,ls.get(i));
										}
										Reporter.getCurrentTestResult().setThrowable(ls.get(i));
									}
								}

								tc.getFailedTests().addResult(testResult, Reporter.getCurrentTestResult().getMethod());
								//tc.getSkippedTests().addResult(testResult, Reporter.getCurrentTestResult().getMethod());
							}
							CustomAssert.map.clear();
							CustomAssert.verificationFailuresMap.clear();
							testResult.setStatus(TestResult.FAILURE);
						}

					}
				}
			}
		}
		System.out.println("After invocationnnnnnnnnnnnnnnnn "+testResult);
	}
}