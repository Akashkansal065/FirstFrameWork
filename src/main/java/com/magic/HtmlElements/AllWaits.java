package com.magic.HtmlElements;

import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.magic.base.AllDrive;
import com.magic.utilities.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class AllWaits {

	WebDriver driver = AllDrive.getWebDriver();

	public WebElement elementToBeClickable(WebElement ele,int time)
	{
		WebElement firstResult = new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(ele));
		ExtentTestManager.getTest().log(LogStatus.INFO,"Element Clicked" ,ele.toString());
		return firstResult;
	}

	public void alertIsPresent(int time)
	{
		try {
		Alert alert = new WebDriverWait(driver, time).until(ExpectedConditions.alertIsPresent());
		ExtentTestManager.getTest().log(LogStatus.INFO,"Element Clicked");
		alert.accept();
	}
		catch(Exception e) {
			System.out.println("Alert is not Present");
		}
	}
	public Boolean elementToBeSelected(WebElement ele,int time)
	{
		ExtentTestManager.getTest().log(LogStatus.INFO,"elementToBeSelected" ,ele.toString());
		Boolean firstResult = new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeSelected(ele));
		return firstResult;
	}

	public Boolean elementSelectionStateToBe(WebElement ele,int time,boolean state)
	{
		ExtentTestManager.getTest().log(LogStatus.INFO,"elementSelectionStateToBe" ,ele.toString());
		Boolean firstResult = new WebDriverWait(driver, time).until(ExpectedConditions.elementSelectionStateToBe(ele, state));
		return firstResult;
	}

	public WebElement elementToBeVisibile(WebElement element,int time) { 
		WebElement firstResult = new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
		return firstResult; 
	}

	public Boolean elementNotToBeVisible(WebElement element,int time) { 
		Boolean firstResult = new WebDriverWait(driver, time).until(ExpectedConditions.invisibilityOf(element));
		return firstResult; 
	}

	public List<WebElement> presenceOfAllElementsByCss(String ele,int time)
	{
		List<WebElement> firstResult = new WebDriverWait(driver, time).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(ele)));
		ExtentTestManager.getTest().log(LogStatus.INFO,"Element Clicked" ,ele);
		return firstResult;
	}

	public WebElement fluent(WebElement elements)
	{

		Wait<WebElement> fluentWait = new FluentWait<WebElement>(elements)
				.pollingEvery(Duration.ofMillis(250))
				.withTimeout(Duration.ofSeconds(10))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(ElementClickInterceptedException.class)
				;

		System.out.println(elements.toString()+"Rechecking at time " + new Date());

		return fluentWait.until(element -> element);

	}
	public WebElement fluent(WebElement elements,int sec)
	{

		Wait<WebElement> fluentWait = new FluentWait<WebElement>(elements)
				.pollingEvery(Duration.ofMillis(250))
				.withTimeout(Duration.ofSeconds(sec))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(ElementClickInterceptedException.class)
				;

		System.out.println(elements.toString()+"Rechecking at time " + new Date());

		return fluentWait.until(element -> element);

	}

	public WebElement fluentFunc(WebElement elements)
	{

		Wait<WebElement> fluentWait = new FluentWait<WebElement>(elements)
				.pollingEvery(Duration.ofMillis(250))
				.withTimeout(Duration.ofSeconds(10))
				.ignoring(StaleElementReferenceException.class)
				.ignoring(NoSuchElementException.class)
				.ignoring(ElementClickInterceptedException.class)
				;

		WebElement foo = fluentWait.until(new Function<WebElement, WebElement>() {
			public WebElement apply(WebElement driver) {
				//System.out.println("Rechecking at time " + new Date());
				return driver;
			}
		});

		return foo;

	}

	public WebElement fluentPred(WebElement elements)
	{

		Wait<WebElement> fluentWait = new FluentWait<WebElement>(elements)
				.pollingEvery(Duration.ofMillis(250))
				.withTimeout(Duration.ofSeconds(10))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(ElementClickInterceptedException.class)
				;

		WebElement foo = fluentWait.until(new Function<WebElement, WebElement>() {
			public WebElement apply(WebElement driver) {
				//System.out.println("Rechecking at time " + new Date());
				return driver;
			}
		});

		return foo;

	}

	public void elementToBeClicked(WebElement ele,int time)
	{
		WebElement firstResult = new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(ele));
		ExtentTestManager.getTest().log(LogStatus.INFO,"Element Clicked" ,ele.toString());
		firstResult.click();
	}

}
