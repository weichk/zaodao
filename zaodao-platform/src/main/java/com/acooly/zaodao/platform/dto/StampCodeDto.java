/*
 * 修订记录:
 * zhike@yiji.com 2017-08-13 18:14 创建
 *
 */
package com.acooly.zaodao.platform.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class StampCodeDto implements Serializable {

    /**
     * 图章编码
     */
    private String code;

    /**
     * 图章名称
     */
    private String message;
}
