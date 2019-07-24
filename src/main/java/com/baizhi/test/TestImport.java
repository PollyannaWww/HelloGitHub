package com.baizhi.test;

import com.baizhi.entity.Admin;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TestImport {
    public static void main(String[] args) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("F:/test.xls")));
        HSSFSheet sheet = workbook.createSheet("用户信息表");
        ArrayList<Admin> list = new ArrayList<>();
        for(int i = 2; i < sheet.getLastRowNum(); i++){
            HSSFRow row = sheet.getRow(i);
            Admin admin = new Admin();
            HSSFCell cell = row.getCell(0);
            String s = cell.getStringCellValue();
            admin.setId(s);
            HSSFCell cell1 = row.getCell(1);
            String s1 = cell1.getStringCellValue();
            admin.setAdminNickname(s1);
        }
    }
}
