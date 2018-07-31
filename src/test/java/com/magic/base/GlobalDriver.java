package com.magic.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class GlobalDriver {

	public WebDriver driver = AllDrive.getWebDriver();

	//public WebElement element = null;

	public void click(WebElement element) {
		element.click();
	}
}
