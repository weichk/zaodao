package com.acooly.zaodao.platform.service.platform.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Dates;
import com.acooly.core.utils.Ids;
import com.acooly.module.ofile.domain.OnlineFile;
import com.acooly.module.ofile.service.OnlineFileService;
import com.acooly.zaodao.platform.service.platform.FileTransferService;
import com.acooly.zaodao.portal.dto.PersistOfile;
import com.acooly.zaodao.portal.dto.UploadResource;
import com.alibaba.fastjson.JSONObject;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.acooly.module.ofile.enums.OFileType;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 文件传输
 *
 * @author xiaohong
 * @create 2017-11-25 12:45
 **/
@Slf4j
@Service("fileTransferService")
public class FileTransferServiceImpl implements FileTransferService {

    @Autowired
    private OnlineFileService onlineFileService;

    /**
     * 保存文件信息至ofile
     * @param persistOfile
     * @throws Exception
     */
    @Override
    public OnlineFile saveOfile(PersistOfile persistOfile) throws Exception {
        if(persistOfile == null){
            throw new BusinessException("持久化对象不能为空");
        }
        File file = persistOfile.getFile();
        if(!persistOfile.getFile().exists() || !persistOfile.getFile().isFile()){
            throw new Exception("找不到文件");
        }

        OnlineFile onlineFile = new OnlineFile();
        onlineFile.setInputName(persistOfile.getInputName());
        onlineFile.setFileExt(Files.getFileExtension(file.getName()));
        onlineFile.setFileName(file.getName());
        onlineFile.setFilePath(persistOfile.getSavePath());
        onlineFile.setFileSize(file.length());
        onlineFile.setFileType(persistOfile.getOFileType());
        onlineFile.setOriginalName(persistOfile.getOriFileName());
        onlineFile.setStatus("enable");
        onlineFile.setThumbnail("");
        onlineFile.setUserName(persistOfile.getUserName());
        onlineFile.setObjectId(Ids.gid());
        log.info(String.format("ofile信息: %s ", JSONObject.toJSON(onlineFile)));
        onlineFileService.save(onlineFile);

        return onlineFile;
    }

    /**
     * 创建保存路径
     * @param storageRoot
     * @return
     */
    @Override
    public String getSubDirectoryPath(String storageRoot) {
        String path = "";

        try {

            Date date = new Date();

            String pathTimestamp = Dates.format(date, "yyyyMMddHHmmssSSS");
            String yearPart = StringUtils.left(pathTimestamp, 4);
            String monthPart = StringUtils.substring(pathTimestamp, 4, 6);
            String dayPart = StringUtils.substring(pathTimestamp, 6, 8);
            path = File.separator + "video" + File.separator + yearPart + File.separator + monthPart + File.separator + dayPart;

            File pathFile = new File(storageRoot + path);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
        } catch (Exception e) {
            log.warn("创建保存路径失败");
        }

        return path;
    }
    /**
     * 获取文件信息
     * @param file
     * @param realPath
     * @return
     * @throws IOException
     */
    @Override
    public UploadResource getUploadResource(MultipartFile file, String realPath) throws IOException {
        UploadResource uploadResource = new UploadResource();

        uploadResource.setOriFilename(file.getOriginalFilename());
        uploadResource.setExtension(Files.getFileExtension(file.getOriginalFilename()));
        String name = System.currentTimeMillis() + "";
        String fileName = name + "." + uploadResource.getExtension();
        uploadResource.setName(name);
        uploadResource.setFullFileName(String.format("%s%s", realPath, fileName));
        uploadResource.setFileName(fileName);
        uploadResource.setFileSize(file.getSize());
        uploadResource.setContentType(file.getContentType());
        uploadResource.setInputStream(file.getInputStream());

        log.info(String.format("资源原名称: %s" , uploadResource.getOriFilename()));
        log.info(String.format("资源新名称: %s" , uploadResource.getFileName()));
        log.info(String.format("资源新全称: %s" , uploadResource.getFullFileName()));
        log.info(String.format("资源类型:   %s" , uploadResource.getContentType()));
        log.info(String.format("资源大小:   %s" , uploadResource.getFileSize()));

        return uploadResource;
    }
}
