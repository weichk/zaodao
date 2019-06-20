package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.PageOrder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 评论列表请求参数
 *
 * @author xiaohong
 * @create 2017-10-27 14:52
 **/
@Data
public class TravelReviewListOrder extends PageOrder {
    /**
     * 旅声内容ID
     */
    @NotNull
    private Long travelVoiceId;
}
