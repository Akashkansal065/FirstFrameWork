package com.magic.testcases;

import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.magic.base.Base;
import com.magic.base.Provider;

public class TestClassFirst extends Base{

	@Test(dataProviderClass = Provider.class,dataProvider="gInsert")
	public void firstTestCase(String first,String second,String third)
	{
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		log.debug("First Test Case");
		System.out.println("Driver  Launched");
		
		driver.findElement(By.cssSelector(OR.getProperty("Textenter"))).sendKeys(first+second+third);;
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}

		/*String Text=driver.findElement(By.cssSelector(OR.getProperty("ALL"))).getText();
		Assert.assertEquals("All",Text);
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("ALL"))));*/
		log.debug("Test NEd");
		Reporter.log("Test complete");
		Reporter.log("<a target=\"blank\" href=\"D:\\electric.png\">Click</a>");
		System.out.println("Fail");
	}
	
	
	@Test(dataProviderClass = Provider.class,dataProvider="insert")
	public void secondTestCase(String first,String second,String third)
	{
		log.debug("Second Test Case");
		System.out.println("Driver  Launched");
		
		driver.findElement(By.cssSelector(OR.getProperty("Textenter"))).sendKeys(first+second+third);
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}

		/*String Text=driver.findElement(By.cssSelector(OR.getProperty("ALL"))).getText();
		System.out.println("Fail1111");
		Assert.assertEquals("All",Text);
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("ALL"))));*/
		log.debug("Test NEd");
		System.out.println("Fail");
	}
	
}