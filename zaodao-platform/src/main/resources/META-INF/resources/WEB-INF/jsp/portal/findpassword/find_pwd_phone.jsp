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

<div class="find_pwd_content find_pwd_byphone pie">
    <h2 class="find_pwd_t"><a href="/portal/findPassword/intoFindPassword.html">忘记密码</a> > 验证码找回</h2>
    <form action="/portal/findPassword/portlet/findPasswordByPhone.html" method="post" name="find_pwd_by_phone" class="find_pwd_form">
        <p class="ac">请填写您的手机验证码，来完成密码修改</p>
        <div class="form_item">
            <label for="find_pwd_mobileNo_id">手机</label><input id="find_pwd_mobileNo_id" type="text" name="mobileNo" datatype="m" nullmsg="请输入手机号码！" errormsg="请输入正确的手机号码！" class="text" ajaxurl="/portal/findPassword/verifyMobileRepeatHave.html">

            <div class="Validform_checktip"></div>
        </div>

        <div class="form_item">
            <label for="">验证码</label><span class="get_code_box"><input type="text"  name="mobileCaptcha" datatype="*" nullmsg="请输入短信验证码！" errormsg="短信验证码错误！" placeholder="短信验证码" class="text"><a href="javascript:void(0)" id="click_send_sms_id" class="get_code" onclick="clickSendCode()">获取验证码</a></span>

            <div class="Validform_checktip"></div>
        </div>

        <div class="form_item">
            <label for="">密码</label><input type="password" name="password" datatype="*6-16" nullmsg="请输入密码" errormsg="请输入6-16位的密码！" placeholder="请输入密码" class="text">

            <div class="Validform_checktip"></div>
        </div>

        <div class="form_item">
            <label for="">确认密码</label><input type="password" name="validPassword" datatype="*" recheck="password" nullmsg="请再次输入密码" errormsg="您两次输入的账号密码不一致！" placeholder="请再次输入密码" class="text">

            <div class="Validform_checktip"></div>
        </div>

        <input type="submit" name="submit" value="确定" class="find_pwd_btn">
    </form>

</div>

<!-- footer -->
<jsp:include page="/portal/portlet/footer.html" />
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/platformjs/find_password.js"></script>
</body>
</html>