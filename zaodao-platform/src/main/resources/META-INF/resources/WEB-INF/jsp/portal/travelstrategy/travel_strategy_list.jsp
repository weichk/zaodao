<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp"/>
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
</head>


<body class="grey_bg">
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=travelStrategy"/>
<!-- banner -->
<jsp:include page="/portal/portlet/innerBanner.html?bannerType=travelStrategyBanner"/>
<div class="wrap">
    <div class="page_crumb">
        <div class="crum"><a href="/portal/travelStrategy/travelStrategyIndex.html">游者无疆</a> >${area}游者无疆</div>
        <div class="site_search_type1 pie">
            <form action="/portal/travelStrategy/travelStrategyList.html?area=${area}" method="post" name="search" class="sch_form">
                <input type="text" name="searchContent" value="${searchContent}" placeholder="请输入关键字" class="sch_input" id="search_content_id">
                <input type="submit" value="搜索" class="sch_submit">
            </form>
        </div>
    </div>

    <div class="commen_title"><span class="bold">${area}游者无疆</span></div>

    <ul class="travel_list_a" id="travel_strategy_list_div_id">
        <c:forEach items="${articleList}" var="e" varStatus="s">
            <li>
                <a target="_blank" href="/portal/guideHome/getArticleInfo_${e.id}.html" class="img"><img src="${e.cover}" alt=""></a>
                <div class="con">
                    <a target="_blank" href="/portal/guideHome/getArticleInfo_${e.id}.html" class="t">${e.title}</a>
                    <p class="summary">${e.content}</p>
                    <div class="f"><span class="view"><i class="sprite icon_eye_bl"></i>${e.readCount}</span><span class="like"><i
                            class="sprite icon_heart_bl"></i>${e.praiseCount}</span><span class="date"><fmt:formatDate value="${e.createTime}" pattern="yyyy-MM-dd"/></span></div>
                </div>
            </li>
        </c:forEach>
    </ul>
    <!--  -->
    <!--标记当前页数-->
    <input type="hidden" id="current_area_value_id" value="${area}">
    <input type="hidden" id="current_page_no_index_id" value="1">
    <div class="more_list_item last ac"><a href="javascript:void(0);" onclick="getMoreArticles()">查看更多<i class="arrow"></i></a></div>
</div>
<!-- main content end -->
<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>
<script type="text/javascript" src="/js/platformjs/travel_strategy_list.js"></script>
</body>
</html>