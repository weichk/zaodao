/*
 * 修订记录:
 * zhike@yiji.com 2017-08-13 16:31 创建
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
public class ArticleRewardLogDto implements Serializable {
    /**
     * 打赏用户ID
     */
    private String userId;

    /**
     * 打赏用户头像
     */
    private String headImg;
}
