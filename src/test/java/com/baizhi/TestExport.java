package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestExport {
    @Autowired
    private AdminDao adminDao;
@Test
    public void test1() throws IOException {
        List<Admin> admins = adminDao.selectAll();
    HSSFWorkbook sheets = new HSSFWorkbook();
    HSSFSheet sheet = sheets.createSheet("管理员信息表");
    //第一行
    HSSFRow row = sheet.createRow(0);
    HSSFCell cell = row.createCell(0);
    cell.setCellValue("管理员详细信息");
    //合并
    CellRangeAddress rangeAddress = new CellRangeAddress(0, 0, 0, 3);
    sheet.addMergedRegion(rangeAddress);
    //居中
    HSSFCellStyle cellStyle = sheets.createCellStyle();
    cellStyle.setAlignment(HorizontalAlignment.CENTER);
    cell.setCellStyle(cellStyle);

    //设置日期格式
    HSSFDataFormat dataFormat = sheets.createDataFormat();
    short format = dataFormat.getFormat("yyyy-MM-dd HH:mm:ss");
    cellStyle.setDataFormat(format);
    //第二行 表头
    HSSFRow row1 = sheet.createRow(1);
    String[] arr = {"编号","昵称","密码","真实姓名"};
    for(int i = 0; i < arr.length; i++){
        HSSFCell cell1 = row1.createCell(i);
        cell1.setCellValue(arr[i]);
    }
    //导出用户数据 内容
    for (int i = 0; i <admins.size(); i++){
        Admin admin = admins.get(i);
        HSSFRow row2 = sheet.createRow(2 + i);

        HSSFCell cell1 = row2.createCell(0);
        cell1.setCellValue(admin.getId());

        HSSFCell cell2 = row2.createCell(1);
        cell2.setCellValue(admin.getAdminNickname());

        HSSFCell cell3 = row2.createCell(2);
        cell3.setCellValue(admin.getAdminPassword());

        HSSFCell cell4 = row2.createCell(3);
        cell4.setCellValue(admin.getAdminRealname());
        cell4.setCellStyle(cellStyle);
    }
    sheets.write(new File("F:/test.xls"));
}
}
