package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AdminTest {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private AdminService adminService;

    @Test
    public void test1(){
        List<Admin> admins = adminDao.selectAll();
        admins.forEach(admin -> System.out.println(admin));
    }
    @Test
    public void test2(){
        Admin admin = new Admin();
        admin.setId("1");
        Admin admin1 = adminDao.selectOne(admin);
        System.out.println(admin1);
    }
    @Test
    public void test3(){
        Example example = new Example(Admin.class);
        example.createCriteria().andEqualTo("adminNickname","admin").andEqualTo("adminPassword","123456");
        Admin admin1 = adminDao.selectOneByExample(example);
        System.out.println(admin1);
    }
}
