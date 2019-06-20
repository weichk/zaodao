package com.acooly.zaodao.openapi.message.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统评价标签
 *
 * @author xiaohong
 * @create 2018-05-11 9:55
 **/
@Data
public class EvalTagInfoDto implements Serializable {
    /**
     * 标签ID
     */
    private Long id;

    /**
     * 标签内容
     */
    private String tagContent;
}
