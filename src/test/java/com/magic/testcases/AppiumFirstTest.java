package com.magic.testcases;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.magic.base.AllDrive;
import com.magic.base.Base;
import com.magic.base.Provider;

import io.appium.java_client.android.AndroidElement;

public class AppiumFirstTest extends Base{

	@Test()
	public void twos()
	{

		System.out.println("bbbbbbbbbbbbbbbbbbbb");


		
		try {
			Thread.sleep(5000); 
		}
		catch (InterruptedException e1) {
			e1.printStackTrace(); 
		}
		AllDrive.getAppDriver().findElementById("com.timesgroup.magicbricks:id/alledit").sendKeys("Akash KAnsal"); 
		AllDrive.getAppDriver().findElementByAndroidUIAutomator("text(\"Skip\")").
		click();
		//driver.findElementById("com.timesgroup.magicbricks:id/txtObNextButton").click(); 
		List<AndroidElement> list =AllDrive.getAppDriver().findElements(By.xpath(
				"//*[@resource-id='com.timesgroup.magicbricks:id/title']")); 
		for(int i=0;i<list.size();i++) {
			System.out.println("check"+list.get(i));
			System.out.println(list.get(i).getAttribute("text")); 
		}
		try {
			Thread.sleep(2000); } catch (InterruptedException e1) { e1.printStackTrace(); }

	}
	@Test()
	public void two()
	{

		System.out.println("asdqwdawdadaaasdsaasas");


		
		try {
			Thread.sleep(5000); 
		}
		catch (InterruptedException e1) {
			e1.printStackTrace(); 
		}
		AllDrive.getAppDriver().findElementById("com.timesgroup.magicbricks:id/alledit").sendKeys("Akash KAnsal"); 
		AllDrive.getAppDriver().findElementByAndroidUIAutomator("text(\"Skip\")").
		click();
		//driver.findElementById("com.timesgroup.magicbricks:id/txtObNextButton").click(); 
		List<AndroidElement> list =AllDrive.getAppDriver().findElements(By.xpath(
				"//*[@resource-id='com.timesgroup.magicbricks:id/title']")); 
		for(int i=0;i<list.size();i++) {
			System.out.println("check"+list.get(i));
			System.out.println(list.get(i).getAttribute("text")); 
		}
		try {
			Thread.sleep(2000); } catch (InterruptedException e1) { e1.printStackTrace(); }

	}
//	@Test()
//	public void twoss()
//	{
//
//		System.out.println("bbbbbbbbbbbbbbbbbb");
//
//
//		AllDrive.getAppDriver().findElementById("com.timesgroup.magicbricks:id/alledit").sendKeys("Akash KAnsal"); 
//		try {
//			Thread.sleep(2000); 
//		}
//		catch (InterruptedException e1) {
//			e1.printStackTrace(); 
//		}
//		AllDrive.getAppDriver().findElementByAndroidUIAutomator("text(\"Skip\")").
//		click();
//		//driver.findElementById("com.timesgroup.magicbricks:id/txtObNextButton").click(); 
//		List<AndroidElement> list =AllDrive.getAppDriver().findElements(By.xpath(
//				"//*[@resource-id='com.timesgroup.magicbricks:id/title']")); 
//		for(int i=0;i<list.size();i++) {
//			System.out.println("check"+list.get(i));
//			System.out.println(list.get(i).getAttribute("text")); 
//		}
//		try {
//			Thread.sleep(2000); } catch (InterruptedException e1) { e1.printStackTrace(); }
//
//	}
}
