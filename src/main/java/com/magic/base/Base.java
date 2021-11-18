package com.magic.base;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.magic.seleniumUtils.SeleniumContext;
import com.magic.utilities.Constant;
import com.magic.utilities.ExtentManager;
import com.magic.utilities.ExtentTestManager;
import com.magic.utilities.SendMail;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;

public class Base {

	//public static WebDriver driver;
	//public Properties config = new Properties();
	//public static final Logger log = Logger.getLogger("devpinoyLogger");
	public static final Logger log = LogManager.getLogger("devpinoyLogger");

	/*String spreadsheetId="14-7PNS2RzGrdvvx5VMBvRlZoyX_oHJ_JJtiPidrgbEs";
	String range="'Sprint 20'";
	GoogleSheet sheetAPI = new GoogleSheet();
	public List<List<Object>> values;
	public ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"//src//test//resources//excel//testData.xlsx");
	 */
	public Date date = new Date();
	//public StringWriter writer;
	//public PrintStream captor;
	//public LogConfig originalLogConfig;
	public static Map<String,String> acegic = new LinkedHashMap<>();
	public static Map<String,String> token = new LinkedHashMap<>();
	public static Map<String,Map<String,String>> completeCookieWeb = new LinkedHashMap<>();
	public static Map<String,Map<String,String>> completeCookieWap = new LinkedHashMap<>();
	public static String AppServiceUrl = null;
	public WebDriver driver; 

	@BeforeSuite(alwaysRun=true)
	public void beforeSuite() throws IOException
	{
		System.out.println("Before Suite");
	}

	@BeforeTest(alwaysRun = true)
	public void beforeTest(ITestContext context) throws Exception {
		System.out.println("beforeTest");

	}


	@BeforeMethod(alwaysRun=true)
	public void beforeMethod(ITestContext context,Method m)	
	{
		SeleniumContext.context = context;
		String URi= context.getCurrentXmlTest().getParameter("URL");
		driver = AllDrive.getWebDriver(context.getCurrentXmlTest().getParameter("browserName"));
		driver.get(URi);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); 


		Class<? extends Object> className = m.getDeclaringClass();
		Test test = m.getAnnotation(Test.class);

		ExtentTestManager.startTest(className.getSimpleName()+"."+m.getName().
				toUpperCase(),test.description());
		ExtentTestManager.getTest().assignCategory(className.getSimpleName());
		ExtentTestManager.getTest().log(LogStatus.INFO,"TestCase Name"
				,m.getName());

	}


	@AfterMethod(alwaysRun=true)
	public void afterMethod()
	{

		try { Thread.sleep(10000); } catch (InterruptedException e) {
			e.printStackTrace(); } 
		AllDrive.stopProxy();
		AllDrive.cleanUp();

		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	@AfterSuite(alwaysRun=true)
	public void afterSuite(ITestContext context)
	{
		AllDrive.cleanUp();
		AllDrive.stopProxy();
		log.debug("END");
		ExtentManager.getInstance().flush();
		ExtentManager.move();
		try {
			Thread.sleep(1);		
			SendMail.mail(Constant.to,Constant.cc,Constant.username,Constant.password,Constant.filename,Constant.Extentfilename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Total:  "+context.getAllTestMethods().length);
		System.out.println("Passed:  "+context.getPassedTests().size());
		System.out.println("failed:  "+context.getFailedTests().size());
		System.out.println("skipped: "+context.getSkippedTests().size());
	}

	public static String loggedRequestPathIn(StringWriter writer) {
		System.out.println(writer.toString());
		return StringUtils.substringBetween(writer.toString().replaceAll("[[<>#;.(){\\\\/]$%%}]"," "), "", "HTTP");
	}
	public static String loggedResponsePathIn(StringWriter writer) {
		return StringUtils.substringAfter(writer.toString().replaceAll("[[<>#;.(){\\\\/]$%%}]"," "), "HTTP");
	}
	public static String loggedRequestPathInLast(StringWriter writer) {
		return StringUtils.substringAfterLast(writer.toString().replaceAll("[[<>#;.(){\\\\/]$%%}]"," "),"Request method");
	}
	public static void RestReset()
	{
		AllDrive.cleanWriter();
		AllDrive.cleanPrintStream();
		RestAssured.baseURI = null;
		RestAssured.reset();
	}

	public static void restProxy()
	{
		if(SeleniumContext.getTestLevelRestProxy().equalsIgnoreCase("Yes"))
			RestAssured.proxy(8080);
	}
}
