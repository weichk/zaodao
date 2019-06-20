/*
 * 修订记录:
 * zhike@yiji.com 2017-06-14 14:09 创建
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
public class SessionGuideInfoDto implements Serializable{

    private Integer isGuide;

    private String tourRank;
}
