package com.acooly.zaodao.platform.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author xiaohong
 * @create 2018-05-23 11:30
 **/
public enum IsTourGuide {
    WAIT_APPROVE(-1, "待审核"),
    NOT_PASS(0, "否"),
    ONE_LEVEL(1, "一级导游"),
    TWO_APPROVE(2, "二级导游审核中"),
    TWO_LEVEL(3, "二级导游");

    private int key;
    private String message;

    IsTourGuide(int key, String message){
        this.key = key;
        this.message = message;
    }

    public static String getMessage(int key){
        for(IsTourGuide g : IsTourGuide.values()){
            if(g.getKey() == key){
                return g.getMessage();
            }
        }
        return null;
    }

    public static Map<Integer, String> getMaps(){
        Map<Integer, String> maps = Maps.newLinkedHashMap();
        for(IsTourGuide g : IsTourGuide.values()){
           maps.put(g.getKey(), g.getMessage());
        }
        return maps;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public int getKey(){
        return key;
    }

    public void setKey(int key){
        this.key = key;
    }
}
