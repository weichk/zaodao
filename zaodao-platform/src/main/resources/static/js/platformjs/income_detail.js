$(document).ready(function () {
    tradingRecordList(1, 1);
})

/**
 * 获取当前标签下的新闻列表
 * @type {any}
 */
var tradingRecordListSource = $("#tradingRecordList-template").html();
var tradingRecordListTemplate = Handlebars.compile(tradingRecordListSource);
var tradingRecordContext;
function tradingRecordList(totalPageNo, pageNo) {
    var tradingType = $('#trading_type_id').val();
    var startTime = $('#start_time_id').val();
    var endTime = $('#end_time_id').val();
    var countOfCurrentPage = 10;
    if (totalPageNo < pageNo || pageNo < 1) {
        return;
    }
    $.ajax({
        type: "post",
        url: "/portal/services/tradingRecord/tradingRecordList.html",
        dataType: 'json',
        async: true,
        data: {"currentPageNo": pageNo, "countOfCurrentPage": countOfCurrentPage, "tradingType": tradingType
        ,"startTime":startTime,"endTime":endTime},
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
            tradingRecordContext = data;
            var tradingRecordListHtml = tradingRecordListTemplate(tradingRecordContext);
            $('#trading_record_list_id').html(tradingRecordListHtml);
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
    $("#tradingRecordPageId").jqPaginator({
        totalPages: totalPages,
        visiblePages: 10,
        currentPage: currentPage,
        first: '<a href="javascript:void(0);" onclick="tradingRecordList(1,1)">首页<\/a>',
        prev: '<a href="javascript:void(0);" onclick="tradingRecordList({{totalPages}},{{page}})">上一页<\/a>',
        next: '<a href="javascript:void(0);" onclick="tradingRecordList({{totalPages}},{{page}})">下一页<\/a>',
        last: '<a href="javascript:void(0);" onclick="tradingRecordList({{totalPages}},{{totalPages}})">末页<\/a>',
        page: '<a href="javascript:void(0);" onclick="tradingRecordList({{totalPages}},{{page}})">{{page}}<\/a>',
        onPageChange: function (n) {
            $("#demo1-text").html("当前第" + n + "页");
        }
    });
}
;

laydate.skin('qianhuang')
laydate({
    format: 'YYYY-MM-DD',
    festival:true,
    elem: '#start_time_id'
});

laydate({
    format: 'YYYY-MM-DD',
    festival:true,
    elem: '#end_time_id'
});