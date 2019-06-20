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
<jsp:include page="/portal/portlet/nav.html?pageIndex=firstPage"/>
<!-- banner -->
<jsp:include page="/portal/portlet/banner.html?type=indexBanner"/>
<%--<!-- 人气导游 -->--%>
<%--<jsp:include page="/portal/guide/popularTours.html"/>--%>
<!-- 导游论坛新闻 -->
<jsp:include page="/portal/portlet/firstNews.html"/>
<c:if test="${sessionScope.sessionKeyUserInfo != null}">
    <!-- 右侧固定签到 -->
    <a href="javascript:void(0);" class="fixed_signin" onclick="signIn()"></a>
</c:if>
<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>
<%--<script type="text/javascript" src="/js/choose_single_date.js"></script>--%>
<%--<script type="text/javascript" src="/js/platformjs/index.js" charset="utf-8"></script>--%>
</body>
</html>