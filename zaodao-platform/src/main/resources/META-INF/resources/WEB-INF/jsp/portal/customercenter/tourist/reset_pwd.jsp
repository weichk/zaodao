<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!-- page: 修改密码 -->

<div class="page_wrap">

    <div class="mpage_title">修改密码</div>

    <form action="/portal/services/customer/resetPassword.html" method="post" name="reset_pwd" class="account_conf_form" id="reset_pws_form_id">
        <div class="form_item">
            <label for="old_password">原密码</label><input type="password" name="oldPassword" datatype="*6-16" nullmsg="请输入原密码" errormsg="原密码不正确！" id="old_password" class="text">

            <div class="Validform_checktip"></div>
        </div>

        <div class="form_item">
            <label for="new_password">新密码</label><input type="password" name="newPassword" datatype="*6-16" nullmsg="密码不能为空" errormsg="请输入6-16位的密码！" id="new_password" class="text">

            <div class="Validform_checktip"></div>
        </div>

        <div class="form_item">
            <label for="valid_new_password">确认新密码</label><input type="password" name="reNewPassword" datatype="*" recheck="newPassword" nullmsg="请再次输入密码" errormsg="您两次输入的账号密码不一致！" id="valid_new_password" class="text">

            <div class="Validform_checktip"></div>
        </div>

        <input type="submit" name="confirm_account_conf" value="确定" class="uni_btn pie">
    </form>
</div>

<script type="text/javascript" src="/js/platformjs/reset_pwd.js" charset="utf-8"></script>
