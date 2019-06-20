package com.acooly.zaodao.platform.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 是否开通
 *
 * @author xiaohong
 * @create 2018-05-24 20:22
 **/
public enum IsOpenable{
    NO(0, "否"),
    YES(1, "是");

    private int key;
    private String message;

    IsOpenable(int key, String message){
        this.key = key;
        this.message = message;
    }

    public static String getMessage(int key){
        for(IsOpenable g : IsOpenable.values()){
            if(g.getKey() == key){
                return g.getMessage();
            }
        }
        return null;
    }

    public static Map<Integer, String> getMaps(){
        Map<Integer, String> maps = Maps.newLinkedHashMap();
        for(IsOpenable g : IsOpenable.values()){
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
