<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>

<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>${article.title}</title>
    <link rel="stylesheet" type="text/css" href="/js/layer/mobile/need/layer.css"/>
    <link rel="stylesheet" href="/css/bootstrap.css"/>
    <link rel="stylesheet" href="/css/travel_diary_h5.css"/>
    <style type="text/css">
        .row{
            margin-left:0px;
            margin-right:0px;
        }
    </style>
</head>
<body style="margin-bottom: 2.5em; ">
    <div class="container">
        <input type="hidden" value="${isLogin}" id="is_login_id"/>
        <input type="hidden" value="${hasRedBag}" id="hava_red_bag_id"/>
        <input type="hidden" value="${point}" id="get_point_id"/>
        <input type="hidden" value="${isShutup}" id="is_shutup_id"/>
        <input type="hidden" name="articleId" value="${id}" id="articleId"/>
        <!--帖子标题-->
        <div class="row clearfix head">
            <h3>${article.title}</h3>
            <section class="head-count">
                <span><i class="sprite icon_eye_bl" title="浏览数量"></i>${article.readCount}</span>
                <span><i class="sprite icon_dial_bl" title="文章数量"></i>${articleCount}</span>
                <span><i class="sprite icon_ensh_bl" title="收藏数量"></i>${article.enshrineCount}</span>
            </section>
        </div>
        <!--帖子内容-->
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12 column article">
                 <div class="row">
                    <div class="media">
                        <a href="#" class="pull-left">
                            <c:choose>
                                <c:when test="${headImg != null && headImg != ''}">
                                    <img src="${headImg}" alt="" />
                                </c:when>
                                <c:otherwise>
                                    <img src="/images/default_header.png" alt="" class="pie" />
                                </c:otherwise>
                            </c:choose>
                        </a>
                        <div class="media-body">
                            <div class="media-heading uname">${userName}</div>
                            <div>
                                <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${article.createTime}" />
                            </div>
                        </div>
                    </div>
                 </div>
                <div class="row">
                    <section class="article-content">
                        <p>${article.content}</p>
                    </section>
                </div>
            </div>
        </div>
        <hr />
        <a id="article_review_back" href="#article_review_item_id"></a>
        <!--帖子回复-->
        <div id="article_review_item_id"></div>
        <!--分页-->
        <div class="row footer_pages" >
            <div class="pages" id="reviewsPageId"></div>
        </div>
        <!--回复帖子-->
        <!-- 2017-12-27 xh delete -->
        <!-- 2018-07-13 xh recovery -->
        <!-- 2018-07-13 xh add,如果是1级导游或者2级导游则可以评论 -->
        <c:if test="${isTourGuide == 1}">
            <div class="navbar navbar-default navbar-fixed-bottom" style="min-height: 2.5em;">
                <div class="navbar-inner">
                    <div class="input-group col-md-12 col-sm-12 col-xs-12" >
                        <input id="article_review_content_text" type="text" class="form-control" placeholder="回复内容" style="height: 2.5em;">
                        <span class="input-group-btn">
                            <button id="article_review_content_submit" class="btn btn-warning" type="submit">发表</button>
                        </span>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
    <!-- 2018-07-13 xh add,如果是1级导游或者2级导游则查看评论 -->
    <c:if test="${isTourGuide == 1}">
    <!--回复模板-->
    <script id="atricleReview-template" type="text/html">
        {{#each rows}}
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12 column article">
                <div class="row">
                    <div class="media">
                        <a href="#" class="pull-left">
                            {{#if headImg}}
                            <img src="{{headImg}}" />
                            {{else}}
                            <img src="/images/default_header.png" />
                            {{/if}}
                        </a>
                        <div class="media-body">
                            <div class="media-heading uname">
                                {{#if userName}}
                                {{userName}}
                                {{else}}
                                {{mobileNo}}
                                {{/if}}
                            </div>
                            <div>{{articleReview.createTime}} </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <section>
                        <p>{{{articleReview.content}}}</p>
                    </section>
                </div>
            </div>
        </div>
        <hr />
        {{/each}}
    </script>
    </c:if>

    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/js/layer/mobile/layer.js" ></script>
    <script src="/js/handlebars-v4.0.5.js" charset="utf-8"></script>
    <script src="/js/common/myif.js" charset="utf-8"></script>
    <script src="/js/common/handlebarsextend.js" charset="utf-8"></script>
    <script src="/js/common/imageCaptcha.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/jqPaginator.js"></script>
    <script type="text/javascript" src="/js/platformjs/articleInfo.js"></script>
</body>
</html>
