/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 10:42 创建
 *
 */
package com.acooly.zaodao.gateway.gsy;

import com.acooly.zaodao.common.ConfigurableConstants;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public class GsyApiConstant extends ConfigurableConstants {

    static {
        initWithProfile("application.properties");
    }

    public static final String NOTIFY_SITE = "/gsy/notify";

    public static final String SIGN_TYPE = "signType";

    public static final String SIGN_TYPE_RSA = "RSA";

    public static final String PARTNER_ID = "partnerId";

    public static final String ORDER_NO = "orderNo";

    public static final String REQUEST_NO = "requestNo";

    public static final String FORMAT = "format";

    public static final String SERVICE = "service";

    public static final String TIMESTAMP = "timestamp";

    public static final String VERSION = "version";

    public static final String SIGN = "sign";

    public static final String CHARSET = "charset";

    public static final String NOTIFY_URL = "notifyUrl";

    public static final String RETURN_URL = "returnUrl";

    public static final String PROTOCOL = "protocol";

    //扩展字段
    public static final String BIZ_CONTENT_KEY = "biz_content";

    /**
     * 默认时间格式
     **/
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Date默认时区
     **/
    public static final String DATE_TIMEZONE = "GMT+8";

    /**
     * UTF-8字符集
     **/
    public static final String CHARSET_UTF8 = "UTF-8";
    //请求商户号
    public static final String GATEWAY_PARTNER_ID = getProperty("gsyPay.gateway.partnerId", "");
    //商户密钥
    public static final String GATEWAY_SECRETKEY = getProperty("gsyPay.gateway.secretKey", "");
    //服务请求地址
    public static final String SERVICE_GATEWAY_URL = getProperty("gsyPay.service.gateway.url", "");
    //服务请求超时配置
    public static final int HTTP_CONNECT_TIMEOUT = Integer.parseInt(getProperty("gsyPay.http.connect.timeout", "60"));
    //服务响应超时配置
    public static final int HTTP_READ_TIMEOUT = Integer.parseInt(getProperty("gsyPay.http.read.timeout", "60"));
}
