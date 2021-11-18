package com.magic.testcases;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.magic.base.AllDrive;
import com.magic.base.Base;
import com.magic.base.Provider;
import com.magic.pageobject.FiestObject;
import com.magic.pageobject.TestclassSecondobject;
import com.magic.utilities.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class ComScore extends Base{
	
	
	@Test(groups={"Single"},dataProviderClass = Provider.class,dataProvider="insert",description = "This will check the ComSore directly on Single Page")
	public void three(HashMap<String,String> table)
	{
		//log.debug("First Test Case");
		ExtentTestManager.getTest().log(LogStatus.INFO,"saanjadn");
		System.out.println("Driver  Launched");
		System.out.println(table.get("name"));
		driver.get(table.get("name"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
