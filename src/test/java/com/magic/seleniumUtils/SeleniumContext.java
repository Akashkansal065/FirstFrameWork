package com.magic.seleniumUtils;

import org.testng.ITestContext;

public class SeleniumContext {

	public static ITestContext context = null;

	public static ITestContext getAllContext()
	{
		return context;
	}

	/*public SeleniumContext(ITestContext context ){
		SeleniumContext.context = context;
	}*/

	static String BROWSER_TYPE = "browserName";
	static String DEVICE_NAME = "deviceName";
	static String GRID_SWITCH = "Grid";
	static String GRID_URL = "GURL";
	static String URL = "URL";

	public static String getTestLevelBROWSER_TYPE()
	{
		return context.getCurrentXmlTest().getParameter(BROWSER_TYPE).toString();
	}

	public static String getSuiteLevelBROWSER_TYPE()
	{
		return context.getSuite().getParameter(BROWSER_TYPE).toString();
	}
	public static String getTestLevelURL()
	{
		return context.getCurrentXmlTest().getParameter(URL).toString();
	}

	public static String getSuiteLevelURL()
	{
		return context.getSuite().getParameter(URL).toString();
	}
	public static String getTestLevelDEVICE_NAME()
	{
		return context.getCurrentXmlTest().getParameter(DEVICE_NAME).toString();
	}

	public static String getSuiteLevelDEVICE_NAME()
	{
		return context.getSuite().getParameter(DEVICE_NAME).toString();
	}
	public static String getTestLevelGRID()
	{
		return context.getCurrentXmlTest().getParameter(GRID_SWITCH).toString();
	}

	public static String getSuiteLevelGRID()
	{
		return context.getSuite().getParameter(GRID_SWITCH).toString();
	}
	public static String getTestLevelGRID_URL()
	{
		return context.getCurrentXmlTest().getParameter(GRID_URL).toString();
	}

	public static String getSuiteLevelGRID_URL()
	{
		return context.getSuite().getParameter(GRID_URL).toString();
	}

}