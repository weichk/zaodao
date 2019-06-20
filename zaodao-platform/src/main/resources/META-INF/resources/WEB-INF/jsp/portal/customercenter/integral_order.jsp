<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!-- page: 积分订单 -->

<div class="points_booking page_wrap">
    <div class="mpage_title">积分订单</div>

    <div id="shop_order_info_id"></div>


</div>

<script id="shopOrderInfoList-template" type="text/html">
    <table width="100%" cellpadding="0" cellspacing="0" border="0" class="table mt20">
        <tr>
            <th width="12%">商品名称<br>订单号</th>
            <th width="10%">数量</th>
            <th width="10%">订单积分</th>
            <th width="12%">收货人<br>手机号码</th>
            <th width="20%">收货地址</th>
            <th width="16%">下单时间</th>
            <th width="20%">订单状态</th>
        </tr>
        <tr>
            <td colspan="7" class="space2"></td>
        </tr>
        {{#each rows}}
        <tr>
            <td>{{goodsName}}<br>{{orderNo}}</td>
            <td>{{quantity}}</td>
            <td><span class="red">{{amount}}</span></td>
            <td>{{realName}}<br>{{mobileNo}}</td>
            <td>{{provName}}{{cityName}}{{distName}}{{address}}</td>
            <td>{{prettifyDate createTime}}</td>
            <td>{{hShopOrderStatus status}}</td>
        </tr>
        {{/each}}
    </table>
    <div class="pages" id="shopOrderInfoPageId">

    </div>
</script>

<script>

    $(document).ready(function () {
        shopOrderInfoList(1, 1);
    });

    /**
     * 获取当前标签下的新闻列表
     * @type {any}
     */
    var shopOrderInfoListSource = $("#shopOrderInfoList-template").html();
    var shopOrderInfoListTemplate = Handlebars.compile(shopOrderInfoListSource);
    var shopOrderInfoContext;
    function shopOrderInfoList(totalPageNo, pageNo) {
        var countOfCurrentPage = 10;
        if (totalPageNo < pageNo || pageNo < 1) {
            return;
        }
        $.ajax({
            type: "post",
            url: "/portal/services/shopOrderInfo/orderInfoList.html",
            dataType: 'json',
            async: true,
            data: {"currentPageNo": pageNo, "countOfCurrentPage": countOfCurrentPage},
            beforeSend: function (XHR) {
                loading = top.layer.load(2, {
                    shade: [0.3, '#eee'] //0.1透明度的白色背景
                });
            },
            error: function (data) {
                alert("Connection error");
                return false;
            },
            success: function (data) {
                shopOrderInfoContext = data;
                var shopOrderInfoListHtml = shopOrderInfoListTemplate(shopOrderInfoContext);
                $('#shop_order_info_id').html(shopOrderInfoListHtml);
                //分页
                var totalPage = data.total % countOfCurrentPage == 0 ? data.total / countOfCurrentPage : Math.ceil(data.total / countOfCurrentPage);
                if (totalPage > 1) {
                    pageFunction(totalPage, pageNo);
                }

                layer.closeAll();
            },
        });
    }
    ;

    //分页
    function pageFunction(totalPages, currentPage) {
        $("#shopOrderInfoPageId").jqPaginator({
            totalPages: totalPages,
            visiblePages: 10,
            currentPage: currentPage,
            first: '<a href="javascript:void(0);" onclick="shopOrderInfoList(1,1)">导游论坛<\/a>',
            prev: '<a href="javascript:void(0);" onclick="shopOrderInfoList({{totalPages}},{{page}})">上一页<\/a>',
            next: '<a href="javascript:void(0);" onclick="shopOrderInfoList({{totalPages}},{{page}})">下一页<\/a>',
            last: '<a href="javascript:void(0);" onclick="shopOrderInfoList({{totalPages}},{{totalPages}})">末页<\/a>',
            page: '<a href="javascript:void(0);" onclick="shopOrderInfoList({{totalPages}},{{page}})">{{page}}<\/a>',
            onPageChange: function (n) {
                $("#demo1-text").html("当前第" + n + "页");
            }
        });
    }
    ;
</script>