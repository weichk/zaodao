package com.acooly.zaodao.portal.dto;

import lombok.Data;

import java.io.InputStream;
import java.io.Serializable;

/**
 * @author xiaohong
 * @create 2017-11-20 9:47
 **/
@Data
public class UploadResource implements Serializable {
    /**
     * 原文件名
     */
    private String oriFilename;
    /**
     * 文件全名
     */
    private String fullFileName;
    /**
     * 文件名(后缀)
     */
    private String fileName;
    /**
     * 文件名
     */
    private String name;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 文件类别
     */
    private String contentType;
    /**
     * 文件后缀
     */
    private String extension;
    /**
     * 文件流
     */
    private InputStream inputStream;
}