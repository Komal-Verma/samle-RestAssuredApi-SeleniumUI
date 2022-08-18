package com.sample.lib;

import java.io.FileInputStream;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	public static List<LinkedHashMap<String, String>> readExcelHashMap(String excelPath, String excelSheetName, String conditionCol,
			String conditionColValue) {
		String headerKey[] = new String[0];
		String value[] = new String[0];
		LinkedHashMap<String, String> rowData;
		List<LinkedHashMap<String, String>> dataList = new ArrayList<>();
		try {

			Workbook wb = null;

			FileInputStream fis = new FileInputStream(excelPath);

			if (excelPath.contains(".xlsx"))
				wb = new XSSFWorkbook(fis);
			else
				wb = new HSSFWorkbook(fis);

			Sheet sheet = wb.getSheet(excelSheetName);

			Iterator<Row> rowIterator = sheet.iterator();

			DataFormatter formatter = new DataFormatter(Locale.US);

			while (rowIterator.hasNext()) {
				Boolean headerRow = false;
				value = new String[headerKey.length];
				Row row = rowIterator.next();

				if (row.getRowNum() == 0)
					headerRow = true;
				
				
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (headerRow && (cell.getCellType() != Cell.CELL_TYPE_BLANK)) {
						headerKey = Arrays.copyOf(headerKey, headerKey.length + 1);
						headerKey[cell.getColumnIndex()] = formatter.formatCellValue(cell);
					} else if (!headerRow && (headerKey[cell.getColumnIndex()] != null)) {
						if (cell.getCellType() != Cell.CELL_TYPE_BLANK)
							value[cell.getColumnIndex()] = formatter.formatCellValue(cell);
						else
							value[cell.getColumnIndex()] = null;
					}
				}

				if (headerRow && !Arrays.asList(headerKey).contains(conditionCol)) {
					wb.close();
					throw new InvalidParameterException(
							"Condition coulumn: " + conditionCol + "no found in sheet: " + excelSheetName);

				}
				if ((headerKey.length != 0) && (value.length != 0)) {
					rowData = new LinkedHashMap<String, String>();
					for (int i = 0; i < headerKey.length; i++) {
						if (i < value.length)
							rowData.put(headerKey[i], value[i]);
						else {
							rowData.put(headerKey[i], null);
						}
					}
					if ((rowData.get(conditionCol) != null)
							&& (rowData.get(conditionCol).trim().equalsIgnoreCase(conditionColValue.trim()))) {
						dataList.add(rowData);
					}
				}
			}
			wb.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

}
