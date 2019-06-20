/*
 * 修订记录:
 * zhike@yiji.com 2017-03-08 14:16 创建
 *
 */
package com.acooly.zaodao;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public class SysConstant {
    /**
     * 用户手机验证码session
     */
    public static final String MOBILE_CAPTCHA_SESSION = "mobileCaptchaSession";

    /**
     * 用户session
     */
    public static final String SESSION_KEY_USERINFO = "sessionKeyUserInfo";

    /**
     * 服務器相對路徑
     */
    public static final String SESSION_SERVICE_ROOT = "serverRoot";

    /**
     * 绑卡信息
     */
    public static final String SESSION_KEY_CARD = "sessionKeyCard";

    /**
     * 导游相关信息
     */
    public static final String SESSION_KET_GUIDEINFO = "sessionKeyGuideInfo";

    /**
     * 会员积分余额
     */
    public static final String CUSTOMER_POINT_BALANCE = "customerPointBalance";

    /**
     * 会员未读消息数
     */
    public static final String CUSTOMER_MSG = "customerMsg_";
    /**
     * true:mock环境，false:非mock
     */
    private final boolean isMock = true;
}
