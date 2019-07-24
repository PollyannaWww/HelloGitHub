package com.baizhi.test;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TestPoi {
    public static void main(String[] args) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("新工作表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setItalic(true);
        short format = dataFormat.getFormat("yyyy-MM-dd HH:mm:ss");
        cellStyle.setDataFormat(format);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cell.setCellValue(new Date());
        cell.setCellStyle(cellStyle);
        workbook.write(new File("F:/test.xls"));
    }
}
