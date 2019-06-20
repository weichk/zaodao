/*
 * 修订记录:
 * zhike@yiji.com 2017-05-22 00:48 创建
 *
 */
package com.acooly.zaodao.platform.dao.platform.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.platform.NewsInfoDao;
import com.acooly.zaodao.platform.dto.NewsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Service
@Slf4j
public class NewsInfoDaoImpl extends AbstractJdbcTemplateDao implements NewsInfoDao {

    @Override
    public NewsDto findNesInfoPreviousOrNext(String type, Long id, boolean isNext) {
        NewsDto result = new NewsDto();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT cc.ID id,cc.TITLE title,cc.`SUBJECT` subject,cc.HITS hits,cc.CREATE_TIME createTime,cc.COVER cover from cms_content cc");
            sql.append(" INNER JOIN cms_content_type cct ON cc.TYPE = cct.ID");
            sql.append(" WHERE cct.`CODE` = '" + type + "' AND cc.STATUS=1");
            if (isNext) {
                sql.append(" AND cc.ID>" + id + " ORDER BY cc.ID ASC");
            } else {
                sql.append(" AND cc.ID<" + id + " ORDER BY cc.ID DESC");
            }
            sql.append(" LIMIT 1");
            result = this.jdbcTemplate.queryForObject(sql.toString(), new RowMapper<NewsDto>() {
                @Override
                public NewsDto mapRow(ResultSet arg0, int arg1) throws SQLException {
                    NewsDto newsDto = new NewsDto();
                    newsDto.setId(arg0.getLong("id"));
                    newsDto.setCover(arg0.getString("cover"));
                    newsDto.setCreateTime(arg0.getString("createTime"));
                    newsDto.setHits(arg0.getString("hits"));
                    newsDto.setSubject(arg0.getString("subject"));
                    newsDto.setTitle(arg0.getString("title"));
                    return newsDto;
                }
            });
        } catch (EmptyResultDataAccessException erere) {
            if (isNext) {
                log.info("没有下一条新闻数据");
            } else {
                log.info("没有上一条新闻数据");
            }
        }
        return result;
    }

    @Override
    public PageInfo<NewsDto> getPageNewsListByType(PageInfo<NewsDto> pageInfo, String type) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ccb.ID id,ccb.TITLE title,ccb.`SUBJECT` subject,ccb.HITS hits,ccb.CREATE_TIME createTime,ccb.COVER cover from cms_content ccb");
        sql.append(" INNER JOIN cms_content_type cct ON ccb.TYPE = cct.ID");
        sql.append(" WHERE cct.`CODE` = '" + type + "' and ccb.STATUS=1");
        sql.append(" order by ccb.CREATE_TIME desc");
        PageInfo<NewsDto> result = query(pageInfo, sql.toString(), NewsDto.class);
        return result;
    }

    @Override
    public List<NewsDto> getNewsByType(String type, int count) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT cc.ID id,cc.TITLE title,cc.`SUBJECT` subject,cc.HITS hits,cc.CREATE_TIME createTime,cc.COVER cover from cms_content cc");
        sql.append(" INNER JOIN cms_content_type cct ON cc.TYPE = cct.ID");
        sql.append(" WHERE cct.`CODE` = '" + type + "' and cc.STATUS=1 order by cc.CREATE_TIME desc limit " + count + "");
        List<NewsDto> result = queryForList(sql.toString(), NewsDto.class);
        return result;
    }
}
