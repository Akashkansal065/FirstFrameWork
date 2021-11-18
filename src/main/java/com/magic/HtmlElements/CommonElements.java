package com.magic.HtmlElements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.magic.base.AllDrive;
import com.magic.utilities.DBManager;
import com.magic.utilities.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public abstract class CommonElements {

	public WebDriver driver = AllDrive.getWebDriver();
	public AllWaits waits = new AllWaits();
	public DBManager db = new DBManager();
	
	public int RandomNumber(int size) {
		if (size > 2) {
			//int max = size;
			int min = 1;
			int range = size - min + 1;
			int randnum = (int) (Math.random() * range) + min;
			//System.out.println("Random Num" + randnum);
			return randnum;

		} else {
			return size;
		}
	}

	public final void selectWindow() {

		driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);
		//wait(1);
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"select window");
		// Check whether it's the expected page.
	}

	public void closeWindow() {
		driver.close();
	}

	public void refreshWindow() {
		driver.navigate().refresh();
	}

	public final void selectWindow(final int index) {
		driver.switchTo().window((String)
				driver.getWindowHandles().toArray()[index]);
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"select window, locator={\"" + index + "\"}");
	}

	public final void selectNewWindow() {
		driver.switchTo().window((String)
				driver.getWindowHandles().toArray()[1]);
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"select new window");
		//wait(1);
	}
	public final void selectNewWindow2() {
		driver.switchTo().window((String)
				driver.getWindowHandles().toArray()[2]);
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"select new window");
		//wait(1);
	}

	public void scrollToElement(WebElement element)
	{               
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-175)");
		ExtentTestManager.getTest().log(LogStatus.PASS,"Scrolling to element.");
		wait(3);
	}

	public void find(WebElement element) {
		Actions act =  new Actions(driver);
		act.moveToElement(element).click().perform();
	}
	public void clickAction(WebElement element){

		try {
			Actions act =  new Actions(driver);
			act.moveToElement(element).click().perform();
		}catch(ElementClickInterceptedException e) {System.out.println("Trying using js");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		}
	}

	public void sendKeysAction(WebElement element, String s) {
		try {
			Actions act =  new Actions(driver);
			act.moveToElement(element).sendKeys(s).perform();
		}catch(ElementClickInterceptedException e) {System.out.println("Trying using js");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].value='"+s+"';", element);
		}
	}

	public void wait(int sec) {
		try {
			synchronized (driver) {
				driver.wait(sec * 1000);
			}
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	public String getOtpUsingUrl(String mobileNo)
	{ 
		String response = null;
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL("https://apistg.magicbricks.com/bricks/stagingotp.html?mobile="+mobileNo);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");


			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			int status = con.getResponseCode();
			if(status > 205)
			{
				Assert.fail("Otp Api is not working");
			}
			response = content.toString().trim();
			System.out.println(response);
		} catch (IOException |NullPointerException e) {
		}
		
		return response;
	}
	
	public void scroll(WebElement element)
	{               
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		ExtentTestManager.getTest().log(LogStatus.PASS,"Scrolling to element.");
		wait(3);
	}
}
