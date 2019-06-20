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

    <div class="find_pwd_content pie">
        <h2 class="find_pwd_t sp"><span class="yellow">忘记密码</span></h2>
        <div class="choose_con">
            <a href="/portal/findPassword/intoFindPasswordByPhone.html"><img src="/images/find_pwd_phone.png" alt=""><span>手机验证码找回</span></a>
            <a href="/portal/findPassword/intoFindPasswordProblemOne.html"><img src="/images/find_pwd_secret.png" alt=""><span>密保资料找回</span></a>
        </div>
    </div>

    <!-- footer -->
    <jsp:include page="/portal/portlet/footer.html" />
    <script type="text/javascript" src="/js/global.js"></script>
    </body>
</html>