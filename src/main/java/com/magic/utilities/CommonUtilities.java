package com.magic.utilities;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CommonUtilities {

	public static String decode(String value) {

		value = value.replace("%%", "%");
		value = value.replace("%=", "=");
		value = value.replace("%>", ">");
		value = value.replace("<%", "<");
		String decod = null;
		try {
			decod = URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decod;
	}

	public static String decodes(String value)  
	{  
		value = value.replace("%%", "%");
		value = value.replace("%=", "=");
		value = value.replace("%>", ">");
		value = value.replace("<%", "<");
		try {  
			String prevURL="";  
			String decodeURL=value;  
			while(!prevURL.equals(decodeURL))  
			{  
				prevURL=decodeURL; 
				// decodeURL=URLDecoder.decode( decodeURL, "UTF-8" ); 
				decodeURL=URLDecoder.decode( decodeURL, StandardCharsets.UTF_8.toString() ); 
			}  
			return decodeURL;  
		} catch (UnsupportedEncodingException e) {  
			return "Issue while decoding" +e.getMessage();  
		}  
	} 
	
	public static String normalDecodes(String value)  
	{  
		try {  
			String prevURL="";  
			String decodeURL=value;  
			while(!prevURL.equals(decodeURL))  
			{  
				prevURL=decodeURL; 
				// decodeURL=URLDecoder.decode( decodeURL, "UTF-8" ); 
				decodeURL=URLDecoder.decode( decodeURL, StandardCharsets.UTF_8.toString() ); 
			}  
			return decodeURL;  
		} catch (UnsupportedEncodingException e) {  
			return "Issue while decoding" +e.getMessage();  
		}  
	} 
	public static String encode(String value) {

		String encod = null;
		try {
			encod = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encod;
	}
}