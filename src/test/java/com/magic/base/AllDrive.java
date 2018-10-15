package com.magic.base;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;

import org.apache.commons.io.output.WriterOutputStream;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.specification.RequestSpecification;

public class AllDrive {

	public static ThreadLocal<WebDriver> driverSession = new ThreadLocal<WebDriver>();
	public static ThreadLocal<StringWriter> writer = new ThreadLocal<StringWriter>();
	public static ThreadLocal<PrintStream> captor = new ThreadLocal<PrintStream>();
	
	
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

	private static WebDriver createWebDriver() throws Exception {
		WebDriver driver = BrowserType.browser();
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
	public static void cleanUp() {
            WebDriver driver = driverSession.get();
            if (driver != null) {
                try {
                    driver.quit();
                } catch (WebDriverException ex) {
                    ex.printStackTrace();
                }
                driver = null;
            }
        driverSession.remove();
        System.out.println("Driver gets cleaned");
    }

}
