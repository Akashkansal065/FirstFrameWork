package com.magic.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.magic.seleniumUtils.SeleniumContext;
import com.magic.utilities.ExcelReader;
import com.magic.utilities.ExtentManager;
import com.magic.utilities.GoogleSheet;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Base {

	public static WebDriver driver;
	public Properties config = new Properties();
	public Properties OR = new Properties();
	FileInputStream fis ;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	static String spreadsheetId="14-7PNS2RzGrdvvx5VMBvRlZoyX_oHJ_JJtiPidrgbEs";
	static String range="'Sprint 20'";
	static GoogleSheet sheetAPI = new GoogleSheet();
	public static List<List<Object>> values;
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"//src//test//resources//excel//testData.xlsx");
	public Date date = new Date();
	public ExtentReports report = ExtentManager.getInstance();
	public static ExtentTest testRep;

	@BeforeSuite
	public void Marvel(ITestContext context) throws IOException
	{
		SeleniumContext.context = context;
		fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//config.properties");
		config.load(fis);
		log.debug("Config File Loaded");
		fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//OR.properties");
		OR.load(fis);
		log.debug("OR file Loaded");
		driver = BrowserType.browser();
		//log.debug("Drver Loaded with browser name:-"+new SeleniumContext().getTestLevelBROWSER_TYPE());
		driver.get(config.getProperty("URL"));
		log.debug("Url Opened:-"+config.getProperty("URL"));
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWait")), TimeUnit.SECONDS);
		//values = sheetAPI.getSpreadSheetRecords(spreadsheetId, range);
		

	}
	@AfterSuite
	public void Thanos()
	{
		if(driver!=null)
			driver.quit();
		log.debug("END");
	}
	
	public void sendData(String locator,String Value)
	{
		testRep.log(LogStatus.INFO,"sendData",Value);
		driver.findElement(By.cssSelector(locator)).sendKeys(Value);
		testRep.log(LogStatus.INFO,"Data Entered Succesfully");
	}
	public boolean isElementPresent(By by)
	{
		try
		{
		driver.findElement(by);
		return true;
		}
		catch(NoSuchElementException e)
		{
		return false;
		}
	}


}
