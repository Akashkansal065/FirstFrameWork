package com.magic.utilities;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import com.magic.seleniumUtils.SeleniumContext;

public class BrowserType {

	public static WebDriver browser()
	{
		WebDriver driver;
		String browserName = null;
		if(browserName==null)
		{
			browserName=SeleniumContext.getTestLevelBROWSER_TYPE();
		}
		System.out.println(browserName);
		switch(browserName){

		case "chrome":
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//test//resources//drivers//chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("test-type");
			chromeOptions.addArguments("disable-infobars");
			chromeOptions.addArguments("start-maximized");
			driver=new ChromeDriver(chromeOptions);
			return driver;
			/**********************************************************************************/
		case "firefox":
			System.setProperty("webdriver.gecko.driver","D://Jars//geckodriver.exe");
			driver = new FirefoxDriver();
			return driver;
			/**********************************************************************************/
		case "iexplorer":
			System.setProperty("webdriver.ie.driver","D://Jars//geckodriver.exe");
			driver = new InternetExplorerDriver();
			return driver;
			/**********************************************************************************/
		case "safari":
			DesiredCapabilities dcap = DesiredCapabilities.safari();
			dcap.setPlatform(Platform.MAC);
			driver = new SafariDriver(dcap);
			return driver;
			/**********************************************************************************/
		case "apple":
			System.setProperty("webdriver.chrome.driver","D://Jars//chromedriver.exe");

			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", SeleniumContext.getTestLevelDEVICE_NAME());

			ChromeOptions chromeappleOptions1 = new ChromeOptions();
			chromeappleOptions1.addArguments("disable-infobars");
			chromeappleOptions1.setExperimentalOption("mobileEmulation", mobileEmulation);

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeappleOptions1);

			driver = new ChromeDriver(capabilities);
			return driver;
			/**********************************************************************************/
		case "iphonesize":
			System.setProperty("webdriver.chrome.driver","D://Jars//chromedriver.exe");

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
			System.setProperty("webdriver.chrome.driver","D://Jars//chromedriver.exe");

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
			System.setProperty("webdriver.chrome.driver","D://Jars//chromedriver.exe");
			ChromeOptions chromeOptions1 = new ChromeOptions();
			chromeOptions1.addArguments("test-type");
			chromeOptions1.addArguments("disable-infobars");
			chromeOptions1.addArguments("start-maximized");
			driver=new ChromeDriver(chromeOptions1);
			return driver;
		}
	}

}
