<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/metaLogin.jsp" />
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp" />
</head>
<body>
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=firstPage"/>

<div class="login_content">
    <div class="wrap">
        <div class="l"><img src="/images/login_words.png" alt="导游展现才货和成长的新平台,旅游者选择导游的首选平台"></div>
        <div class="login_box">
            <ul class="login_tabnav">
                <li><a href="javascript:void(0)">登录</a><i class="line"></i></li>
                <li id="register_li_id"><a href="javascript:void(0)">注册</a></li>
            </ul>

            <div class="login_con">
                <div class="item">
                    <form id="login_from_id" action="/portal/services/login.html" method="post" name="login_form" class="login_form">
                        <div class="form_item"><input type="text" id="login_mobile_no_id" autocomplete="off" name="mobileNo"
                                                      datatype="m" nullmsg="请输入手机号码！" errormsg="请输入正确的手机号码！" placeholder="请输入手机号码" class="phone_num">
                            <i class="login_phone"></i>
                            <div class="Validform_checktip"></div>
                        </div>
                        <div class="form_item"><input type="password" name="password" datatype="*6-16"
                                                      nullmsg="请输入密码" errormsg="密码错误！" placeholder="请输入登录密码"
                                                      autocomplete="off" class="pwd">
                            <i class="login_key"></i>
                            <div class="Validform_checktip"></div></div>
                        <div class="setting">
                            <%--<input type="radio" name="remember" value="1" class="remember">7天免登录--%>
                            <a href="/portal/findPassword/intoFindPassword.html" class="forget">忘记密码?</a></div>
                        <input type="submit" name="login" value="立即登录" id="login_btn" class="btn pie">
                    </form>
                </div>
                <div class="item" style="display: none" id="register_div_id">
                    <form id="regist_form_id" action="/portal/services/register.html" method="post" name="regist_form" class="regist_form">
                        <div class="form_item"><input type="text" name="mobileNo" id="register_mobile_no_id" datatype="m" nullmsg="请输入手机号码！" errormsg="请输入正确的手机号码！" ajaxurl="/portal/services/verifyMobileRepeat.html" placeholder="请输入手机号码"><div class="Validform_checktip"></div></div>
                        <div class="form_item">
                            <input type="text" name="userName" id="register_user_name_id" placeholder="请输入昵称"
                                                      datatype="s2-7" nullmsg="昵称不能为空！" errormsg="2-7个字符，支持中英文、数字">
                            <div class="Validform_checktip"></div>
                        </div>
                        <div class="form_item"><input type="text" name="mobileCaptcha" datatype="*" nullmsg="请输入短信验证码！" errormsg="短信验证码错误！" placeholder="短信验证码"><a href="javascript:void(0);" id="click_send_sms_id" class="get_sms" onclick="clickSendCode()">获取验证码</a><div class="Validform_checktip"></div></div>
                        <div class="form_item"><input type="password" name="password" datatype="*6-16" plugin="passwordStrength" nullmsg="请输入密码" errormsg="请输入6-16位的密码！" placeholder="请输入密码">
                            <div class="Validform_checktip"></div></div>
                        <div class="form_item"><input type="password" name="rePassword" recheck="password" datatype="*" placeholder="请再次输入密码" errormsg="您两次输入的账号密码不一致！"><div class="Validform_checktip"></div></div>
                        <div class="form_item">
                            <input type="radio" name="protocolRadio"> <strong>我同意</strong><a href="javascript:void(0);" class="service_rules yellow">《早导网用户服务协议》</a>
                        </div>
                        <input type="submit" name="regist" value="立即注册" id="regist_btn" class="btn pie">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/platformjs/plat_login_register.js"></script>

</body>
</html>
