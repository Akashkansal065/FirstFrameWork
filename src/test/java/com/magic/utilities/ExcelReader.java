package com.magic.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fos = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;

	public ExcelReader(String path)
	{
		this.path =path;
		try {
			fis = new FileInputStream(new File(path));
			workbook = new XSSFWorkbook(fis);
			//int sheetSize = workbook.getNumberOfSheets();
			//sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getRowCount(String sheetname)
	{
		if(!sheetname.equals("") || !sheetname.equals(null))
		{
			int index = workbook.getSheetIndex(sheetname);
			if(index == -1)
			{
				return 0;
			}
			else
			{
				sheet = workbook.getSheetAt(index);
				int number = sheet.getLastRowNum();
				return number;
			}
		}
		else
		{
			sheet = workbook.getSheetAt(0);
			int number = sheet.getLastRowNum();
			return number;
		}	
	}

	public int getColumnCount(String sheetname)
	{
		if(!sheetname.equals("") || !sheetname.equals(null))
		{
			int index = workbook.getSheetIndex(sheetname);
			if(index == -1)
			{
				return 0;
			}
			else
			{
				sheet = workbook.getSheetAt(index);
				int number = sheet.getRow(0).getLastCellNum();
				return number;
			}
		}
		else
		{
			sheet = workbook.getSheetAt(0);
			int number = sheet.getRow(0).getLastCellNum();
			return number;
		}	
	}

	@SuppressWarnings("deprecation")
	public String getCellData(String sheetName,int colNo,int rowNo)
	{
		int index = workbook.getSheetIndex(sheetName);
		if(index == -1)
		{
			return "Sheet Not found";
		}
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNo);
		System.out.println("row no.:- "+rowNo);
		System.out.println("colNo:- "+colNo);
		cell = row.getCell(colNo);
		if(cell == null)
		{
			System.out.println("Column not exist");
			return "No Value in column";
		}
		else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){

			String cellText  = String.valueOf(cell.getNumericCellValue());
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				// format in form of M/D/YY
				double d = cell.getNumericCellValue();

				Calendar cal =Calendar.getInstance();
				cal.setTime(HSSFDateUtil.getJavaDate(d));
				cellText =
						(String.valueOf(cal.get(Calendar.YEAR))).substring(2);
				cellText = cal.get(Calendar.MONTH)+1 + "/" +
						cal.get(Calendar.DAY_OF_MONTH) + "/" +
						cellText;
			}
			System.out.println("Date type");
			return cellText;
		}
		else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
		{
			System.out.println("No Value in Cell");
			return "";
		}
		else
		{
			System.out.println(cell.getStringCellValue());
			return cell.getStringCellValue();
		}
	}
}
