package com.magic.base;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Provider extends Base{

	@DataProvider
	public static Object[][] insert()
	{
		String sheetname ="Sheet1";
		int rows = excel.getRowCount(sheetname);
		int column = excel.getColumnCount(sheetname);
		System.out.println("Row Count:-"+rows);
		System.out.println("Column Count:-"+column);

		Object [][] arr = new String [rows][column];
		System.out.println(arr.length);
		for(int i=1;i<=arr.length;i++)
		{
			for(int j=0;j<column;j++)
			{
				arr[i-1][j]=excel.getCellData(sheetname, j, i);
			}
		}
		return arr;
	}

	@DataProvider
	public static String[][] gInsert()
	{
		try {values = sheetAPI.getSpreadSheetRecords(spreadsheetId, range);} catch (IOException e) {e.printStackTrace();}

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
