package com.magic.utilities;

import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentTestManager {

	static ExtentReports report = ExtentManager.getInstance();

	static Map<Integer,ExtentTest> map = new HashMap<Integer,ExtentTest>();

	public static synchronized ExtentTest startTest(String testName)
	{
		return startTest(testName, "");
	}
	
	public static synchronized ExtentTest startTest(String testName,String description)
	{
		ExtentTest testRep = report.startTest(testName, description);
		map.put((int)Thread.currentThread().getId(), testRep);
		return testRep;
	}

	public static synchronized ExtentTest getTest()
	{
		return map.get((int)Thread.currentThread().getId());
	}

	public static synchronized void endTest()
	{
		report.endTest(map.get((int)Thread.currentThread().getId()));
	}






}
