<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!-- page: 账户信息 -->

<div class="account_info">
    <div class="score">我的积分：<span class="num">${pointAccount.available}</span>
        <div class="signature">
            <c:if test="${creditSignin.todayCreditSignin == false || creditSignin == null}">
                <a href="#" class="singin" onclick="signIn()">签到</a>
                <span class="tips">今日未签到</span>
            </c:if>
            <c:if test="${creditSignin.todayCreditSignin == true}">
                <span class="singin">已签到</span>
                <span class="tips">已连续签到${creditSignin.times}天</span>
            </c:if>
        </div>
    </div>
</div>

<div class="account_conf page_wrap mt20">

    <div class="col i1">
        <div class="label">实名认证:</div>
        <c:if  test="${sessionScope.sessionKeyUserInfo.isCertification == 1}">
            <div class="con">${sessionScope.sessionKeyUserInfo.realName} | <c:out value="${fn:substring(sessionScope.sessionKeyUserInfo.idNumber,0,1)}****************"></c:out></div>
            <span class="info">已认证</span>
        </c:if>
        <c:if  test="${sessionScope.sessionKeyUserInfo.isCertification == 0}">
            <div class="con">未认证</div>
            <%--<a href="/portal/services/customer/childpage.html?childIndex=certification" id="personal_id" class="uni_btn pie">实名认证</a>--%>
        </c:if>
    </div>
    <div class="col">
        <div class="label">手机认证:</div>
        <div class="con">
            <div class="tel"><c:out value="${fn:substring(sessionScope.sessionKeyUserInfo.mobileNo,0,3)}****${fn:substring(sessionScope.sessionKeyUserInfo.mobileNo,7,11)}"></c:out></div>
            <p>已通过手机短信验证认证</p>
        </div>
        <a href="/portal/services/customer/childpage.html?childIndex=resetTelOne" class="uni_btn pie">修改</a>
    </div>
    <div class="col">
        <div class="label">登陆密码:</div>
        <div class="con">
            <p>为了您的交易和账户安全，请确保密码妥善<br>保管，不要泄露密码给任何人!</p>
        </div>
        <a href="/portal/services/customer/childpage.html?childIndex=resetPwd" class="uni_btn pie">修改</a>
    </div>
    <div class="col">
        <div class="label">密保问题：</div>
        <div class="con">
            <p>您已设置密保问题。请牢记您的答案。 </p>
        </div>
        <a href="/portal/services/resetPwdguard/stepOne.html" id="reset_pwdguard" class="uni_btn pie">修改</a>
    </div>
    <c:if test="${sessionScope.sessionKeyUserInfo.isTourGuide == 1}">
        <div class="col">
            <div class="label">实名绑卡：</div>
            <div class="con">
                <c:choose>
                    <c:when test="${sessionScope.sessionKeyCard != null}">
                        <p>已实名绑卡：${sessionScope.sessionKeyCard.cardName}（${sessionScope.sessionKeyCard.showCardNo}）</p>
                    </c:when>
                    <c:otherwise>
                        <p>未实名绑卡。请尽快实名绑卡，否则无法进行资金交易。</p>
                        <p class="yellow">注意：绑定银行卡前，必须先通过实名认证！</p>
                    </c:otherwise>
                </c:choose>
            </div>
            <a href="javascript:void(0);" onclick="intoBindingCard(${sessionScope.sessionKeyUserInfo.isCertification})" class="uni_btn pie">
                <c:choose>
                    <c:when test="${sessionScope.sessionKeyCard != null}">
                        换绑卡
                    </c:when>
                    <c:otherwise>
                        立即绑卡
                    </c:otherwise>
                </c:choose>
            </a>
        </div>
    </c:if>
</div>
<script type="text/javascript" src="/js/platformjs/tourist_account_info.js" charset="utf-8"></script>