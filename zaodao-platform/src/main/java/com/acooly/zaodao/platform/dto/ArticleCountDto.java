package com.acooly.zaodao.platform.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/17.
 */
@Data
public class ArticleCountDto implements Serializable {

    /**
     * 类型
     */
    private String articleTypeCode;

    /**
     * 类型
     */
    private String articleTypeMsg;

    /**
     * 数量
     */
    private int count;
}
