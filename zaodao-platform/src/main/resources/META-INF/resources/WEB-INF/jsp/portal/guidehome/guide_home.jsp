<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/metaGuideHome.jsp"/>
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
    <link rel="stylesheet" href="/css/guide_club.css"/>
</head>

<body class="grey_bg">
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=guideHome"/>
<!-- banner -->
<jsp:include page="/portal/portlet/banner.html?type=guideHomeBanner"/>
<!-- banner end -->
<div class="guide_club_home wrap" style="width: 1220px">
    <%--<!-- 搜索 -->--%>
    <%--<jsp:include page="/portal/portlet/search.html" />--%>
    <div class="gch_left whb">
        <div class="m40">
            <!-- 导游秘籍 -->
            <jsp:include page="/portal/guideHome/guideSecret.html"/>

            <!-- 带团日志 -->
            <jsp:include page="/portal/guideHome/experienceTour.html"/>

            <!-- 导游树洞 -->
            <jsp:include page="/portal/guideHome/guideTreeHole.html"/>

            <!-- 案例分析 -->
            <jsp:include page="/portal/guideHome/caseAnalysis.html"/>

        </div>
    </div>

    <div class="gch_right">
        <div class="whb">
            <div class="ml20 mr20 mb20">
                <h2 class="gch_right_t"><span>重要公告</span></h2>
                <ul class="gch_news_list">
                    <c:forEach items="${noticeList}" var="e" varStatus="s">
                        <li><a href="javascript:void(0);"
                               onclick="readNewsInfo('${e.id}','${sessionScope.sessionKeyUserInfo.isTourGuide}','notice','${sessionScope.sessionKeyUserInfo.userId}')">${e.title}</a>
                        </li>
                    </c:forEach>
                </ul>
                <div class="ar"><a target="_blank" href="/portal/news/newsinfoList.html?type=notice">更多 》</a></div>
            </div>
        </div>
        <div class="whb">
            <div class="ml20 mr20 mb20">
                <!-- 活动列表 -->
                <h2 class="gch_right_t"><span>活动列表</span></h2>
                <ul class="gch_news_list">
                    <c:forEach items="${activityList}" var="e" varStatus="s">
                        <li><a href="javascript:void(0);"
                               onclick="readNewsInfo('${e.id}','${sessionScope.sessionKeyUserInfo.isTourGuide}','activityList','${sessionScope.sessionKeyUserInfo.userId}')">${e.title}</a>
                        </li>
                    </c:forEach>
                </ul>
                <div class="ar"><a target="_blank" href="/portal/news/newsinfoList.html?type=activityList">更多 》</a>
                </div>
            </div>
        </div>
        <div class="whb">
            <div class="ml20 mr20 mb20">
                <!-- 话题排行 -->
                <jsp:include page="/portal/guideHome/getDateArticleList.html"/>
            </div>
        </div>
        <div class="whb">
            <div class="ml20 mr20 mb20">
                <!-- 时事热点 -->
                <jsp:include page="/portal/guideHome/lighthouseList.html"/>
            </div>
        </div>

    </div>
    <!-- right end -->
</div>
<!-- guide_club_home end -->

<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>


<!-- 右侧浮动按钮 -->
<%--<div class="fix_right_btn">--%>
<%--<a href="#" class="pie"><i class="sprite icon_car"></i>一键订车</a>--%>
<%--<a href="#" class="pie"><i class="sprite icon_building"></i>一键订房</a>--%>
<%--<a href="#" class="pie"><i class="sprite icon_ticket"></i>一键订票</a>--%>
<%--</div>--%>
<script type="text/javascript" src="/js/platformjs/guide_home.js"></script>
<!-- tab切换 -->
<script type="text/javascript" src="/js/jquery.tools.min.js"></script>
<script type="text/javascript" src="/js/jquery.easing.1.3.js"></script>
</body>
</html>