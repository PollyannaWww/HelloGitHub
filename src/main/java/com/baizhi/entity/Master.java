package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.security.provider.certpath.PKIXTimestampParameters;
import tk.mybatis.mapper.annotation.NameStyle;

import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "c_master")
@NameStyle
public class Master {
    @Id
    private String id;
    private String masterName;
    private String masterPhoto;
    private String masterStatus;
}
