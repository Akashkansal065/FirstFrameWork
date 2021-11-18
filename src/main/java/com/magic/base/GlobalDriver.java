package com.magic.base;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.magic.HtmlElements.CommonElements;
import com.magic.utilities.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public abstract class GlobalDriver extends CommonElements{

	//public WebDriver driver = AllDrive.getWebDriver();

	public void click(WebElement element) {
		try {
			waits.fluent(element);
			waits.elementToBeClickable(element, 5).click();
		}catch(ElementClickInterceptedException e)
		{
			clickAction(element);
		}
		catch(TimeoutException e)
		{
			element.click();
		}
	}

	public void sendKeys(WebElement element, String key) {
		try {
			click(element);
			element.clear();
			element.sendKeys(key);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Data Entered is :-" + key, element.toString());
		}catch(ElementClickInterceptedException e)
		{
			sendKeysAction(element, key);
		}
	}

	public boolean isElementPresent(WebElement element) {
		try {
			wait(2);
			element.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void mousehover(WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element).build().perform();
		//driver.wait(3);
	}
	
	public void click(WebElement element, int seconds) {
		try {
			waits.fluent(element,seconds);
			waits.elementToBeClickable(element, seconds).click();
		}catch(ElementClickInterceptedException e)
		{
			clickAction(element);
		}
		catch(TimeoutException e)
		{
			element.click();
		}
	}
	
}
