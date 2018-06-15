package com.magic.base;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.magic.utilities.ExcelReader;
import com.magic.utilities.ExtentTestManager;
import com.magic.utilities.GoogleSheet;
import com.relevantcodes.extentreports.LogStatus;

public class Provider{


	@DataProvider
	public Object[][] insert(Method m)
	{

		System.out.println("Data Provider Executing method name:-"+m.getName());
		Class<? extends Object> className = m.getDeclaringClass();
		/*System.out.println(className.getSimpleName());
		Method[] meth =className.getDeclaredMethods();
		for (Method method : meth) {
			System.out.println(method.getName());
		}*/
		ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"//src//test//resources//excel//"+className.getSimpleName()+".xlsx");
		String sheetname ="Sheet1";
		int rows = excel.getRowCount(sheetname);
		int column = excel.getColumnCount(sheetname);
		Map<String,List<Map<String,String>>> allData = new LinkedHashMap<>();
		
		for(int i=1;i<=rows;i++)
		{
			HashMap<String,String> map = new HashMap<>();
			for(int j=1;j<column;j++)
			{
				//System.out.println(excel.getCellData(0, j,sheetname)+" : "+excel.getCellData(i, j,sheetname));
				map.put(excel.getCellData(0, j,sheetname),excel.getCellData(i, j,sheetname));
			}
			String methodName = excel.getCellData(i, 0,sheetname);
			
			if(allData.get(methodName) == null) {
				allData.put(methodName, new ArrayList<Map<String,String>>());
			}
			allData.get(methodName).add(map);
		}

		if(!allData.containsKey(m.getName()))
		{
			Reporter.log("Test start onTestStart:- "+m.getName());
			Test test = m.getAnnotation(Test.class);
			ExtentTestManager.startTest(className.getSimpleName()+"."+m.getName().toUpperCase(),test.description());
			ExtentTestManager.getTest().assignCategory(className.getSimpleName());
			ExtentTestManager.getTest().log(LogStatus.INFO,"Test Going to be skipped",m.getName());
			throw new SkipException("Test Case Skipped as not present in "+className.getSimpleName()+".xlsx"+" Excel");
		}
		
		Object [][] arr = new Object [allData.get(m.getName()).size()][1];

		for(int i=0;i<arr.length;i++)				//No. of Rows should be -1 as starting to fetch value from Second row.
		{
			arr[i][0] = allData.get(m.getName()).get(i);
			System.out.println(i+": "+arr[i][0].toString());
		}
		return arr;
	}

	@DataProvider
	public String[][] gInsert()
	{
		String spreadsheetId="14-7PNS2RzGrdvvx5VMBvRlZoyX_oHJ_JJtiPidrgbEs";
		String range="'Sprint 20'";
		GoogleSheet sheetAPI = new GoogleSheet();
		List<List<Object>> values = null;
		try {
			values = sheetAPI.getSpreadSheetRecords(spreadsheetId, range);
		} catch (IOException e) {e.printStackTrace();}

		String arr[][] = new String[1][];
		for(int i=0;i<1;i++)
		{
			arr[i] = new String[3];
			for(int j=0;j<3;j++)
			{
				System.out.print(values.get(i).get(j).toString()+",");
				arr[i][j]= values.get(i).get(j).toString().trim();
			}
		}
		return arr;
	}
}
