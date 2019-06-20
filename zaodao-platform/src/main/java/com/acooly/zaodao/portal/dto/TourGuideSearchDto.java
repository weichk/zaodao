package com.acooly.zaodao.portal.dto;

import lombok.Data;

/**
 * Created by Administrator on 2017/5/24.
 */
@Data
public class TourGuideSearchDto {

    private String keywords;

    private String province;

    private String city;

    private String tripTime;

    private String level;

    private String gender;

    private String language;

    private String sex;

    private int hotGuide = -1;

    private String guideLevel;
}
