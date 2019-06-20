/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-07
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.CustomerAlbumDao;
import com.acooly.zaodao.platform.dao.manage.CustomerImgDao;
import com.acooly.zaodao.platform.entity.CustomerAlbum;
import com.acooly.zaodao.platform.entity.CustomerImg;
import com.acooly.zaodao.platform.service.manage.CustomerAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户相册表 Service实现
 * <p>
 * Date: 2017-06-07 11:37:17
 *
 * @author zhike
 */
@Service("customerAlbumService")
public class CustomerAlbumServiceImpl extends EntityServiceImpl<CustomerAlbum, CustomerAlbumDao> implements CustomerAlbumService {

    @Autowired
    private CustomerImgDao customerImgDao;

    @Transactional
    @Override
    public void uploadDefaulAlbum(String userId, String imgPath,String thumbnailImgPath) {
        //获取默认相册
        CustomerAlbum customerAlbum = getEntityDao().getDefaulCustomerAlbumByUserId(userId);
        if(customerAlbum == null) {
            customerAlbum = new CustomerAlbum();
            //保存默认相册
            customerAlbum.setUserId(userId);
            customerAlbum.setAlbumName("默认相册");
            getEntityDao().save(customerAlbum);
        }
        //保存照片
        CustomerImg customerImg = new CustomerImg();
        customerImg.setAlbumId(customerAlbum.getId());
        customerImg.setCusPicture(imgPath);
        customerImg.setThumbPic(thumbnailImgPath);
        customerImgDao.save(customerImg);
    }
}
