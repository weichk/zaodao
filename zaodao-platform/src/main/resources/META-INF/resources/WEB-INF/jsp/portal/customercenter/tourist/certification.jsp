<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!-- page: 实名认证 -->

<div class="page_wrap">

    <div class="mpage_title">实名认证</div>

    <form action="/portal/services/customer/certification.html" method="post" name="personal_id" id="certification_from_id" class="account_conf_form">
        <div class="form_item">
            <label for="realname">真实姓名</label><input type="text" name="realName" datatype="s2-40" nullmsg="真实姓名不能为空"
                                                     errormsg="请输入您的真实姓名！" id="realname" class="text">

            <div class="Validform_checktip"></div>
        </div>

        <div class="form_item">
            <label for="id_number_id">身份证号码</label><input type="text" name="idNumber" datatype="idcard"
                                                          nullmsg="身份证号码不能为空" errormsg="请输入正确的身份证号码！" id="id_number_id"
                                                          class="text">

            <div class="Validform_checktip"></div>
        </div>

        <input type="submit" name="confirm_account_conf" value="确定" class="uni_btn pie">
    </form>
</div>

<script type="text/javascript" src="/js/platformjs/certification.js" charset="utf-8"></script>
