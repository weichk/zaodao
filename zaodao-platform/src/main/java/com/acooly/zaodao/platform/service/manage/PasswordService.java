package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.zaodao.platform.order.ForgetPasswordOrder;
import com.acooly.zaodao.platform.order.ModifyPasswordOrder;

/**
 * Created by xiaohong on 2017/9/29.
 */
public interface PasswordService {
    /**
     * 修改密码
     * @param modifyPasswordOrder
     * @return
     */
    ResultBase ModifyPassword(ModifyPasswordOrder modifyPasswordOrder);

    /**
     * 忘记密码
     * @param forgetPasswordOrder
     * @return
     */
    ResultBase ForgetPassword(ForgetPasswordOrder forgetPasswordOrder);
}
