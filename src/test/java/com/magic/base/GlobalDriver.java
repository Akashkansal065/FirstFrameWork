package com.magic.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.magic.utilities.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import com.vimalselvam.cucumber.listener.Reporter;

public abstract class GlobalDriver {

	public WebDriver driver = AllDrive.getWebDriver();

	//public WebElement element = null;

	public void click(WebElement element) {

		element.click();
	}
}
