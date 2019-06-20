package com.acooly.zaodao.platform.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信用户信息
 *
 * @author xiaohong
 * @create 2017-12-18 17:57
 **/
@Data
public class OpenWxUserDto implements Serializable {
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String headImgUrl;
}
