<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp"/>
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
</head>

<body>
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=firstPage"/>

<div class="find_pwd_content apply_guide_content pie">
    <h2 class="find_pwd_t"><span class="yellow">收银台</span></h2>

    <div class="apply_guide_finish">
        <img src="/images/find_pwd_success.png" alt="">
        <p class="info">恭喜您，支付成功！</p>
        <a href="#" class="find_pwd_btn">查看订单</a>
    </div>
</div>
<!-- content end -->
<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>
</body>
</html>
