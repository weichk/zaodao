<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp"/>
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
    <link rel="stylesheet" href="/css/news.css"/>
</head>
<body>
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=firstPage"/>

<!-- banner -->
<jsp:include page="/portal/portlet/innerBanner.html?bannerType=guideHomeBanner"/>

<!-- 新闻列表 -->
<div class="news_content wrap">
    <div class="breadcrum"><a href="/portal/index.html">导游论坛</a> >
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
    </div>

    <div class="w730">
        <div class="news_wrap" id="">
            <div class="commen_title"><span class="yellow">
                 <c:if test="${type=='news'}">
                     旅情播报
                 </c:if>
                <c:if test="${type=='lighthouse'}">
                    时事热点
                </c:if>
                <c:if test="${type=='notice'}">
                    公告
                </c:if>
                <c:if test="${type=='activityList'}">
                    活动列表
                </c:if>
            </span></div>
            <%--<form action="#" name="news_filter" class="news_sort mt20">--%>
                <%--<select name="topic" id="topic" class="topic">--%>
                    <%--<option value="1">全部主题</option>--%>
                    <%--<option value="2">主题1</option>--%>
                    <%--<option value="3">主题2</option>--%>
                <%--</select>--%>
                <%--<select name="date" id="date" class="date">--%>
                    <%--<option value="1">全部时间</option>--%>
                    <%--<option value="2">今天</option>--%>
                    <%--<option value="3">昨天</option>--%>
                <%--</select>--%>
            <%--<select name="sort" id="sort" class="sort">--%>
                    <%--<option value="1">默认排序</option>--%>
                    <%--<option value="2">升序</option>--%>
                    <%--<option value="3">降序</option>--%>
                <%--</select>--%>
            <%--</form>--%>
            <div id="news_list_id">

            </div>
        </div>
    </div>
    <!-- news left list end -->

    <div class="w260">
        <div class="news_side_wrap">
            <div class="news_side_t">公告</div>
            <ul class="news_side_list">
                <c:forEach items="${noticeList}" var="e" varStatus="s">
                    <li><a href="/portal/news/newsInfo.html?id=${e.id}&type=notice">${e.title}</a></li>
                </c:forEach>
            </ul>
            <div class="ar">
                <a href="/portal/news/newsinfoList.html?type=notice">更多 》</a></div>
        </div>
    </div>
    <!-- news side end -->

</div>
<!-- main content end -->

<%--根据tag分类列表--%>
<script id="newsList-template" type="text/html">
    {{#each data.newsList}}
    <ul class="news_list">
        <li>
            <a href="/portal/news/newsInfo.html?id={{id}}&type={{../data.type}}" class="t">{{title}}</a>
            <p class="summary">{{subject}}</p>
            <div class="f"><span class="time">{{createTime}}</span><span class="view"><i class="sprite icon_eye_bl"></i>{{hits}}</span></div>
        </li>
    </ul>
    {{/each}}

    <div class="pages" id="newsPageId">

    </div>
</script>
<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>
<script type="text/javascript" src="/js/platformjs/news_list.js" charset="utf-8"></script>
</body>
</html>