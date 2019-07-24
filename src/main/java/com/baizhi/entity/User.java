package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.NameStyle;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@NameStyle
@Table(name = "c_user")
public class User {
    @Id
    @Excel(name = "编号")
    private String id;
    @Excel(name = "手机号码")
    private String userMobile;
    @Excel(name = "密码")
    private String userPassword;
    @Excel(name = "用户名")
    private String userName;
    @Excel(name = "头像",type = 2)
    private String userPhoto;
    @Excel(name = "真实姓名")
    private String userRealname;
    @Excel(name = "性别")
    private String userSex;
    @Excel(name = "省份")
    private String userProvince;
    @Excel(name = "城市")
    private String userCity;
    @Excel(name = "用户状态")
    private String userStatus;
    @Excel(name = "用户简介")
    private String userDescription;
    @Excel(name = "注册时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date userRegistDate;
    @Excel(name = "上师编号")
    private String masterId;
}
