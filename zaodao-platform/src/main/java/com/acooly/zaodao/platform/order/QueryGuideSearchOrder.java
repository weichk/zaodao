package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.PageOrder;
import com.acooly.zaodao.platform.enums.GuideLevel;
import lombok.Data;

/**
 * Created by okobboko on 2017/10/16.
 */
@Data
public class QueryGuideSearchOrder extends PageOrder {
    /**
     * 关键字
     */
    private String keywords;

    private Integer hotGuide;

    private GuideLevel guideLevel;
}
