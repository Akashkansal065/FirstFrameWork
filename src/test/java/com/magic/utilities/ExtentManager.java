package com.magic.utilities;

import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	static Date date =new Date();
	public static ExtentReports extent;
	public static String filename = ""+date.getTime()+".html";
	public static String reportPath= "./Reports/extent_reports/"+ (date.getMonth()+1) +"/"+date.getDate()+"/"+filename;
	
	public static ExtentReports getInstance()
	{
		if(extent == null)
		{
			extent = new ExtentReports(reportPath,true);
		}
		return extent;
		
	}
	
}
