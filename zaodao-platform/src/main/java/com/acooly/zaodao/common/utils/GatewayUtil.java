/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 20:59 创建
 *
 */
package com.acooly.zaodao.common.utils;

import javax.servlet.ServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public class GatewayUtil{
    /**
     * 异步通知请求参数转map
     * @return
     */
    public static Map<String, String> getNotifyParameters(ServletRequest request) {
        Map<String, String> params = new TreeMap<>();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String[] values = request.getParameterValues(name);
            if (values == null || values.length == 0) {
                continue;
            }
            String value = values[0];
            // 注意：这里是判断不为null,没有包括空字符串的判断。
            if (value != null) {
                params.put(name, value);
            }
        }
        return params;
    }

    /**
     * 同步跳转通知请求参数转map
     * @return
     */
    public static Map<String, String> getRedirectParameters(ServletRequest request) {
        Map<String, String> params = new TreeMap<>();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String[] values = request.getParameterValues(name);
            if (values == null || values.length == 0) {
                continue;
            }
            String value = convert(values[0]);
            // 注意：这里是判断不为null,没有包括空字符串的判断。
            if (value != null) {
                params.put(name, value);
            }
        }
        return params;
    }

    /**
     * 将字符编码
     * @return
     */
    public static String convert(String target) {
        try {
            return new String(target.trim().getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return target;
        }
    }
}
