package utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public static List<Map<String, String>> readExcelData(String filePath, String sheetName) {
        List<Map<String, String>> testData = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);

            if (headerRow == null) {
                throw new RuntimeException("Header row is missing in sheet: " + sheetName);
            }

            int lastRowNum = sheet.getLastRowNum();
            int lastCellNum = headerRow.getLastCellNum();

            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                boolean isEmptyRow = true;
                Map<String, String> dataMap = new HashMap<>();

                for (int j = 0; j < lastCellNum; j++) {
                    Cell headerCell = headerRow.getCell(j);
                    String key = (headerCell == null) ? "Column" + j : headerCell.getStringCellValue();

                    Cell cell = row.getCell(j);
                    String value = (cell == null) ? "" : formatter.formatCellValue(cell);

                    dataMap.put(key, value);

                    if (!value.trim().isEmpty()) {
                        isEmptyRow = false;
                    }
                }

                if (!isEmptyRow) {
                    testData.add(dataMap);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return testData;
    }
}
