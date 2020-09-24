package com.magic.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;

public class ScreenShot{

	public static String ShotCaptured(WebDriver driver,String image) throws IOException
	{
		File captureScreenShot=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String imageName=image+".png";
		File destination=new File(imageName);
		FileUtils.copyFile(captureScreenShot, destination);
		
		return imageName;
	}
	public static String AppShotCaptured(AppiumDriver driver,String image) throws IOException
	{
		File captureScreenShot=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String imageName=image+".png";
		File destination=new File(imageName);
		FileUtils.copyFile(captureScreenShot, destination);
		
		return imageName;
	}
}
