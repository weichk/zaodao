$(document).ready(function () {
    orderInfoList(1, 1);
})

function getOrderInfListByOrderStatus(status) {
    $('#order_status_id').val(status);
    orderInfoList(1, 1);
}

/**
 * 获取订单列表
 * @type {any}
 */
var orderInfoListSource = $("#orderInfoList-template").html();
var orderInfoListTemplate = Handlebars.compile(orderInfoListSource);
var orderInfoContext;

function orderInfoList(totalPageNo, pageNo) {
    var orderStatus = $('#order_status_id').val();
    var countOfCurrentPage = 10;
    if (totalPageNo < pageNo || pageNo < 1) {
        return;
    }
    $.ajax({
        type: "post",
        url: "/portal/services/orderInfo/orderInfoList.html",
        dataType: 'json',
        async: true,
        data: {"currentPageNo": pageNo, "countOfCurrentPage": countOfCurrentPage, "orderStatus": orderStatus},
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
            orderInfoContext = data;
            var orderInfoListHtml = orderInfoListTemplate(orderInfoContext);
            $('#order_info_list_id').html(orderInfoListHtml);
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
    $("#orderInfoPageId").jqPaginator({
        totalPages: totalPages,
        visiblePages: 10,
        currentPage: currentPage,
        first: '<a href="javascript:void(0);" onclick="orderInfoList(1,1)">首页<\/a>',
        prev: '<a href="javascript:void(0);" onclick="orderInfoList({{totalPages}},{{page}})">上一页<\/a>',
        next: '<a href="javascript:void(0);" onclick="orderInfoList({{totalPages}},{{page}})">下一页<\/a>',
        last: '<a href="javascript:void(0);" onclick="orderInfoList({{totalPages}},{{totalPages}})">末页<\/a>',
        page: '<a href="javascript:void(0);" onclick="orderInfoList({{totalPages}},{{page}})">{{page}}<\/a>',
        onPageChange: function (n) {
            $("#demo1-text").html("当前第" + n + "页");
        }
    });
}
;

/**
 * 删除订单
 * @param id
 */
function deleteOrder(orderNo) {
    layer.msg('确定要删除订单么？', {
        time: 0 //不自动关闭
        , skin: 'alarm__popup'
        , shade: .5
        , scrollbar: false
        , shadeClose: true
        , btn: ['删除', '取消']
        , yes: function (index) {
            $.ajax({
                type: "post",
                url: "/portal/services/orderInfo/deleteOrder.html",
                dataType: 'json',
                async: true,
                data: {"orderNo": orderNo},
                beforeSend: function (XHR) {
                    loading = top.layer.load(2, {
                        shade: [0.3, '#eee'] //0.1透明度的白色背景
                    });
                },
                error: function (data) {
                    layer.msg('订单删除失败');
                    return false;
                },
                success: function (data) {
                    if (data.success) {
                        layer.msg('订单已删除');
                        orderInfoList(1, 1);
                    } else {
                        layer.msg('订单删除失败');
                    }
                    layer.closeAll();
                },
            });
            layer.close(index);
        }
    });
}

/**
 * 订单确认
 * @param orderNo
 */
function confirm(orderNo,orderTime,guideName,place,amount) {
    layer.open({
        type: 1,
        title: false,
        area: ['430px', 'auto'],
        closeBtn: 1,
        shadeClose: true,
        skin: "sure-details",
        resize: false,
        content: '<div class="sure-details">' +
        '<div class="sure-details-title">订单信息</div>' +
        '<div class="sure-details-item"><span>订单号：</span><span>'+orderNo+'</span></div>' +
        '<div class="sure-details-item"><span>下单时间：</span><span>'+orderTime+'</span></div>' +
        '<div class="sure-details-item"><span>导游姓名：</span><span>'+guideName+'</span></div>' +
        '<div class="sure-details-item"><span>出行地点：</span><span>'+place+'</span></div>' +
        '<div class="sure-details-item"><span>订单金额：</span><span>￥'+amount+'</span></div>' +
        '</div>',
        btn: ["确认", "取消"],
        yes: function (index) {
            $.ajax({
                type: "post",
                url: "/portal/services/orderInfo/confirm.html",
                dataType: 'json',
                async: true,
                data: {"orderNo": orderNo},
                beforeSend: function (XHR) {
                    loading = top.layer.load(2, {
                        shade: [0.3, '#eee'] //0.1透明度的白色背景
                    });
                },
                error: function (data) {
                    layer.msg('订单确认失败');
                    return false;
                },
                success: function (data) {
                    if (data.success) {
                        layer.msg("订单确认成功",
                            {
                                time: 2000,
                                icon: 1,
                                skin: 'layui-layer-rim',
                                closeBtn: 1
                            }, function () {
                                orderInfoList(1, 1);
                            })
                    } else {
                        layer.msg("确认失败",
                            {
                                time: 1000,
                                icon: 2,
                                skin: 'layui-layer-rim'
                            })
                    }
                    layer.close(loading);
                },
            });
        }
    });
}

/**
 * 取消订单
 * @param orderStatus
 * @param id
 */
function closeOrder(orderStatus, orderNo) {
    layer.msg('确定要取消订单么？', {
        time: 0 //不自动关闭
        , skin: 'uni__popup'
        , shade: .5
        , scrollbar: false
        , shadeClose: true
        , btn: ['确定', '再想想']
        , yes: function (index) {
            $.ajax({
                type: "post",
                url: "/portal/services/orderInfo/cancleOrder.html",
                dataType: 'json',
                async: true,
                data: {"orderStatus": orderStatus, "orderNo": orderNo},
                beforeSend: function (XHR) {
                    loading = top.layer.load(2, {
                        shade: [0.3, '#eee'] //0.1透明度的白色背景
                    });
                },
                error: function (data) {
                    layer.msg('订单取消失败');
                    return false;
                },
                success: function (data) {
                    if (data.success) {
                        layer.msg('订单已取消');
                        orderInfoList(1, 1);
                    } else {
                        layer.msg('订单取消失败');
                    }
                    layer.closeAll();
                },
            });
            layer.close(index);
        }
    });
}