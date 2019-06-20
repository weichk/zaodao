package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.web.support.JsonResult;
import lombok.Data;

/**
 * Created by xiaohong on 2017/9/27.
 */
@Data
public class RewardResult extends ResultBase {
    /**
     * 打赏结果
     */
    private JsonResult jsonResult = new JsonResult();
}
