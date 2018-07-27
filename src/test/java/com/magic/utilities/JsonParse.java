package com.magic.utilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;

import com.jayway.jsonpath.JsonPath;

public class JsonParse {

	public static void main(String[] args) throws IOException {

		String url = "http://192.168.207.112/MagicBricks/InsiderClub/select?q=*:*&fq=id:3366397&wt=json";
		LinkedHashMap<Object,Object> map = new LinkedHashMap();

		map = JsonPath.read(new URL(url),"$."+"response.docs[0]");
		System.out.println(map);
		Object obj = JsonPath.read(new URL(url),"$."+"response.docs[0]");
		//System.out.println(obj);
	}

}
