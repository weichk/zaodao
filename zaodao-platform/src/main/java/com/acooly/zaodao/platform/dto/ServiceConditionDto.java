package com.acooly.zaodao.platform.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Comparator;

/**
 * 拆分条件
 *
 * @author xiaohong
 * @create 2018-05-30 15:42
 **/
@Data
public class ServiceConditionDto implements Serializable {
    /**
     * conditionName_0
     */
    private String source;
    /**
     * conditionName
     */
    private String name;
    /**
     * 0
     */
    private String index;
    /**
     * order_status
     */
    private String value;
}
