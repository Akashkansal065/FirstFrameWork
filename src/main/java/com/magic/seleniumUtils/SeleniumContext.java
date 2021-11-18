package com.magic.seleniumUtils;

import org.testng.ITestContext;

public class SeleniumContext {

	public static ITestContext context = null;

	public ITestContext contextNext = null;
	
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
	static String DRIVERSWITCH= "WebDriverRequired";
	static String RProxy= "RestProxy";
	static String AppDriver = "AppiumDriverRequired";
	static String UDID = "mobileAdbName";
	static String SYSPORT = "systemPort";
	
	public static String getTestLevelRestProxy()
	{
		String ProxyEnable = null;
		try
		{
			ProxyEnable = context.getCurrentXmlTest().getParameter(RProxy).toString();
		}
		catch(NullPointerException e)
		{
			ProxyEnable = "NO";
		}
		return ProxyEnable;
	}

	public static String getSuiteLevelRestProxy()
	{

		String ProxyEnable = null;
		try
		{
			ProxyEnable = context.getSuite().getParameter(RProxy).toString();
		}
		catch(NullPointerException e)
		{
			ProxyEnable = "NO";
		}
		return ProxyEnable;
	}
	public static String getTestLevelBROWSER_TYPE()
	{
		String browser = null;
		try
		{
			browser = context.getCurrentXmlTest().getParameter(BROWSER_TYPE).toString();
		}
		catch(NullPointerException e)
		{
			browser = "firefox";
		}
		return browser;
	}

	public static String getSuiteLevelBROWSER_TYPE()
	{
		String browser = null;
		try
		{
			browser = context.getSuite().getParameter(BROWSER_TYPE).toString();
		}
		catch(NullPointerException e)
		{
			browser = "firefox";
		}
		return browser;
	}
	public static synchronized String getTestLevelURL()
	{
		return context.getCurrentXmlTest().getParameter(URL).toString();
	}

	public static synchronized String getSuiteLevelURL()
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
	public static String getTestLevelmobileAdbName()
	{
		return context.getCurrentXmlTest().getParameter(UDID).toString();
	}
	public static String getTestLevelsystemPort()
	{
		return context.getCurrentXmlTest().getParameter(SYSPORT).toString();
	}

	public static String getSuiteLevelmobileAdbName()
	{
		return context.getSuite().getParameter(UDID).toString();
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
	public static String getTestLevelDriverRequired()
	{
		String WebDriverEnable = null;
		try
		{
			WebDriverEnable = context.getCurrentXmlTest().getParameter(DRIVERSWITCH).toString();
		}
		catch(NullPointerException e)
		{
			WebDriverEnable = "NO";
		}
		return WebDriverEnable;
	}

	public static String getSuiteLevelDriverRequired()
	{
		String WebDriverEnable = null;
		try
		{
			WebDriverEnable = context.getSuite().getParameter(DRIVERSWITCH).toString();
		}
		catch(NullPointerException e)
		{
			WebDriverEnable = "YES";
		}
		return WebDriverEnable;
	}
	public static String getTestLevelAppDriverRequired()
	{
		String AppDriverEnable = null;
		try
		{
			AppDriverEnable = context.getCurrentXmlTest().getParameter(AppDriver).toString();
		}
		catch(NullPointerException e)
		{
			AppDriverEnable = "NO";
		}
		return AppDriverEnable;
	}

}