package com.baizhi.test;

import com.baizhi.entity.Admin;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ImportReflect {
    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("f:/test.xls")));
        HSSFSheet sheet = workbook.createSheet("信息表");
        ArrayList<Admin> admins = new ArrayList<>();
        for (int i = 2; i < sheet.getLastRowNum(); i++){
            HSSFRow row = sheet.getRow(1);
            Admin admin = new Admin();
            Class<? extends Admin> aClass = admin.getClass();
            Field[] fields = aClass.getDeclaredFields();
            for(int j = 0; j < fields.length; j++){
                HSSFCell cell = row.getCell(j);
                //拿到属性名
                Field field = fields[j];
                String fieldName = field.getName();
                //拿到方法名
                String methodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                Method method = aClass.getDeclaredMethod(methodName, String.class);
                String value = cell.getStringCellValue();
                method.invoke(admin,value);
            }
        admins.add(admin);
        }
        admins.forEach(admin -> System.out.println(admin));
    }
}
