package com.acooly.zaodao.platform.dto;

import com.acooly.core.utils.enums.ResultStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * 支付
 *
 * @author xiaohong
 * @create 2017-11-24 14:47
 **/
@Data
public class ScanCodePay implements Serializable{
    /**
     * 二维码链接图片
     */
    private String codeUrlImg;
    /**
     * 二维码链接内容
     */
    private String codeUrlContent;
    /**
     * 二维码链接内容扩展
     */
    private String codeUrlContentEx;
    /**
     * 消息
     */
    private String message;
    /**
     * 状态
     */
    private ResultStatus status;
}
