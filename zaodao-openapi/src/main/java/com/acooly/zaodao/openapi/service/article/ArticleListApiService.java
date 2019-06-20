/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 14:47 创建
 *
 */
package com.acooly.zaodao.openapi.service.article;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.article.ArticleListRequest;
import com.acooly.zaodao.openapi.message.article.ArticleListResponse;
import com.acooly.zaodao.platform.entity.Article;
import com.acooly.zaodao.platform.enums.ArticleHotType;
import com.acooly.zaodao.platform.order.QueryArticleOrder;
import com.acooly.zaodao.platform.service.RedisClientService;
import com.acooly.zaodao.platform.service.manage.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiService(name = "articleList", desc = "文章列表", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_article", name = "文章")
public class ArticleListApiService extends BaseApiService<ArticleListRequest, ArticleListResponse> {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisClientService redisClientService;

    @Value("${site.gateway.url}")
    private String siteGatewayUrl;

    @Override
    protected void doService(ArticleListRequest request, ArticleListResponse response) {
        QueryArticleOrder queryArticleOrder = request.toOrder(QueryArticleOrder.class);
        BeanCopier.copy(request, queryArticleOrder);
        PageResult<Article> pageResult = articleService.getPageArticleList(queryArticleOrder);

        String token = UUID.randomUUID().toString();
        if (Strings.isNotBlank(request.getAppUserId())) {
            redisClientService.setRedis(token, request.getAppUserId());
        }

        response.setPageResult(
                pageResult,
                (article, articleDto) -> {
                    articleDto.setContent(StringUtils.stripStart(removeMarker(articleDto.getContent()), "\r\n").replaceAll("[\\s\\u00A0]+", ""));
                    articleDto.setArticleId(article.getId());
                    articleDto.setUrl(String.format("%s/portal/articleInfo/article_%s.html?token=%s", siteGatewayUrl, article.getId(), token));
                });
    }

    private String removeMarker(String htmlStr) {
        Pattern p_html1;
        Matcher m_html1;
        String IMG_html1 = "<img\\s[^>]+/>";
        p_html1 = Pattern.compile(IMG_html1);
        m_html1 = p_html1.matcher(htmlStr);
        htmlStr = m_html1.replaceAll(""); // 过滤IMG标签
        // <p>段落替换为换行
        htmlStr = htmlStr.replaceAll("<p .*?>", "\r\n");
        // <br><br/>替换为换行
        htmlStr = htmlStr.replaceAll("<br\\s*/?>", "\r\n");
        // 去掉其它的<>之间的东西
        htmlStr = htmlStr.replaceAll("\\<.*?>", "");
        if (htmlStr.length() > 110) {
            return StringUtils.substring(htmlStr, 0, 110) + "......";
        } else {
            return htmlStr;
        }
    }
}
