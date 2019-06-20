/*
 * 修订记录:
 * zhike@yiji.com 2017-03-02 11:05 创建
 *
 */
package com.acooly.zaodao.common;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Slf4j
public class CommonUtils {
    /**
     * 生成一个随机数
     *
     * @return
     */
    public static String randomNum() {
        String[] beforeShuffle = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        List list = Arrays.asList(beforeShuffle); //将数组转成List
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(3, 9);
        return result;
    }

    /**
     * 生成唯一标识
     *
     * @return
     */
    public static String genUniqIdentify() {
        return Long.toString((long) (Math.random() * Long.MAX_VALUE), 36);
    }

//    /**
//     * 校验手机验证码
//     *
//     * @param request
//     * @return
//     */
//    public static int checkMobileCaptcha(HttpServletRequest request) {
//        try {
//            String mobileCaptcha = request.getParameter("mobileCaptcha");
//            HttpSession session = request.getSession();
//            MobileCaptchaDto mobileCaptchaDto = (MobileCaptchaDto) session.getAttribute(SysConstant.MOBILE_CAPTCHA_SESSION);
//            Date putDate = mobileCaptchaDto.getSendTime();
//            Date nowDate = new Date();
//            long interval = (nowDate.getTime() - putDate.getTime()) / 1000;
//            if (interval > 60) {
//                return 0;
//            } else if (!StringUtils.equals(mobileCaptcha, mobileCaptchaDto.getMobileCaptcha())) {
//                return 1;
//            } else {
//                return -1;
//            }
//        } catch (Exception e) {
//            log.warn("校验验证码失败{}", e.getMessage());
//            throw new BusinessException("验证码错误");
//        }
//    }

    public static String getRandomJianHan(int len) {
        String ret = "";
        for (int i = 0; i < len; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); // 获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); // 获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                str = new String(b, "GBK"); // 转成中文
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            ret += str;
        }
        return ret;
    }

    /**
     * 计算字符串所占字符数（中文两个英文一个）
     *
     * @param str
     * @return
     */
    public static int getStrBytesLength(String str) {
        try {
            byte[] buff = str.getBytes("GB18030");
            int f = buff.length;
            return f;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 根据剩余个数随机计算积分
     *
     * @param num    剩余个数
     * @param amount 积分
     * @return
     */
    public static int calculatingIntegral(Integer num, Long amount) {
        if(num == 1) {
            return amount.intValue();
        }
        num = num - 1;
        Random random = new Random();
        //拿到四个随机数，可以做个池什么的每次取四个来提升效率
        List<Double> r = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            r.add(random.nextDouble());
        }
        //排序
        r.sort(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o1 < o2 ? -1 : 1;
            }
        });
        //用这四个随机数来打断一个数，来取得五份分解之后的数
        List<Integer> out = new ArrayList<>();
        int last = 0;
        for (int i = 0; i < num; i++) {
            int c = (int) (r.get(i) * amount);
            if (c - last == 0) {
                c = c + 1;
            }
            out.add(c - last);
            last = c;
        }
        out.add(num - last);
        return out.get(0);
    }
}
