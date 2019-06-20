<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!-- page: 修改手机号码 第二步 -->

<div class="page_wrap">

    <div class="mpage_title">修改手机号码</div>

    <form action="/portal/services/customer/resetMobileNo.html" method="post" name="binding_card" class="account_conf_form" id="reset_mobile_no_step_two_form_id">
        <div class="form_item">
            <label for="reset_new_mobile_no_id">新手机号码</label><input type="text" name="mobileNo" datatype="m" nullmsg="新手机号不能为空" errormsg="请输入正确的手机号！" id="reset_new_mobile_no_id" class="text">

            <div class="Validform_checktip"></div>
        </div>

        <div class="form_item">
            <label for="valid_code">验证码</label><span class="input_wrap"><input type="text" name="mobileCaptcha" datatype="*" nullmsg="请输入验证码" errormsg="验证码不正确！" placeholder="短信验证码" id="valid_code" class="text"><a href="javascript:void(0);" id="click_send_sms_id" onclick="clickSendCode()" class="get_phonecode">获取验证码</a></span>

            <div class="Validform_checktip"></div>
        </div>

        <input type="submit" name="confirm_account_conf" value="确定" class="uni_btn pie">
    </form>
</div>
<script type="text/javascript" src="/js/platformjs/reset_mobile_step_two.js" charset="utf-8"></script>
