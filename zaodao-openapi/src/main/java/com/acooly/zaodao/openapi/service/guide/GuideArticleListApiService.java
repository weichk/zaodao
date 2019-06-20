package com.acooly.zaodao.openapi.service.guide;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.Strings;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.guide.GuideArticleListRequest;
import com.acooly.zaodao.openapi.message.guide.GuideArticleListResponse;
import com.acooly.zaodao.platform.dto.GuideArticleDto;
import com.acooly.zaodao.platform.entity.Article;
import com.acooly.zaodao.platform.order.GuideArticleListOrder;
import com.acooly.zaodao.platform.service.RedisClientService;
import com.acooly.zaodao.platform.service.manage.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 导游文章列表
 *
 * @author xiaohong
 * @create 2017-12-06 14:00
 **/
@OpenApiService(name = "guideArticleList", desc = "导游文章列表", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_guide", name = "导游")
public class GuideArticleListApiService extends BaseApiService<GuideArticleListRequest, GuideArticleListResponse> {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisClientService redisClientService;

    @Value("${site.gateway.url}")
    private String siteGatewayUrl;

    @Override
    protected void doService(GuideArticleListRequest request, GuideArticleListResponse response) {
        GuideArticleListOrder order = request.toOrder(GuideArticleListOrder.class);
        order.setGid(Ids.gid());
        PageResult<GuideArticleDto> pageResult = articleService.getArticleList(order);
        pageResult.throwExceptionIfNotSuccess();
        String token = UUID.randomUUID().toString();
        if (Strings.isNotBlank(request.getUserId())) {
            redisClientService.setRedis(token, request.getUserId());
        }

        response.setPageResult(pageResult, (u,t)->{
            t.setContent(StringUtils.stripStart(removeMarker(t.getContent()), "\r\n").replaceAll("[\\s\\u00A0]+", ""));
            t.setUrl(String.format("%s/portal/articleInfo/article_%s.html?token=%s", siteGatewayUrl, t.getArticleId(), token));
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
