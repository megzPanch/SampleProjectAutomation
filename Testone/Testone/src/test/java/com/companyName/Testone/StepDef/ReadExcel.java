package com.companyName.Testone.StepDef;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.text.DateFormatter;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	private static ThreadLocal<XSSFWorkbook> xssfWorkbook = new ThreadLocal<>();
	private static DataFormatter dataFormatter = new DataFormatter();
	private static ThreadLocal<XSSFSheet> xssfSheet = new ThreadLocal<>();
	private static Logger logger = Logger.getLogger(ReadExcel.class.getName());
	private static ThreadLocal<XSSFSheet> hssfSheet = new ThreadLocal<>();
	private static ThreadLocal<InputStream> fis = new ThreadLocal<>();

	private static void setup(String fileName, String sheetName) throws IOException {
		InputStream inputStreamXls = ReadExcel.class.getResourceAsStream("/Data/" + fileName + ".xls");
		InputStream inputStreamXlsx = ReadExcel.class.getResourceAsStream("/Data/" + fileName + ".xlsx");
		if (inputStreamXls != null) {
			fis.set(inputStreamXls);
		} else if (inputStreamXlsx != null) {
			fis.set(inputStreamXlsx);
		} else {
			throw new IOException("ExcelDetails annotation may be missing or excel file/sheet doesn't exists.");
		}
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fis.get());
		xssfSheet.set(xssfWorkbook.getSheet(sheetName));
		xssfWorkbook.close();
	}

	public static Object[][] readData(String[] excelInfo) {
		String excelName = excelInfo[0];
		String sheetName = excelInfo[1];
		List<Object[]> results = new ArrayList();
		try {
			setup(excelName, sheetName);
			int numRows = ((XSSFSheet) xssfSheet.get()).getLastRowNum();
			for (int i = 1; i <= numRows; i++) {
				Map<String, String> inputValues = getHashMapDataFromRow((Sheet) xssfSheet.get(), i);
				results.add(new Object[] { inputValues });
			}
		} catch (IOException e) {
			logger.warning(e.getMessage());
		} finally {
			IOUtils.closeQuietly(fis.get());
		}
		return results.<Object[]>toArray(new Object[0][]);
	}

	public static List<HashMap<String, String>> readData(String excelName, String sheetName) {
		System.out.println("Before Read data: " + Thread.currentThread().getId());
		List<HashMap<String, String>> excelData = new ArrayList<>();
		try {
			setup(excelName, sheetName);
			int numRows = ((XSSFSheet) xssfSheet.get()).getLastRowNum();
			for (int i = 1; i <= numRows; i++) {
				HashMap<String, String> inputvalues = getHashMapDataFromRow((Sheet) xssfSheet.get(), i);
				excelData.add(inputvalues);
			}
		} catch (IOException e) {
			logger.warning(e.getMessage());
		} finally {
			IOUtils.closeQuietly(fis.get());
			return excelData;
		}
	}

	private static HashMap<String, String> getHashMapDataFromRow(Sheet sheet, int rowIndex) {
		HashMap<String, String> results = new HashMap<>();
		String[] columnHeaders = getDataFromRow(sheet, 0);
		String[] valuesFromRow = getDataFromRow(sheet, rowIndex);
		for (int i = 0; i < columnHeaders.length; i++) {
			if (i >= valuesFromRow.length) {
				results.put(columnHeaders[i], "");
			} else {
				results.put(columnHeaders[i], valuesFromRow[i]);
			}
		}
		return results;
	}

	private static String[] getDataFromRow(Sheet sheet, int rowIndex) {
		FormulaEvaluator formulaEvaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
		Row row = sheet.getRow(rowIndex);
		short numCells = row.getLastCellNum();
		String[] result = new String[numCells];
		for (int i = 0; i < numCells; i++) {
			result[i] = getValueAsString(row.getCell(i), formulaEvaluator);
		}
		return result;
	}

	private static String getValueAsString(Cell cell, FormulaEvaluator formulaEvaluator) {
		if (cell != null) {
			CellType cellType = cell.getCellType();
			if (cellType.equals(CellType.BOOLEAN))
				return String.valueOf(cell.getBooleanCellValue());
			if (cellType.equals(CellType.NUMERIC))
				return dataFormatter.formatCellValue(cell);
			if (cellType.equals(CellType.STRING))
				return cell.getRichStringCellValue().getString();
			if (cellType.equals(CellType.FORMULA))
				return formulaEvaluator.evaluate(cell).getStringValue();
		}
		return "";
	}

	public static Object[][] getData(String excelName, String sheetName) {
		List<Object[]> results = new ArrayList();
		try {
			setup(excelName, sheetName);
			int numRows = ((XSSFSheet) xssfSheet.get()).getLastRowNum();
			for (int i = 1; i <= numRows; i++) {
				Map<String, String> inputValues = getMapDataFromRow((Sheet) xssfSheet.get(), i);
				results.add(new Object[] { inputValues });
			}
		} catch (IOException e) {
			logger.warning(e.getMessage());
		} finally {
			IOUtils.closeQuietly(fis.get());
		}
		return results.<Object[]>toArray(new Object[0][]);
	}

	public static int getRowCount(File file, String sheetName) {
		int numRows = 0;
		try {
			if (file.exists()) {
				fis.set(new FileInputStream(file));
				xssfWorkbook.set(new XSSFWorkbook(fis.get()));
				xssfSheet.set(((XSSFWorkbook) xssfWorkbook.get()).getSheet(sheetName));
				numRows = ((XSSFSheet) xssfSheet.get()).getLastRowNum();
				System.out.println("Number of Rows in the excel sheet" + file.getName() + "is" + numRows);
				((XSSFWorkbook) xssfWorkbook.get()).close();
			}
		} catch (

		Exception e) {
			logger.warning(e.getMessage());
		}
		return numRows;
	}

	private static Map<String, String> getMapDataFromRow(Sheet sheet, int rowIndex) {
		Map<String, String> results = new LinkedHashMap<>();
		String[] columnHeaders=	getDataFromRow(sheet, 0);
		String[] valuesFromRow= getDataFromRow(sheet, rowIndex);
		for (int i = 0; i < columnHeaders.length; i++) {
		if (i >= valuesFromRow.length) {
		results.put(columnHeaders[i],"");
		} else {
		results.put(columnHeaders[i],valuesFromRow[i]);
		}
		}
		return results;
		
	}
}