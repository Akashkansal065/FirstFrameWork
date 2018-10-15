package stepDefinationsCook;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;

import com.magic.pageobject.TestclassSecondobject;
import com.magic.utilities.DBManager;
import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class Single {
	TestclassSecondobject obj = new TestclassSecondobject();
	DBManager db = new DBManager();
	@Given("^Go to https://www\\.google\\.com$")
	public void go_to_https_www_google_com() throws Throwable {
		System.out.println("aasdsaddsaa");
		Reporter.addStepLog("Step Log message adsadsadsa here");
	}

	@ Then("^enter \"([\\w]+)\" and \"([\\d]+)\"$")
	public void enterusername(String username,String password) throws InterruptedException
	{
		Thread.sleep(5000);
		Assert.fail();
		System.out.println(""+username+"      	"+password);
		Reporter.addStepLog("Step Log message goes adasasdsa");
	}

	@Then("^wait for page load$")
	public void wait_for_page_load() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String query = "SELECT * FROM property.ilp_client_detail";
		Map<String,String> data= db.executeQuerySRow(query);
		System.out.println("ssssssssssssssssss"+""+data.get("to_email_ids")+"");
		
		
		Reporter.addStepLog("a Log message goes here");
	}

	@Then("^enter data in google seacrh field$")
	public void enter(DataTable data) throws Throwable {

		Reporter.addStepLog("Step adsasdsadas message goes here");
		List<Map<String, String>> ls =data.asMaps(String.class, String.class);
		for(int i=0;i<ls.size();i++)
		{
			Map<String,String> map = ls.get(i);
			Set<String> set = map.keySet();
			Iterator<String> itr = set.iterator();
			while(itr.hasNext())
			{
				System.out.println(map.get(itr.next()));
			}
		}

	}
	@Then("^enter data in google seacrh field (.+) (.+)$")
	public void enter_data_in_google_seacrh_fiel(String first,String second) throws Throwable {

		Reporter.addStepLog("Step adasd message goes here");
		System.out.println(first+second);
	}

	@Then("^click on google search$")
	public void click_on_google_search() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Reporter.addStepLog("Step Log adsadas goes here");
		obj.search();
	}
	@Given("^Enter the browser name and keep it synchronized$")
	public void browser()
	{
		Reporter.addStepLog("Step Log mesasdassage goes here");
		System.out.println("browser loaded");
	}
	@Then("^set the driver in a thread$")
	public void driver()
	{
		Reporter.addStepLog("Step Log messasdage goes here");
		System.out.println("driver");
	}
}