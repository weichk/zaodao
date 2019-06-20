package com.acooly.zaodao;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Strings;
import com.acooly.module.ofile.auth.OFileUploadAuthenticate;
import com.acooly.openapi.framework.common.enums.ApiServiceResultCode;
import com.acooly.openapi.framework.core.auth.realm.AuthInfoRealm;
import com.acooly.openapi.framework.core.executer.ApiContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiyang on 2017/9/26.
 */
@Slf4j
public class AppOFileUploadAuthenticateImpl implements OFileUploadAuthenticate{
    @Resource
    protected AuthInfoRealm authInfoRealm;

    @Override
    public void authenticate(HttpServletRequest request) {

        String timestamp = request.getParameter("timestamp");
        String accessKey = request.getParameter("accessKey");
        String signType = request.getParameter("signType");
        String appClient = request.getParameter("appClient");
        String sign = request.getParameter("sign");

        if (Strings.isBlank(timestamp)) {
            throw new BusinessException("时间戳(timestamp)不能为空", ApiServiceResultCode.PARAMETER_ERROR.code());
        }
        if (Strings.isBlank(accessKey)) {
            throw new BusinessException("请求接入访问Key(accessKey)不能为空", ApiServiceResultCode.PARAMETER_ERROR.code());
        }
        if (Strings.isBlank(sign)) {
            throw new BusinessException("请求签名(sign)不能为空", ApiServiceResultCode.PARAMETER_ERROR.code());
        }
        if (Strings.isBlank(signType)) {
            signType = "MD5";
        }
        if (Strings.isBlank(appClient)) {
            appClient = "false";
        }

        if (ApiContextHolder.getApiContext() == null) {
            ApiContextHolder.init();
            ApiContextHolder.getApiContext().setAppClient(Boolean.valueOf(appClient));
        }

        String secretKey = (String) authInfoRealm.getAuthenticationInfo(accessKey);

        String signWaiting = "accessKey=" + accessKey + "&signType=" + signType + "&timestamp=" + timestamp + "&appClient=" + appClient + secretKey;
        log.debug("文件上传 待签字符串: {}", signWaiting);
        String serverSign = DigestUtils.md5Hex(signWaiting);
        if (!Strings.equals(serverSign, sign)) {
            throw new BusinessException(ApiServiceResultCode.UN_AUTHENTICATED_ERROR.message(), ApiServiceResultCode.UN_AUTHENTICATED_ERROR.code());
        }
        log.info("文件上传签名认证成功。accessKey:{}, appClient:{}", accessKey, appClient);
    }
}
