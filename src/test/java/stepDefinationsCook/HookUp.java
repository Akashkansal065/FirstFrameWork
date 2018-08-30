package stepDefinationsCook;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.magic.base.AllDrive;
import com.magic.seleniumUtils.SeleniumContext;
import com.magic.utilities.ScreenShot;
import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class HookUp {

	
	@Before
	public void start()
	{
		System.out.println("Beforeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		WebDriver driver;
		driver = AllDrive.getWebDriver();
		driver.get(SeleniumContext.getTestLevelURL());
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	@After
	public void end(Scenario scenario)
	{
		Date date = new Date();
		if(scenario.isFailed())
		{
			String shotName = null;
			try {
				System.out.println("Try to capture Screen Shot");
				shotName = ScreenShot.ShotCaptured(AllDrive.getWebDriver(), System.getProperty("user.dir")+"/output/"+ 
						(date.getMonth()+1) +"/"+date.getDate()+"/"+scenario.getName()+date.getTime());
				Reporter.addScreenCaptureFromPath(shotName);
			}
			catch (IOException e)
			{e.printStackTrace();}
			Reporter.addStepLog("Broked up");	
		}
		AllDrive.cleanUp();
		System.out.println("Broked up");
	}
}
