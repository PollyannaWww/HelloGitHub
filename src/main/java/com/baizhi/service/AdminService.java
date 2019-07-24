package com.baizhi.service;

import com.baizhi.entity.Admin;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
public interface AdminService {
    Admin selectByNickenameAndPassword(String adminNickname, String adminPassword, String code, HttpServletRequest request);
}
