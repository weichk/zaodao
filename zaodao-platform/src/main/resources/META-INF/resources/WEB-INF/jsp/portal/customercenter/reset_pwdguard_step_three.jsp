




<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp"/>
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
    <link rel="stylesheet" href="/css/find_pwd.css"/>
</head>

<body>
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=customerCenter"/>

<div class="find_pwd_content apply_guide_content pie">
    <h2 class="find_pwd_t"><span class="yellow">设置密保</span></h2>
    <ul class="find_pwd_nav apply_guide_nav step3">
        <li class="n1">
            <span class="n pie">1</span>
            <p>确认身份</p>
        </li>
        <li class="n2">
            <span class="n pie">2</span>
            <p>修改问题</p>
        </li>
        <li class="n3">
            <span class="n pie">3</span>
            <p>完成</p>
        </li>
    </ul>
    <div class="apply_guide_finish">
        <img src="/images/find_pwd_success.png" alt="">
        <p class="info">恭喜您,密保成功！
            <br>请牢记您的答案！</p>
        <a href="/portal/services/customer/customeIndex.html" class="find_pwd_btn">确定</a>
    </div>
</div>

<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>
</body>
</html>