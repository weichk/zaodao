package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.PageOrder;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.common.enums.ArticleTypeEnum;
import com.acooly.zaodao.platform.enums.ArticleHotType;
import com.acooly.zaodao.platform.enums.ArticleStatusEnum;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 * Created by xiyang on 2017/9/19.
 */
@Data
public class QueryArticleOrder extends PageOrder {

    @NotNull
    private String userId;

    @NotNull
    private ArticleHotType articleHotType;

    @NotNull
    private ArticleTypeEnum articleType;

    /**
     * 是否加精{0:不加精,1:加精}
     */
    private Integer essenceStatus;

    private String keywords;

    /** 文章状态 {@link com.acooly.zaodao.platform.enums.ArticleStatusEnum} */
    private ArticleStatusEnum articleStatus;

}
