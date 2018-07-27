package com.magic.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.magic.base.AllDrive;

public class TestclassSecondobject {

	public TestclassSecondobject()
	{
		PageFactory.initElements(AllDrive.getWebDriver(), this);
	}
	
	
	@FindBy(css="#hplogo")
	public WebElement gLogo;
	
	@FindBy(css = ".jsb [name='btnK']")
	public WebElement gSearch;
	
}
