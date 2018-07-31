package com.magic.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.magic.base.GlobalDriver;

public class TestclassSecondobject extends GlobalDriver{

	//WebDriver driver =AllDrive.getWebDriver();

	public TestclassSecondobject()
	{
		PageFactory.initElements(driver, this);
	}

	public void logo()
	{
		driver.findElement(By.cssSelector("#hplogo")).click();
	}

	public void search()
	{
		//driver.findElement(By.cssSelector(".jsb [name='btnK']")).click();
		click(all);
	}
	@FindBy(css="#hplogo")
	public WebElement gLogo;

	@FindBy(css = ".jsb [name='btnK']")
	public WebElement gSearch;
	
	@FindAll({
		   @FindBy(css = ".jsb [name='btnKss']"),
		   @FindBy(css = ".jsb [name='btnK']")
		})
	public WebElement all;

}
