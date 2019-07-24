package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin selectByNickenameAndPassword(String adminNickname, String adminPassword, String code, HttpServletRequest request) {

        Admin admin = new Admin();
        admin.setAdminNickname(adminNickname);
        Admin admin1 = adminDao.selectOne(admin);
        Object code1 = request.getSession().getAttribute("securityCode");
        if(code.equals(code1)){//验证验证码
            if(admin1 != null){//验证用户名
                if(admin1.getAdminPassword().equals(adminPassword)){//验证密码
                    request.getSession().setAttribute("admin",admin1);
                    return admin1;
                }else {
                    throw new RuntimeException("密码错误!");
                }
            }else {
                throw new RuntimeException("用户不存在！");
            }
        }else {
            throw new RuntimeException("验证码错误！");
        }
    }
}
