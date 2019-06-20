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
    <h2 class="find_pwd_t"><a href="/portal/findPassword/intoFindPassword.html">忘记密码</a> > 密保找回</h2>

    <ul class="find_pwd_nav step3">
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
        <li class="n4 disabled">
            <span class="n pie">4</span>
            <p>完成</p>
        </li>
    </ul>

    <form action="/portal/findPassword/updatePassword.html" name="find_pwd_by_secret" class="find_pwd_form" id="find_pwd_update_form_id">
        <input type="hidden" name="mobileNo" value="${mobileNo}"/>
        <p class="ac">请您重新填写登录密码</p>
        <div class="form_item">
            <label for="">新密码</label><input type="password" name="password" datatype="*6-16" nullmsg="请输入新密码" errormsg="请输入6-16位的密码！" placeholder="请输入新密码" class="text">

            <div class="Validform_checktip"></div>
        </div>

        <div class="form_item">
            <label for="">确认密码</label><input type="password" name="rePassword" datatype="*6-16" recheck="password" nullmsg="密码不一致" errormsg="密码不一致" placeholder="请再次输入新密码" class="text">

            <div class="Validform_checktip"></div>
        </div>

        <input type="submit" name="submit" value="下一步" class="find_pwd_btn">
    </form>

</div>

<!-- footer -->
<jsp:include page="/portal/portlet/footer.html" />
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/platformjs/find_pwd_problem_three.js"></script>
</body>
</html>