package com.acooly.zaodao.web.protal;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.module.ofile.domain.OnlineFile;
import com.acooly.module.ofile.enums.OFileType;
import com.acooly.module.ofile.service.OnlineFileService;
import com.acooly.zaodao.common.VideoFrameGrapper;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import com.acooly.zaodao.platform.service.platform.FileTransferService;
import com.acooly.zaodao.portal.dto.PersistOfile;
import com.acooly.zaodao.portal.dto.UploadResource;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.tomcat.jni.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Http文件操作
 *
 * @author xiaohong
 * @create 2017-11-20 9:39
 **/
@Slf4j
@Controller
@RequestMapping(value = "/protal/fileTransfer")
public class FileTransferController {
    @Value("${acooly.ofile.storageRoot}")
    private String storageRoot;

    @Value("${image.fileType.extentions}")
    private  String IMAGE_EXTENSIONS;

    @Value("${video.fileType.extentions}")
    private String VIDEO_EXTENSIONS;


    @Autowired
    private FileTransferService fileTransferService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult upload(HttpServletRequest request, @RequestParam("file") MultipartFile file, @RequestParam("userId") String userId) {
        JsonResult result = new JsonResult();
        Map<Object, Object> map = Maps.newHashMap();

        try {
            Customer customer = customerService.getUser(userId);
            if(customer == null){
                throw new BusinessException("未找到员工信息");
            }
            log.info("开始上传文件...");

            String subDirectory = fileTransferService.getSubDirectoryPath(storageRoot);
            String url = String.format("%s://%s:%s%s", request.getScheme(), request.getServerName(), request.getServerPort(), storageRoot);

            String root = String.format("%s%s%s",storageRoot, subDirectory, File.separator);
            String saveVedioDirectory = root; //request.getSession().getServletContext().getRealPath(root);

            log.info(String.format("相对根目录: %s", root));
            log.info(String.format("视频保存目录: %s", saveVedioDirectory));
            log.info(String.format("获取上传文件信息..."));
            UploadResource uploadResource = fileTransferService.getUploadResource(file, saveVedioDirectory);

            log.info(String.format("获取保存图片路径..."));
            String imageFilePath = String.format("%s%s",saveVedioDirectory, uploadResource.getName() + ".jpg");
            log.info(String.format("截帧图片保存路径: %s", imageFilePath));

            //初始化帧图截取组件
            VideoFrameGrapper videoFrameGrapper = new VideoFrameGrapper(uploadResource.getFullFileName(), imageFilePath, IMAGE_EXTENSIONS, VIDEO_EXTENSIONS);

            try {
                //保存视频
                File videoFile = new File(uploadResource.getFullFileName());
                videoFrameGrapper.saveFile(uploadResource.getInputStream(), videoFile);
                PersistOfile persistVideo = getPersistOfile(videoFile, subDirectory, uploadResource.getOriFilename(), OFileType.other, "", customer.getUserName());
                //视频信息存ofile
                OnlineFile onlineVideo = fileTransferService.saveOfile(persistVideo);
                map.put("videoOfileId", onlineVideo.getId());
                map.put("videoOfileUrl", onlineVideo.getAccessUrl());
                log.info("结束上传文件");
            } catch (Exception e) {
                throw new BusinessException(String.format("输入流保存为文件发生异常, 异常消息=%s", e.getMessage()));
            }
            File imageFile = null;
            try {
                //视频取帧
                imageFile = videoFrameGrapper.grabVedioImage();
                //帧图片旋转
                PersistOfile persistImage = getPersistOfile(imageFile, subDirectory, uploadResource.getOriFilename(), OFileType.picture,"", customer.getUserName());
                OnlineFile onlineImage = fileTransferService.saveOfile(persistImage);
                map.put("imageOfileId", onlineImage.getId());
                map.put("imageOfileUrl", onlineImage.getAccessUrl());
            } catch (Exception e) {
                throw new Exception(String.format("视频文件取帧发生异常, 异常消息=%s", e.getMessage()));
            }
            if(imageFile == null){
                throw new BusinessException("截取帧图失败");
            }
            String imageUrl = String.format("%s/%s", url, imageFile.getName());
            log.info(String.format("帧图路径: %s", imageUrl));
            result.setData(map);
        } catch (BusinessException bx) {
            result.setSuccess(false);
            result.setMessage(bx.getMessage());
        }catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    /**
     * 获取ofile持久化实体
     * @param file
     * @param storageRoot
     * @param oriFilename
     * @param ofileType
     * @param inputName
     * @param userName
     * @return
     */
    private PersistOfile getPersistOfile(File file,String storageRoot, String oriFilename, OFileType ofileType, String inputName, String userName){
        PersistOfile persistOfile = new PersistOfile();
        persistOfile.setFile(file);
        persistOfile.setSavePath(String.format("%s%s%s", storageRoot, File.separator, file.getName()));
        persistOfile.setOriFileName(oriFilename);
        persistOfile.setOFileType(ofileType);
        persistOfile.setInputName(inputName);
        persistOfile.setUserName(userName);
        return persistOfile;
    }

}
