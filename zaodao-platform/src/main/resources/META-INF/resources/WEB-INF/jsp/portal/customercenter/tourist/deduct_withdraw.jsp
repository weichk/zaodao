<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!-- page: 充值提现 -->

<div class="check_io page_wrap">

    <div class="mslide_tabs">
        <ul class="nav">
            <%--<li><a id="check_in_nav" class="current" href="#">账户充值</a></li>--%>
            <li><a id="check_out_nav" class="current" href="#">账户提现</a></li>
        </ul>
        <span class="m_indicator"></span>
    </div>

    <div class="mslide_content mt30">

        <%--<div id="check_in_con" class="item">--%>
            <%--<div class="tips ac"><span>温馨提示：</span>输入金额之后跳转到银行网上银行使用银行卡和密码支付充值，充值界面以第三方支付和银行界面为准。</div>--%>

            <%--<form action="/portal/services/deductWithdraw/tradeCreate.html" name="checkin_form" class="check_form" id="deposit_amount_form_id">--%>
                <%--<div class="form_item"><label>用户名</label><span class="info">${sessionScope.sessionKeyUserInfo.userName}</span></div>--%>

                <%--<div class="form_item">--%>
                    <%--<label for="deposit_amount_id">充值金额</label><input type="text" name="amount" id="deposit_amount_id" class="minput_text" datatype="amount" nullmsg="充值金额不能为空" errormsg="请填写数字,（小数位不超过2位）">--%>
                    <%--<div class="Validform_checktip"></div>--%>
                <%--</div>--%>
                <%--<input type="submit" value="充值" name="checkin_btn" class="checkin_btn uni_btn pie">--%>
            <%--</form>--%>
        <%--</div>--%>
        <!-- item -->

        <div id="check_out_con" class="item">
            <div class="tips ac"><span>温馨提示：</span>周末或非工作时间因第三方支付原因，故障处理有可能延后，建议在工作时间进行提现操作。</div>

            <form action="/portal/services/deductWithdraw/withdraw.html" name="checkin_form" class="check_form" id="withdraw_amount_from_id">
                <input type="hidden" value="${balance}" id="customer_balance_id">
                <div class="form_item"><label>账户余额</label><span class="info yellow">${balance}</span></div>
                <div class="form_item"><label>银行名称</label>
                    <c:choose>
                        <c:when test="${customerCard != null&&customerCard.cardName != null}">
                            <input type="hidden" value="1" id="is_bind_card_id">
                            <span class="info">${customerCard.cardName}  尾号（${customerCard.showCardNo}）</span><span class="alt yellow">仅限本人银行卡</span>
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" value="0" id="is_bind_card_id">
                            <span class="info">未绑卡,请先绑定银行卡</span><a href="/portal/services/customer/childpage.html?childIndex=bindingCard"><span class="alt yellow">--->绑卡</span></a>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="form_item">
                    <label for="checkout_num">提现金额</label><input type="text" name="amount" id="withdraw_amount_id" class="minput_text" datatype="amount" nullmsg="提现金额不能为空" errormsg="请填写数字,（小数位不超过2位）"> 元
                    <div class="Validform_checktip"></div>
                </div>
                <div class="form_item">
                    <label for="valid_code">验证码</label><span class="input_wrap"><input type="text" name="captchaCode" datatype="*" nullmsg="请输入验证码" errormsg="验证码不正确！" placeholder="短信验证码" id="valid_code" class="minput_text"><a href="javascript:void(0);" id="click_send_sms_id" onclick="clickSendCode()" class="get_phonecode">获取验证码</a></span>
                    <div class="Validform_checktip"></div>
                </div>
                <input type="submit" value="提现" name="checkin_btn" class="checkin_btn uni_btn pie">
            </form>
        </div>
        <!-- item -->
    </div>

</div>
<script type="text/javascript" src="/js/platformjs/deduct_withdraw.js" charset="utf-8"></script>
<!-- tab切换 -->
<script type="text/javascript">
    $(function() {

        // tab slide function
        function tabSlide(nav, idc, item) {
            var indicator = $(idc),
                indicatorHalfWidth = indicator.width() / 2,
                lis = $(nav).children('li');

            $(nav).tabs(item, {
                effect: 'fade',
                fadeOutSpeed: 0,
                fadeInSpeed: 400,
                onBeforeClick: function(event, index) {
                    var li = lis.eq(index),
                        newPos = li.position().left + (li.width() / 2) - indicatorHalfWidth;
                    indicator.stop(true).animate({
                        left: newPos
                    }, 600, 'easeInOutExpo');
                }
            });
        }
        tabSlide(".mslide_tabs .nav", ".m_indicator", ".mslide_content .item");
    });
</script>
