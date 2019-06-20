package com.acooly.zaodao.platform.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xiaohong
 * @create 2018-05-29 14:01
 **/
@Getter
@Setter
public class QueryBase implements Serializable {
    /**
     * 页大小
     */
    private Integer rows;

    /**
     * 页索引
     */
    private Integer page;

    /**
     * 排序
     */
    private String sort;

    /**
     * 正序/倒序
     */
    private String order;
}
