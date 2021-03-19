package com.royalcarribbean.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFile {

	// Storing the Validation Messages in the Excel Sheet
	public static void createReport(ArrayList<String> reportMessagesList) {

		try {

			// Creating the workbook
			XSSFWorkbook workbook = new XSSFWorkbook();

			// Create first/desired sheet from the workbook
			XSSFSheet sheet = workbook.createSheet("Test Report");
			int listSize = reportMessagesList.size();
			for (int i = 0; i < listSize; i++) {

				// creating the Row
				Row r = sheet.createRow(i);

				// creating the Cell
				r.createCell(0).setCellValue(reportMessagesList.get(i));
			}
			// Creating the Excel File
			FileOutputStream outFile = new FileOutputStream(System.getProperty("user.dir") + "\\ExcelReport\\"
					+ "ExcelReport_" + System.currentTimeMillis() + ".xlsx");
			// Writing in the Excel File
			workbook.write(outFile);
			workbook.close(); // closing the workbook
			outFile.close(); // closing the file
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
