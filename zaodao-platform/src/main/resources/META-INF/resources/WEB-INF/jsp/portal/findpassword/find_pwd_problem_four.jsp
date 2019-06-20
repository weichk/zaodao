<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp"/>
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
    <link rel="stylesheet" href="/css/find_pwd.css" />
</head>

<body class="grey_bg">
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=firstPage" />

<div class="find_pwd_content find_pwd_bysecret pie">
    <h2 class="find_pwd_t"><a href="/portal/findPassword/intoFindPassword.html.html">忘记密码</a> > 密保找回</h2>

    <ul class="find_pwd_nav step4">
        <li class="n1">
            <span class="n pie">1</span>
            <p>确认身份</p>
        </li>
        <li class="n2">
            <span class="n pie">2</span>
            <p>回答问题 </p>
        </li>
        <li class="n3">
            <span class="n pie">3</span>
            <p>重置密码</p>
        </li>
        <li class="n4">
            <span class="n pie">4</span>
            <p>完成</p>
        </li>
    </ul>

    <div class="find_pwd_step4">
        <img src="/images/find_pwd_success.png" alt="">
        <p class="info">恭喜您，密码修改成功！</p>
        <a href="/portal/portlet/login_register.html" class="find_pwd_btn">确定</a>
    </div>
</div>

<!-- footer -->
<jsp:include page="/portal/portlet/footer.html" />
<script type="text/javascript" src="/js/global.js"></script>
</body>
</html>