<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp" />
        <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp" />
    </head>

    <body>
        <!-- 导航 -->
        <jsp:include page="/portal/portlet/nav.html?pageIndex=firstPage" />
        <!-- banner -->
        <jsp:include page="/portal/portlet/innerBanner.html?bannerType=hotGuideBanner" />
        <!-- 搜索结果 -->
        <jsp:include page="/portal/portlet/searchResult.html" />
        <!-- footer -->
        <jsp:include page="/portal/portlet/footer.html" />

        <script type="text/javascript" src="/js/platformjs/searchResult.js" charset="utf-8"></script>
    </body>
</html>