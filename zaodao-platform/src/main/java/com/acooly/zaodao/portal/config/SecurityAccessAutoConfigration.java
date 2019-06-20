/*
 * 修订记录:
 * zhike@yiji.com 2017-03-09 11:27 创建
 *
 */
package com.acooly.zaodao.portal.config;

import com.acooly.zaodao.SysConstant;
import com.acooly.zaodao.portal.filter.SecurityAccessControlFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Configuration
@ConditionalOnWebApplication
public class SecurityAccessAutoConfigration {

    @Bean
    public FilterRegistrationBean securityAccessFilter() {
        SecurityAccessControlFilter filter = new SecurityAccessControlFilter();
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.addInitParameter("exclusions", "/portal/services/login.*,/portal/services/logout.html,/portal/services/register.*,/portal/services/sendSms.*,/portal/services/verifyMobileRepeat.*,/portal/services/checkCaptcha.*");
        registration.addInitParameter("sessionKey", SysConstant.SESSION_KEY_USERINFO);
        registration.addInitParameter("loginUrl", "/portal/portlet/login_register.html");
        registration.setName("securityAccessControlFilter");
        registration.addUrlPatterns("/portal/services/*");
        return registration;
    }
}
