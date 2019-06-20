package com.acooly.zaodao.platform.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 关注数量和粉丝数量
 *
 * @author xiaohong
 * @create 2017-10-30 18:13
 **/
@Data
public class CustomerFocusCountDto implements Serializable {
    /**
     * 关注数量
     */
    private int count;
    /**
     * 粉丝数量
     */
    private int focusCount;
}
