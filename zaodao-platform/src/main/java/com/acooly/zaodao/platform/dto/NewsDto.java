/*
 * 修订记录:
 * zhike@yiji.com 2017-04-24 20:39 创建
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
public class NewsDto implements Serializable {
    private Long id;

    private String title;

    private String subject;

    private String hits;

    private String createTime;

    private String cover;
}
