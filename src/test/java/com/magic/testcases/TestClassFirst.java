package com.magic.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.magic.base.Base;
import com.magic.base.Provider;
import com.relevantcodes.extentreports.LogStatus;

public class TestClassFirst extends Base{

	@Test(dataProviderClass = Provider.class,dataProvider="gInsert",description = "Failed Test")
	public void firstTestCase(String first,String second,String third)
	{
		
		log.debug("First Test Case");
		testRep.log(LogStatus.INFO,"saanjadn");
		System.out.println("Driver  Launched");
		
		sendData(OR.getProperty("Textenter"),first+second+third);
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}

		String Text=driver.findElement(By.cssSelector(OR.getProperty("ALL"))).getText();
		Assert.assertEquals("All",Text);
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("ALL"))));
		log.debug("Test NEd");
		System.out.println("Fail");
	}
	
	
	@Test(dataProviderClass = Provider.class,dataProvider="insert",description = "Test printing out all the Spring beans.")
	public void secondTestCase(String first,String second,String third)
	{
		log.debug("Second Test Case");
		System.out.println("Driver  Launched");
		testRep.log(LogStatus.INFO,first+second+third);
		
		sendData(OR.getProperty("Textenter"),first+second+third);
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}

		/*String Text=driver.findElement(By.cssSelector(OR.getProperty("ALL"))).getText();
		System.out.println("Fail1111");
		Assert.assertEquals("All",Text);
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("ALL"))));*/
		log.debug("Test NEd");
		System.out.println("Fail");
	}
	
}