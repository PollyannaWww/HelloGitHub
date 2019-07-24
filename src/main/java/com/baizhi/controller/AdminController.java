package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.SecurityCode;
import com.baizhi.util.SecurityImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes(value = {"securityCode"})
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(String adminNickname, String adminPassword, String code, HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<>();
        try{
            adminService.selectByNickenameAndPassword(adminNickname,adminPassword,code,request);
            map.put("status",true);
        }catch (Exception e){
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }

    @RequestMapping("/captcha")
    public String captcha(Model model, HttpServletResponse response){
        //获取验证码随机数，将验证码随机数存入session作用域，以便后续作验证
        String securityCode = SecurityCode.getSecurityCode();
        model.addAttribute("securityCode",securityCode);
        //获取验证码图片
        BufferedImage image = SecurityImage.createImage(securityCode);
        //将验证码图片响应到客户端
        try {
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image,"png",out);
        }catch (Exception e){
            e.printStackTrace();
        }
        //返回null，不做跳转
        return null;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("nickname");
        return "redirect:/login.jsp";
    }

}
