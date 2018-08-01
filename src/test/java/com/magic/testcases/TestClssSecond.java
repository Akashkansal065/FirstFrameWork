package com.magic.testcases;

import java.util.HashMap;

import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.magic.base.Base;
import com.magic.base.Provider;
import com.magic.pageobject.TestclassSecondobject;
import com.magic.utilities.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

@Guice
public class TestClssSecond extends Base{

	@Test(groups={"end"},dataProviderClass = Provider.class,dataProvider="insert",description = "sdfsdfds")
	public void two(HashMap<String,String> table)
	{
		log.debug("First Test Case");
		ExtentTestManager.getTest().log(LogStatus.INFO,"saanjadn");
		System.out.println("Driver  Launched");
		System.out.println(table.get("name"));
		//CustomAssert.AssertEqual("first","second","Value are not same");
		sendData("#lst-ib",table.get("name"));
		//try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		TestclassSecondobject obj = new TestclassSecondobject();
		//obj.gLogo.click();
		obj.logo();
		obj.search();
		//obj.gSearch.click();
		//try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
	}


	@Test(groups={"ends"},dataProviderClass = Provider.class,dataProvider="insert",description = "ddssd")
	public void three(HashMap<String,String> table)
	{
		log.debug("First Test Case");
		ExtentTestManager.getTest().log(LogStatus.INFO,"second");
		System.out.println("Second group");
		System.out.println(table.get("name"));
		//CustomAssert.AssertEqual("first","second","Value are not same");
		sendData("#lst-ib",table.get("name"));
		//try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		TestclassSecondobject obj = new TestclassSecondobject();
		//obj.click(obj.gLogo);
		//obj.gLogo.click();
		obj.logo();
		obj.search();
		//obj.gSearch.click();
		//try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
	}
}
