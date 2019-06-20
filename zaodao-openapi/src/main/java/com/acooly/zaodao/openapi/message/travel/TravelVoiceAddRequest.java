package com.acooly.zaodao.openapi.message.travel;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.zaodao.openapi.message.dto.ResourceOfileDto;
import com.google.common.collect.Lists;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by xiaohong on 2017/10/26.
 */
@Data
@OpenApiMessage(service = "travelVoiceAdd", type = ApiMessageType.Request)
public class TravelVoiceAddRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(desc = "上传用户ID", constraint = "上传用户ID")
    private String userId;

    @OpenApiField(desc = "旅声内容标题", constraint = "旅声内容标题")
    private String title;

    @OpenApiField(desc = "文章内容", constraint = "文章内容")
    private String content;

    @OpenApiField(desc = "发布位置名称", constraint = "发布位置名称")
    private String positionName;

    @OpenApiField(desc = "发布位置纬度", constraint = "发布位置纬度")
    private String positionLat;

    @OpenApiField(desc = "发布位置经度", constraint = "发布位置经度")
    private String positionLng;

    //@OpenApiField(desc = "旅声资源文件id集合", constraint = "旅声资源文件id集合",  demo = "1,2,3,4")
    //private String resourceIds;

    //@OpenApiField(desc = "旅声资源文件ofile集合", constraint = "旅声资源文件ofile集合")
    //private List<ResourceOfileDto> travelResourceList = Lists.newArrayList();
    @OpenApiField(desc = "旅声资源文件ofile集合", constraint = "旅声资源文件ofile集合", demo = "[{\"resourceOfileId\":\"\",\"resourceUrl\":\"\",\"coverOfileId\":\"\",\"coverOfileUrl\":\"\"}]")
    private String travelResourceListJson;
}
