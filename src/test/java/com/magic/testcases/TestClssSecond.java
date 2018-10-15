package com.magic.testcases;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.magic.base.Base;
import com.magic.base.Provider;
import com.magic.pageobject.FiestObject;
import com.magic.pageobject.TestclassSecondobject;
import com.magic.utilities.DBManager;
import com.magic.utilities.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

@Guice
public class TestClssSecond extends Base{

	@Test(groups={"end"},dataProviderClass = Provider.class,dataProvider="insert",description = "description will come here")
	public void two(HashMap<String,String> table)
	{
		//log.debug("First Test Case");
		ExtentTestManager.getTest().log(LogStatus.INFO,"saanjadn");
		System.out.println("Driver  Launched");
		System.out.println(table.get("name"));
		//CustomAssert.AssertEqual("first","second","Value are not same");
		//sendData("#lst-ib",table.get("name"));
		//try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		TestclassSecondobject obj = new TestclassSecondobject();
		FiestObject obj1 = new FiestObject();
		obj.click(obj1.textEnter);
		obj.textEnter.sendKeys(table.get("name"));
		obj.logo();
		obj.search();
		obj1.gSearch.click();
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
	}


	@Test(groups={"ends"},dataProviderClass = Provider.class,dataProvider="insert",description = "description will come here")
	public void three(HashMap<String,String> table)
	{
		//log.debug("First Test Case");
		ExtentTestManager.getTest().log(LogStatus.INFO,"second");
		System.out.println("Second group");
		System.out.println(table.get("name"));
		//CustomAssert.AssertEqual("first","second","Value are not same");
		//sendData("#lst-ib",table.get("name"));
		//DBManager db = new DBManager();
		//String query = "SELECT * FROM property.ilp_client_detail";
		//Map<String,String> data= db.executeQuerySRow(query);
		//System.out.println("ssssssssssssssssss"+""+data.get("to_email_ids")+"");
		//try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		//Assert.fail();
		TestclassSecondobject obj = new TestclassSecondobject();
		obj.click(obj.gLogo);
		obj.gLogo.click();
		obj.logo();
		obj.search();
		obj.gSearch.click();
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		//Assert.fail();
	}
}
