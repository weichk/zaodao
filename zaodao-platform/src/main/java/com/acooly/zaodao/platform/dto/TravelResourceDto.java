package com.acooly.zaodao.platform.dto;

import com.acooly.zaodao.platform.enums.OfileType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * 旅声评论
 *
 * @author xiaohong
 * @create 2017-10-30 10:35
 **/
@Data
public class TravelResourceDto {
    /** 旅声内容ID */
    private Long travelVoiceId;

    /** 资源名称 */
    private Long ofileId;

    /** 封面url */
    private String coverUrl;
    /**
     * 资源类型
     */
    private OfileType ofileType;

    /** resource_url */
    private String resourceUrl;
}
