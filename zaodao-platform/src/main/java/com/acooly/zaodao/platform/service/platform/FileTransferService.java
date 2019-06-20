package com.acooly.zaodao.platform.service.platform;

import com.acooly.module.ofile.domain.OnlineFile;
import com.acooly.module.ofile.enums.OFileType;
import com.acooly.zaodao.platform.service.platform.impl.FileTransferServiceImpl;
import com.acooly.zaodao.portal.dto.PersistOfile;
import com.acooly.zaodao.portal.dto.UploadResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;


public interface FileTransferService {
    /**
     * 将文件保存至ofile
     * @param persistOfile
     * @throws Exception
     */
    OnlineFile saveOfile(PersistOfile persistOfile) throws Exception;

    /**
     * 创建保存路径
     * @param storageRoot
     * @return
     */
    String getSubDirectoryPath(String storageRoot);

    /**
     * 获取资源信息
     * @param file
     * @param realPath
     * @return
     */
    UploadResource getUploadResource(MultipartFile file, String realPath) throws IOException;
}
