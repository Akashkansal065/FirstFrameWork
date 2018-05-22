package com.magic.listners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.magic.base.Base;
import com.magic.utilities.ScreenShot;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListner extends Base implements ITestListener{


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

	@SuppressWarnings("deprecation")
	@Override
	public void onTestFailure(ITestResult arg0) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		String shotName = null;
		try {
			shotName = ScreenShot.ShotCaptured(driver, System.getProperty("user.dir")+"/Reports/extent_reports/"+ (date.getMonth()+1) +"/"+date.getDate()+"/"+arg0.getMethod().getMethodName()+date.getTime());
		}
		catch (IOException e) {e.printStackTrace();}
		
		testRep.log(LogStatus.FAIL, "Failed");
		testRep.log(LogStatus.INFO, testRep.addScreenCapture(shotName));
		Reporter.log("Test complete");
		Reporter.log("<a target=\"blank\" href="+shotName+">Click</a>");
		report.endTest(testRep);
		report.flush();
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestStart(ITestResult arg0) {
		Reporter.log("Test start onTestStart:- "+arg0.getMethod().getMethodName());
		testRep = report.startTest(arg0.getName().toUpperCase(),arg0.getMethod().getDescription());
		testRep.log(LogStatus.INFO,"", arg0.getName());
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		Reporter.log("Test Success:- "+arg0.getMethod().getMethodName());
		testRep.log(LogStatus.PASS, arg0.getName().toUpperCase()+"Success");
		report.endTest(testRep);
		report.flush();
	}

}
