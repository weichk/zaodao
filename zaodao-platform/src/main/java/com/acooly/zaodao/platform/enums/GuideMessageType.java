package com.acooly.zaodao.platform.enums;

import com.acooly.core.utils.enums.Messageable;

import java.util.LinkedHashMap;
import java.util.Map;

public enum GuideMessageType implements Messageable {
    reviewArtice("reviewArtice", "评论文章"),

    reviewCourse("reviewCourse", "评论课程"),

    reviewTravelVoice("reviewTravelVoice", "评论旅声"),

    focusUser("focusUser", "关注用户"),

    focusCourse("focusCourse", "关注课程"),

    focusTravelVoice("focusTravelVoice", "关注旅声"),

    publishArticle("publishArticle","发布文章"),

    publishCourse("publishCourse","发布课程"),

    publishTravelVoice("publishTravelVoice","发布旅声"),

    praiseTravelVoice("praiseTravelVoice","点赞旅声")
    ;
    private final String code;
    private final String message;

    private GuideMessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    public static Map<String, String> mapping() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (GuideMessageType type : values()) {
            map.put(type.getCode(), type.getMessage());
        }
        return map;
    }
}
