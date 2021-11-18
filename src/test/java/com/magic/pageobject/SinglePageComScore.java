package com.magic.pageobject;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.filters.ResponseFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

public class SinglePageComScore implements RequestFilter,ResponseFilter{


	public BrowserMobProxyServer server;
	public String cookie;
	
	//public ExtentTest et;

	@Override
	public HttpResponse filterRequest(HttpRequest request, HttpMessageContents contents, HttpMessageInfo messageInfo) {
		
		//ls.add(messageInfo.getOriginalUrl());
		//MobProxy.ls.add(messageInfo.getOriginalUrl());
		//if (messageInfo.getOriginalUrl().contains("https://sb.scorecardresearch.com")) {
			
			//server.stop();
			//System.out.println("Header added");
			//server.addHeader("Cookie", "ACEGI_SECURITY_HASHED_REMEMBER_ME_COOKIE=\"MzkzNzg0NTQ6MTYxMjQyNzk3NzIxNDo1MjE1MTAwNGVjMzMyYzU3ODY0ZTY2MDg2MjVjOGJkMw==\"");
		//}
			//System.out.println(messageInfo.getOriginalUrl());
			//System.out.println(messageInfo.getOriginalRequest().getUri());
			//System.out.println(request.getUri());
			//System.out.println(contents.getTextContents());
		//}
		/*
		 * System.out.println(ls.size()); if(ls.size()>30) { server.stop(); }
		 */
		// retrieve the existing message contents as a String or, for binary contents, as a byte[]

		//et.log(LogStatus.INFO,"Cookies","cookie Added for User Login");
		//server.addHeader("Cookie", cookie);
		//server.addHeader("Cookie", "ACEGI_SECURITY_HASHED_REMEMBER_ME_COOKIE=\"MzkzNzg0NTQ6MTYxMjQyNzk3NzIxNDo1MjE1MTAwNGVjMzMyYzU3ODY0ZTY2MDg2MjVjOGJkMw==\"");
		if (messageInfo.getOriginalUrl().equals("https://deployment.magicbricks.com/bricks/onlinePaymentSuccess.html")) {
			//et.log(LogStatus.FAIL,"Payment Success","Order Valid");			
			Assert.fail("Payment Success");
		}
		if (messageInfo.getOriginalUrl().equals("https://deployment.magicbricks.com/bricks/onlinePaymentFailure.html")) {
			//et.log(LogStatus.PASS,"Payment Failed","Invalid Order");
			Assert.assertEquals("https://deployment.magicbricks.com/bricks/onlinePaymentFailure.html",messageInfo.getOriginalUrl());;
		}
		return null;
	}
	
	
	

	@Override
	public void filterResponse(HttpResponse response, HttpMessageContents contents, HttpMessageInfo messageInfo) {

		System.out.println("Filter response");
		if(messageInfo.getOriginalUrl().endsWith(".magicbricks.com/PGI/PaymentResponse"))
		{
			System.out.println(messageInfo.getOriginalUrl());
			String OQSIResponseCode = "9FAF1C5966E29E6468B1E3F3E91B8C88";
			String IQSIResponseCode = "5764268358208C6E77BDFBDD5BBF04CE";
			String OGATEWAYSTATUS = "AD7F2238F2091F8C8576CDB371291D31";
			String IGATEWAYSTATUS = "777918088737739B0DC49F0A8D3F9DCE";
			//et.log(LogStatus.INFO,"Replace QSIResponseCode",OQSIResponseCode);
			//et.log(LogStatus.INFO,"Replaced QSIResponseCode",IQSIResponseCode);
			
			String messageContents = contents.getTextContents().replace(OQSIResponseCode,IQSIResponseCode).replace(OGATEWAYSTATUS, IGATEWAYSTATUS);
			//String messageContents = contents.getTextContents().replace("A4DBC46D759E58C954663EA78EBC1C1F", "40B45BE800D16D9CBAE8576C8F7ADDD1");
			//et.log(LogStatus.INFO,"Replace Original GATEWAYSTATUS",OGATEWAYSTATUS);
			//et.log(LogStatus.INFO,"Replaced GATEWAYSTATUS",IGATEWAYSTATUS);
			System.out.println("messageInfo.getOriginalUrl()"+messageContents);
			contents.setTextContents(messageContents);
			//et.log(LogStatus.INFO,"Response After Modification","<img alt=\""+messageContents+"\">");
		}

	}


}
