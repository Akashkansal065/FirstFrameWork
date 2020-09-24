package com.magic.RestUtils;

public class URLs {

	String WebDeployment ="https://deployment.magicbricks.com/";
	String WebLive ="https://www.magicbricks.com/";
	String WebStaging ="https://staging.magicbricks.com/";
	String WapDeployment ="https://mstg.magicbricks.com/";
	String WapLive ="https://m.magicbricks.com/";
	String WapStagingMbs ="https://mstg.magicbricks.com/mbs";
	String ApiDeployment ="https://apistg.magicbricks.com/";
	String ApiLive ="https://api.magicbricks.com/";

	public String Url(String type)
	{
		switch(type){

		case "WebDeployment":
			return WebDeployment;
		case "WebLive":
			return WebLive;
		case "WebStaging":
			return WebStaging;
		case "WapDeployment":
			return WapDeployment;
		case "WapLive":
			return WapLive;
		case "WapStagingMbs":
			return WapStagingMbs;
		case "ApiDeployment":
			return ApiDeployment;
		case "ApiLive":
			return ApiLive;
		default:
			return WebDeployment;
		}
	}
}
