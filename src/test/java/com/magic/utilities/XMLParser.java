package com.magic.utilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XMLParser {

	public Document doc;
	public String data;
	
	public XMLParser(String url)
	{
		SAXReader read = new SAXReader();
		try {
			this.doc = read.read(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}
	public String getLocator(String url) throws Exception
	{
		data = doc.selectSingleNode(url).getText();
		return data;
	}
	public String getLocator() throws Exception
	{
		data = doc.asXML();
		return data;
	}
	
	public static void main(String args[]) throws Exception
	{
		XMLParser parse = new XMLParser("http://192.168.207.112/MagicBricks/InsiderClub/select?q=*:*&fq=id:3366397");
		System.out.println(parse.getLocator());
		//System.out.println(parse.getLocator("suite/listeners/listener[1]"));
	}
}
