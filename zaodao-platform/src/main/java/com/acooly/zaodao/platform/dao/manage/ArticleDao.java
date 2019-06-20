/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-05-22
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.Article;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 文章表 JPA Dao
 * <p>
 * Date: 2017-05-22 15:36:00
 *
 * @author zhike
 */
public interface ArticleDao extends EntityJpaDao<Article, Long>, ArticleCusDao {

	@Query(value = "select * from zd_article where article_type = ?1 ORDER BY create_time DESC limit 5", nativeQuery = true)
	List<Article> getEntityByType(String type);

	@Query(value = "select * from zd_article where article_type = ?1 and label = ?2 and article_status ='published' ORDER BY up_status DESC,create_time DESC,essence_status DESC limit 5", nativeQuery = true)
	List<Article> getEntityByTypeAndLabel(String type, String label);

	@Query("FROM Article WHERE userId = ?1")
	List<Article> getCusArticleCount(String userId);

	List<Article> findByUserId(String userId);

	@Query(value = "select count(*) from zd_article where user_id=?1 and article_type=?2 and article_status=?3", nativeQuery = true)
	int countByTypeAndStatus(String userId, String code, String status);

	@Query(value = "select count(*) from zd_article where user_id=?1 and article_status=?2", nativeQuery = true)
	int countUserArticles(String userId, String status);
}
