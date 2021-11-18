package com.magic.seleniumUtils;

import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

public class CommonGaUtilities {

	//	public List<LinkedHashMap<String,String>> gaUrlsDataExtrations(List<String> lss)
	//	{
	//		List<LinkedHashMap<String, String>> list = new LinkedList<>();
	//		LinkedHashMap<String, String> map = null;
	//
	//		for(int i=0;i<lss.size();i++)
	//		{
	//			String[] first =lss.get(i).toString().split("&");
	//			map = new LinkedHashMap<>();
	//			for(int k=0;k<first.length;k++)
	//			{
	//				String[] second = first[k].split("=",2);
	//				if(second.length < 2)
	//				{
	//					//System.out.println(second[0]+":-null");
	//					map.put(second[0],"");
	//				}
	//				else {
	//					//System.out.println(second[0]+":-"+second[1]);
	//					map.put(second[0],second[1]);
	//				}
	//			}
	//			list.add(map);
	//		}
	//		for(int i=0;i<list.size();i++)
	//		{
	//			Map<String,String> pops = list.get(i);
	//			for (String string : pops.keySet()) {
	//				System.out.print(string+":-"+pops.get(string));
	//			}
	//			System.out.println("");
	//		}
	//		return list;
	//
	//	}

	public List<LinkedHashMap<String,String>> gaUrlsDataExtrationsNew(List<String> lss)
	{
		List<LinkedHashMap<String, String>> list = new LinkedList<>();
		LinkedHashMap<String, String> map = null;
		List<NameValuePair> queryParams = null;

		for(int i=0;i<lss.size();i++)
		{
			try {
				queryParams = new URIBuilder(urls(lss.get(i).toString())).getQueryParams();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			map = new LinkedHashMap<>();
			for (NameValuePair nameValuePair : queryParams) {
				System.out.println(nameValuePair.getName()+":"+nameValuePair.getValue());
				map.put(nameValuePair.getName(),nameValuePair.getValue());
			}
			list.add(map);
		}
		//		for(int i=0;i<list.size();i++)
		//		{
		//			Map<String,String> pops = list.get(i);
		//			for (Map.Entry<String, String> string : pops.entrySet()) {
		//				//System.out.println(string.getKey()+":->"+string.getValue());
		//			}
		//			//System.out.println("");
		//		}
		return list;
	}

	public String urls(String Url)
	{
		String cutOut = check(Url);
		String spitOut = null;
		if(cutOut == null || cutOut == "")
		{}
		else {
			spitOut = cutOutReplace(cutOut);
			Url = Url.replace(cutOut, spitOut);
		}
		String data = null;
		
		data = Url.replace("&%20", "%26%20");
		data = data.replace("& ", "%26%20");
		data = data.replace("%%", "%");
		data = data.replace("%=", "=");
		data = data.replace("%>", ">");
		data = data.replace("<%", "<");
		data = data.replace(" &el", "&el");
		//data = data.replace("%25", "%");
		data = data.replace("+", "%2b");
		data = data.replace(" &", "%20%26");
		data = data.replace("%20&", "%20%26");
		data = data.replace(" ", "%20").replace("|", "%7c");
		data = data.replace(">", "%3E");
		data = data.replace("<", "%3c");
		//System.out.println(data);
		return data;
	}
	public String check(String s)
	{
		Pattern p = Pattern.compile("&dt=(.*?)&sd");
		Matcher m = p.matcher(s); 
		String ss = null;
		if(m.find())
		{
			ss = m.group(0);
			//System.out.println(ss);
		}
		return ss;
	}

	public String cutOutReplace(String str)
	{
		String dats = null;
		try {
			dats = str.replace("&", "%26");
			dats = dats.replace("%26dt", "&dt");
			dats = dats.replace("%26sd", "&sd");
		}catch(NullPointerException e) {dats = null;}

		//System.out.println(dats);
		return dats;
	}
}
