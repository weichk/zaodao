package com.acooly.zaodao.platform.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by xiyang on 2017/9/28.
 */
@Data
public class GuideInfoDto implements Serializable {

    /** 用户ID */
    private String userId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 导游封面图
     */
    private String guideCoverImg;


    /** 带团时间 */
    private Integer tourTime = 0;

    /** 常驻城市 */
    private String permanentCity;

    /** 擅长路线 */
    @Size(max = 512)
    private String goodRoute;

    /** 自我介绍 */
    @Size(max = 512)
    private String introduceMyself;

    /** 价格 */
    private Long pricePerDay;

    /** 语种 */
    private String language;

    /** 星级 */
    private Integer starLevel;

    /** 是否为人气导游{1:是,0:否} */
    private Integer hotGuide;

}
