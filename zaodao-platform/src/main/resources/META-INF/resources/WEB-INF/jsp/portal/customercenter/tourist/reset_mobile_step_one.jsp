<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!-- page: 修改手机号码 -->

<div class="page_wrap">

    <div class="mpage_title">修改手机号码</div>

    <form action="/portal/services/customer/checkMobileNoAndMobileCaptcha.html" method="post" class="account_conf_form" id="reset_mobile_no_step_one_form_id">
        <input type="hidden" name = "mobileNo" id="reset_old_mobile_no_id" value="${sessionScope.sessionKeyUserInfo.mobileNo}">
        <div class="form_item">
            <label for="">手机</label><span class="info"><c:out value="${fn:substring(sessionScope.sessionKeyUserInfo.mobileNo,0,3)}****${fn:substring(sessionScope.sessionKeyUserInfo.mobileNo,7,11)}"></c:out></span>

            <div class="Validform_checktip"></div>
        </div>
        <div class="form_item">
            <label for="valid_code">验证码</label><span class="input_wrap"><input type="text" name="mobileCaptcha" datatype="*" nullmsg="请输入验证码" errormsg="验证码不正确！" placeholder="短信验证码" id="valid_code" class="text"><a href="javascript:void(0);" id="click_send_sms_id" onclick="clickSendCode()" class="get_phonecode">获取验证码</a></span>

            <div class="Validform_checktip"></div>
        </div>

        <input type="submit" name="confirm_account_conf" value="下一步" class="uni_btn pie">
    </form>
</div>

<script type="text/javascript" src="/js/platformjs/reset_mobile_step_one.js" charset="utf-8"></script>