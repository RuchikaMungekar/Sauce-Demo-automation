package utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	    public static List<Map<String, String>> readExcelData(String filePath, String sheetName) {
	        List<Map<String, String>> testData = new ArrayList<>();
	        try (FileInputStream fis = new FileInputStream(filePath);
	             Workbook workbook = new XSSFWorkbook(fis)) {

	            Sheet sheet = workbook.getSheet(sheetName);
	            Row headerRow = sheet.getRow(0);

	            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	            	
	                Row row = sheet.getRow(i);
	                Map<String, String> dataMap = new HashMap<>();
	                for (int j = 0; j < row.getLastCellNum(); j++) {
	                	String value = "";
		            	Cell cell = row.getCell(j);
		            	System.out.println("cell"+ cell);
		            	String key = headerRow.getCell(j).getStringCellValue();
		            	System.out.println("key"+key);
		            	if (cell != null) {
		            	    value = cell.getStringCellValue();
		            	}
		            	dataMap.put(key, value);
//	                    value = row.getCell(j).getStringCellValue();
	                }
	                testData.add(dataMap);
	                System.out.println("datamap"+dataMap);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return testData;
	    }
	}
