package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.enums.OfileType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 旅声资源
 *
 * @author xiaohong
 * @create 2017-10-30 9:54
 **/
@Data
public class TravelResourceInfoDto implements Serializable {

    @OpenApiField(desc = "旅声内容ID", constraint = "旅声内容ID")
    private Long travelVoiceId;

    @OpenApiField(desc = "资源ID", constraint = "资源ID")
    private Long ofileId;

    @OpenApiField(desc = "封面url", constraint = "封面url")
    private String coverUrl;

    @OpenApiField(desc = "资源类型", constraint = "资源类型", demo = "other,image,video")
    private OfileType ofileType;

    @Size(max=255)
    @OpenApiField(desc = "url", constraint = "url")
    private String resourceUrl;
}
