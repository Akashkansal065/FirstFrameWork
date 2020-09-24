package com.magic.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;

public class JsonParse {

	public static String GenerateResourceString(String path) throws IOException
	{
		return new String(Files.readAllBytes(Paths.get(path)));
	}

	public static void main(String[] args) throws IOException {

		String jsa = null;
		try {
			jsa = GenerateResourceString("D://cmp_json1.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*String url = "http://192.168.207.112/MagicBricks/InsiderClub/select?q=*:*&fq=id:3366397&wt=json";
		LinkedHashMap<String,Object> map = new LinkedHashMap();

		map = JsonPath.read(new URL(url),"$");
		for (String string : map.keySet())
		{
			//System.out.println(string+":"+map.get(string));
		}*/
		//System.out.println(map);
		List<String> obj = JsonPath.read(jsa,"$."+"listOfEntities[0].hierarchyChildren.listCmsEntities");
		System.out.println(obj.size());
		List<Object> obj2 = new LinkedList<>();
		for(int i=0;i<obj.size();i++)
		{
			 obj2.add(JsonPath.read(jsa,"$."+"listOfEntities[0].hierarchyChildren.listCmsEntities["+i+"].msId"));
			//System.out.println(obj2);
		}
		for(int i=0;i<obj.size();i++)
		{
			System.out.println(obj2.get(i));
		}
		List<Map<Object, Object>> map =JsonPath.read(jsa,"$."+"listOfEntities[0].hierarchyChildren.listCmsEntities");
		for(int i=0;i<obj.size();i++)
		{
			System.out.println(map.get(i));
		}
		/*for(int i=0;i<obj.size();i++)
		{
			System.out.println(obj2.get(i));
		}*/
	}
}