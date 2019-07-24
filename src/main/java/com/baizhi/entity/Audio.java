package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.NameStyle;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@NameStyle
@Table(name = "c_audio")
public class Audio {
    @Id
    private String id;
    private String audioName;
    private String audioSize;
    private String audioDuration;
    private String audioLocation;
    private Integer audioOrder;
    private String albumId;
}
