package com.magic.base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.magic.utilities.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import com.vimalselvam.cucumber.listener.Reporter;

public abstract class GlobalDriver {

	public WebDriver driver = AllDrive.getWebDriver();


	public void click(WebElement element) {

		element.click();
		ExtentTestManager.getTest().log(LogStatus.INFO,"Click on Element",element.toString());
	}
	public void sendKeys(WebElement element,String key) {

		element.clear();
		element.sendKeys(key);
		ExtentTestManager.getTest().log(LogStatus.INFO,"Data Entered is :-"+key,element.toString());
	}
		
	public void wait(int sec) throws InterruptedException
	{
        try{
            synchronized (driver) {
                  driver.wait(sec * 1000);
            }
     }catch(NullPointerException npe) {
            npe.printStackTrace();
     } catch(InterruptedException ie){
            ie.printStackTrace();
     }
}
	public boolean isElementPresent(WebElement element)
	{
		try
		{
			wait(2);
			element.isDisplayed();
			return true;
		}
		catch(NoSuchElementException | InterruptedException e)
		{
			return false;
		}
	}
}
