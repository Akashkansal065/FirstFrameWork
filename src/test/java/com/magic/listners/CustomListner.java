package com.magic.listners;

import java.io.IOException;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.TestResult;

import com.magic.base.AllDrive;
import com.magic.base.Base;
import com.magic.utilities.ExtentManager;
import com.magic.utilities.ExtentTestManager;
import com.magic.utilities.ScreenShot;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListner extends Base implements ITestListener,IInvokedMethodListener{

	@Override
	public void onTestStart(ITestResult arg0) {
		//driver = AllDrive.getWebDriver();
		//driver.get(config.getProperty("URL"));
		//driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWait")), TimeUnit.SECONDS);
		Reporter.log("Test start onTestStart:- "+arg0.getMethod().getMethodName());
		ExtentTestManager.startTest(arg0.getName().toUpperCase(),arg0.getMethod().getDescription());
		ExtentTestManager.getTest().assignCategory(arg0.getTestContext().getCurrentXmlTest().getName());
		ExtentTestManager.getTest().log(LogStatus.INFO,"" ,arg0.getName());
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		AllDrive.cleanUp();
		Reporter.log("Test Success:- "+arg0.getMethod().getMethodName());
		ExtentTestManager.getTest().log(LogStatus.PASS, arg0.getName().toUpperCase()+"Success");
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onTestFailure(ITestResult arg0) {
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		String shotName = null;
		try {
			System.out.println("Try to capture Screen Shot");
			shotName = ScreenShot.ShotCaptured(AllDrive.getWebDriver(), System.getProperty("user.dir")+"/Reports/extent_reports/"+ 
					(date.getMonth()+1) +"/"+date.getDate()+"/"+arg0.getMethod().getMethodName()+date.getTime());
		}
		catch (IOException e) {e.printStackTrace();}

		ExtentTestManager.getTest().log(LogStatus.FAIL, ExtentTestManager.getTest().addScreenCapture(shotName));
		Reporter.log("Test complete");
		Reporter.log("<a target=\"blank\" href="+shotName+">Click</a>");
		AllDrive.cleanUp();
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext arg0) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

		Reporter.setCurrentTestResult(testResult);
		System.out.println("About to End Following method:- "+method.getTestMethod().getMethodName());

		// Handle Soft CustomAssertion
		if (method.isTestMethod()) 
		{
			if(!CustomAssert.map.isEmpty())
			{
				if(CustomAssert.map.get(Thread.currentThread().getId()) == false)
				{
					testResult.setStatus(TestResult.FAILURE);
					CustomAssert.map.clear();
				}
			}
		}
	}
}