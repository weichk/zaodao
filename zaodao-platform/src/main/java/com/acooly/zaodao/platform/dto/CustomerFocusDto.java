package com.acooly.zaodao.platform.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户粉丝、关注列表
 *
 * @author xiaohong
 * @create 2017-10-31 9:35
 **/
@Data
public class CustomerFocusDto  implements Serializable {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 用户头像
     */
    private String headImg;

}
