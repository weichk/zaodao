<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/metaAbout.jsp"/>
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
</head>

<body class="grey_bg">
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html"/>
<div class="common_page_content wrap">
    <div class="side_nav">
        <ul class="nav">
            <li><a href="javascript:void(0);" onclick="pageIndex('aboutus')" id="aboutus">关于我们</a></li>
            <li><a href="javascript:void(0);" onclick="pageIndex('serviceAssurance')" id="serviceAssurance">服务保障</a></li>
            <li><a href="javascript:void(0);" onclick="pageIndex('supportingOrganizations')" id="supportingOrganizations">支持机构</a></li>
            <li><a href="javascript:void(0);" onclick="pageIndex('guideProblem')" id="guideProblem">导游问答</a></li>
            <li><a href="javascript:void(0);" onclick="pageIndex('siteProblem')" id="siteProblem">反馈中心</a></li>
        </ul>
    </div>
    <div class="content">
        <!-- 关于我们 -->
        <c:if test="${childIndex == 'aboutus'}">
            <jsp:include page="aboutme.jsp"/>
        </c:if>
        <!-- 服务保障 -->
        <c:if test="${childIndex == 'serviceAssurance'}">
            <jsp:include page="service_assurance.jsp"/>
        </c:if>
        <!-- 支持机构 -->
        <c:if test="${childIndex == 'supportingOrganizations'}">
            <jsp:include page="supporting_organizations.jsp"/>
        </c:if>
        <!-- 导游常见问题 -->
        <c:if test="${childIndex == 'guideProblem'}">
            <jsp:include page="guide_problem.jsp"/>
        </c:if>
        <!-- 网站常见问题 -->
        <c:if test="${childIndex == 'siteProblem'}">
            <jsp:include page="site_problem.jsp"/>
        </c:if>
    </div>
    <!-- main content end -->
    <!-- footer -->
    <jsp:include page="/portal/portlet/footer.html"/>

    <script>
        $(document).ready(function () {
            var childIndex = getUrlValue("childIndex");
            $("#" + childIndex).addClass("active");
        });

        function pageIndex(type) {
            window.location.href = "/portal/about.html?childIndex=" + type;

        };
    </script>
</body>
</html>

