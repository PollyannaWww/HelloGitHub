package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.NameStyle;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.DocFlavor;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@NameStyle
@Table(name = "c_article")
public class Article {
    @Id
    private String id;
    private String articleTitle;
    //@JSONField(format = "yyyy-MM-dd HH:mm:ss")
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date articlePubDate;
    private String articleContent;
    private String authorName;
}
