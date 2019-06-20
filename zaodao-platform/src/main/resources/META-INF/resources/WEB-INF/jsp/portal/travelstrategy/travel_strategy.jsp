<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/metaTravel.jsp" />
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp" />
</head>


<body class="grey_bg">
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=travelStrategy" />
<!-- banner -->
<jsp:include page="/portal/portlet/innerBanner.html?bannerType=travelStrategyBanner" />
<div class="wrap">
    <!-- 搜索 -->
    <%--<jsp:include page="/portal/portlet/search.html" />--%>

    <div id="travel_strategy_div_id">
    <c:forEach items="${areaList}" var="e" varStatus="s">
    <div class="commen_title"><span class="bold">${e.areaName}游者无疆</span><a target="_blank" href="/portal/travelStrategy/travelStrategyList.html?area=${e.areaName}" class="more">更多 》</a></div>
        <ul class="col3_list">
        <c:forEach items="${articleList}" var="ae" varStatus="as">
                <c:if test="${e.areaName == ae.area}">
                    <li>
                        <a target="_blank" href="/portal/guideHome/getArticleInfo_${ae.id}.html" class="travel_list_img"><img src="${ae.cover}" alt=""></a>
                        <div class="travel_list_info">
                            <a target="_blank" href="/portal/guideHome/getArticleInfo_${ae.id}.html" class="t">${ae.title}</a>
                            <div class="f"><span class="view"><i class="sprite icon_eye_bl"></i>${ae.readCount}</span><span class="like"><i class="sprite icon_heart_bl"></i>${ae.praiseCount}</span></div>
                        </div>
                    </li>
                </c:if>
        </c:forEach>
        </ul>
    </c:forEach>
    </div>

    <!--标记当前页数-->
    <input type="hidden" id="current_page_no_index_id" value="1">
    <div class="more_list_item last ac"><a href="javascript:void(0)" onclick="getMore()">查看更多<i class="arrow"></i></a></div>
</div>
<!-- main content end -->
<!-- footer -->
<jsp:include page="/portal/portlet/footer.html" />
<script type="text/javascript" src="/js/platformjs/travel_strategy.js"></script>
</body>
</html>