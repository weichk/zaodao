<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<link rel="stylesheet" href="/css/pay_method.css">
<!-- page: 出行订单 -->

<div class="travel_booking page_wrap">

    <div class="mpage_title member_booking_filter"><a href="javascript:void(0);" onclick="getOrderInfListByOrderStatus('all')" class="active">全部订单<i></i></a><a href="javascript:void(0);" onclick="getOrderInfListByOrderStatus('noPay')">待付款<i></i></a><a href="javascript:void(0);" onclick="getOrderInfListByOrderStatus('pay')">已付款<i></i></a><a href="javascript:void(0);" onclick="getOrderInfListByOrderStatus('close')">已取消</a></div>
    <input type="hidden" name="orderStatus" id="order_status_id" value="all"/>
    <div id="order_info_list_id">

    </div>

</div>


<%--根据tag分类列表--%>
<script id="orderInfoList-template" type="text/html">
    <table width="100%" cellpadding="0" cellspacing="0" border="0" class="table mt30">
        <tr>
            <th width="19%">导游详情</th>
            <th width="19%">出行日期</th>
            <th width="15%">人数</th>
            <th width="15%">金额</th>
            <th>订单状态</th>
            <th width="15%">操作</th>
        </tr>
        <tr>
            <td colspan="6" class="space15"></td>
        </tr>
        {{#each rows}}
            <tr>
                <td colspan="6" class="item_head">
                    <span class="booking_id">订单号：<span class="id">{{platOrderNo}}</span></span>
                    <span class="booking_date">下单时间：<span class="date">{{createTime}}</span></span>
                </td>
            </tr>
            <tr>
                <td>
                    <a class="person_info">
                        <img src="{{headImg}}" alt="">
                        <div class="r"><span class="n">{{userName}}</span><span class="location">{{destination}}</span></div>
                    </a>
                </td>
                <td>
                    <div class="start_date">{{prettifyDate startPlayTime}}~</div>
                    <div class="end_date">{{prettifyDate endPlayTime}} </div>
                    <div class="days">（共 3 天）</div>
                </td>
                <td>{{adultCount}}个成人<br>{{childCount}}个儿童</td>
                <td><span class="price">￥{{fmoney orderAmount 2}}</span></td>
                <td><span class="state nopay">{{orderStatus orderStatus}}</span>
                    {{#myif orderStatus '!=' 'close' }}
                        <a href="/portal/services/orderInfo/orderDetail/{{orderNo}}" class="open_booking">查看订单</a></td>
                    {{/myif}}
                <td>
                    <div class="tools">
                        <c:choose>
                            <c:when test="${sessionScope.sessionKeyUserInfo.isTourGuide != 1}">
                                {{#myif orderStatus '==' 'noPay'}}
                                    <a href="/portal/services/orderInfo/orderDetail/{{orderNo}}" class="uni_btn pie">立即支付</a>
                                {{else}}
                                    {{#myif orderStatus '==' 'pay'}}
                                        <a href="javascript:void(0);" onclick="confirm('{{orderNo}}','{{createTime}}','{{userName}}','{{destination}}','{{fmoney orderAmount 2}}')" class="uni_btn pie">确认订单</a>
                                    {{else}}
                                        <a href="javascript:void(0);" class="tool_delete cancel" onclick="deleteOrder('{{orderNo}}')">删除订单</a>
                                    {{/myif}}
                                {{/myif}}
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0);" class="tool_delete cancel" onclick="deleteOrder('{{orderNo}}')">删除订单</a>
                            </c:otherwise>
                        </c:choose>
                        <%--{{#myif orderStatus '==' 'close'}}--%>
                            <%--<a href="javascript:void(0);" class="tool_delete cancel" onclick="deleteOrder('{{orderNo}}')">删除订单</a>--%>
                        <%--{{else}}--%>
                            <%--<a href="#" class="tool_cancel1 cancel" onclick="closeOrder('{{orderStatus}}','{{orderNo}}')">取消订单</a>--%>
                        <%--{{/myif}}--%>
                    </div>
                </td>
            </tr>
            <tr>
        {{/each}}
    </table>
    <div class="pages" id="orderInfoPageId">

    </div>
</script>

<script type="text/javascript" src="/js/platformjs/trip_order.js" charset="utf-8"></script>
<!-- 提现、充值跳转 -->
<script>
    $(document).ready(function(){

        $('.member_booking_filter a').each(function(i) {
            $('.member_booking_filter a').eq(i).click(function(e) {
                e.preventDefault();
                $(this).addClass('active').siblings('a').removeClass('active');
            });
        })

    });
</script>
