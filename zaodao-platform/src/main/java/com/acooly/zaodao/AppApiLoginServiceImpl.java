package com.acooly.zaodao;

import com.acooly.module.appopenapi.support.AppApiLoginService;
import com.acooly.module.appopenapi.support.LoginDto;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by xiyang on 2017/9/18.
 */
@Service
public class AppApiLoginServiceImpl implements AppApiLoginService {

    @Autowired
    private CustomerService customerService;

    @Override
    public LoginDto login(String userName, String password, Map<String, Object> map) {
        Customer customer = customerService.login(userName, password);
        LoginDto loginDto = new LoginDto();
        loginDto.setAccessKey(userName);
        loginDto.setCustomerId(customer.getUserId());
        return loginDto;
    }
}
