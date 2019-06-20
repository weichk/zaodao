/*
 * 修订记录:
 * zhike@yiji.com 2017-06-08 16:14 创建
 *
 */
package com.acooly.zaodao.platform.dao.manage.impl;

import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.manage.CustomerImgCusDao;
import com.acooly.zaodao.platform.entity.CustomerImg;

import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public class CustomerImgDaoImpl extends AbstractJdbcTemplateDao implements CustomerImgCusDao {

    @Override
    public List<CustomerImg> getCusDefaultImgListByUserId(String userId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT zci.* FROM zd_customer_img zci");
        sql.append(" INNER JOIN zd_customer_album zca ON zci.album_id = zca.id");
        sql.append(" WHERE zca.user_id = '" + userId + "'");
        List<CustomerImg> customerImgList = queryForList(sql.toString(), CustomerImg.class);
        return customerImgList;
    }
}
