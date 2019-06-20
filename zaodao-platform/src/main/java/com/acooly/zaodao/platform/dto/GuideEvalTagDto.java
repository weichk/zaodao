package com.acooly.zaodao.platform.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 导游评价标签
 *
 * @author xiaohong
 * @create 2018-05-07 21:07
 **/
@Getter
@Setter
public class GuideEvalTagDto implements Serializable {
    /**
     * 表签数量
     */
    private Long tagCount;
    /**
     * 表签内容
     */
    private String tagContent;

    public GuideEvalTagDto(){}

    public GuideEvalTagDto(Long tagCount, String tagContent){
        this.tagCount = tagCount;
        this.tagContent = tagContent;
    }
}
