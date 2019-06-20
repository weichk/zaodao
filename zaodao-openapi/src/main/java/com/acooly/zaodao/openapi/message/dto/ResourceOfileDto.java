package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

/**
 * 资源文件
 *
 * @author xiaohong
 * @create 2017-11-03 17:51
 **/
@Data
public class ResourceOfileDto {
    @OpenApiField(desc = "资源文件ofileId", constraint = "资源文件ofileId")
    private Long resourceOfileId;

    @OpenApiField(desc = "资源文件Url", constraint = "资源文件Url")
    private String resourceUrl;

    @OpenApiField(desc = "资源文件对应的封面文件ofileId(资源文件为视频时使用)", constraint = "封面文件ofileId")
    private Long coverOfileId;

    @OpenApiField(desc = "资源文件对应的封面文件Url(资源文件为视频时使用)", constraint = "封面文件Url")
    private String coverOfileUrl;
}
