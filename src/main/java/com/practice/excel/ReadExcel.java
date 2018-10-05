package com.practice.excel;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.util.Constants;

public class ReadExcel {

	public Object[][] getExcelData() {

		try {

			Object[][] dataSets = null;
			FileInputStream FIS = new FileInputStream(Constants.EXCELDATAFILEPATH);
			XSSFWorkbook workbook = new XSSFWorkbook(FIS);
			XSSFSheet sheet = workbook.getSheetAt(0);

			int rowCount = sheet.getLastRowNum();
			int colcount = sheet.getRow(0).getLastCellNum();

			System.out.println("Row Count: " + rowCount);
			System.out.println("Cell count: " + colcount);

			dataSets = new Object[rowCount][colcount];

			Iterator<Row> rowIterator = sheet.rowIterator();

			int i = 0;
			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int j = 1;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					switch (cell.getCellTypeEnum()) {

					case STRING:
						System.out.println(i + " " + j);
						dataSets[i][j] = cell.getStringCellValue();
						System.out.println(cell.getStringCellValue());
						break;
					case NUMERIC:
						dataSets[i][j] = cell.getNumericCellValue();
					case BOOLEAN:
						dataSets[i][j] = cell.getBooleanCellValue();
						break;
					case BLANK:
						dataSets[i][j] = "";
						break;
					case ERROR:
						break;
					case FORMULA:
						break;
					case _NONE:
						break;
					default:
						System.out.println("No values found");
						break;
					}
					j++;
				}
				i++;
			}
			return dataSets;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object[][] excelData() {
		XSSFWorkbook workBook = null;
		
		try {
			FileInputStream FIS = new FileInputStream(Constants.EXCELDATAFILEPATH);
			workBook = new XSSFWorkbook(FIS);
			Sheet sheet = workBook.getSheetAt(0);
			int rowCount = sheet.getLastRowNum();
			int colCount = sheet.getRow(0).getLastCellNum();

			Object[][] data = null;
			data = new Object[rowCount][colCount];

			for (int i = 0; i <rowCount; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < colCount; j++) {
					//Cell cell = row.getCell(j);
					Cell cell = sheet.getRow(i+1).getCell(j);
					switch (cell.getCellTypeEnum()) {
					case STRING:
						System.out.print(cell.getStringCellValue());
						data[i][j] = cell.getStringCellValue();
						break;
					case NUMERIC:
						System.out.print(cell.getNumericCellValue());
						break;
					case BLANK:
						System.out.print(" ");
						break;
					case BOOLEAN:
						System.out.print(cell.getBooleanCellValue());
						break;
					default:
						System.out.print(" ");
						break;
					}
					System.out.println(" ");
				}
			}

			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

/*	@DataProvider(name = "testdata")
	public Object[][] getdata() {
		Object[][] data = ReadExcel.excelData();
		return data;
	}

	@Test(dataProvider = "testdata")
	public void test(String str1, String str2) {
		System.out.println("String 1: " + str1);
		System.out.println("String 2: " + str2);
	}*/

}
