package com.acooly.zaodao.platform.dto;

import com.acooly.module.ofile.domain.OnlineFile;

import java.util.Comparator;

/**
 * ofile实体排序
 *
 * @author xiaohong
 * @create 2018-06-26 18:36
 **/
public class OnlineFileDtoSort implements Comparator<OnlineFile> {
    @Override
    public int compare(OnlineFile o1, OnlineFile o2) {
        return o1.getInputName().compareTo(o2.getInputName());
    }
}
