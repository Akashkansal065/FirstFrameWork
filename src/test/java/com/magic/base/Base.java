package com.magic.base;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.magic.RestUtils.Cookies;
import com.magic.seleniumUtils.SeleniumContext;
import com.magic.utilities.DBManager;
import com.magic.utilities.ExtentManager;
import com.magic.utilities.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;

public class Base {

	//public static WebDriver driver;
	//public Properties config = new Properties();
	public static Logger log = Logger.getLogger("devpinoyLogger");
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
	public static String AppServiceUrl;

	@BeforeSuite(alwaysRun=true)
	public void Marvel() throws IOException
	{
		System.out.println("Before Suite");
		//new BrowserType().launchEmulator("olddevice");
		//AppServiceUrl = BrowserType.AppiumServiceUrl();
	}

	@BeforeTest(alwaysRun = true)
	//@Parameters({"deviceName", "mobileAdbName", "systemPort"})
	public void setup() throws Exception {
		//SeleniumContext.context = context;
		//			System.out.println( platform+ udid+ systemPort);
		//			try {
		//				AllDrive.createAppDriver(platform, udid, systemPort);
		//			} catch (Exception e) {
		//				e.printStackTrace();
		//			}

		String Web = SeleniumContext.getTestLevelDriverRequired();
		System.out.println("-------0-0------------0--000000000000000"+Web);
//		if(Web.equalsIgnoreCase("NO")) 
//		{
//			try
//			{
//				ExtentTestManager.startTest(SeleniumContext.getAllContext().getCurrentXmlTest().getName()+".Before Test");
//				ExtentTestManager.getTest().log(LogStatus.INFO,"@Before Test","Data");
//				new DBManager().executeUpdate();
//				new Cookies().addCookies(); 
//			} 
//			finally
//			{
//				ExtentTestManager.endTest();
//				ExtentManager.getInstance().flush();
//				RestReset(); 
//			} 
//		}

	}

	
	//@Parameters({"deviceName", "mobileAdbName", "systemPort"})
//	public void captainAmerica(String platform, String udid, String systemPort,Method m)
	@BeforeMethod(alwaysRun=true)
	public void captainAmerica(ITestContext context,Method m)	
	{
		SeleniumContext.context = context;
//		System.out.println( platform+ udid+ systemPort);
//		try {
//			AllDrive.createAppDriver(platform, udid, systemPort);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		System.out.println("Before Mehod Executing fo the method :- "+m.getName());
		//System.out.println("App Load Driver");
		//AllDrive.getAppDriver();
		String URi=SeleniumContext.getTestLevelURL(); 
		System.out.println(URi); 
		String Rest = SeleniumContext.getTestLevelDriverRequired(); 
		String App = SeleniumContext.getTestLevelAppDriverRequired();
		if(Rest.equals("NO") && App.equalsIgnoreCase("NO")) {
			System.out.println("Rest Assured"); 
			RestAssured.baseURI = URi; 
			restProxy();
			RestAssured.config =
					RestAssured.config().logConfig(LogConfig.logConfig().defaultStream(AllDrive.
							getPrintStream()).enablePrettyPrinting(true));
			RestAssured.useRelaxedHTTPSValidation(); 
		} 
		else if(App.equals("YES"))
		{
			System.out.println("App Load Driver"); 
			AllDrive.getAppDriver(); 
		} 
		else 
		{
			System.out.println("Load Driver"); 
			WebDriver driver; 
			driver = AllDrive.getWebDriver();
			driver.get(URi);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
		}


		Class<? extends Object> className = m.getDeclaringClass();
		Test test = m.getAnnotation(Test.class);
		ExtentTestManager.startTest(className.getSimpleName()+"."+m.getName().toUpperCase(),test.description());
		ExtentTestManager.getTest().assignCategory(className.getSimpleName());
		ExtentTestManager.getTest().log(LogStatus.INFO,"@Before Method" ,m.getName());
	}
	@AfterMethod(alwaysRun=true)
	public void ironMan()
	{
		String Rest = SeleniumContext.getTestLevelDriverRequired();
		if(Rest.equals("NO"))
		{
			RestReset();
		}
		System.out.println("After Methoddddddddddddddddd");
	}

	@AfterSuite(alwaysRun=true)
	public void Thanos(ITestContext context)
	{
		System.out.println("Thanossssssssssssssssssssssssssssssssssssssssssssssssssss");
		AllDrive.cleanUp();
		AllDrive.cleanAppDriver();
		/*if(driver!=null)
			driver.quit();*/
		log.debug("END");
		ExtentManager.getInstance().flush();
		ExtentManager.move();
		try {
			Thread.sleep(1);		
			//SendMail.mail(Constant.to,Constant.cc,Constant.username,Constant.password,Constant.filename,Constant.Extentfilename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("fffffffffffffffffffffffffffffff"+"Total:  "+context.getAllTestMethods().length);
		System.out.println("fffffffffffffffffffffffffffffff"+"Passed:  "+context.getPassedTests().size());
		System.out.println("fffffffffffffffffffffffffffffff"+"failed:  "+context.getFailedTests().size());
		System.out.println("fffffffffffffffffffffffffffffff"+"skipped: "+context.getSkippedTests().size());
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
