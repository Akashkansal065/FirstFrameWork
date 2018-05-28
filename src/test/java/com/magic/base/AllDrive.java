package com.magic.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class AllDrive {

	public static ThreadLocal<WebDriver> driverSession = new ThreadLocal<WebDriver>();

	public WebDriver driver;

	public WebDriver createWebDriver() throws Exception {
		System.out.println("jdadsadadnadjsakdnkandjkasndkdnakjsndskndas");
		driver = BrowserType.browser();
		driverSession.set(driver);
		return driver;
	}
	public static WebDriver getWebDriver(final Boolean isCreate) {
		if (driverSession.get() == null && isCreate) {
			try {
				new AllDrive().createWebDriver();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(driverSession.get() == null)
		{
		try {
			new AllDrive().createWebDriver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
    }

}
