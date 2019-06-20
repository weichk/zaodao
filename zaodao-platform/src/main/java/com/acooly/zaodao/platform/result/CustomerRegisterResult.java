package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.zaodao.platform.entity.Customer;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by xiyang on 2017/9/18.
 */
@Getter
@Setter
public class CustomerRegisterResult extends ResultBase {
    private Customer customer;
}
