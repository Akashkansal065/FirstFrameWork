package com.magic.base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.magic.seleniumUtils.SeleniumContext;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.service.local.flags.ServerArgument;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserType {

	static AppiumDriverLocalService service;
	String sdkPath = "C:\\Users\\Akash.Kansal\\AppData\\Local\\Android\\Sdk\\";// or for windows D:/Android/adt-bundle-windows-x86_64-20140702/sdk/
	String adbPath = sdkPath + "platform-tools" + File.separator + "adb";
	String emulatorPath = sdkPath + "tools" + File.separator + "emulator";
	//Please make sure to change the value of "sdkPath" variable to your SDK installation directory.
	//The following code will start an emulator with the provided AVD name.

	/**
	 * Starts an emulator for the provided AVD name
	 * 
	 * @param nameOfAVD
	 */
	public void launchEmulator(String nameOfAVD) {
		System.out.println("Starting emulator for '" + nameOfAVD + "' ...");
		String[] aCommand = new String[] { emulatorPath, "-avd", nameOfAVD };
		System.out.println(aCommand);
		try {
			Process process = new ProcessBuilder(aCommand).start();
			process.waitFor(120, TimeUnit.SECONDS);
			System.out.println("Emulator launched successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	static WebDriver browser() throws MalformedURLException
	{
		WebDriver driver;
		String browserName = null;
		String Grid = null;
		if(browserName==null)
		{
			browserName = SeleniumContext.getTestLevelBROWSER_TYPE();
			Grid = SeleniumContext.getSuiteLevelGRID();
		}
		System.out.println("browserName---:"+browserName);
		switch(browserName){

		case "chromeauto":
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//test//resources//drivers//chromedriver.exe");
			//WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("test-type");
			chromeOptions.addArguments("disable-infobars");
			chromeOptions.addArguments("start-maximized");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			if(Grid.equals("ON"))
			{
				driver = new RemoteWebDriver(new URL(SeleniumContext.getSuiteLevelGRID_URL()), capabilities);
			}
			else
			{
				driver=new ChromeDriver(chromeOptions);
			}
			return driver;
			/**********************************************************************************/
		case "chrome":
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//test//resources//drivers//chromedriver.exe");
			//WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions1 = new ChromeOptions();
			chromeOptions1.addArguments("test-type");
			chromeOptions1.addArguments("disable-infobars");
			chromeOptions1.addArguments("start-maximized");
			chromeOptions1.setExperimentalOption("useAutomationExtension", false);
			chromeOptions1.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));    
			DesiredCapabilities capabilities1 = DesiredCapabilities.chrome();
			capabilities1.setCapability(ChromeOptions.CAPABILITY, chromeOptions1);
			if(Grid.equals("ON"))
			{
				driver = new RemoteWebDriver(new URL(SeleniumContext.getSuiteLevelGRID_URL()), capabilities1);
			}
			else
			{
				driver=new ChromeDriver(chromeOptions1);
			}
			return driver;
			/**********************************************************************************/
		case "firefoxauto":
			//System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//src//test//resources//drivers//geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();			
			DesiredCapabilities capabilities3 = DesiredCapabilities.firefox();
			if(Grid.equals("ON"))
			{
				driver = new RemoteWebDriver(new URL(SeleniumContext.getSuiteLevelGRID_URL()), capabilities3);
			}
			else
			{
				driver=new FirefoxDriver();
			}
			return driver;
			/**********************************************************************************/
		case "firefox":
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//src//test//resources//drivers//geckodriver.exe");
			//WebDriverManager.firefoxdriver().setup();			
			DesiredCapabilities capabilities31 = DesiredCapabilities.firefox();
			if(Grid.equals("ON"))
			{
				driver = new RemoteWebDriver(new URL(SeleniumContext.getSuiteLevelGRID_URL()), capabilities31);
			}
			else
			{
				driver=new FirefoxDriver();
			}
			return driver;
			/**********************************************************************************/
		case "iexplorer":
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"//src//test//resources//drivers//IEDriverServer.exe");
			if(Grid.equals("ON"))
			{
				DesiredCapabilities capabilities4 = DesiredCapabilities.internetExplorer();
				driver = new RemoteWebDriver(new URL(SeleniumContext.getSuiteLevelGRID_URL()), capabilities4);
			}
			else
			{
				driver=new InternetExplorerDriver();
			}
			return driver;
			/**********************************************************************************/
		case "safari":
			DesiredCapabilities dcap = DesiredCapabilities.safari();
			dcap.setPlatform(Platform.MAC);
			driver = new SafariDriver(dcap);
			return driver;
			/**********************************************************************************/
		case "apple":
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//test//resources//drivers//chromedriver.exe");

			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", SeleniumContext.getTestLevelDEVICE_NAME());

			ChromeOptions chromeappleOptions1 = new ChromeOptions();
			chromeappleOptions1.addArguments("disable-infobars");
			chromeappleOptions1.setExperimentalOption("mobileEmulation", mobileEmulation);

			DesiredCapabilities capabilities11 = DesiredCapabilities.chrome();
			capabilities11.setCapability(ChromeOptions.CAPABILITY, chromeappleOptions1);

			driver = new ChromeDriver(capabilities11);
			return driver;
			/**********************************************************************************/
		case "iphonesize":
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//test//resources//drivers//chromedriver.exe");

			Map<String, Object> deviceMetrics = new HashMap<String, Object>();
			deviceMetrics.put("width", 375);
			deviceMetrics.put("height", 610);
			deviceMetrics.put("pixelRatio", 2.0);
			Map<String, Object> mobileEmulation1 = new HashMap<String, Object>();

			mobileEmulation1.put("deviceMetrics", deviceMetrics);
			mobileEmulation1.put("userAgent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");

			ChromeOptions chromeOptionssize = new ChromeOptions();
			chromeOptionssize.addArguments("disable-infobars");
			chromeOptionssize.setExperimentalOption("mobileEmulation", mobileEmulation1);
			DesiredCapabilities capabilitiesSize = DesiredCapabilities.chrome();
			capabilitiesSize.setCapability(ChromeOptions.CAPABILITY, chromeOptionssize);

			driver = new ChromeDriver(capabilitiesSize);
			return driver;

			/**********************************************************************************/
		case "androidsize":
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//test//resources//drivers//chromedriver.exe");

			Map<String, Object> androidMetrics = new HashMap<String, Object>();
			androidMetrics.put("width", Integer.parseInt("375"));
			androidMetrics.put("height", Integer.parseInt("620"));
			androidMetrics.put("pixelRatio", 3.5);
			Map<String, Object> androidEmulation = new HashMap<String, Object>();

			androidEmulation.put("deviceMetrics", androidMetrics);
			androidEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 6 Build/LYZ28E) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/%s Mobile Safari/537.36");

			ChromeOptions chromeOptionsAndroid = new ChromeOptions();
			chromeOptionsAndroid.addArguments("disable-infobars");
			chromeOptionsAndroid.setExperimentalOption("mobileEmulation", androidEmulation);
			DesiredCapabilities androidSize = DesiredCapabilities.chrome();
			androidSize.setCapability(ChromeOptions.CAPABILITY, chromeOptionsAndroid);

			driver = new ChromeDriver(androidSize);
			return driver;
			/**********************************************************************************/

		default:
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//test//resources//drivers//chromedriver.exe");
			ChromeOptions chromeOptions11 = new ChromeOptions();
			chromeOptions11.addArguments("test-type");
			chromeOptions11.addArguments("disable-infobars");
			chromeOptions11.addArguments("start-maximized");
			driver=new ChromeDriver(chromeOptions11);
			return driver;
		}
	}
	
	
	public static String AppiumServiceUrl()
	{
	//.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
	String service_url;
	service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
			.usingPort(0).withArgument(GeneralServerFlag.LOG_LEVEL,"error").usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
			.withAppiumJS(new File(
					"C:\\Users\\Akash.Kansal\\AppData\\Local\\appium-desktop\\app-1.5.0\\resources\\app\\node_modules\\appium\\build\\lib\\main.js")));
	service_url = service.getUrl().toString();
	service.start();
	return service_url;
	}
	
	public void stopAppiumService()
	{
		service.stop();
	}

	static AndroidDriver app() throws MalformedURLException
	{
		AndroidDriver<?> driver;
		
		String browserName = null;
		String Grid = null;
		if(browserName==null)
		{
			browserName = SeleniumContext.getTestLevelBROWSER_TYPE();
			//Grid = SeleniumContext.getSuiteLevelGRID();
		}
		System.out.println("browserName---:"+browserName);
		switch(browserName){
		case "chromeapp":
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//test//resources//drivers//chromedriver.exe");

			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("Chrome");
			cap.setPlatform(Platform.ANDROID);
			cap.setVersion("5.1");
			cap.setCapability("deviceName", "Moto E");
			cap.setCapability("udid", "TA09407DHD");
			cap.setAcceptInsecureCerts(true);

			driver = new AndroidDriver<WebElement>(new URL(SeleniumContext.getTestLevelGRID_URL()),cap);
			return driver;

			/**********************************************************************************/
		case "app":
			
			DesiredCapabilities cap1 = new DesiredCapabilities();
			cap1.setCapability(MobileCapabilityType.DEVICE_NAME, SeleniumContext.getTestLevelDEVICE_NAME().toString());
			//cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Moto E");
			cap1.setCapability("udid", SeleniumContext.getTestLevelmobileAdbName().toString());
			//cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1");

			cap1.setCapability("appPackage", "com.timesgroup.magicbricks");
			cap1.setCapability("appActivity", "com.til.mb.splash.SplashView");
			cap1.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+"//src//test//resources//apps//Magicbricks.apk");
			cap1.setCapability(MobileCapabilityType.FULL_RESET,true);
			cap1.setCapability("autoGrantPermissions", true);
			cap1.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);
			cap1.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
			System.out.println("portttttttttt"+SeleniumContext.getTestLevelsystemPort().toString());
			cap1.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, SeleniumContext.getTestLevelsystemPort().toString());
			//System.out.println("addddddddddddd"+AppiumServiceUrl());
			driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap1);
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return driver;
			/**********************************************************************************/
		default:
			DesiredCapabilities cap11 = new DesiredCapabilities();
			cap11.setPlatform(Platform.ANDROID);
			cap11.setVersion("5.1");
			cap11.setCapability("deviceName", "Moto E");
			cap11.setCapability("udid", "TA09407DHD");
			cap11.setAcceptInsecureCerts(true);

			driver = new AndroidDriver<WebElement>(new URL(SeleniumContext.getTestLevelGRID_URL()),cap11);
			return driver;
		}
	}
		static AndroidDriver apps(String platform, String udid, String systemPort) throws MalformedURLException
		{
			System.out.println( platform+ udid+ systemPort);
			AndroidDriver<?> driver;
			DesiredCapabilities cap1 = new DesiredCapabilities();
			cap1.setCapability(MobileCapabilityType.DEVICE_NAME, platform);
			//cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Moto E");
			cap1.setCapability("udid", udid);
			cap1.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");

			cap1.setCapability("appPackage", "com.timesgroup.magicbricks");
			cap1.setCapability("appActivity", "com.til.mb.splash.SplashView");
			cap1.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+"//src//test//resources//apps//Magicbricks.apk");
			cap1.setCapability(MobileCapabilityType.FULL_RESET,true);
			cap1.setCapability("autoGrantPermissions", true);
			cap1.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);
			//cap1.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
			System.out.println("portttttttttt"+systemPort);
			cap1.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
			//System.out.println("addddddddddddd"+AppiumServiceUrl());
			driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap1);
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return driver;
	}

}