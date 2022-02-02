package utilities;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelDataFile {

	public static void setDataFile(Map<Integer, Object[]> itemMap) {
		try {
			// Create a Workbook
			XSSFWorkbook workbook = new XSSFWorkbook();

			// Create a Sheet
			XSSFSheet sheet = workbook.createSheet("Items");

			// Create a Font for styling header cells
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.SKY_BLUE.getIndex());

			// Create a cellStyle with the font
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Create cells with Item brand, name and price in the excel sheet
			int rowNumber = 0;
			int columnSize = 0;
			for (Integer key : itemMap.keySet()) {
				Row row = sheet.createRow(rowNumber++);
				Object[] data = itemMap.get(key);
				columnSize = data.length;
				int cellNumber = 0;
				for (Object value : data) {
					Cell cell = row.createCell(cellNumber++);
					cell.setCellValue((String) value);
					if (cellNumber <= 4 && rowNumber == 1)
						cell.setCellStyle(headerCellStyle); // Apply the cell style
				}
			}

			// Resize all columns to fit the content size
			for (int i = 0; i < columnSize; i++) {
				sheet.autoSizeColumn(i);
			}

			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(
					System.getProperty("user.dir") + "\\stored-values\\result.xlsx");
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

