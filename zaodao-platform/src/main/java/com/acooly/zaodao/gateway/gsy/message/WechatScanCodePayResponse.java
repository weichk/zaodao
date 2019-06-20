/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 15:40 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.zaodao.gateway.base.ResponseBase;
import lombok.Data;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class WechatScanCodePayResponse extends ResponseBase{
    /**
     * 二维码图片
     */
    private String codeUrlImg;

    /**
     * 二维码链接
     */
    private String codeUrlContent;

    /**
     * 二维码链接内容扩展
     */
    private String codeUrlContentExt;
}
