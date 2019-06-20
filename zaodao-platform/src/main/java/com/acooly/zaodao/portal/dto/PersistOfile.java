package com.acooly.zaodao.portal.dto;

import com.acooly.module.ofile.enums.OFileType;
import lombok.Data;

import java.io.File;

/**
 * 持久化ofile
 *
 * @author xiaohong
 * @create 2017-11-25 16:15
 **/
@Data
public class PersistOfile {
    /**
     * 文件
     */
    private File file;
    /**
     * 文件原名称
     */
    private String oriFileName;
    /**
     * 表单名称(ofile表)
     */
    private String inputName;
    /**
     * ofile类型
     */
    private OFileType oFileType;
    /**
     * 操作人员
     */
    private String userName;

    /**
     * db存储路径
     */
    private String savePath;
}
