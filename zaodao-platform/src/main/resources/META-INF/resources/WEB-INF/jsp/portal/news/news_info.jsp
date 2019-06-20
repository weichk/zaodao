<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>${newsInfo.webTitle}-早导网</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="${newsInfo.keywords}">
    <meta http-equiv="description" content="${newsInfo.subject}">
    <meta charset="utf-8">
    <meta name="X-CSRF-TOKEN" content="${requestScope["org.springframework.security.web.csrf.CsrfToken"].token}"/>
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <meta name="renderer" content="webkit">
    <meta name="baidu-site-verification" content="FlZ0sOZiyI" />
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
    <link rel="stylesheet" href="/css/news.css"/>
</head>
<body class="grey_bg">
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=firstPage"/>

<%--<!-- banner -->--%>
<%--<jsp:include page="/portal/portlet/innerBanner.html?bannerType=travelStrategyBanner" />--%>

<!-- 新闻详情 -->

<div class="news_content news_detail wrap">

    <div class="w730">
        <div class="news_wrap">
            <div class="commen_title">
                <c:if test="${type=='news'}">
                    <a href="/portal/news/newsinfoList.html?type=news">旅情播报</a>
                </c:if>
                <c:if test="${type=='lighthouse'}">
                    <a href="/portal/news/newsinfoList.html?type=lighthouse">时事热点</a>
                </c:if>
                <c:if test="${type=='notice'}">
                    <a href="/portal/news/newsinfoList.html?type=notice">公告</a>
                </c:if>
                <c:if test="${type=='activityList'}">
                    <a href="/portal/news/newsinfoList.html?type=activityList">活动列表</a>
                </c:if>
             >${newsInfo.title}
            </div>
            <h2 class="article_title">${newsInfo.title}</h2>
            <div class="article_info">
                <%--<span class="author">作者：那年夏天</span>--%>
                <span class="date">发布时间：<fmt:formatDate value="${newsInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                <span class="view"><i class="sprite icon_eye_bl"></i>${newsInfo.hits}</span>
            </div>

            <div class="article_con">
                ${newsInfo.contentBody.body}
            </div>
        </div>

        <div class="article_guide">
            <div class="prev">
                <p>上一篇</p>
                <c:if test="${previousNews.id==null}" >
                    暂无
                </c:if>
                <c:if test="${previousNews.id!=null}" >
                    <a href="/portal/news/newsInfo.html?id=${previousNews.id}&type=${type}">${previousNews.title}</a>
                </c:if>
            </div>
            <div class="next">
                <p>下一篇</p>
                <c:if test="${nextNews.id==null}" >
                    暂无
                </c:if>
                <c:if test="${nextNews.id!=null}" >
                    <a href="/portal/news/newsInfo.html?id=${nextNews.id}&type=${type}">${nextNews.title}</a>
                </c:if>
            </div>
        </div>
    </div>
    <!-- news left list end -->

    <div class="w260">
        <div class="news_side_wrap">
            <div class="news_side_t">热点导读</div>
            <ul class="news_side_list i1">
                <c:forEach items="${hotNewsList}" var="e" varStatus="s">
                    <li><a href="/portal/news/newsInfo.html?id=${e.id}&type=${type}">${e.title}</a></li>
                </c:forEach>
            </ul>
            <div class="ar">
                <c:if test="${type=='news'}">
                    <a href="/portal/news/newsinfoList.html?type=news">
                </c:if>
                <c:if test="${type=='lighthouse'}">
                    <a href="/portal/news/newsinfoList.html?type=lighthouse">
                </c:if>
                <c:if test="${type=='notice'}">
                    <a href="/portal/news/newsinfoList.html?type=notice">
                </c:if>
                <c:if test="${type=='activityList'}">
                    <a href="/portal/news/newsinfoList.html?type=activityList">
                </c:if>
                更多 》</a></div>
        </div>
    </div>
    <!-- news side end -->

</div>
<!-- main content end -->
<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>
</body>
</html>