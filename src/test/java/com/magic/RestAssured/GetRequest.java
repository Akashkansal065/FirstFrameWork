package com.magic.RestAssured;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.magic.base.AllDrive;
import com.magic.base.Base;
import com.magic.utilities.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest extends Base{

	/*@BeforeMethod
	public void beforeMeth()
	{
		RestAssured.baseURI	="https://staging.magicbricks.com";
		RestAssured.useRelaxedHTTPSValidation();
	}*/
	/*@Test
	public void main()
	{
		//RestAssured.baseURI	="https://maps.googleapis.com";
		given().
		param("location", "-33.8670522,151.1957362").
		param("radius", "1500").
		param("type", "restaurant").
		param("keyword", "cruise").
		param("key", "AIzaSyDZA8HsqnKxDKHt-I7AcKk8aYNAe-PGf8U").
		when().
		get("/maps/api/place/nearbysearch/json").
		then().
		assertThat().
		statusCode(300);
		.
		and().
		contentType(ContentType.JSON).and().body("results[0].geometry.location.lng",Matchers.equalTo(151.21004f));
	}
	@Test
	public void post()
	{
		given().
		cookie("ACEGI_SECURITY_HASHED_REMEMBER_ME_COOKIE","NTQ4NDI5OToxNTM2OTE1ODAzNzcwOjBiMDNiZWIwMDk0NTg3NTZiYzQzZGVkMDU0MTU0YTkx").
		body("mobile=8588934240&propType=R").
		when().
		post("/ownerdashboard/buyerprofile/viewdetails").then()
		.assertThat().statusCode(200);
	}*/
	/*@Test
	public void body()
	{
		//RestAssured.requestSpecification = RestAssured.given().config(RestAssured.config().logConfig(new LogConfig(AllDrive.getPrintStream(),true)));
		given()
		//.proxy(8080)
		.log().all()
		.header("Content-Type", "application/x-www-form-urlencoded").
		header("Cookie","ACEGI_SECURITY_HASHED_REMEMBER_ME_COOKIE=NTQ4NDI5OToxNTM5ODM5MDU0MTUzOjdhY2Q4ZDg2NDM4NzgyZmQwODgxYzY0ZTNjMmI1YTIz").
		body("mobile=8588934240&propType=R")
		.when()
		.post("/ownerdashboard/buyerprofile/viewdetails");
		//getDetailedCookie("ACEGI_SECURITY_HASHED_REMEMBER_ME_COOKIE").toString());
		//.then()
		//log().all().
		//assertThat().body(Matchers.containsString("praveencava@gmail.comaaaaa"),Matchers.containsString("8588934240")).
		//.assertThat().contentType(ContentType.JSON);
		ExtentTestManager.getTest().log(LogStatus.INFO,"REQUEST",AllDrive.getWriter().toString());
		System.err.println(AllDrive.getWriter().toString());
	}

	@Test
	public void body2()
	{
		//RestAssured.requestSpecification = RestAssured.given().config(RestAssured.config().logConfig(new LogConfig(AllDrive.getPrintStream(),true)));
		given()
		//.proxy(8080)
		.log().all()
		.header("Content-Type", "application/x-www-form-urlencoded").
		header("Cookie","ACEGI_SECURITY_HASHED_REMEMBER_ME_COOKIE=NTQ4NDI5OToxNTM5ODM5MDU0MTUzOjdhY2Q4ZDg2NDM4NzgyZmQwODgxYzY0ZTNjMmI1YTIz").
		body("mobile=8588934240&propType=R")
		.when()
		.post("/ownerdashboard/buyerprofile/viewdetails")
		//getDetailedCookie("ACEGI_SECURITY_HASHED_REMEMBER_ME_COOKIE").toString());
		.then()
		//log().all().
		//assertThat().body(Matchers.containsString("praveencava@gmail.comaaaaa"),Matchers.containsString("8588934240")).
		.assertThat().contentType(ContentType.JSON);
		ExtentTestManager.getTest().log(LogStatus.INFO,"REQUEST",AllDrive.getWriter().toString());
		System.err.println(AllDrive.getWriter().toString());
	}*/
	RequestSpecification req = null;
	Response res = null;
	/*@Test
	public void cook()
	{
		//Cookies.ACEGIC("8979921513", "adjka");
		req = given().header("Content-Type", "application/x-www-form-urlencoded")
		.body("ubimobile=8979921513&ubilogin=akash.kansal@yopmail.com");
		res = req.log().all().post("/bricks/fetchUserDetailsAndSendOtp.html");
		
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"RESPONSE",res.asString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"REQUEST",AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"RESPONSE",res.prettyPrint());
	}
	@Test
	public void cook3()
	{
		//Cookies.ACEGIC("8979921513", "adjka");
		Response res = given().header("Content-Type", "application/x-www-form-urlencoded")
		.body("ubimobile=8979921513&ubilogin=akash.kansa@yopmail.com")
		.when().post("/bricks/fetchUserDetailsAndSendOtp.html");
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"RESPONSE",res.asString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"REQUEST",AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO,"RESPONSE",res.prettyPrint());
	}*/
	@Test
	public void cook2()
	{
		//Cookies.ACEGIC("8979921513", "adjka");
		given().log().all().header("Content-Type", "application/x-www-form-urlencoded")
		.body("ubimobile=8979921513&ubilogin=akash.kansal@yopmail.com")
		.when().post("/bricks/fetchUserDetailsAndSendOtp.html").then().log().all().and().statusCode(400);
		//Assert.fail();
		ExtentTestManager.getTest().log(LogStatus.INFO,"REQUEST",loggedRequestPathIn(AllDrive.getWriter()));
		ExtentTestManager.getTest().log(LogStatus.INFO,"RESPONSE",loggedResponsePathIn(AllDrive.getWriter()));
	}

}