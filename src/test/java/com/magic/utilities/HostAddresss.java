package com.magic.utilities;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostAddresss {
	
	public static void main(String args[]) throws UnknownHostException
	{
		String ReportUrL = "http://"+InetAddress.getLocalHost().getHostAddress()+":8080/job/FirstFrame/HTML_20Report/";
		System.out.println(ReportUrL);
	}

}
