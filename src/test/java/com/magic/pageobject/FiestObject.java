package com.magic.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.magic.base.GlobalDriver;

public class FiestObject extends GlobalDriver{

	public FiestObject()
	{
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);
	}
	
	WebElement clickLogo = driver.findElement(By.cssSelector("#hplogo"));
	public void logo()
	{
		clickLogo.click();
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
	
	@FindBy(css="#lst-ib")
	public WebElement textEnter;
	
}
