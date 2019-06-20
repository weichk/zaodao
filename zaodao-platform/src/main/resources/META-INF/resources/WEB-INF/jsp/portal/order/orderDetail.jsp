<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp"/>
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
    <link rel="stylesheet" href="/css/pay_method.css">
</head>

<body>
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=firstPage"/>
<div class="pay_detail_con">
    <div class="head">
        <h2 class="t">预约信息</h2>
        <div class="goods_head">
            <span class="i1">订单号：${orderInfo.orderNo}</span>
            <span class="i2">下单时间：${orderInfo.orderTime}</span>
            <span class="i3">订单状态：${orderInfo.orderStatus.message}</span>
            <c:if test="${orderInfo.orderStatus.code !='pay' && orderInfo.orderStatus.code!='confirm'}">
                <a href="#" id="cancel_check" class="cancel_check">取消订单 </a>
            </c:if>
        </div>
    </div>
    <!-- head end -->

    <form id="payDetailForm" method="post" action="/portal/services/orderInfo/checkOrderInfo.html"
          name="book_person_info"
          class="book_person_info mt10">
        <input type="hidden" name="orderNo" value="${orderInfo.orderNo}">
        <div class="pay_detail_col1">
            <div class="img"><img src="${guide.guideCoverImg}" alt=""></div>
            <div class="con">
                <div class="item">
                    <span class="label">导游</span>
                    <span class="name">${guide.realName}</span>
                    <span class="location"><i class="sprite icon_location_bl"></i>${guide.permanentCity}</span>
                    <span class="price">￥${guide.amount}/天</span>
                </div>
                <div class="item mt20">
                    <span class="label">出行日期</span>
                    <div class="date_container book_date">
                        <div class="date_input pie">
                            <div class="room_num">
                                <span id="checkin-date" class="date_txt">${orderInfo.startTime}</span>
                                <span id="checkout-date" class="date_txt">${orderInfo.endTime}</span>
                            </div>
                        </div>
                        <!-- Swiper -->
                        <div class="swiper-container date_swiper pie">
                            <div id="calendar-box" class="calendar swiper-wrapper"></div>
                            <!-- Add Navigation -->
                            <div class="swiper-button-prev"></div>
                            <div class="swiper-button-next"></div>
                        </div>
                    </div>
                    <span class="total">共<em id="total-days">${orderInfo.dayCount}</em>天</span>
                </div>
                <div class="item mt30">
                    <span class="label">人数</span>
                    <div class="person_num">
                        <div class="person_num_head pie"><span id="person_num_1">${orderInfo.adultCount}</span>个成人，<span
                                id="person_num_2">${orderInfo.childCount}</span>个儿童<i class="arrow"></i></div>

                        <div class="person_selection pie">
                            <div class="item set_num_1"><span class="info">成人</span><a href="javascript:;"
                                                                                       class="J_minus_1 pie">-</a><input
                                    type="text" value="1" class="J_input_1"/><a href="javascript:;"
                                                                                class="J_add_1 pie">+</a></div>
                            <div class="item set_num_2 mt30"><span class="info">儿童</span><a href="javascript:;"
                                                                                            class="J_minus_2 pie">-</a><input
                                    type="text" value="2" class="J_input_2"/><a href="javascript:;"
                                                                                class="J_add_2 pie">+</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- pay_detail_col1 end -->

        <div class="pay_detail_col2">
            <h2 class="t">预约人信息</h2>

            <div class="form_item">
                <label for="contactName">姓名</label><input type="text" id="contactName" name="contactName" datatype="*"
                                                          nullmsg="请填写姓名！"
                                                          value="${orderInfo.contactName}"
                                                          class="text">

                <div class="Validform_checktip"></div>
            </div>

            <div class="form_item">
                <label for="contactPhone">联系方式</label><input type="text" id="contactPhone" name="contactPhone"
                                                             datatype="m"
                                                             nullmsg="请输入手机号码！"
                                                             errormsg="请输入正确的手机号码！" value="${orderInfo.contactPhone}"
                                                             class="text">

                <div class="Validform_checktip"></div>
            </div>

            <div class="form_item">
                <label for="others">备注</label><textarea name="contactMemo" id="others"
                                                        placeholder="选填，建议填写您和导游达成一致的特殊要求"
                                                        class="textarea"></textarea>
            </div>


               <%--<input type="hidden" id="dateStart" name="dateStart">--%>
            <%--<input type="hidden" id="dateEnd" name="dateEnd">--%>
            <%--<input type="hidden" id="totalDays" name="totalDays">--%>
            <%--<input type="hidden" id="childrenNum" name="childrenNum">--%>
            <%--<input type="hidden" id="adultNum" name="adultNum">--%>
            <c:choose>
                <c:when test="${orderInfo.orderStatus.code=='noPay'}">
                    <div class="pay-method">
                        <h2 class="t">付款方式</h2>
                        <div class="radio-beautify form_item ">
                            <input type="hidden" id="pay_type_id">
                            <input type="radio" name="payTypes" value="aliScan" /><label><span class="zf"><img src="/images/zfb.png"/></span></label>
                            <input type="radio" name="payTypes" value="weixinScan" /><label><span class="zf"><img src="/images/wechat.png"/></span></label>
                        </div>
                    </div>
                    <div class="price">应付金额：<span class="n"><em>${orderInfo.amount}</em>.00 元</span></div>
                    <input type="submit" id="payNow" name="submit" value="去支付" class="paynow_btn">
                </c:when>
                <c:otherwise>
                    <div class="price">应付金额：<span class="n"><em>${orderInfo.amount}</em>.00 元</span></div>
                </c:otherwise>
            </c:choose>
        </div>
        <!-- pay_detail_col2 end -->
    </form>
</div>
<!-- content end -->
<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>
</body>
</html>
<script type="text/javascript">
    $(function () {
        $("#payDetailForm").Validform({
            tiptype: function (msg, o, cssctl) {
                if (!o.obj.is("form")) {
                    var objtip = o.obj.parents(".form_item").find(".Validform_checktip");
                    // alert(o.obj[0].tagName);
                    cssctl(objtip, o.type);
                    objtip.text(msg);
                }
            },
            ajaxPost:true,
            showAllError: true,
            usePlugin: {
                jqtransform: {
                    //会在当前表单下查找这些元素;
                    selector: "select,:checkbox,:radio,.decorate"
                }
            },
            beforeSubmit:function (curform) {
                var payType = $("input:radio[name='payTypes']:checked").val();
                if(payType==''||payType==null){
                    $.platform.msg("请选择支付方式", 1000, 5);
                    return false;
                }else {
                    $("#pay_type_id").val(payType);
                }
                loading = top.layer.load(2, {
                    shade: [0.3,'#eee'] //0.1透明度的白色背景
                });
            },
            callback:function(data){
                var payType = $("input:radio[name='payTypes']:checked").val();
                if (data.success) {
                    window.location.href = '/portal/services/orderInfo/orderPay/'+payType+'/${orderNo}'
                } else {
                    $.platform.msg(data.message, 1000, 5);
                }
            }
        });
    });

    $("#cancel_check").click(function (e) {
        e.preventDefault();

        layer.msg('确定要取消订单么？', {
            time: 0 //不自动关闭
            , skin: 'uni__popup'
            , shade: .5
            , scrollbar: false
            , shadeClose: true
            , closeBtn: 1
            , btn: ['确定', '再想想']
            , yes: function (index) {
                $.ajax({
                    type: "post",
                    url: "/portal/services/orderInfo/cancleOrder.html",
                    dataType: 'json',
                    async: true,
                    data: {
                        "orderNo": '${orderInfo.orderNo}'
                    },
                    beforeSend: function (curform) {
                        loading = top.layer.load(2, {
                            shade: [0.3, '#eee'] //0.1透明度的白色背景
                        });
                    },
                    complete: function () {
                        layer.closeAll();
                    },
                    error: function (data) {
                        alert("Connection error");
                        return false;
                    },
                    success: function (data) {
                        layer.close(index);
                        layer.msg('订单已取消');
                    },
                });
            }
        });
    });
</script>
