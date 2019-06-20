<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!-- page: 绑定银行卡 -->

<div class="page_wrap">

    <div class="mpage_title">绑定银行卡</div>

    <form action="/portal/services/customer/bindingCard.html" method="post" name="binding_card" id="binding_card_from_id" class="account_conf_form">
        <div class="form_item">
            <label for="bank_name">银行名称</label><select name="bankId" datatype="*" nullmsg="请选择银行" errormsg="请选择银行！" id="bank_name">
            <option value selected>请选择</option>
            <option value="CIB">兴业银行</option>
            <option value="CITIC">中信银行</option>
            <option value="BOC">中国银行</option>
            <option value="ICBC">工商银行</option>
            <option value="CCB">建设银行</option>
        </select>

            <div class="Validform_checktip"></div>
        </div>

        <div class="form_item">
            <label for="card_no_id">卡号</label><input type="text" name="bankcardNum" datatype="n13-19" nullmsg="卡号不能为空" errormsg="请输入正确的银行卡号！" class="text" id="card_no_id">

            <div class="Validform_checktip"></div>
        </div>
        <div class="form_item">
            <label for="realname">真实姓名</label><input type="text" name="realName" datatype="s2-40" nullmsg="真实姓名不能为空"
                                                         errormsg="请输入您的真实姓名！" id="realname" class="text" value="${sessionScope.sessionKeyUserInfo.realName}">

            <div class="Validform_checktip"></div>
        </div>
        <div class="form_item">
            <label for="id_number_id">身份证号码</label><input type="text" name="idNumber" datatype="idcard"
                                                          nullmsg="身份证号码不能为空" errormsg="请输入正确的身份证号码！" id="id_number_id"
                                                          class="text">

            <div class="Validform_checktip"></div>
        </div>
        <%--<div class="form_item">--%>
            <%--<label for="binding_card_mobile_no_id">手机</label><input type="text" name="mobileNo" value="${sessionScope.sessionKeyUserInfo.mobileNo}" datatype="m" nullmsg="手机号不能为空" errormsg="请输入正确的手机号！" id="binding_card_mobile_no_id" class="text">--%>

            <%--<div class="Validform_checktip"></div>--%>
        <%--</div>--%>
        <%--<div class="form_item">--%>
            <%--<label for="valid_code">验证码</label><span class="input_wrap"><input type="text" name="captcha" datatype="*" nullmsg="请输入验证码" errormsg="验证码不正确！" placeholder="短信验证码" id="valid_code" class="text"><a href="javascript:void(0);" id="click_send_sms_id" onclick="clickSendCode()" class="get_phonecode">获取验证码</a></span>--%>

            <%--<div class="Validform_checktip"></div>--%>
        <%--</div>--%>

        <input type="submit" name="confirm_account_conf" value="确定" class="uni_btn pie">
    </form>
</div>
<script type="text/javascript" src="/js/platformjs/binding_card.js" charset="utf-8"></script>
