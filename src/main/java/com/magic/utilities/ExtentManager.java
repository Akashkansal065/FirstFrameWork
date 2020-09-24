package com.magic.utilities;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	static Date date =new Date();
	public static ExtentReports extent;
	public static String filename = "ExtentReport"+date.toString().replace(':','_').replace(' ','_')+".html";
	public static String reportPath= "./Reports/extent_reports/"+ (date.getMonth()+1) +"/"+date.getDate()+"/"+filename;
	
	public static ExtentReports getInstance()
	{
		if(extent == null)
		{
			extent = new ExtentReports(reportPath,true);
		}
		return extent;
		
	}
	public static void move()
	{
		File source = new File(reportPath);
	    File dest = new File("./Reports/");
	    File dest2 = new File("./");
	   // File fileToDelete = new File("./reports/*.html");
	    File[] fileToDelete = dest.listFiles(new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return name.toLowerCase().endsWith(".html");
	        }
	    }); 
	    
	    for(File f:fileToDelete){
	    		f.delete();
	    }
	    File[] fileToDelete2 = dest2.listFiles(new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return name.toLowerCase().endsWith(".html");
	        }
	    }); 
	    
	    for(File f:fileToDelete2){
	    		f.delete();
	    }
	    
	    
	    try {
	        FileUtils.copyFileToDirectory(source, dest);
	        FileUtils.copyFileToDirectory(source, dest2);
	        File oldfile =new File("./"+filename);
			File newfile =new File("./Report.html");
			
			if(oldfile.renameTo(newfile)){
				System.out.println("Rename succesful");
			}else{
				System.out.println("Rename failed");
			}
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	
}
