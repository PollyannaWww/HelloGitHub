package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.NameStyle;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "c_admin")
@NameStyle
public class Admin {
    @Id
    private String id;
    private String adminNickname;
    private String adminPassword;
    private String adminRealname;

}

