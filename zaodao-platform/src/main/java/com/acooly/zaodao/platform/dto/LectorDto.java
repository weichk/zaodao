package com.acooly.zaodao.platform.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 讲师
 *
 * @author xiaohong
 * @create 2018-05-17 10:41
 **/
@Data
public class LectorDto implements Serializable {
    public LectorDto(){}

    public LectorDto(Long id, String userId, String realName){
        this.id = id;
        this.userId = userId;
        this.realName = realName;
    }
    private Long id;

    private String userId;

    private String realName;

    private Integer isLector;
}
