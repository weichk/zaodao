/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-15
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.ShopGoodsTypeDao;
import com.acooly.zaodao.platform.entity.ShopGoodsType;
import com.acooly.zaodao.platform.service.manage.ShopGoodsTypeService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 商品分类 Service实现
 *
 * Date: 2017-06-15 15:23:25
 *
 * @author zhike
 *
 */
@Service("shopGoodsTypeService")
public class ShopGoodsTypeServiceImpl extends EntityServiceImpl<ShopGoodsType, ShopGoodsTypeDao> implements ShopGoodsTypeService {

    protected String getMaxCode(Long parentId) {
        if (parentId == null) {
            return getEntityDao().getTopMaxCode();
        } else {
            return getEntityDao().getMaxCode(parentId);
        }
    }

    @Override
    public ShopGoodsType create(Long parentId, String name, String comments) {
        String code = null;
        String maxCode = getMaxCode(parentId);
        if (StringUtils.isNotBlank(maxCode)) {
            code = String.valueOf(Integer.parseInt(maxCode) + 1);
        } else {
            if (parentId == null) {
                code = "100";
            } else {
                ShopGoodsType parent = get(parentId);
                code = parent.getCode() + "100";
            }
        }
        ShopGoodsType type = new ShopGoodsType();
        type.setCode(code);
        type.setComments(comments);
        type.setName(name);
        type.setParentId(parentId);
        type.setSortTime(System.currentTimeMillis());
        save(type);
        return type;
    }

    @Override
    public List<ShopGoodsType> loadTree(Long parentId) {
        List<ShopGoodsType> types = null;
        if (parentId == null) {
            types = getEntityDao().getAll();
        } else {
            ShopGoodsType parent = get(parentId);
            types = getEntityDao().findByCodeStartingWith(parent.getCode());
        }
        List<ShopGoodsType> result = Lists.newLinkedList();
        try {
            Map<Long, ShopGoodsType> dtoMap = Maps.newHashMap();
            for (ShopGoodsType type : types) {
                dtoMap.put(type.getId(), type);
            }
            for (Map.Entry<Long, ShopGoodsType> entry : dtoMap.entrySet()) {
                ShopGoodsType node = entry.getValue();
                if (node.getParentId() == null || node.getParentId() == 0) {
                    result.add(node);
                } else {
                    if (dtoMap.get(node.getParentId()) != null) {
                        dtoMap.get(node.getParentId()).addChild(node);
                        Collections.sort(dtoMap.get(node.getParentId()).getChildren(),
                                new ShopGoodsType.NodeComparator());
                    }
                }
            }
            types.clear();
            types = null;
            dtoMap.clear();
            dtoMap = null;
            Collections.sort(result, new ShopGoodsType.NodeComparator());
        } catch (Exception e) {
            throw new BusinessException("获取授权资源列表失败：" + e.getMessage());
        }
        return result;

    }

    @Override
    public void removeById(Serializable id) throws BusinessException {
        super.removeById(id);
    }
}
