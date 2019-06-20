package com.acooly.zaodao.platform.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统评价标签
 *
 * @author xiaohong
 * @create 2018-05-11 9:58
 **/
@Data
public class EvalTagDto implements Serializable {
    /**
     * 标签ID
     */
    private Long id;

    /**
     * 标签内容
     */
    private String tagContent;
}
