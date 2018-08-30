package RunningSteps;

import java.io.File;

import org.testng.annotations.AfterClass;

import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/com/magic/features/Logout.feature",
monochrome=true,glue="stepDefinationsCook",
plugin={"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:"})
public class TestRunners extends AbstractTestNGCucumberTests{

	@AfterClass
	public static void teardown() {
		Reporter.loadXMLConfig(new File("src/test/java/extent_config.xml"));
		Reporter.setSystemInfo("user", System.getProperty("user.name"));
		Reporter.setSystemInfo("os", "Mac OSX");
		Reporter.setTestRunnerOutput("Sample test runner output message");
	}

	//	@BeforeClass
	//	public static void setup() {		
	//	    ExtentProperties extentProperties = ExtentProperties.INSTANCE;
	//	    extentProperties.setReportPath("output/Extent.html");
	//	}
}