package com.magic.testcases;

import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.magic.base.Base;
import com.magic.base.Provider;
import com.magic.listners.CustomAssert;
import com.magic.utilities.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

@Guice
public class TestClssSecond extends Base{


	@Test(dataProviderClass = Provider.class,dataProvider="gInsert",description = "sdfsdfds")
	public void firstTestCases(String first,String second,String third)
	{
		log.debug("First Test Case");
		ExtentTestManager.getTest().log(LogStatus.INFO,"saanjadn");
		System.out.println("Driver  Launched");

		sendData(OR.getProperty("Textenter"),first+second+third);
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		CustomAssert.AssertEqual(first,first,"Value are same");
		/*String Text=driver.findElement(By.cssSelector(OR.getProperty("ALL"))).getText();
		Assert.assertEquals("All",Text);
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("ALL"))));
		log.debug("Test NEd");
		Assert.fail();
		System.out.println("Fail");*/
	}


	@Test(dataProviderClass = Provider.class,dataProvider="insert",description = "ddssd")
	public void secondTestCases(String first,String second,String third)
	{
		log.debug("Second Test Case");
		System.out.println("Driver  Launched");
		ExtentTestManager.getTest().log(LogStatus.INFO,first+second+third);

		sendData(OR.getProperty("Textenter"),first+second+third);
		try {Thread.sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
		CustomAssert.AssertEqual(first,first,"Value are not same");
		//CustomAssert.AssertTrue(isElementPresent(By.cssSelector(OR.getProperty("ALL"))),"");
		log.debug("Test NEd");
		System.out.println("Fail");
	}


}
