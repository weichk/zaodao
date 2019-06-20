package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改密码
 * Created by xiaohong on 2017/9/29.
 */
@Data
public class ModifyPasswordOrder extends OrderBase {
    /**
     * 旧密码
     */
    @NotBlank
    private String oldPassword;
    /**
     * 新密码
     */
    @NotBlank
    private String newPassword;

    /**
     * 用户唯一标识
     */
    @NotBlank
    private String userId;
}
