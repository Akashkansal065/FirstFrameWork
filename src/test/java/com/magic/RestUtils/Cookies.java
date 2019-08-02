package com.magic.RestUtils;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.magic.base.AllDrive;
import com.magic.base.Base;
import com.magic.base.Provider;
import com.magic.utilities.DBManager;
import com.magic.utilities.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Cookies {

	public Properties RestCon = new Properties();
	public FileInputStream fis ;
	URLs obj = new URLs();
	String Web = obj.Url(loaded().getProperty("Web"));
	String Api = obj.Url(loaded().getProperty("Api"));
	String Mobile = obj.Url(loaded().getProperty("Mobile"));

	public Properties loaded()
	{
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//RestConfig.properties");
			RestCon.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return RestCon;
	}


	public void ACEGIC(String mobile,String emailId)
	{
		System.out.println(Web);
		RestAssured.baseURI	= Web;
		Base.restProxy();
		RestAssured.config = RestAssured.config().logConfig(LogConfig.logConfig().defaultStream(AllDrive.getPrintStream()).enablePrettyPrinting(true));
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification req = given().log().all().header("Content-Type", "application/x-www-form-urlencoded")
				.body("ubimobile="+mobile+"&ubilogin="+emailId);
		Response res = req.post("/bricks/fetchUserDetailsAndSendOtp.html");
		ExtentTestManager.getTest().log(LogStatus.INFO,"ACEGIC Request",AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"Cookies",res.cookies().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"ACEGIC Response Body",res.prettyPrint());
		emailId = res.jsonPath().getString("ubilogin");
		Map<String,String> otp = new DBManager().executeQuerySRow("select * from tpmvt where mvtmobile="+mobile);
		req = given().log().all().header("Content-Type",ContentType.URLENC)
				.body("ubimobile="+mobile+"&otp="+otp.get("EXFIELD2")+"&ubilogin="+emailId);
		res = req.post("/bricks/verifyLoginOtp.html");
		ExtentTestManager.getTest().log(LogStatus.INFO,"ACEGIC Request Continued",Base.loggedRequestPathInLast(AllDrive.getWriter()));
		ExtentTestManager.getTest().log(LogStatus.INFO,"Cookies",res.cookies().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"Response Body",res.prettyPrint());
		Base.acegic.put(mobile,res.getDetailedCookie("ACEGI_SECURITY_HASHED_REMEMBER_ME_COOKIE").toString());
		Base.completeCookieWeb.put(mobile,res.getCookies());
		System.err.println("Cookies generated for the "+mobile+ " : "+Base.completeCookieWeb.get(mobile));
		Base.RestReset();
	}
	public void mobileNoACEGIC(String mobile)
	{
		RestAssured.baseURI	= Web;
		Base.restProxy();
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification req = given().log().all().header("Content-Type", "application/x-www-form-urlencoded")
				.body("ubimobile="+mobile+"&from=login");
		Response res = req.post("/bricks/processValidMobile.html");
		ExtentTestManager.getTest().log(LogStatus.INFO,"ACEGIC Request",AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"Cookies",res.cookies().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"ACEGIC Response Body",res.prettyPrint());
		String encId = res.jsonPath().getString("ubilogin");
		Map<String,String> otp = new DBManager().executeQuerySRow("select * from tpmvt where mvtmobile="+mobile);
		req = given().log().all().header("Content-Type",ContentType.URLENC)
				.body("ubimobile="+mobile+"&otp="+otp.get("EXFIELD2")+"&ubilogin="+encId);
		res = req.post("/bricks/verifyLoginOtp.html");
		ExtentTestManager.getTest().log(LogStatus.INFO,"ACEGIC Request Continued",AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"Cookies",res.cookies().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"ACEGIC Response Body",res.prettyPrint());
		Base.acegic.put(mobile,res.getDetailedCookie("ACEGI_SECURITY_HASHED_REMEMBER_ME_COOKIE").toString());
		Base.RestReset();
	}
	public void EmailPasswordToken(String emailId,String password)
	{
		RestAssured.baseURI	= Mobile;
		Base.restProxy();
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification req = given().log().all().redirects().follow(true)
				.header("Content-Type", "application/x-www-form-urlencoded")
				.body("username="+emailId+"&password="+password);
		Response res = req.log().all().post("/mbs/login.html");
		ExtentTestManager.getTest().log(LogStatus.INFO,"EmailPasswordToken Request",AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"Cookies",res.cookies().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"EmailPasswordToken Response Body",res.prettyPrint());
		String tokenn = res.getDetailedCookie("token").toString();
		Base.token.put(emailId,tokenn);
		Base.RestReset();
	}
	public void MobileToken(String mobileno)
	{
		RestAssured.baseURI	= Api;
		Base.restProxy();
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification req = given().log().all()
				.header("Content-Type", "application/x-www-form-urlencoded")
				.body("uinput="+mobileno+"&campCode=ios&type=mobile");
		Response res = req.log().all().post("/bricks/dologinbytype.html");
		ExtentTestManager.getTest().log(LogStatus.INFO,"MobileToken Request",AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"Cookies",res.cookies().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"MobileToken Response Body",res.prettyPrint());
		String encrID =res.jsonPath().getString("uId[0]");
		Map<String,String> otp = new DBManager().executeQuerySRow("select * from tpmvt where mvtmobile="+mobileno);
		req = given().log().all().header("Content-Type",ContentType.URLENC)
				.body("mobile="+mobileno+"&uinput="+encrID+"&campCode=android&otp="+otp.get("EXFIELD2"));
		res = req.log().all().post("/bricks/domobilelogin.html");
		ExtentTestManager.getTest().log(LogStatus.INFO,"MobileToken Request Continued",AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"Cookies",res.cookies().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"MobileToken Response Body",res.prettyPrint());
		String tokenn = res.jsonPath().getString("token");
		Base.token.put(mobileno,tokenn);
		Base.completeCookieWap.put(mobileno,res.getCookies());
		Base.RestReset();
	}
	public void MultipleEmailToken(String mobileno,String emailId)
	{
		System.out.println(Api);
		RestAssured.baseURI	= Api;
		Base.restProxy();
		RestAssured.config = RestAssured.config().logConfig(LogConfig.logConfig().defaultStream(AllDrive.getPrintStream()).enablePrettyPrinting(true));
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification req = given()
				.header("Content-Type",ContentType.URLENC)
				.body("uinput="+mobileno+"&userid="+emailId+"&campCode=android&type=mobile");
		Response res = req.log().all().post("/bricks/dologinbytype.html");
		ExtentTestManager.getTest().log(LogStatus.INFO,"MultipleEmailToken Request",AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"Cookies",res.cookies().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"MultipleEmailToken Response Body",res.prettyPrint());
		String encrID =res.jsonPath().getString("uId[0]");
		Map<String,String> otp = new DBManager().executeQuerySRow("select * from tpmvt where mvtmobile="+mobileno);

		req = given().header("Content-Type",ContentType.URLENC)
				.body("mobile="+mobileno+"&uinput="+encrID+"&campCode=android&otp="+otp.get("EXFIELD2"));
		res = req.log().all().post("/bricks/domobilelogin.html");
		ExtentTestManager.getTest().log(LogStatus.INFO,"MultipleEmailToken Request Continued",Base.loggedRequestPathInLast(AllDrive.getWriter()));
		ExtentTestManager.getTest().log(LogStatus.INFO,"Cookies",res.cookies().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"MultipleEmailToken Response Body",res.prettyPrint());
		String tokenn = res.jsonPath().getString("token");
		Base.token.put(mobileno,tokenn);
		Base.RestReset();
	}
	public void JiraSession(String mobile,String emailId)
	{
		System.out.println("https://timesgroup.jira.com/");
		RestAssured.baseURI	= "https://timesgroup.jira.com/";
		Base.restProxy();
		RestAssured.config = RestAssured.config().logConfig(LogConfig.logConfig().defaultStream(AllDrive.getPrintStream()).enablePrettyPrinting(true));
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification req = given().log().all().header("Content-Type",ContentType.JSON)
				.body("{"+"\"username\":\"akash.kansal@yopmail.com\","+"\"password\":\"6789067890\""+"}");
		Response res = req.post("/jira/rest/auth/1/session");
		ExtentTestManager.getTest().log(LogStatus.INFO,"ACEGIC Request",AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"Cookies",res.cookies().toString());
		Base.acegic.put(mobile,res.getDetailedCookie("ACEGI_SECURITY_HASHED_REMEMBER_ME_COOKIE").toString());
		Base.RestReset();
	}
	public void addCookies()
	{
		System.out.println("AddCookies");
		Map<String,Map<String,List<List<String>>>> allDataMAp = new Provider().restData();
		System.out.println("cookies Edit");

		for (String acegicKey : allDataMAp.keySet()) {

			System.out.println(acegicKey);

			Map<String,List<List<String>>> mobileDataMap = allDataMAp.get(acegicKey);

			for (String mobileKey : mobileDataMap.keySet())
			{
				List<List<String>> mobileDataList = mobileDataMap.get(mobileKey); 

				for (List<String> dataValueList : mobileDataList) {

					System.out.println(dataValueList);

					//for(int columnData=0; columnData < dataValueList.size(); columnData++)
					{
						System.out.println(dataValueList.get(0)+":"+dataValueList.get(1));
						if("ACEGIC".equalsIgnoreCase(acegicKey)) {
							
							System.out.println("Check");
							
							new DBManager().executeMobilEmailUpdate(dataValueList.get(0),mobileKey,dataValueList.get(1));
							
							ACEGIC(mobileKey,dataValueList.get(0));
						} 
						else if(acegicKey.contains("Token"))
						{
							MultipleEmailToken(mobileKey,dataValueList.get(0));
						}
					}
				}

			}
		}
	}
}