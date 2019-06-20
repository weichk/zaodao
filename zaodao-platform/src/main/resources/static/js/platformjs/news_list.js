$(document).ready(function () {
    newsList(1, 1);
})
/**
 * 获取当前标签下的新闻列表
 * @type {any}
 */
var newsListSource = $("#newsList-template").html();
var newsListTemplate = Handlebars.compile(newsListSource);
var context;
function newsList(totalPageNo, pageNo) {
    var type = decodeURI(getUrlValue("type"));
    var countOfCurrentPage = 10;
    if (totalPageNo < pageNo || pageNo < 1) {
        return;
    }
    $.ajax({
        type: "get",
        url: "/portal/news/getMoreNews.html",
        dataType: 'json',
        async: true,
        data: {"currentPageNo": pageNo,"countOfCurrentPage":countOfCurrentPage, "type": type},
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
            context = data;
            var newsListHtml = newsListTemplate(context);
            $('#news_list_id').html(newsListHtml);
            //分页
            var totalPage = data.data.total % countOfCurrentPage == 0 ? data.data.total / countOfCurrentPage : Math.ceil(data.data.total / countOfCurrentPage);
            pageFunction(totalPage, pageNo);
            layer.closeAll();
        },
    });
}
;


//分页
function pageFunction(totalPages, currentPage) {
    $("#newsPageId").jqPaginator({
        totalPages: totalPages,
        visiblePages: 10,
        currentPage: currentPage,
        first: '<a href="javascript:void(0);" onclick="newsList(1,1)">首页<\/a>',
        prev: '<a href="javascript:void(0);" onclick="newsList({{totalPages}},{{page}})">上一页<\/a>',
        next: '<a href="javascript:void(0);" onclick="newsList({{totalPages}},{{page}})">下一页<\/a>',
        last: '<a href="javascript:void(0);" onclick="newsList({{totalPages}},{{totalPages}})">末页<\/a>',
        page: '<a href="javascript:void(0);" onclick="newsList({{totalPages}},{{page}})">{{page}}<\/a>',
        onPageChange: function (n) {
            $("#demo1-text").html("当前第" + n + "页");
        }
    });
}
;