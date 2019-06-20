<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>

<div class="popup popup_form popup_trade" style="width: 500px;">
    <div class="popup_box">
        <div class="p1">
            <div class="info red">${shopGoods.name} &nbsp;<span class="sale st" style="font-size: 12px;"></span>
            </div>
        </div>
        <div class="form-wrap1 mt10">
            <form id="credit_payment_form" action="#">
                <input type="hidden" id="deliveryType" value="${shopGoods.deliveryType}">
                <input type="hidden" id="goodsId" value="${shopGoods.id}">
                <input type="hidden" id="quantity" value="${quantity}"/>
                <p>
                    <label>应付积分：</label>
                    <span id="pointsAmount" class="payPrice">${quantity*shopGoods.credits}</span>&nbsp;积分&nbsp;
                    <span>(${shopGoods.credits} × ${quantity} )</span>
                </p>
                <p>
                    <label>登录密码：</label><input name="password" id="password" type="password" class="ml5" style="width:186px" datatype="*" nullmsg="请填写登录密码">
                </p>
                <p><label >验证码：</label>
                    <input name="captcha" id="captcha" type="text" style="width:120px" datatype="*" nullmsg="请填写验证码"/>
                    <img id="captchaImage" onclick="captchaImageRefresh()" src="/jcaptcha.jpg" title="看不清楚？点击更换"/>
                </p>
                <hr>
                <c:if test="${shopGoods.deliveryType == 2}">
                    <p><label >收货人：</label> <input type="text" id="realName" id="realName"  value="${realName}" </p>
                    <p><label >手机号码：</label> <input type="text" id="mobileNo" id="mobileNo" value="${mobileNo}"/></p>
                </c:if>
                <c:if test="${shopGoods.deliveryType == 1}">
                    <p><label >收货人：</label> <input type="text" id="realName" id="realName"  value="${realName}" </p>
                    <p><label >手机号码：</label> <input type="text" id="mobileNo" id="mobileNo" value="${mobileNo}"/></p>
                    <p>
                        <label >收货地址：</label>
                        <select name="provName" id="liveProvince"></select>
                        <select name="cityName" id="liveCity"></select>
                        <select name="distName" id="liveCounty"></select><br>
                        <label ></label>
                        <textarea rows="2" cols="100" style="width: 300px;height: 70px;" id="address" name="address">${address}</textarea>
                    </p>
                </c:if>
                <p class="mt10 ac">
                    <input class="btn btn-success" type="button" onclick="$.point.payment();" value="立即兑换">
                    <a href="javascript:;" onclick="$.aportal.closeLayer(this);" class="btn btn-default">取消</a>
                </p>
            </form>
        </div>
    </div>
</div>
<script src="/js/common/imageCaptcha.js" charset="utf-8"></script>
<script>
    addressInit('liveProvince', 'liveCity', 'liveCounty', '', '', '');
</script>
