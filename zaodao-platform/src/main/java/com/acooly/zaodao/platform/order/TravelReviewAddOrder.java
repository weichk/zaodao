package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 旅声评论添加
 * Created by xiaohong on 2017/10/27.
 */
@Data
public class TravelReviewAddOrder extends OrderBase {
    /** 旅声内容ID */
    @NotNull
    private Long travelVoiceId;

    /** 发布用户ID */
    @NotBlank
    private String userId;

    /** 评论父级ID */
    private Integer reviewParentId;

    /** 评论内容 */
    @NotBlank
    private String content;
}
