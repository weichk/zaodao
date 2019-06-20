package com.acooly.zaodao.openapi.message.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 代理商
 *
 * @author xiaohong
 * @create 2018-05-05 14:48
 **/
@Getter
@Setter
public class TravelAgencyInfoDto implements Serializable {
    /** id */
    private Long id;

    /** 旅行社名称 */
    private String agencyName;

    /** 许可证 */
    private String licenseNo;

    /** 联系人名称 */
    private String contactName;

    /** 联系人电话 */
    private String contactPhone;
}
