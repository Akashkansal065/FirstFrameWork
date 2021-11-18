package com.magic.base;

import java.io.PrintStream;
import java.io.StringWriter;

import org.apache.commons.io.output.WriterOutputStream;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import io.appium.java_client.android.AndroidDriver;
import net.lightbody.bmp.BrowserMobProxyServer;

public class AllDrive {

	public static ThreadLocal<WebDriver> driverSession = new ThreadLocal<WebDriver>();
	public static ThreadLocal<StringWriter> writer = new ThreadLocal<StringWriter>();
	public static ThreadLocal<PrintStream> captor = new ThreadLocal<PrintStream>();
	public static ThreadLocal<AndroidDriver> appDriverSession = new ThreadLocal<AndroidDriver>();
	public static ThreadLocal<BrowserMobProxyServer> mobProxyServer = new ThreadLocal<BrowserMobProxyServer>();
	public static BrowserMobProxyServer proxyss = new BrowserMobProxyServer();


	public static void createPrintStream()
	{
		PrintStream capture = new PrintStream(new WriterOutputStream(AllDrive.getWriter()), true);
		captor.set(capture);
	}
	public static PrintStream getPrintStream()
	{
		if(captor.get() ==null)
		{
			createPrintStream();
		}
		return captor.get();
	}

	public static void cleanPrintStream()
	{
		//captor.get().close();
		captor.remove();
		System.out.println("Captor Cleared");
	}

	public static synchronized void createWriter()
	{
		StringWriter write = new StringWriter();
		writer.set(write);
	}

	public static StringWriter getWriter()
	{
		if(writer.get() ==null)
		{
			createWriter();
		}
		return writer.get();
	}

	public static void cleanWriter()
	{
		//		try {
		//			writer.get().close();
		//		} catch (IOException e) {
		//			//e.printStackTrace();
		//		}
		//		finally
		{
			writer.remove();
		}
		System.out.println("Writer Cleared");
	}

	private static BrowserMobProxyServer createMobProxyServer() throws Exception {
		BrowserMobProxyServer mobProxy = new BrowserMobProxyServer();
		System.out.println("Proxy started");
		mobProxy.setTrustAllServers(true);
		mobProxy.start();
		mobProxyServer.set(mobProxy);
		//proxyss = mobProxy;
		return mobProxy;
	}

	public static BrowserMobProxyServer getMobProxyServer() {

		if(mobProxyServer.get() == null)
		{
			System.out.println("No proxy present");
			try {
				createMobProxyServer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mobProxyServer.get();
	}

	public static void stopProxy()
	{
		BrowserMobProxyServer mobProxy = mobProxyServer.get();
		if(mobProxy != null)
		{
			mobProxy.abort();
			mobProxy = null;
		}
		mobProxyServer.remove();

	}


	private static WebDriver createWebDriver() throws Exception {
		return createWebDriver("");
	}
	private static WebDriver createWebDriver(String browser) throws Exception {
		WebDriver driver = BrowserType.browser(browser);
		driverSession.set(driver);
		return driver;
	}
	private static WebDriver getWebDriver(final Boolean isCreate) {
		if (driverSession.get() == null && isCreate) {
			try {
				createWebDriver();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(driverSession.get() == null)
		{
			try {
				createWebDriver();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return driverSession.get();
	}

	public static WebDriver getWebDriver() {
		return getWebDriver(false);
	}

	public static WebDriver getWebDriver(String browser) {
		return getWebDriver(false,browser);
	}

	private static WebDriver getWebDriver(boolean isCreate, String browser) {
		if (driverSession.get() == null && isCreate && browser == null || browser == "") {
			try {
				createWebDriver();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(driverSession.get() == null && isCreate)
		{
			try {
				createWebDriver(browser);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(driverSession.get() == null)
		{
			try {
				createWebDriver(browser);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return driverSession.get();
	}

	public static void cleanUp() {
		WebDriver driver = driverSession.get();
		if (driver != null) {
			try {
				System.out.println("Quitting Driver");
				driver.quit();
			} catch (WebDriverException ex) {
				ex.printStackTrace();
			}
			driver = null;
		}
		driverSession.remove();
		System.out.println("Driver gets cleaned");
	}

	public static AndroidDriver createAppDriver(String platform, String udid, String systemPort) throws Exception {
		AndroidDriver driver = BrowserType.apps(platform,udid,systemPort);
		appDriverSession.set(driver);
		return driver;
	}
	private static AndroidDriver createAppDriver() throws Exception {
		AndroidDriver driver = BrowserType.app();
		appDriverSession.set(driver);
		return driver;
	}
	private static AndroidDriver getAppDriver(final Boolean isCreate) {
		if (appDriverSession.get() == null && isCreate) {
			try {
				createAppDriver();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(appDriverSession.get() == null)
		{
			try {
				createAppDriver();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return appDriverSession.get();
	}

	public static AndroidDriver getAppDriver() {
		return getAppDriver(false);
	}
	public static void cleanAppDriver() {
		AndroidDriver driver = appDriverSession.get();
		if (driver != null) {
			try {
				driver.quit();
			} catch (WebDriverException ex) {
				ex.printStackTrace();
			}
			driver = null;
		}
		appDriverSession.remove();
		System.out.println("App Driver gets cleaned");
	}

}
