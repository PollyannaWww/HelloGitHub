package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baizhi.entity.User;
import com.baizhi.entity.UserCount;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
//展示所有用户
    @RequestMapping("showAllUsers")
    public Map<String,Object> showAllUsers(Integer page,Integer rows,String sidx,String sord){
        Map<String, Object> map = new HashMap<>();
        map = userService.findAllUsers(page,rows,sidx,sord);
        return map;
    }
    //用户信息表导出
    @RequestMapping("exportUserFile")
    public void exportUserFile(HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<User> users = userService.findAll();
        for (int i = 0;i < users.size();i++) {
            User user = users.get(i);
            String photo = user.getUserPhoto();
            String newPhoto = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/user/images/"+photo;
            user.setUserPhoto(newPhoto);
            users.set(i,user);
        }
        String s = "用户信息表（" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xls";
        s = new String(s.getBytes("GBK"),"ISO-8859-1");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition","attachment;filename="+s);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户表", "用户表"),User.class, users);
        workbook.write(response.getOutputStream());
    }
//用户信息导入
    @RequestMapping("importUserFile")
    public void importUserFile() throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        ExcelImportUtil.importExcel(new FileInputStream(new File("/user/")),User.class,params);
    }
    //用户图表展示
    @RequestMapping("showUserChart")
    public Map<String, Object> showUserChart(){
        Map<String, Object> map = new HashMap<>();
        List<UserCount> male = userService.findByConditions("男");
        List<UserCount> female = userService.findByConditions("女");
        ArrayList<UserCount> list1 = new ArrayList<>();
        for (UserCount m : male) {
            list1.add(m);
        }
        map.put("male",list1);
        ArrayList<UserCount> list2 = new ArrayList<>();
        for (UserCount f : female) {
            list2.add(f);
        }
        map.put("female",list2);
        return map;
    }

}
