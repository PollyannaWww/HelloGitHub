package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.NameStyle;

import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "c_carousel")
@NameStyle
public class Carousel {
    @Id
    private String id;
    private String carouselName;
    private String carouselPicture;
    private String carouselDescription;
    private Integer carouselStatus;
}
