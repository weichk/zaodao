<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp"/>
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
</head>

<body>
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=firstPage"/>

<div class="find_pwd_content check_out pie">
    <h2 class="find_pwd_t"><span class="yellow">收银台</span></h2>

    <div class="check_out_con">
        <%--<div class="t">请选择付款方式</div>--%>
        <div class="info">
            <div class="id">订单号：${orderInfo.orderNo}</div>
            <div class="cash_num">实付金额：<span class="num">
                <span class="xl">
                    <c:choose>
                        <c:when test="${payType == 'aboutGuide'}">
                            ${orderInfo.amount}
                        </c:when>
                        <c:otherwise>
                            <fmt:formatNumber value="${orderInfo.amount/100}" pattern="#,###.##" type="currency"/>
                        </c:otherwise>
                    </c:choose>

                </span>元</span></div>
        </div>
        <ul class="qr_list">
            <li class="i">
                <img src="${orderInfo.scanUrl}" alt="">
                <c:if test="${orderPayType == 'aliScan'}">
                    <p class="info">使用支付宝扫描此二维码进行支付</p>
                    <p class="info_1">支付宝支付</p>
                </c:if>
                <c:if test="${orderPayType == 'weixinScan'}">
                    <p class="info">使用微信扫描此二维码进行支付</p>
                    <p class="info_1">微信支付</p>
                </c:if>

            </li>
        </ul>
    </div>
</div>
<!-- content end -->
<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>
</body>
</html>
<script type="application/javascript">
    $(document).ready(function () {
        setTimeRefreshOrder();
    });

    function setTimeRefreshOrder() {
        $.ajax({
            type: "post",
            url: "/portal/services/orderInfo/setTimeRefreshOrder.html",
            dataType: 'json',
            async: true,
            data: {"orderNo": '${orderInfo.orderNo}'},
            error: function (data) {
                return false;
            },
            success: function (data) {
                if (data.success) {
                    if (data.data.orderStatus == 'success') {
                        layer.msg("支付成功",
                            {
                                time: 2000,
                                icon: 1,
                                skin: 'layui-layer-rim',
                                closeBtn: 1
                            }, function () {
                                window.location.href = '/portal/services/customer/childpage.html?childIndex=tripOrder';
                            })
                    } else {
                        setTimeout("setTimeRefreshOrder();", 2000);
                    }
                } else {
                    $.platform.msg("订单支付失败", 1000, 5);
                }
            }
            ,
        });
    }
    ;
</script>
