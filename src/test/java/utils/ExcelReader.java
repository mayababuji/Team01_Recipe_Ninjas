package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;

public class ExcelReader {

    private final XSSFWorkbook workbook;
    private final DataFormatter formatter = new DataFormatter();
    public ExcelReader(String resourcePath) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (input == null) {
                throw new RuntimeException("Excel file not found: " + resourcePath);
            }
            workbook = new XSSFWorkbook(input);
        } catch (Exception e) {
            throw new RuntimeException("Unable to load excel file: " + resourcePath, e);
        }
    }

    public String getCellData(String sheetName, int colNum, int rowNum) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return "";
        Row row = sheet.getRow(rowNum);
        if (row == null || row.getCell(colNum) == null) return "";
        return formatter.formatCellValue(row.getCell(colNum)).trim();
    }
}