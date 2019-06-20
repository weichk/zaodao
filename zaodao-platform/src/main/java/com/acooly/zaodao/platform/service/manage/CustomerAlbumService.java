/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-07
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.CustomerAlbum;

/**
 * 用户相册表 Service接口
 *
 * Date: 2017-06-07 11:37:17
 * @author zhike
 *
 */
public interface CustomerAlbumService extends EntityService<CustomerAlbum> {

    void uploadDefaulAlbum(String userId,String imgPath,String thumbnailImgPath);
}
